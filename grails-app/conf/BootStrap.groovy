import com.iii.fogal.*
class BootStrap {

				
	/*
	 * This bootstrap relies on having 6 jpeg images (named as shown below with 'Photo.originalFilename' values),
	 * located on the filesystem at C:\$file.upload.directory\$gallery.path.
	 * 
	 * The value of 'file.upload.directory' is set in Config.groovy - the default is C:\\fogalFiles
	 * 
	 * JPEG images are expected to have these metadata fields populated:
	 * EXIF - User Comment (Photo.title)
	 * EXIF - Image Description (Photo.description)
	 * EXIF - Date/Time Original (Photo.photoDate)
	 * IPTC - City (amalgamated as Photo.location)
	 * IPTC - Sub-location (amalgamated as Photo.location)
	 * IPTC - Province/State (amalgamated as Photo.location)
	 * IPTC - Country/Primary Location Name (amalgamated as Photo.location)
	 * 
	 * The tool I used to populated image metadata is found at:
	 * http://freeweb.siol.net/hrastni3/foto/exif/exiftoolgui.htm
	 * 
	 */
	
	def galleryService
	def grailsApplication
	
    def init = { servletContext ->
		println "onstart.init: ${grailsApplication.config.fogal.onstart.init}"
		println "tmpdir: ${System.getProperty("java.io.tmpdir")}"
		environments {
			development {
				if (grailsApplication.config.fogal.onstart.init) {
					initCategories()
					initGalleries()
					initPhotos()
					initLinks()
					initStories()
					security1()
				}
			}
//			production {
//				initCategories()
//				initGalleries()
//				initPhotos()
//				//initLinks()
//				//initStories()
//				security1()
//			}
		}
    }
	
    def destroy = {
    }
	
	def initCategories() {
		println "initCategories"
		Category farmworkers = new Category(name:"Farmworkers", description:"Farmworkers", path:"farmworkers", homePageTile:HomePageTile.TILE_01)
		farmworkers.save(flush:true)
		Category immigrants = new Category(name:"Immigrants", description:"Immigrants", path:"immigrants", homePageTile:HomePageTile.TILE_02)
		immigrants.save(flush:true)
		Category mexico = new Category(name:"Mexico", description:"Mexico", path:"mexico", homePageTile:HomePageTile.TILE_03)
		mexico.save(flush:true)
		Category students = new Category(name:"Students", description:"Students", path:"students", homePageTile:HomePageTile.TILE_04)
		students.save(flush:true)
		Category unions = new Category(name:"Unions", description:"Unions", path:"unions", homePageTile:HomePageTile.TILE_05)
		unions.save(flush:true)
	}

	def initGalleries() {
		println "initGalleries"
		//
		Category farmworkers = Category.findByPath("farmworkers")
		Category immigrants = Category.findByPath("immigrants")
		Category mexico = Category.findByPath("mexico")
		Category students = Category.findByPath("students")
		Category unions = Category.findByPath("unions")
		// bicycles
		Gallery bicycles = new Gallery(name:"Bicycles", description:"Bicycles for sale", path:"bicycles")
		students.galleries.add(bicycles)
		students.save()
		bicycles.category = students
		bicycles.save(flush:true)
		// daydead
		Gallery daydead = new Gallery(name:"Day of the Dead", description:"This gallery is for 'Day of the Dead' photos", path:"daydead")
		mexico.galleries.add(daydead)
		mexico.save()
		daydead.category = mexico
		daydead.save(flush:true)
		// blancaNavidad
		Gallery blancaNavidad = new Gallery(name:"Blanca Navidad", description:"The Revolutionary Community of Blanca Navidad", path:"blancaNavidad")
		mexico.galleries.add(blancaNavidad)
		mexico.save()
		blancaNavidad.category = mexico
		blancaNavidad.save(flush:true)
		// coastalFarmworkers
		Gallery coastalFarmworkers = new Gallery(name:"Coastal Farmworkers", description:"Farm Workers of the Coastal Valleys", path:"coastalFarmworkers")
		farmworkers.galleries.add(coastalFarmworkers)
		farmworkers.save()
		coastalFarmworkers.category = farmworkers
		coastalFarmworkers.save(flush:true)
		// feeHikeProtest
		Gallery feeHikeProtest = new Gallery(name:"Fee Hike Protest", description:"Fee Hike Protest", path:"feeHikeProtest")
		students.galleries.add(feeHikeProtest)
		students.save()
		feeHikeProtest.category = students
		feeHikeProtest.save(flush:true)
		// janitorsStrike
		Gallery janitorsStrike = new Gallery(name:"Janitors Strike", description:"Janitors Strike", path:"janitorsStrike")
		unions.galleries.add(janitorsStrike)
		unions.save()
		janitorsStrike.category = unions
		janitorsStrike.save(flush:true)
		// nahuatls
		Gallery nahuatls = new Gallery(name:"Nahuatls", description:"Limoneros Purepechas and Nahuatls", path:"nahuatls")
		immigrants.galleries.add(nahuatls)
		immigrants.save()
		nahuatls.category = immigrants
		nahuatls.save(flush:true)
		// teachersProtest
		Gallery teachersProtest = new Gallery(name:"Teachers Protest", description:"Teachers Protest", path:"teachersProtest")
		students.galleries.add(teachersProtest)
		students.save()
		teachersProtest.category = students
		teachersProtest.save(flush:true)
	}
	
