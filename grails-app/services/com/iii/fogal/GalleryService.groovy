package com.iii.fogal

import com.drew.metadata.*
import com.drew.metadata.exif.*
import com.drew.metadata.iptc.*
import com.drew.imaging.*
import com.drew.imaging.jpeg.*
import java.awt.image.BufferedImage
import java.nio.file.Path
import java.nio.file.Paths
import java.nio.file.Files
import javax.imageio.*;
import javax.imageio.stream.*;
import javax.imageio.metadata.*;
import javax.imageio.ImageIO
import org.apache.commons.io.FileDeleteStrategy
import org.imgscalr.Scalr
import org.springframework.web.multipart.MultipartFile
import org.w3c.dom.*;

class GalleryService {

	private static final String TITLE_OLD = "User Comment"// "Headline"
	private static final String DESCRIPTION_OLD = "Image Description"// "Caption/Abstract"
	private static final String ARTIST_OLD = "Artist"// "Credit", "Special Instructions" aka copyright
	private static final String PHOTO_DATE_OLD = "Date/Time Original"// "Date Created"
	//
	private static final String TITLE = "Headline"
	private static final String DESCRIPTION = "Caption/Abstract"
	private static final String CREDIT = "Credit"
	private static final String COPYRIGHT = "Special Instructions"
	private static final String PHOTO_DATE = "Date Created"
	private static final String CITY = "City"
	private static final String SUB_LOCATION = "Sub-location"
	private static final String PROVINCE_STATE =  "Province/State"
	private static final String COUNTRY = "Country/Primary Location Name"
	private static final Integer THUMBNAIL_SIZE = 200
	private static final String IMAGE_HEIGHT = "Image Height"
	private static final String IMAGE_WIDTH = "Image Width"
	// "Keywords", "Copyright Notice"
	
	def grailsApplication
	def photoService
	
	List<Photo> createPhotosFromUpload(List<MultipartFile> files, Integer galleryId) {
		List<Photo> photoList = new ArrayList<Photo>()
		try {
			Gallery gallery = Gallery.findById(galleryId)
			println "gallery.path: ${gallery.path}"
			for (MultipartFile file in files) {
				String oName = file.getOriginalFilename()
				println "oName: ${oName}"
				String newFilename = _buildNewFilename(oName)
				File photoFile = _createNewFileFromUpload(file, newFilename, gallery)
				File tnFile = _createThumbnail(photoFile, newFilename, gallery)
				
				Photo photo = new Photo(title:"${oName}", fileSize:photoFile.length(), originalFilename:newFilename, thumbnailFilename:tnFile.name)
				_initPhoto(photo, photoFile, gallery, photoList)
			}
		} catch (Exception e) {
			log.debug "createPhotosFromUpload: ${e}"
		}
		photoList
	}

	List<Photo> createPhotosFromDisk(List<String> fileNames, String galleryPath) {
		List<Photo> photoList = new ArrayList<Photo>()
		try {
			Gallery gallery = Gallery.findByPath(galleryPath)
			log.debug "gallery.path: ${gallery.path}"
			String sourceFileDir = grailsApplication.config.file.newFile.directory?:'C:\\fogalFilesNew'
			String targetFileDir = grailsApplication.config.file.upload.directory?:'C:\\fogalFiles'//'/fogalFiles'
			for (String fn in fileNames) {
				File photoFile = _createNewFileFromDisk(fn, gallery, sourceFileDir, targetFileDir)
				File tnFile = _createThumbnail(photoFile, photoFile.name, gallery)
				
				Photo photo = new Photo(title:"${fn}", fileSize:photoFile.length(), originalFilename:photoFile.name, thumbnailFilename:tnFile.name)
				_initPhoto(photo, photoFile, gallery, photoList)
			}
		} catch (Exception e) {
			log.debug "createPhotosFromDisk: ${e}"
			e.printStackTrace()
		}
		photoList
	}
	
	private File _createNewFileFromDisk(String oName, Gallery gallery, String sourceDir, String targetDir) {
		print "_createNewFileFromDisk"
		println "oName: ${oName}"
		println "gallery.path: ${gallery.path}"
		println "sourceDir: ${sourceDir}"
		println "targetDir: ${targetDir}"
		String newFileName = _buildNewFilename(oName)
		Path sourcePath = Paths.get(sourceDir, oName)
		println "sourcePath: ${sourcePath}"
		Path targetPath = Paths.get(targetDir, gallery.category.path, gallery.path, newFileName)
		println "targetPath: ${targetPath}"
		Path newPath = Files.copy(sourcePath, targetPath)
		File newFile = newPath.toFile()
		newFile
	}
	
	private File _createNewFileFromUpload(MultipartFile file, String fileName, Gallery gallery) {
		print "_createNewFileFromUpload"
		String imageDir = grailsApplication.config.file.upload.directory?:'C:\fogalFiles'//'/fogalFiles'
		println "imageDir: ${imageDir}"
		Path newPath = Paths.get(imageDir, gallery.category.path, gallery.path, fileName)
		print "newPath: ${newPath}"
		File newFile = newPath.toFile()
		file.transferTo(newFile)
		newFile
	}
	
