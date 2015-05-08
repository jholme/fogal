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
	
	def renderImage() {
		Photo photo = Photo.findById(params.id)
		try {
			String imageDir = grailsApplication.config.file.upload.directory?:'/tmp'
			File imageFile = new File("${imageDir}/${photo.originalFilename}")
			response.setContentLength(imageFile.size() as Integer)
			InputStream fileStream = new FileInputStream(imageFile)
			response.outputStream.write(IOUtils.toByteArray(fileStream))
		} catch (Exception e) {
			response.sendError(404)
		}
	}

	def renderImageDB() {
		Photo photo = Photo.findById(params.id)
		if (photo?.image) {
			response.setContentLength(photo.image.length)
			response.outputStream.write(photo.image)
		} else {
			response.sendError(404)
		}
	}
	
	@Override
	def save() {
		println request
		try {
			if (request instanceof MultipartHttpServletRequest){
//				List<MultipartFile> files = request.getFiles("image")
//				for (file in files) println file.getOriginalFilename()
				String storageDirectory = grailsApplication.config.file.upload.directory?:'/tmp'
				println storageDirectory
				for (filename in request.getFileNames()) {
					println filename
					List<MultipartFile> files = request.getFiles(filename)
					for (MultipartFile file in files) {
						String oName = file.getOriginalFilename()
						println oName
						Long fileSize = file.getSize()
//						def fileNameArray = file.originalFilename.split(".")
//						String oFileBase = fileNameArray[0]
//						String oFileExtension = fileNameArray[1]//file.originalFilename.substring(file.originalFilename.lastIndexOf("."))
						String newFilenameBase = UUID.randomUUID().toString()
						String newFilename = params.title + "_" + newFilenameBase + "_" + oName
						File newFile = new File("$storageDirectory/$newFilename")
						file.transferTo(newFile)
						Photo photo = new Photo(title:"${params.title}_${oName}", fileSize:fileSize, originalFilename:newFilename)
						Gallery gallery = Gallery.findById(2)
						gallery.addToPhotos(photo)
						gallery.save(flush:true)
						if (photo.validate()) {
							photo.save(flush:true)
							println "save photo: ${photo}"
						} else {
							println "failed to save photo: ${photo}"
						}
					}
//					MultipartFile file = request.getFile(filename)
//					File newFile = new File("${storageDirectory}/${filename}")
//					println newFile.path
//					file.transferTo(newFile)
				}
//				def fileMap = request.getFileMap()
//				fileMap.each{ k, v ->
//					println "${k}:${v.originalFilename}"
//				}
				render(view:'/photo/index')
			}
		} catch (Exception e) {
			println e
		}
//		def count = 1
//		while (request.getParameterNames().hasMoreElements()) {
//			//String name = request.getParameterNames().nextElement().toString()
//			Object name = request.getParameterNames().nextElement()
//			String param = request.getParameter(name.toString())
//			count++
//			println name
//		}
//		println "count: ${count}"
		//super.save()
	}
	
	def imageUpload() {
		render(view: "imageUpload", model: [:])
	}
	
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
