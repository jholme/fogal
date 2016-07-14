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
		redirect(controller:'category', action:'show', params:[id:category.id])
	}
	
	def beforeInterceptor = [action:this.&_validateGallery, only:'update']
	//def afterInterceptor = [action:this.&_handleGalleryPathUpdate, only:'update']
	
	private _validateGallery() {
		_validateGalleryIdx()
		_handleGalleryPathUpdate()
	}
	
	private Boolean _handleGalleryPathUpdate() {
		log.debug "_handleGalleryPathUpdate"
		Boolean success = true
		String newPath = params.path
		log.debug "newPath: ${newPath}"
		Gallery gallery = Gallery.findById(params.id as Long)
		log.debug "oldPath: ${gallery.path}"
		if (!newPath?.equals(gallery.path)) {
			success = galleryService.updateGalleryOnFileSystem(gallery, newPath)
		}
		if (!success) {
			String msg = "Could not change gallery path from '${gallery?.path}' to '${newPath}'"
			log.debug msg
			flash.message = msg
			redirect(action: 'edit', params:['id':params.id])
		}
		success
	}
		
	private Boolean _validateGalleryIdx() {
		Map photoIdx = [:]
		for (param in params) {
			if (param.toString().startsWith('galleryIdx')) {
				log.debug (param)
				Map paramMap = _populatePhotoIdx(param)
				photoIdx.put(paramMap.entrySet().key[0], paramMap.entrySet().value[0])
				log.debug"photoIdx: ${photoIdx}"
			} else {
				log.debug param
			}
		}
		log.debug"photoIdx: ${photoIdx}"
		List origValues = photoIdx.entrySet().collect { entry -> entry.value }
		log.debug "origValues: ${origValues}"
		List uniqueValues = origValues.clone().unique()
		log.debug "uniqueValues: ${uniqueValues}"
		Boolean bool = photoIdx.values().any{ it < 1 }
		log.debug "photoIdx.values().any() < 1: ${bool}"
		log.debug "photoIdx.values().max(): ${photoIdx.values().max()}"
		
		if (photoIdx.values().max() > photoIdx.size()) {
			flash.message = "Photo order values must be contiguous."
			redirect(action: 'edit', params:['id':params.id])
			return false
		} else if (photoIdx.values().any{ it < 1 }) {
			flash.message = "Photo order values must 1 or higher."
			redirect(action: 'edit', params:['id':params.id])
			return false
		} else if (uniqueValues.size() < origValues.size()) {
			flash.message = "Photo order values must be unique."
			redirect(action: 'edit', params:['id':params.id])
			return false
		} else {
			Gallery gallery = Gallery.findById(params.id as Long)
			photoIdx.each { k,v ->
				Photo.withTransaction {
					Photo photo = Photo.findById(k)
					photo.galleryIdx = v
					photo.save(flush:true)
					log.debug "after save: ${photo}"
					log.debug "after save: ${photo.errors}"
				}
			}
			return true
		}
	}
		
	private Map _populatePhotoIdx(param) {
		String key = param.key
		String val = param.value
		log.debug "key: ${key}, value: ${val}"
		Integer idx1 = key.indexOf("_") + 1
		Integer photoId = key.substring(idx1) as Integer
		Integer galIdx = val as Integer
		log.debug"photoId:${photoId}, galIdx:${galIdx}"
		Map retval = [:]
		retval.put(photoId,galIdx)
		retval
	}
	
	def galleryThum() {
		try {
			Long galleryId = params.id as Long
			log.debug "galleryThum: ${galleryId}"
			Gallery gallery = Gallery.get(galleryId)
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
