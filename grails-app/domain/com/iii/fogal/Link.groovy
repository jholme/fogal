package com.iii.fogal

class Link {

	String linkCat
	String linkText
	String linkHref
	
	static public final String BOOKS = "Books"
	static public final String NEWS = "News"
	static public final String SHOS = "Shows"
	static public final String OTH = "Other Links"
	static public final String ORGS = "Organizations"
	static public final String PHO = "Photography Links"
	static public final String PUBS = "Publications"
	static public final String STOS = "Stories"
	static public final String PHOS = "Photos"
	static public final List<String> linkCats = [BOOKS,NEWS,SHOS,ORGS,PUBS,PHO,OTH]
	static public final List<String> allCats = [BOOKS,NEWS,SHOS,ORGS,PUBS,PHO,OTH,PHOS,STOS]
	
    static constraints = {
		linkCat blank:false, nullable:false
		linkText blank:false, nullable:false
		linkHref blank:false, nullable:false, url:true
    }
	
	static mapping = {
		sort "linkText"
	}
}
