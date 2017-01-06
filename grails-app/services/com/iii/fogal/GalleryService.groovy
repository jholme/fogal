package com.iii.fogal

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
import org.jcp.xml.dsig.internal.dom.ApacheCanonicalizer
import org.springframework.web.multipart.MultipartFile
import org.w3c.dom.*;

class GalleryService {
	
	def grailsApplication
	def photoService
	
	List<Photo> createPhotosFromUpload(List<MultipartFile> files, Integer galleryId) {
		List<Photo> photoList = new ArrayList<Photo>()
		try {
			Gallery gallery = Gallery.findById(galleryId)
			String imageDir = grailsApplication.config.file.upload.directory?:'C:\\fogalFiles'//'/fogalFiles'
			println "gallery.path: ${gallery.path}"
			for (MultipartFile file in files) {
				String oName = file.getOriginalFilename()
				println "oName: ${oName}"
				String newFileName = _buildNewFilename(oName)
				Path newPath = Paths.get(imageDir, gallery.category.path, gallery.path, newFileName)
				File newFile = newPath.toFile()
				File photoFile = _createNewFileFromUpload(file, newFile, gallery)
				File tnFile = _createThumbnail(photoFile, newFileName, gallery)
				
				Photo photo = new Photo(title:"${oName}", fileSize:photoFile.length(), originalFilename:newFileName, thumbnailFilename:tnFile.name)
				photo.initPhoto(gallery, photoList, photoFile)
				_resizeImage(photoFile, newFileName)
			}
		} catch (Exception e) {
			log.debug "createPhotosFromUpload: ${e}"
		}
		photoList
	}
	
	private File _createNewFileFromUpload(MultipartFile file, File newFile, Gallery gallery) {
		println "_createNewFileFromUpload"
		println "file: ${file}"
		println "newFile: ${newFile}"
		file.transferTo(newFile)
		newFile
	}

	List<Photo> createPhotosFromDisk(List<String> fileNames, String galleryPath) {
		List<Photo> photoList = new ArrayList<Photo>()
		try {
			Gallery gallery = Gallery.findByPath(galleryPath)
			log.debug "gallery.path: ${gallery.path}"
			String sourceFileDir = grailsApplication.config.file.newFile.directory?:'C:\\fogalFilesNew'
			String targetFileDir = grailsApplication.config.file.upload.directory?:'C:\\fogalFiles'//'/fogalFiles'
			for (String fn in fileNames) {
				String newFileName = _buildNewFilename(fn)
				Path sourcePath = Paths.get(sourceFileDir, fn)
				Path targetPath = Paths.get(targetFileDir, gallery.category.path, gallery.path, newFileName)
				File photoFile = _createNewFileFromDisk(newFileName, gallery, sourcePath, targetPath)
				File tnFile = _createThumbnail(photoFile, photoFile.name, gallery)
				
				Photo photo = new Photo(title:"${fn}", fileSize:photoFile.length(), originalFilename:photoFile.name, thumbnailFilename:tnFile.name)
				photo.initPhoto(gallery, photoList, photoFile)
				_resizeImage(photoFile, newFileName)
			}
		} catch (Exception e) {
			log.debug "createPhotosFromDisk: ${e}"
			e.printStackTrace()
		}
		photoList
	}
	
	private File _createNewFileFromDisk(String newFileName, Gallery gallery, Path sourcePath, Path targetPath) {
		print "_createNewFileFromDisk"
		println "newFileName: ${newFileName}"
		println "gallery.path: ${gallery.path}"
		println "sourcePath: ${sourcePath}"
		println "targetPath: ${targetPath}"
		Path newPath = Files.copy(sourcePath, targetPath)
		File newFile = newPath.toFile()
		newFile
	}
	
	private void _resizeImage(File newFile, String newFileName) {
		Integer photoSize = grailsApplication.config.fogal.photoSize
		BufferedImage resizedImage = Scalr.resize(ImageIO.read(newFile), photoSize)
		String fileExt = newFileName.split(/\./)[1]
		ImageIO.write(resizedImage, fileExt, newFile)
	}
	
	private File _createThumbnail(File newFile, String fileName, Gallery gallery) {
		String imageDir = grailsApplication.config.file.upload.directory?:'C:\fogalFiles'
		println "imageDir: ${imageDir}"
		Integer thumbnailSize = grailsApplication.config.fogal.thumbnailSize
		BufferedImage resizedImage = Scalr.resize(ImageIO.read(newFile), thumbnailSize);
		String thumbnailFilename = _buildThumbnailName(fileName)
		Path tnPath = Paths.get(imageDir, gallery.category.path, gallery.path, thumbnailFilename)
		File thumbnailFile = tnPath.toFile()
		String fileExt = thumbnailFilename.split(/\./)[1]
		ImageIO.write(resizedImage, fileExt, thumbnailFile)
		thumbnailFile = newFile.size() > thumbnailFile.size() ? thumbnailFile : newFile
		thumbnailFile
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
			_deletePhotosFromGallery(gallery)
			_deleteGalleryFromFileSystem(gallery)
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
