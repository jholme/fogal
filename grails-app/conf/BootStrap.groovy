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
	
    def init = { servletContext ->
		environments {
			development {
				initCategories()
				initGalleries()
				initPhotos()
				security1()
			}
			production {
				initCategories()
				initGalleries()
				initPhotos()
				security1()
			}
		}
    }
	
    def destroy = {
    }
	
	def initCategories() {
		println "initCategories"
		Category farmworkers = new Category(name:"Farmworkers", description:"Farmworkers", path:"farmworkers")
		farmworkers.save(flush:true)
		Category immigrants = new Category(name:"Immigrants", description:"Immigrants", path:"immigrants")
		immigrants.save(flush:true)
		Category mexico = new Category(name:"Mexico", description:"Mexico", path:"mexico")
		mexico.save(flush:true)
		Category students = new Category(name:"Students", description:"Students", path:"students")
		students.save(flush:true)
		Category unions = new Category(name:"Unions", description:"Unions", path:"unions")
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
		// daydead
		galleryService.createPhotosFromDisk(['photo1.jpg','photo2.jpg','photo3.jpg'], "daydead")
		// bicycles
		galleryService.createPhotosFromDisk(['photo4.jpg'], "bicycles")
		// blancaNavidad
		galleryService.createPhotosFromDisk(['photo5.jpg'], "blancaNavidad")
		// coastalFarmworkers
		galleryService.createPhotosFromDisk(['photo6.jpg'], "coastalFarmworkers")
	}
	
	def security1() {
		// spring-security
		Role adminRole = new Role(authority: 'ROLE_ADMIN')
		adminRole.save()
//		adminRole.save(flush: true)
		Role userRole = new Role(authority: 'ROLE_USER')
		userRole.save()
//		userRole.save(flush: true)
		User testUser = new User(username: 'me', password: 'password')
		testUser.save()
//		testUser.save(flush: true)
		UserRole.create(testUser, adminRole)
		//assert User.count() == 1
		//assert Role.count() == 2
		//assert UserRole.count() == 1
	}
	
}
