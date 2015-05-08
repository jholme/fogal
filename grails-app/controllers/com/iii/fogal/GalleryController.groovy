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
	
	@Override
	def uploadPhoto() {
		println request
		try {
			if (request instanceof MultipartHttpServletRequest){
				for (filename in request.getFileNames()) {
					println filename
					List<MultipartFile> files = request.getFiles(filename)
					Integer galleryId = new Integer(params.gallery)
					galleryService.createPhotosForImages(files, galleryId)
				}
			}
		} catch (Exception e) {
			println e
		}
//		render(view:"/gallery/show/${params.gallery}")
//		render(view:"/gallery/index")
		def map = [galleryInstance: Gallery.get(params.gallery)]
		render(view: "show", model: map)
	}

}
