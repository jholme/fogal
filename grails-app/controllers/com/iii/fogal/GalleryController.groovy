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

    def scaffold = true
	def galleryService
	
	//@Override
	def uploadPhoto() {
		println request
		try {
			if (request instanceof MultipartHttpServletRequest){
				for (filename in request.getFileNames()) {
					println filename
					List<MultipartFile> files = request.getFiles(filename)
					Integer galleryId = new Integer(params.gallery)
					def photolist = galleryService.createPhotosForImages(files, galleryId)
					photolist = null
				}
			}
		} catch (Exception e) {
			println e
		}
		def map = [galleryInstance: Gallery.get(params.gallery)]
		render(view: "show", model: map)
	}
	
	@Override
	def save() {
		println params.name
		println params.path
		println params.description
		String imageDir = grailsApplication.config.file.upload.directory?:'/fogalFiles'
		println "imageDir: ${imageDir}"
		String newDir = "${imageDir}/${params.path}"
		File dirFile = new File(newDir)
		dirFile.mkdir()
		super.save()
	}

}
