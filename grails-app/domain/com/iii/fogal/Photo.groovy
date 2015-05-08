package com.iii.fogal

class Photo {
	
	String title
	String originalFilename
	String thumbnailFilename
	String description
	String location
	String photoDate // yyyy-MM-dd
	Long fileSize
	byte[] image
	
	static belongsTo = [gallery: Gallery]

    static constraints = {
		title nullable: true, blank: true
		originalFilename nullable: true, blank: true
		thumbnailFilename nullable:true, blank: true
		description nullable: true, blank: true
		location nullable: true, blank: true
		photoDate nullable: true, blank: true
		fileSize nullable:true
		image nullable: true, blank: true, maxSize: 2000000
    }
}
