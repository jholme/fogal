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

}
