package com.iii.fogal

class Photo {
	
	String title
	String fileName
	String description
	String location
	String photoDate // yyyy-MM-dd
	
	static belongsTo = [gallery: Gallery]

    static constraints = {
		title nullable: false, blank: false
		fileName nullable: false, blank: false
		description nullable: true, blank: true
		location nullable: true, blank: true
		photoDate nullable: true, blank: true
    }
}
