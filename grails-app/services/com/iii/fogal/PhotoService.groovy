package com.iii.fogal

import grails.transaction.Transactional
import java.nio.file.Path
import java.nio.file.Paths
import java.nio.file.Files
import org.apache.commons.io.FileDeleteStrategy

@Transactional
class PhotoService {
	
	private static final String FULL_IMAGE = "full image"
	private static final String THUM_IMAGE = "thumbnail image"
	def grailsApplication
	
	
	File prepareToRender(String imageType, Photo photo) {
		try {
			//Photo photo = Photo.findById(imageId as Integer)
			String imageDir = grailsApplication.config.file.upload.directory
			//String galleryPath = photo?.gallery?.path
			Gallery gallery = photo.gallery
			Category category = gallery.category
			String fileName = THUM_IMAGE.equals(imageType) ? photo.thumbnailFilename : FULL_IMAGE.equals(imageType) ? photo.originalFilename : ""
			//File imageFile = new File("${imageDir}/${galleryPath}/${fileName}")
			Path imagePath = Paths.get(imageDir, category.path, gallery.path, fileName)
			File imageFile = imagePath.toFile()
//			response.setContentLength(imageFile.size() as Integer)
//			InputStream fileStream = new FileInputStream(imageFile)
//			response.outputStream.write(IOUtils.toByteArray(fileStream))
		} catch (Exception e) {
			log.debug("Exception prepareToRender: ${e}")
			throw e
		}
	}
	
	def deletePhotoFromGallery(Photo photo) {
		Gallery gallery = photo.gallery
		gallery.photos.remove(photo)
		Photo.withTransaction {
			photo.delete(flush:true)
		}
	}
	
	def deletePhoto(Photo photo) {
		Photo.withTransaction {
			photo.delete(flush:true)
		}
	}

	// delete Photo object before removing associated images from file system
	def deletePhotoFromFilesystem(Photo photo) {
		try {
			log.debug("deletePhotoFromFilesystem: ${photo}")
			String imageDir = grailsApplication.config.file.upload.directory
			Path mainImage = Paths.get(imageDir, photo.gallery.category.path, photo.gallery.path, photo.originalFilename)
			Path thumImage = Paths.get(imageDir, photo.gallery.category.path, photo.gallery.path, photo.thumbnailFilename)
			//photo.gallery.photos.remove(photo)
//			Photo.withTransaction {
//				photo.delete(flush:true)
//			}
			_deleteImagePaths([mainImage,thumImage])
		} catch (Exception e) {
			log.debug "Exception deleting photo from gallery: ${e}"
		}
	}

//	def deletePhotoFromGallery(Photo photo, Gallery gallery) {
//		try {
//			log.debug(photo)
//			_deleteImages(photo)
//			gallery.photos.remove(photo)
//			photo.delete()
//		} catch (Exception e) {
//			log.debug "Exception deleting photo from gallery: ${e}"
//		}
//	}

	private void _deleteImagePaths(List<Path> imagePaths) {
		for (Path ipath : imagePaths) {
			deleteImageFile(ipath)
		}
	}
	
//    private void _deleteImages(Photo photo) {
//		String baseDir = grailsApplication.config.file.upload.directory
//		Path mainImage = Paths.get(baseDir, photo.gallery.category.path, photo.gallery.path, photo.originalFilename)
//		deleteImage(mainImage)
//		Path thumImage = Paths.get(baseDir, photo.gallery.category.path, photo.gallery.path, photo.thumbnailFilename)
//		deleteImage(thumImage)
//    }
	
	def deleteImageFile(Path imagePath) {
		try {
			File imageFile = imagePath.toFile()
			log.debug "ps.deleteImage: ${imageFile} exists: ${imageFile.exists()}"
			imageFile.setWritable(true)
			System.gc()
			def deletedFile = FileDeleteStrategy.FORCE.delete(imageFile)
			log.debug "deleted file ${imageFile} - ${deletedFile}"
		} catch (Exception e) {
			log.debug "deleteImage: ${e}"
			//e.printStackTrace()
		}
	}
}
