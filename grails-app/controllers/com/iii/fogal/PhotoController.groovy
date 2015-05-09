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
	
}
