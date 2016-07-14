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
	//Gallery gallery
	Integer galleryIdx
	String photoDate // yyyy-MM-dd
	String originalFilename
	String thumbnailFilename
	Long fileSize
	Long imageHeight
	Long imageWidth
	String aspect
	byte[] image
	
	static belongsTo = [gallery: Gallery]
	
//	static mapping = {
//		sort galleryIdx: "asc"
//	}

    static constraints = {
		title nullable: true, blank: true
		credit nullable: true, blank: true
		copyright nullable: true, blank: true
		galleryIdx nullable:true, min:1//, unique:'gallery'
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
	
	def beforeDelete() {
		log.debug "Photo.beforeDelete: ${originalFilename}"
	}
	
	def afterDelete() {
		log.debug "Photo.afterDelete: ${originalFilename}"
		List photos = gallery.photos.sort{ a,b -> a.galleryIdx <=> b.galleryIdx}
		photos.each {println "${it.id} => ${it.galleryIdx}"}
		Integer prevIdx = 0
		for (Photo photo in photos) {
			Integer gidx = photo.galleryIdx
			println "${photo.id} => ${photo.galleryIdx}"
			if (gidx != prevIdx + 1) {
				photo.galleryIdx = gidx - 1
				photo.save()
			}
			prevIdx += 1
		}
	}
	
	def afterInsert() {
		galleryIdx = gallery.photos.size()
	}
	
	def beforeUpdate() {
		log.debug "before update"
	}

	def afterUpdate() {
		log.debug "after update"
	}
	
	public void calculateAspect() {
		aspect = imageHeight > imageWidth ? PORTRAIT : LANDSCAPE
	}

	String toString() {
		"${title} (${galleryIdx})"
	}
	
}
