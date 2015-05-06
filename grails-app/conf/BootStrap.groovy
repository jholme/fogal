import com.iii.fogal.*
class BootStrap {

    def init = { servletContext ->
		environments {
			development {
				Gallery daydead = new Gallery(name:"Day of the Dead", description:"This gallery is for 'Day of the Dead' photos", path:"daydead")
				daydead.save()
				Photo photo1 = new Photo(title:"Photo 1", fileName:"photo1.jpg", description:"Photo One", location:"Oaxaca", photoDate:"2015-05-06")
				photo1.save()
				daydead.addToPhotos(photo1)
				Photo photo2 = new Photo(title:"Photo 2", fileName:"photo2.jpg", description:"Photo Two", location:"Oaxaca", photoDate:"2015-05-06")
				photo2.save()
				daydead.addToPhotos(photo2)
				Photo photo3 = new Photo(title:"Photo 3", fileName:"photo3.jpg", description:"Photo One", location:"Oaxaca", photoDate:"2015-05-06")
				photo3.save()
				daydead.addToPhotos(photo3)
				daydead.save()
				
				Gallery otherStuff = new Gallery(name:"Other Stuff", description:"This gallery is for other stuff", path:"ostuff")
				otherStuff.save()
				Photo photo4 = new Photo(title:"Photo 4", fileName:"photo4.jpg", description:"Photo One", location:"Oaxaca", photoDate:"2015-05-06")
				photo4.save()
				otherStuff.addToPhotos(photo4)
				Photo photo5 = new Photo(title:"Photo 5", fileName:"photo5.jpg", description:"Photo One", location:"Oaxaca", photoDate:"2015-05-06")
				photo5.save()
				otherStuff.addToPhotos(photo5)
				Photo photo6 = new Photo(title:"Photo 6", fileName:"photo6.jpg", description:"Photo One", location:"Oaxaca", photoDate:"2015-05-06")
				photo6.save()
				otherStuff.addToPhotos(photo6)
				otherStuff.save()
			}
		}
    }
	
    def destroy = {
    }
	
}
