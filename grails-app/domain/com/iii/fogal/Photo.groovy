package com.iii.fogal

class Photo {
	
	String title
	String originalFilename
	String description
	String location
	String photoDate // yyyy-MM-dd
	Long fileSize
	byte[] image
	
	static belongsTo = [gallery: Gallery]

    static constraints = {
		title nullable: false, blank: false
		originalFilename nullable: false, blank: false
		description nullable: true, blank: true
		location nullable: true, blank: true
		photoDate nullable: true, blank: true
		fileSize nullable:true
		image nullable: true, maxSize: 2000000
    }
}
