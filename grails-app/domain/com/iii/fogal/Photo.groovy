package com.iii.fogal

class Photo {
	
	def photoService
	
	String title
	String description
	String location
	String credit
	String copyright
	String photoDate // yyyy-MM-dd
	String originalFilename
	String thumbnailFilename
	Long fileSize
	byte[] image
	
	static belongsTo = [gallery: Gallery]

    static constraints = {
		title nullable: true, blank: true
		credit nullable: true, blank: true
		copyright nullable: true, blank: true
		originalFilename nullable: true, blank: true
		thumbnailFilename nullable:true, blank: true
		description nullable: true, blank: true, maxSize: 1028
		location nullable: true, blank: true
		photoDate nullable: true, blank: true
		fileSize nullable:true
		image nullable: true, blank: true, maxSize: 2000000
    }
	
	def save() {
		super.save()
	}
	
	def beforeDelete() {
		log.debug "Photo.beforeDelete: ${originalFilename}"
		//photoService.deletePhotoFromGallery(this)
	}
	
	def afterDelete() {
		log.debug "Photo.afterDelete: ${originalFilename}"
		//gallery.photos.remove(this)
	}
	
	String toString() {
		"${title}"
	}
}
