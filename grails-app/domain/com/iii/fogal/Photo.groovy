package com.iii.fogal

class Photo {
	
	def photoService
	public static final String PORTRAIT = "portrait"
	public static final String LANDSCAPE = "landscape"
	
	String title
	String description
	String location
	String credit
	String copyright
	String photoDate // yyyy-MM-dd
	String originalFilename
	String thumbnailFilename
	Long fileSize
	Long imageHeight
	Long imageWidth
	String aspect
	byte[] image
	
	static belongsTo = [gallery: Gallery]

    static constraints = {
		title nullable: true, blank: true
		credit nullable: true, blank: true
		copyright nullable: true, blank: true
		originalFilename nullable: true, blank: true
		thumbnailFilename nullable:true, blank: true
		description nullable: true, blank: true, maxSize: 2048
		location nullable: true, blank: true
		photoDate nullable: true, blank: true
		fileSize nullable: true
		imageHeight nullable: true
		imageWidth nullable: true
		aspect nullable:true, blank:true
		image nullable: true, maxSize: 2000000
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
	
	public void calculateAspect() {
		aspect = imageHeight > imageWidth ? PORTRAIT : LANDSCAPE
	}
	
//	public String getAspect() {
//		aspect = imageHeight > imageWidth ? PORTRAIT : LANDSCAPE
//		log.debug "aspect: ${aspect}"
//		aspect
//	}

	String toString() {
		"${title}"
	}
}