	def initPhotos() {
		println "initPhotos"
		// daydead
		try {
			galleryService.createPhotosFromDisk(['photo1.jpg','photo2.jpg','photo3.jpg'], "daydead")
		} catch (Exception e) {
			log.debug "initPhotos (daydead): ${e}"
		}
		// bicycles
		try {
			galleryService.createPhotosFromDisk(['photo4.jpg', 'photo44.jpg'], "bicycles")
		} catch (Exception e) {
			log.debug "initPhotos (bicycles): ${e}"
		}
		// blancaNavidad
		try {
			galleryService.createPhotosFromDisk(['photo5.jpg'], "blancaNavidad")
		} catch (Exception e) {
			log.debug "initPhotos (blancaNavidad): ${e}"
		}
		// coastalFarmworkers
		try {
			galleryService.createPhotosFromDisk(['photo6.jpg','photo11.jpg'], "coastalFarmworkers")
		} catch (Exception e) {
			log.debug "initPhotos (coastalFarmworkers): ${e}"
		}
		// feeHikeProtest
		try {
			galleryService.createPhotosFromDisk(['photo7.jpg','photo8.jpg'], "feeHikeProtest")
		} catch (Exception e) {
			log.debug "initPhotos (feeHikeProtest): ${e}"
		}
		// janitorsStrike
		try {
			galleryService.createPhotosFromDisk(['photo9.jpg','photo10.jpg','photo99.jpg'], "janitorsStrike")
		} catch (Exception e) {
			log.debug "initPhotos (janitorsStrike): ${e}"
		}
		// nahuatls
		try {
			galleryService.createPhotosFromDisk(['photo12.jpg','photo13.jpg'], "nahuatls")
		} catch (Exception e) {
			log.debug "initPhotos (nahuatls): ${e}"
		}
		// teachersProtest
		try {
			galleryService.createPhotosFromDisk(['photo14.jpg','photo15.jpg'], "teachersProtest")
		} catch (Exception e) {
			log.debug "initPhotos (teachersProtest): ${e}"
		}
	}
	
	// 
	
	def initLinks() {
		println "initLinks"
		try {
			Link link1 = new Link(linkCat:Link.ORGS, linkText:"National Network for Immigrant and Refugee Rights", linkHref:"http://www.nnirr.org/")
			link1.save(flush:true)
			Link link2 = new Link(linkCat:Link.ORGS, linkText:"U.S. Labor Against the War", linkHref:"http://www.uslaboragainstwar.org/")
			link2.save(flush:true)
			Link link3 = new Link(linkCat:Link.ORGS, linkText:"United Farm Workers", linkHref:"http://www.ufw.org/")
			link3.save(flush:true)
			Link link4 = new Link(linkCat:Link.PUBS, linkText:"Race Forward", linkHref:"https://www.raceforward.org/")
			link4.save(flush:true)
			Link link5 = new Link(linkCat:Link.PUBS, linkText:"Foreign Policy in Focus", linkHref:"http://fpif.org/")
			link5.save(flush:true)
			Link link6 = new Link(linkCat:Link.PUBS, linkText:"Labor Notes", linkHref:"http://labornotes.org/")
			link6.save(flush:true)
			Link link7 = new Link(linkCat:Link.BOOKS, linkText:"Illegal People", linkHref:"http://www.beacon.org/Illegal-People-P690.aspx")
			link7.save(flush:true)
			Link link8 = new Link(linkCat:Link.SHOWS, linkText:"Invisible No More", linkHref:"http://davidbaconrealitycheck.blogspot.com/2015/09/invisible-no-more-photography-show-by.html")
			link8.save(flush:true)
		} catch (Exception e) {
			println e
		}
	}
	
	def initStories() {
		println "initStories"
		try {
			Link story1 = new Link(linkCat:Link.STOS, linkText:"Streets of Berlin", linkHref:"http://davidbaconrealitycheck.blogspot.com/2016/12/streets-of-berlin-street-life.html")
			story1.save(flush:true)
			Link story2 = new Link(linkCat:Link.STOS, linkText:"Guadalajara in the Street", linkHref:"http://davidbaconrealitycheck.blogspot.com/2016/12/guadalajara-in-street.html")
			story2.save(flush:true)
			Link story3 = new Link(linkCat:Link.STOS, linkText:"Immigrant Communities Brace for Trump", linkHref:"http://davidbaconrealitycheck.blogspot.com/2016/11/immigrant-communities-brace-for-trump.html")
			story3.save(flush:true)
		} catch (Exception e) {
			println e
		}
	}

	def security1() {
		println "init spring-security"
		Role adminRole = Role.findByAuthority('ROLE_ADMIN')
		if (!adminRole) {
			adminRole = new Role(authority: 'ROLE_ADMIN')
			adminRole.save()
//			adminRole.save(flush: true)
		}
		Role userRole = Role.findByAuthority('ROLE_USER')
		if (!userRole) {
			userRole = new Role(authority: 'ROLE_USER')
			userRole.save()
//			userRole.save(flush: true)
		}
		User testUser = User.findByUsername('db@c0n!')
		if (!testUser) {
//			Long userid = randomUUID()
//			testUser = new User(id:userid, username:'db@c0n!', password:'password')
			testUser = new User(username:'db@c0n!', password:'password')
			testUser.save()
//			testUser.save(flush: true)
			UserRole.create(testUser, adminRole)
		}
		//assert User.count() == 1
		//assert Role.count() == 2
		//assert UserRole.count() == 1
	}
	
}
