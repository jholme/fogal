package com.iii.fogal

import java.nio.file.Path
import java.nio.file.Paths
import java.nio.file.Files

class Gallery {
	
	String name
	String description
	String path
	Category category
	List<Photo> photos = []
	//Map<Integer,Integer> photoIndex
	// GalleryPhotoIndex
	// Gallery gallery hasOne GalleryPhotoIndex
	// Photo photo hasOne GalleryPhotoIndex
	// Integer photoIndex - comparator sorts on photoIndex field
	
	static hasMany = [photos: Photo]
	static belongsTo = [category: Category]
	static mapping = {
		photos cascade: 'all-delete-orphan'
	}

    static constraints = {
		name nullable: false, blank: false
		description nullable: true, blank: true
		path nullable: false, blank: false, unique: 'category'
    }
	
	def grailsApplication
	def galleryService
	
//	public Gallery() {
//		photoIndex = new HashMap<Integer,Integer>()
//	}
	
	/*
	 * http://grails.1312388.n4.nabble.com/Is-it-possible-to-customize-the-hibernate-plugin-autogenerated-CRUD-methods-on-my-grails-app-td2318957.html
	 */
	def afterInsert() {
		//super.save()
		String imageDir = grailsApplication.config.file.upload.directory ?: 'C:\fogalFiles'//'/fogalFiles'
		//println grailsApplication.config.file.upload.directory
		//String fileSep = System.properties.'file.separator'
		//println "imageDir: ${imageDir}"
		//String newDir = "${imageDir}${fileSep}${category.path}${fileSep}${path}"
		Path galleryPath = Paths.get(imageDir, category.path, path)
		File galleryDir = galleryPath.toFile()
		println "galleryDir: ${galleryDir}"
		if (!galleryDir.exists()) galleryDir.mkdir()
//		//println "newDir: ${newDir}"
//		File dirFile = new File(newDir)
//		println "dirFile: ${dirFile}"
//		if (!dirFile.exists()) dirFile.mkdir()
	}
	
	def beforeDelete() {
		println("gallery.beforeDelete: ${this}")
		// this works when a single gallery is deleted
		// but not when all galleries are cascade-deleted as result of category.delete
		galleryService.deleteGalleryFromCategory(this, category)
		println("gallery.beforeDelete: end")
	}
	
	String toString() {
		this.path
	}
}