	private File _createThumbnail(File newFile, String fileName, Gallery gallery) {
		String imageDir = grailsApplication.config.file.upload.directory?:'C:\fogalFiles'//'/fogalFiles'
		println "imageDir: ${imageDir}"
		BufferedImage thumbnail = Scalr.resize(ImageIO.read(newFile), THUMBNAIL_SIZE);
		String thumbnailFilename = _buildThumbnailName(fileName)//newFilenameBase + '-thumbnail.png'
		Path tnPath = Paths.get(imageDir, gallery.category.path, gallery.path, thumbnailFilename)
		File thumbnailFile = tnPath.toFile()
		String fileExt = thumbnailFilename.split(/\./)[1]
		ImageIO.write(thumbnail, fileExt, thumbnailFile)
		thumbnailFile = newFile.size() > thumbnailFile.size() ? thumbnailFile : newFile
		thumbnailFile
	}

	private void _initPhoto(Photo photo, File photoFile, Gallery gallery, List<Photo> photoList) {
		photo = _populatePhotoMetadata(photo, photoFile)
		try {
			gallery.addToPhotos(photo)
			gallery.save(flush:true)
			if (photo.validate()) {
				photo.calculateAspect()
				photo.save(flush:true)
				photoList.add(photo)
				println "save photo: ${photo}"
			} else {
				log.debug "failed to save photo: ${photo}"
				log.error photo.errors
			}
	
		} catch (Exception e) {
			log.error "init photo failed: ${e}"
		}
	}

	private String _buildNewFilename(String oName) {
		String filenameBase = System.currentTimeMillis()
		String newFilename = _buildFileName(filenameBase, oName)
		println newFilename
		newFilename
	}
	
	private String _buildThumbnailName(String oName) {
		String filenameBase = "thumbnail"
		String tnFilename = _buildFileName("thumbnail", oName)
		println tnFilename
		tnFilename
	}
	
	private String _buildFileName(String suffix, String name) {
		String[] fileNameArray = name.split(/\./)
		String oFileBase = fileNameArray[0]
		String oFileExt = fileNameArray[1]
		String newFilename = oFileBase + "_" + suffix + "." + oFileExt
		println newFilename
		newFilename
	}

	// move to Photo.groovy
    Photo _populatePhotoMetadata(Photo photo, File file) {
		String imageDir = grailsApplication.config.file.upload.directory?:'C:\fogalFiles'//'/fogalFiles'
		println "imageDir: ${imageDir}"
        try {
			Metadata metadata = ImageMetadataReader.readMetadata(file)
			String sublocation
			String city
			String state
			String country
			for (Directory directory : metadata.getDirectories()) {
				for (Tag tag : directory.getTags()) {
					String name = tag.tagName
					String desc = tag.description
					//System.out.println("tag.name: ${name}, tag.description: ${desc}")
					if (TITLE.equals(name)) photo.title = desc.trim()
					if (CREDIT.equals(name)) photo.credit = desc.trim()
					if (COPYRIGHT.equals(name)) photo.copyright = desc.trim()
					if (DESCRIPTION.equals(name)) photo.description = _trimAndLog(desc)
					if (PHOTO_DATE.equals(name)) photo.photoDate = _trimAndLog(desc)//desc.trim(); println "photoDate: ${desc.trim()}" }
					if (IMAGE_HEIGHT.equals(name)) photo.imageHeight = _castToLong(desc)
					if (IMAGE_WIDTH.equals(name)) photo.imageWidth = _castToLong(desc)
					if (CITY.equals(name)) city = desc.trim()
					if (SUB_LOCATION.equals(name)) sublocation = desc.trim()
					if (PROVINCE_STATE.equals(name)) state = desc.trim()
					if (COUNTRY.equals(name)) country = desc.trim()
				}
			}
			photo.location = _assembleLocation(sublocation, city, state, country)
        }
        catch (Exception e) {
            e.printStackTrace();
        }
		photo
    }
	
	String _trimAndLog(String term) {
		term = term.trim()
		if (term.length() > 2047) term = term.substring(0, 2047)
		log.debug term
		term
	}
	
	Long _castToLong(String term) {
		term = term.trim()
		Long retval = term.split(" ")[0] as Long
	}
	
