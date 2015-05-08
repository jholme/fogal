package com.iii.fogal

import com.drew.metadata.*
import com.drew.metadata.exif.*
import com.drew.metadata.iptc.*
import com.drew.imaging.*
import com.drew.imaging.jpeg.*
import javax.imageio.*;
import javax.imageio.stream.*;
import javax.imageio.metadata.*;
import org.springframework.web.multipart.MultipartFile
import org.w3c.dom.*;

class GalleryService {

	private static final String TITLE = "User Comment"//, tag.description: girls handlebars // title
	private static final String DESCRIPTION = "Image Description"//, tag.description: girls bike handlebars - 1362
	private static final String ARTIST = "Artist"//, tag.description: John Hol e
	private static final String PHOTO_DATE = "Date/Time Original"//, tag.description: 2014:05:27 17:35:28
	private static final String CITY = "City"//, tag.description: Oakland
	private static final String SUB_LOCATION = "Sub-location"//, tag.description: 56th Street
	private static final String PROVINCE_STATE =  "Province/State"//, tag.description: CA
	private static final String COUNTRY = "Country/Primary Location Name"//, tag.description: USA
	
	def grailsApplication
	
	void createPhotosForImages(List<MultipartFile> files, Integer galleryId) {
		String imageDir = grailsApplication.config.file.upload.directory?:'/tmp'
		println "imageDir: ${imageDir}"
		for (MultipartFile file in files) {
			Long fileSize = file.getSize()
			String oName = file.getOriginalFilename()
			println oName
			String[] fileNameArray = oName.split(/\./)
			String oFileBase = fileNameArray[0]
			String oFileExt = fileNameArray[1]//file.originalFilename.substring(file.originalFilename.lastIndexOf("."))
			String newFilenameBase = UUID.randomUUID().toString()
			String newFilename = oFileBase + "_" + newFilenameBase + "." + oFileExt
			File newFile = new File("$imageDir/$newFilename")
			file.transferTo(newFile)
			Photo photo = new Photo(title:"${oName}", fileSize:fileSize, originalFilename:newFilename)
			photo = populatePhotoMetadata(photo, newFile)
			Gallery gallery = Gallery.findById(galleryId)
			gallery.addToPhotos(photo)
			gallery.save(flush:true)
			if (photo.validate()) {
				photo.save(flush:true)
				println "save photo: ${photo}"
			} else {
				println "failed to save photo: ${photo}"
			}
		}
	}

    Photo populatePhotoMetadata(Photo photo, File file) {
		String imageDir = grailsApplication.config.file.upload.directory?:'/tmp'
		println "imageDir: ${imageDir}"
        try {
			Metadata metadata = ImageMetadataReader.readMetadata(file)
			String sublocation
			String city
			String state
			String country
			for (Directory directory : metadata.getDirectories()) {
				for (Tag tag : directory.getTags()) {
					String name = tag.tagName
					String desc = tag.description
					//System.out.println("tag.name: ${name}, tag.description: ${desc}")
					if (TITLE.equals(name)) photo.title = desc.trim()
					if (DESCRIPTION.equals(name)) photo.description = desc.trim()
					if (PHOTO_DATE.equals(name)) photo.photoDate = desc.trim()
					if (CITY.equals(name)) city = desc.trim()
					if (SUB_LOCATION.equals(name)) sublocation = desc.trim()
					if (PROVINCE_STATE.equals(name)) state = desc.trim()
					if (COUNTRY.equals(name)) country = desc.trim()
				}
			}
			photo.location = "${sublocation}, ${city} ${state}, ${country}"
        }
        catch (Exception e) {
            e.printStackTrace();
        }
		
		photo
    }
	
}
