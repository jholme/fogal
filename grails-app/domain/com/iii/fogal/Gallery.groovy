package com.iii.fogal

class Gallery {
	
	String name
	String description
	String path
	
	static hasMany = [photos: Photo]

    static constraints = {
		name nullable: false, blank: false
		description nullable: true, blank: true
		path nullable: false, blank: false
    }
}
