package com.iii.fogal

import org.springframework.http.HttpStatus
import grails.converters.JSON
import org.springframework.web.multipart.MultipartHttpServletRequest
import org.springframework.web.multipart.MultipartFile
import java.awt.image.BufferedImage
import org.imgscalr.Scalr
import javax.imageio.ImageIO
import org.apache.commons.io.IOUtils

class GalleryController {

    static scaffold = Gallery
	def galleryService
	def photoService
	def utilityService
	
	def uploadPhoto() {
		println request
		try {
			if (request instanceof MultipartHttpServletRequest){
				for (filename in request.getFileNames()) {
					println filename
					List<MultipartFile> files = request.getFiles(filename)
					println "galleryId: ${params.gallery}"
					Integer galleryId = new Integer(params.gallery)
					def photolist = galleryService.createPhotosFromUpload(files, galleryId)
					photolist = null
				}
			}
		} catch (Exception e) {
			println e
		}
		def map = [galleryInstance: Gallery.get(params.gallery)]
		render(view: "show", model: map)
	}
	
	// save gallery object
	// create path on filesystem: imageDir, gallery.category.path, gallery.path
	def save() {
		println "gallery name: ${params.name}"
		println "gallery path: ${params.path}"
		println "gallery description: ${params.description}"
		println "gallery category: ${params.category}"
//		String imageDir = grailsApplication.config.file.upload.directory?:'C:\fogalFiles'//'/fogalFiles'
//		println "imageDir: ${imageDir}"
//		Path galleryPath = Paths.get()
//		String newDir = "${imageDir}/${params.path}"
//		File dirFile = new File(newDir)
//		dirFile.mkdir()
		Gallery newGal = new Gallery(params)
		newGal.save()
		//galleryService.addGalleryToFileSystem(params.path, params.category)
		forward action:"show", id: newGal.id
	}
	
//	def create() {
//		//super.create()
//		def categoryId = params.category
//		println "categoryId: ${categoryId}"
//		Category category = Category.findById(categoryId)
//	}
	
	def delete() {
		Gallery gallery = Gallery.get(params.id)
		Category category = gallery.category
		Gallery.withTransaction {
			gallery.delete()
		}
//		galleryService.deleteGalleryFromCategory(gallery, category)
		redirect(controller:'category', action:'show', params:[id:category.id])
	}
	
	def galleryThum() {
		try {
			Long galleryId = params.id as Long
			log.debug "galleryThum: ${galleryId}"
			Gallery gallery = Gallery.get(galleryId)
			//Gallery gallery = category.galleries[utilityService.getRandom(category.galleries)]
			Photo photo = gallery.photos[utilityService.getRandom(gallery.photos)]
			File imageFile = photoService.prepareToRender(photoService.THUM_IMAGE, photo)
			response.setContentLength(imageFile.size() as Integer)
			InputStream fileStream = new FileInputStream(imageFile)
			response.outputStream.write(IOUtils.toByteArray(fileStream))
		} catch (Exception e) {
			response.sendError(404)
		}
	}

}
