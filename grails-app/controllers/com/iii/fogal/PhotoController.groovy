package com.iii.fogal

import org.springframework.http.HttpStatus
import grails.converters.JSON
import org.springframework.web.multipart.MultipartHttpServletRequest
import org.springframework.web.multipart.MultipartFile
import java.awt.image.BufferedImage
import org.imgscalr.Scalr
import javax.imageio.ImageIO
import org.apache.commons.io.IOUtils
import java.nio.file.Path
import java.nio.file.Paths
import java.nio.file.Files

class PhotoController {

    static scaffold = Photo
	def galleryService
	def photoService
	//static allowedMethods = [save: "POST", update: ['PUT','POST'], delete: 'POST']
	
	def renderMainImage() {
		_renderImage(photoService.FULL_IMAGE)
	}
	
	def renderThumbnailImage() {
		_renderImage(photoService.THUM_IMAGE)
	}
	
	private def _renderImage(String imageType) {
		try {
			Photo photo = Photo.findById(params.id)
//			String imageDir = grailsApplication.config.file.upload.directory?:'/tmp'
//			//String galleryPath = photo?.gallery?.path
//			Gallery gallery = photo.gallery
//			Category category = gallery.category
//			String fileName = THUM_IMAGE.equals(imageType) ? photo.thumbnailFilename : FULL_IMAGE.equals(imageType) ? photo.originalFilename : ""
//			//File imageFile = new File("${imageDir}/${galleryPath}/${fileName}")
//			Path imagePath = Paths.get(imageDir, category.path, gallery.path, fileName)
			File imageFile = photoService.prepareToRender(imageType, photo)//imagePath.toFile()
			response.setContentLength(imageFile.size() as Integer)
			InputStream fileStream = new FileInputStream(imageFile)
			response.outputStream.write(IOUtils.toByteArray(fileStream))
		} catch (Exception e) {
			response.sendError(404)
		}
	}
	
	def delete() {
		Photo photo = Photo.get(params.id)
		Gallery gallery = photo.gallery
//		try {
//			log.debug(photo)
//			photoService.deleteImages(photo)
//			gallery.photos.remove(photo)
//			photo.delete(flush:true)
//		} catch (Exception e) {
//			log.debug e
//		}
		//Photo tempPhoto = _createTempPhoto(photo)
		photoService.deletePhotoFromGallery(photo)
		photoService.deletePhotoFromFilesystem(photo)
		redirect(controller:'gallery', action:'show', params:[id:gallery.id])
	}
	
//	private Photo _createTempPhoto(Photo photo) {
//		Photo tempPhoto = new Photo()
//		tempPhoto.gallery = photo.gallery
//		tempPhoto.originalFilename = photo.originalFilename
//		tempPhoto.thumbnailFilename = photo.thumbnailFilename
//		tempPhoto.title = photo.title
//		tempPhoto
//	}
	
	def save() {
		log.debug "Photo.save.1"
		Photo photo
		try {
			photo = _initializePhotoFromParams(photo)
			if (request instanceof MultipartHttpServletRequest){
				for (filename in request.getFileNames()) {
					log.debug "filename: ${filename}"
					List<MultipartFile> files = request.getFiles(filename)
					log.debug "gallery.id: ${params.gallery.id}"
					Integer galleryId = new Integer(params.gallery.id)
					def photolist = galleryService.createPhotosFromUpload(files, galleryId)
					photo = photolist[0]
				}
			}
		} catch (Exception e) {
			log.debug e
		}
		def map = [photoInstance: photo]
		render(view: "show", model: map)
	}
	
	def show() {
		super.show()
	}
	
	def update() {
		super.update()
	}
	
	// populate default values from UI in case image metadata lacks any fields
	private Photo _initializePhotoFromParams(Photo photo) {
		photo = new Photo(	title:params.title, 
							originalFilename:params.originalFilename, 
							description:params.description, 
							location:params.location, 
							photoDate:params.photoDate, 
							fileSize:_getIntegerFromString(params.fileSize))
	}
	
	private Integer _getIntegerFromString(String param) {
		Integer target
		try {
			target = new Integer(param)
		} catch (Exception e) {
			target = 1
		}
		target
	}
	
}
