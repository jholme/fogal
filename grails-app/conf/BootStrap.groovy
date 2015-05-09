import com.iii.fogal.*
class BootStrap {

    def init = { servletContext ->
		environments {
			development {
				
				// default gallery and photos (1)
				Gallery daydead = new Gallery(name:"Day of the Dead", description:"This gallery is for 'Day of the Dead' photos", path:"daydead")
				Photo photo1 = new Photo(title:"Photo 1", originalFilename:"photo1.jpg", thumbnailFilename:"photo1_thumbnail.jpg", description:"Photo One", location:"Oaxaca", photoDate:"2015-05-06", fileSize:0)
				photo1.save(flush:true)
				daydead.addToPhotos(photo1)
				Photo photo2 = new Photo(title:"Photo 2", originalFilename:"photo2.jpg", thumbnailFilename:"photo2_thumbnail.jpg", description:"Photo Two", location:"Oaxaca", photoDate:"2015-05-06", fileSize:0)
				photo2.save(flush:true)
				daydead.addToPhotos(photo2)
				Photo photo3 = new Photo(title:"Photo 3", originalFilename:"photo3.jpg", thumbnailFilename:"photo3_thumbnail.jpg", description:"Photo One", location:"Oaxaca", photoDate:"2015-05-06", fileSize:0)
				photo3.save(flush:true)
				daydead.addToPhotos(photo3)
				daydead.save(flush:true)
				
				// default gallery and photos (2)
				Gallery otherStuff = new Gallery(name:"Other Stuff", description:"This gallery is for other stuff", path:"ostuff")
				Photo photo4 = new Photo(title:"Photo 4", originalFilename:"photo4.jpg", thumbnailFilename:"photo4_thumbnail.jpg", description:"Photo One", location:"Oaxaca", photoDate:"2015-05-06", fileSize:0)
				photo4.save(flush:true)
				otherStuff.addToPhotos(photo4)
				Photo photo5 = new Photo(title:"Photo 5", originalFilename:"photo5.jpg", thumbnailFilename:"photo5_thumbnail.jpg", description:"Photo One", location:"Oaxaca", photoDate:"2015-05-06", fileSize:0)
				photo5.save(flush:true)
				otherStuff.addToPhotos(photo5)
				Photo photo6 = new Photo(title:"Photo 6", originalFilename:"photo6.jpg", thumbnailFilename:"photo6_thumbnail.jpg", description:"Photo One", location:"Oaxaca", photoDate:"2015-05-06", fileSize:0)
				photo6.save(flush:true)
				otherStuff.addToPhotos(photo6)
				otherStuff.save(flush:true)
				
				// spring-security
				def adminRole = new Role(authority: 'ROLE_ADMIN').save(flush: true)
				def userRole = new Role(authority: 'ROLE_USER').save(flush: true)
				def testUser = new User(username: 'me', password: 'password')
				testUser.save(flush: true)
				UserRole.create testUser, adminRole, true
				assert User.count() == 1
				assert Role.count() == 2
				assert UserRole.count() == 1
		  
			}
		}
    }
	
    def destroy = {
    }
	
}
