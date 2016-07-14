package com.iii.fogal

import org.apache.commons.io.IOUtils

class CategoryController {
    static scaffold = Category
	
	def categoryService
	def photoService
	def utilityService
	
	def save() {
		log.debug params
		Category category = new Category(name:params.name, description:params.description, path:params.path)
		category.save()
		forward action:"show", id:category.id
	}
	
	def delete() {
		log.debug params
		Category category = Category.get(params.id)
		Category.withTransaction {
			category.delete(flush:true)
		}
		redirect(uri:'/')
	}
	
	def porThum() {
		Long categoryId = params.id as Long
		log.debug "porThum: ${categoryId}"
		_aspectThum(categoryId, Photo.PORTRAIT)
	}
	
	def lanThum() {
		Long categoryId = params.id as Long
		log.debug "lanThum: ${categoryId}"
		_aspectThum(categoryId, Photo.LANDSCAPE)
	}
	
	private void _aspectThum(Long categoryId, String aspect) {
		try {
			Category category = Category.get(categoryId)
			Gallery gallery = category.galleries[utilityService.getRandom(category.galleries)]
			//Photo photo = gallery.photos[getRandomWithAspect(gallery.photos, aspect)]
			Photo photo = getRandomWithAspect(gallery.photos, aspect)
			File imageFile = photoService.prepareToRender(photoService.THUM_IMAGE, photo)
			response.setContentLength(imageFile.size() as Integer)
			InputStream fileStream = new FileInputStream(imageFile)
			response.outputStream.write(IOUtils.toByteArray(fileStream))
		} catch (Exception e) {
			response.sendError(404)
		}

	}

	def thumbnail() {
		try {
			//log.debug params
			Long categoryId = params.id as Long
			Category category = Category.get(categoryId)
			Gallery gallery = category.galleries[utilityService.getRandom(category.galleries)]
			Photo photo = gallery.photos[utilityService.getRandom(gallery.photos)]
			File imageFile = photoService.prepareToRender(photoService.THUM_IMAGE, photo)
			response.setContentLength(imageFile.size() as Integer)
			InputStream fileStream = new FileInputStream(imageFile)
			response.outputStream.write(IOUtils.toByteArray(fileStream))
		} catch (Exception e) {
			response.sendError(404)
		}
	}
	
	def galleryThum() {
		try {
			//log.debug params
			Long galleryId = params.id as Long
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
	
	private Photo getRandomWithAspect(List<Photo> photoList, String aspect) {
		log.debug "${aspect}: ${photoList}"
		Photo photo
		if (aspect) {
			List<Photo> withAspect = photoList.findAll { it.aspect.equals(aspect) }
			photo = withAspect[utilityService.getRandom(withAspect)]
		} else {
			photo = photoList[utilityService.getRandom(photoList)]
		}
		log.debug "getRandomWithAspect: ${photo}"
		photo
	}
	
	def beforeInterceptor = [action:this.&_handleCategoryPathUpdate, only:'update']
	
	private Boolean _handleCategoryPathUpdate() {
		log.debug "_handleCategoryPathUpdate"
		Boolean success = true
		String newPath = params.path
		log.debug "newPath: ${newPath}"
		Category category = Category.findById(params.id as Long)
		log.debug "oldPath: ${category.path}"
		if (!newPath?.equals(category.path)) {
			success = categoryService.updateCategoryOnFileSystem(category, newPath)
		}
		if (!success) {
			String msg = "Could not change category path from '${category?.path}' to '${newPath}'"
			log.debug msg
			flash.message = msg
			redirect(action: 'edit', params:['id':params.id])
		}
		success
	}

	private _validateGallery() {
		_validateGalleryIdx()
		_handleCategoryPathUpdate()
	}

}