	private String _assembleLocation(String sublocation, String city, String state, String country) {
		StringBuffer loc = new StringBuffer()
		if (sublocation) loc.append("${sublocation}, ")
		if (city) loc.append("${city} ")
		if (state) loc.append(state)
		if (country) {
			if ((loc.length() > 0) && !sublocation) loc.append(", ")
			loc.append(country)
		}
		return loc.toString()
	}
	
//	Photo _populatePhotoMetadataOld(Photo photo, File file) {
//		String imageDir = grailsApplication.config.file.upload.directory?:'/tmp'
//		println "imageDir: ${imageDir}"
//		try {
//			Metadata metadata = ImageMetadataReader.readMetadata(file)
//			String sublocation
//			String city
//			String state
//			String country
//			for (Directory directory : metadata.getDirectories()) {
//				for (Tag tag : directory.getTags()) {
//					String name = tag.tagName
//					String desc = tag.description
//					//System.out.println("tag.name: ${name}, tag.description: ${desc}")
//					if (TITLE.equals(name)) photo.title = desc.trim()
//					if (DESCRIPTION.equals(name)) photo.description = desc.trim()
//					if (PHOTO_DATE.equals(name)) { photo.photoDate = desc.trim(); println "photoDate: ${desc.trim()}" }
//					if (CITY.equals(name)) city = desc.trim()
//					if (SUB_LOCATION.equals(name)) sublocation = desc.trim()
//					if (PROVINCE_STATE.equals(name)) state = desc.trim()
//					if (COUNTRY.equals(name)) country = desc.trim()
//				}
//			}
//			photo.location = "${sublocation}, ${city} ${state}, ${country}"
//		}
//		catch (Exception e) {
//			e.printStackTrace();
//		}
//		photo
//	}
	
	def addGalleryToFileSystem(String gPath, String categoryId) {
		try {
			Category category = Category.findById(categoryId as String)
			String baseDir = grailsApplication.config.file.upload.directory
			Path galleryPath = Paths.get(baseDir, category.path, gPath)
			File galleryDir = galleryPath.toFile()
			if (!galleryDir.exists()) galleryDir.mkdir()
		} catch (Exception e) {
			log.debug("addGalleryToCategory: ${e}")
			throw e
		}
	}

	// I think this method is useful when deleting a single gallery
	// but not useful when deleting the category and cascade-deleting all the galleries
	def deleteGalleryFromCategory(Gallery gallery, Category category) {
		try {
			log.debug(gallery)
			// delete photos from gallery and filesystem
			// results in ConcurrentModificationException
//			gallery.getPhotos().each { photo ->
//				photoService.deletePhotoFromGallery(photo, gallery)
//			}
			// clean-up remaining image files on filesystem
			_deletePhotosFromGallery(gallery)
			// remove gallery from category, filesystem and database
			_deleteGalleryFromFileSystem(gallery)
			//category.galleries.remove(gallery)
			//gallery.delete(flush:true)
		} catch (Exception e) {
			log.debug "deleteGalleryFromCategory: ${e}"
			throw e
		}
	}
	
	private void _deletePhotosFromGallery(Gallery gallery) {
		log.debug("_deletePhotosFromGallery")
		for (Photo photo : gallery?.photos) {
			photoService.deletePhotoFromFilesystem(photo)
		}
	}
	
//	private void _deleteAllImageFilesFromGalleryPath(Gallery gallery) {
//		try {
//			String baseDir = grailsApplication.config.file.upload.directory
//			Path galleryPath = Paths.get(baseDir, gallery.category.path, gallery.path)
//			File galleryDir = galleryPath.toFile()
//			List<String> photoPathList = galleryDir.list()
//			for (String photoPathString : photoPathList) {
//				Path imagePath = Paths.get(baseDir, gallery.category.path, gallery.path, photoPathString)
//				photoService.deleteImageFile(imagePath)
//			}
//		} catch (Exception e) {
//			log.debug("_deleteAllImageFilesFromGalleryPath: ${e}")
//			throw e
//		}
//	}
	
	private void _deleteGalleryFromFileSystem(Gallery gallery) {
		try {
			String baseDir = grailsApplication.config.file.upload.directory
			Path galleryPath = Paths.get(baseDir, gallery.category.path, gallery.path)
			File galleryDir = galleryPath.toFile()
			System.gc()			
			FileDeleteStrategy.FORCE.delete(galleryDir)
		} catch (Exception e) {
			log.debug("_deleteGalleryFromFileSystem: ${e}")
			throw e
		}
	}
	
	Boolean updateGalleryOnFileSystem(Gallery gallery, String newPath) {
		Boolean success = false
		try {
			Category category = gallery.category//Category.findById(categoryId as String)
			String baseDir = grailsApplication.config.file.upload.directory
			Path galleryPath = Paths.get(baseDir, category.path, gallery.path)
			File galleryDir = galleryPath.toFile()
			log.debug "galleryDir: ${galleryDir} (writeable: ${galleryDir.canWrite()})"
			Path galleryPathNew = Paths.get(baseDir, category.path, newPath)
			File galleryDirNew = galleryPathNew.toFile()
			log.debug "galleryDirNew: ${galleryDirNew} (readable: ${galleryDirNew.canRead()})"
			success = galleryDir.renameTo(galleryDirNew)
			log.debug "galleryDir: ${galleryDir} (success: ${success})"
		} catch (Exception e) {
			log.debug("updateGalleryOnFileSystem: ${e}")
		} finally {
			return success
		}
	}
	
}
