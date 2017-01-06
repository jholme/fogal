package com.iii.fogal

import com.drew.metadata.*
import com.drew.metadata.exif.*
import com.drew.metadata.iptc.*
import com.drew.imaging.*
import com.drew.imaging.jpeg.*
import com.sun.xml.internal.ws.api.streaming.XMLStreamReaderFactory.Default
import org.apache.tika.exception.*
import org.apache.tika.metadata.*
import org.apache.tika.parsers.*
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import org.apache.commons.io.FileUtils
import org.apache.commons.io.IOUtils

import java.io.File
import java.util.List

class Photo {
	
	private static final String TITLE_OLD = "User Comment"// "Headline"
	private static final String DESCRIPTION_OLD = "Image Description"// "Caption/Abstract"
	private static final String ARTIST_OLD = "Artist"// "Credit", "Special Instructions" aka copyright
	private static final String PHOTO_DATE_OLD = "Date/Time Original"// "Date Created"
	//
	private static final String TITLE = "Headline"
	private static final String DESCRIPTION = "Caption/Abstract"
	private static final String CREDIT = "Credit"
	private static final String COPYRIGHT = "Special Instructions"
	private static final String PHOTO_DATE = "Date Created"
	private static final String CITY = "City"
	private static final String SUB_LOCATION = "Sub-location"
	private static final String PROVINCE_STATE =  "Province/State"
	private static final String COUNTRY = "Country/Primary Location Name"
	private static final String IMAGE_HEIGHT = "Image Height"
	private static final String IMAGE_WIDTH = "Image Width"

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
		println "Photo.beforeDelete: ${originalFilename}"
	}
	
	def afterDelete() {
		println "Photo.afterDelete: ${originalFilename}"
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
		println "before update"
	}

	def afterUpdate() {
		println "after update"
	}
	
	public void initPhoto(Gallery gallery, List<Photo> photoList, File photoFile) {
		populatePhotoMetadata(photoFile)
		try {
			gallery.addToPhotos(this)
			gallery.save(flush:true)
			if (this.validate()) {
				this.calculateAspect()
				this.save(flush:true)
				photoList.add(this)
				println "save photo: ${this}"
			} else {
				println "failed to save photo: ${this}"
				println this.errors
			}
	
		} catch (Exception e) {
			println "init photo failed: ${e}"
		}
	}

	public void calculateAspect() {
		aspect = imageHeight > imageWidth ? PORTRAIT : LANDSCAPE
	}
	
	public void populatePhotoMetadata(File file) {
		try {
			Metadata metadata = ImageMetadataReader.readMetadata(file)
			//_tryTika(file) // useful for debugging image metadata problems
						
			String sublocation
			String city
			String state
			String country
			def directories = metadata.getDirectories()
			for (Directory directory : directories) {
				def tags = directory.getTags()
				println "${directory}"
				for (Tag tag : tags) {
					String name = tag.tagName
					String desc = tag.description
					//println("tag.name: ${name}, tag.description: ${desc}")
					if (TITLE.equals(name)) this.title = desc.trim()
					if (CREDIT.equals(name)) this.credit = desc.trim()
					if (COPYRIGHT.equals(name)) this.copyright = desc.trim()
					if (DESCRIPTION.equals(name)) this.description = _trimAndLog(desc)
					if (PHOTO_DATE.equals(name)) this.photoDate = _trimAndLog(desc)
					if (IMAGE_HEIGHT.equals(name)) this.imageHeight = _castToLong(desc)
					if (IMAGE_WIDTH.equals(name)) this.imageWidth = _castToLong(desc)
					if (CITY.equals(name)) city = desc.trim()
					if (SUB_LOCATION.equals(name)) sublocation = desc.trim()
					if (PROVINCE_STATE.equals(name)) state = desc.trim()
					if (COUNTRY.equals(name)) country = desc.trim()
				}
			}
			this.location = _assembleLocation(sublocation, city, state, country)
		}
		catch (Exception e) {
			println e
		}
		println "${this.photoDate} (${this.location}) - ${this.description}"
		this
	}
	
	private String _assembleLocation(String sublocation, String city, String state, String country) {
		StringBuffer loc = new StringBuffer()
		if (sublocation) loc.append("${sublocation}, ")
		if (city) loc.append("${city} ")
		if (state) loc.append(state)
		if (country) {
			if ((loc.length() > 0) && !sublocation) loc.append(", ")
			loc.append(country)
		}
		return loc.toString()
	}
	
	String _trimAndLog(String term) {
		term = term.trim()
		if (term.length() > 2047) term = term.substring(0, 2047)
		println term
		term
	}
	
	Long _castToLong(String term) {
		term = term.trim()
		Long retval = term.split(" ")[0] as Long
	}
	
	private void _tryTika(File file) {
		org.apache.tika.metadata.Metadata metatika = new org.apache.tika.metadata.Metadata()
		try {
			ContentHandler handler = new DefaultHandler()
			org.apache.tika.parser.Parser parser = new org.apache.tika.parser.jpeg.JpegParser()
			org.apache.tika.parser.ParseContext context = new org.apache.tika.parser.ParseContext()
			String mimeType
			InputStream stream = IOUtils.toBufferedInputStream(FileUtils.openInputStream(file))
			org.apache.tika.Tika tika = new org.apache.tika.Tika()
			mimeType = tika.detect(stream)
			metatika.set(org.apache.tika.metadata.Metadata.CONTENT_TYPE, mimeType)
			parser.parse(stream, handler, metatika, context)
		} catch (org.apache.tika.exception.TikaException te) {
			println te
			println te.cause
		} catch (JpegProcessingException jpe) {
			println jpe
			println jpe.detailMessage
		} catch (Exception e) {
			println e
			println e.message
		}
		println "metatika Model: ${metatika.get('Model')}"
	}

	String toString() {
		"${title} (${galleryIdx})"
	}
	
}
