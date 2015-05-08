package com.iii.fogal

import org.springframework.http.HttpStatus
import grails.converters.JSON
import org.springframework.web.multipart.MultipartHttpServletRequest
import org.springframework.web.multipart.MultipartFile
import java.awt.image.BufferedImage
import org.imgscalr.Scalr
import javax.imageio.ImageIO
import org.apache.commons.io.IOUtils

class PhotoController {

    def scaffold = Photo
	def galleryService
	private static final String FULL_IMAGE = "full image"
	private static final String THUM_IMAGE = "thumbnail image"
	
	def renderImage() {
		_renderSelected(FULL_IMAGE)
	}
	
	def renderThumbnail() {
		_renderSelected(THUM_IMAGE)
	}
	
	private def _renderSelected(String imageType) {
		try {
			Photo photo = Photo.findById(params.id)
			String imageDir = grailsApplication.config.file.upload.directory?:'/tmp'
			String galleryPath = photo?.gallery?.path
			String fileName = THUM_IMAGE.equals(imageType) ? photo.thumbnailFilename : FULL_IMAGE.equals(imageType) ? photo.originalFilename : ""
			File imageFile = new File("${imageDir}/${galleryPath}/${fileName}")
			response.setContentLength(imageFile.size() as Integer)
			InputStream fileStream = new FileInputStream(imageFile)
			response.outputStream.write(IOUtils.toByteArray(fileStream))
		} catch (Exception e) {
			response.sendError(404)
		}
	}

//	def renderImageDB() {
//		Photo photo = Photo.findById(params.id)
//		if (photo?.image) {
//			response.setContentLength(photo.image.length)
//			response.outputStream.write(photo.image)
//		} else {
//			response.sendError(404)
//		}
//	}
	
	@Override
	def save() {
		println request
		Photo photo
		try {
			photo = _initializePhotoFromParams(photo)
			if (request instanceof MultipartHttpServletRequest){
				for (filename in request.getFileNames()) {
					println filename
					List<MultipartFile> files = request.getFiles(filename)
					Integer galleryId = new Integer(params.gallery?.id)
					def photolist = galleryService.createPhotosForImages(files, galleryId)
					photo = photolist[0]
				}
			}
		} catch (Exception e) {
			println e
		}
		def map = [photoInstance: photo]
		render(view: "show", model: map)
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
	
//	def imageUpload() {
//		render(view: "imageUpload", model: [:])
//	}
	
//	def upload() {
//		switch(request.method){
//			case "GET":
//				def results = []
//				Photo.findAll().each { Photo photo ->
//					results << [
//							name: photo.originalFilename,
//							size: photo.fileSize,
//							url: createLink(controller:'photo', action:'photo', id: photo.id),
//							thumbnail_url: createLink(controller:'photo', action:'thumbnail', id: photo.id),
//							delete_url: createLink(controller:'photo', action:'delete', id: photo.id),
//							delete_type: "DELETE"
//					]
//				}
//				render results as JSON
//				break;
//			case "POST":
//				def results = []
//				if (request instanceof MultipartHttpServletRequest){
//					for(filename in request.getFileNames()){
//						MultipartFile file = request.getFile(filename)
//
//						String newFilenameBase = UUID.randomUUID().toString()
//						String originalFileExtension = file.originalFilename.substring(file.originalFilename.lastIndexOf("."))
//						String newFilename = newFilenameBase + originalFileExtension
//						String storageDirectory = grailsApplication.config.file.upload.directory?:'/tmp'
//
//						File newFile = new File("$storageDirectory/$newFilename")
//						file.transferTo(newFile)
//
//						BufferedImage thumbnail = Scalr.resize(ImageIO.read(newFile), 290);
//						String thumbnailFilename = newFilenameBase + '-thumbnail.png'
//						File thumbnailFile = new File("$storageDirectory/$thumbnailFilename")
//						ImageIO.write(thumbnail, 'png', thumbnailFile)
//
//						Photo photo = new Photo(
//								originalFilename: file.originalFilename,
//								thumbnailFilename: thumbnailFilename,
//								newFilename: newFilename,
//								fileSize: file.size
//						).save()
//
//						results << [
//								name: photo.originalFilename,
//								size: photo.fileSize,
//								url: createLink(controller:'photo', action:'photo', id: photo.id),
//								thumbnail_url: createLink(controller:'photo', action:'thumbnail', id: photo.id),
//								delete_url: createLink(controller:'photo', action:'delete', id: photo.id),
//								delete_type: "DELETE"
//						]
//					}
//				}
//
//				render results as JSON
//				break;
//			default: render status: HttpStatus.METHOD_NOT_ALLOWED.value()
//		}
//	}
//
//	def photo(){
//		def pic = Photo.get(params.id)
//		File picFile = new File("${grailsApplication.config.file.upload.directory?:'/tmp'}/${pic.newFilename}")
//		response.contentType = 'image/jpeg'
//		response.outputStream << new FileInputStream(picFile)
//		response.outputStream.flush()
//	}
//
//	def thumbnail(){
//		def pic = Photo.get(params.id)
//		File picFile = new File("${grailsApplication.config.file.upload.directory?:'/tmp'}/${pic.thumbnailFilename}")
//		response.contentType = 'image/png'
//		response.outputStream << new FileInputStream(picFile)
//		response.outputStream.flush()
//	}
//
//	def delete(){
//		def pic = Photo.get(params.id)
//		File picFile = new File("${grailsApplication.config.file.upload.directory?:'/tmp'}/${pic.newFilename}")
//		picFile.delete()
//		File thumbnailFile = new File("${grailsApplication.config.file.upload.directory?:'/tmp'}/${pic.thumbnailFilename}")
//		thumbnailFile.delete()
//		pic.delete()
//
//		def result = [success: true]
//		render result as JSON
//	}
	
}
