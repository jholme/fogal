package com.iii.fogal

class Link {

	String linkCat
	String linkText
	String linkHref
	
	static String OTH = "Other Links"
	static String ORGS = "Organizations"
	static String PHO = "Photography Links"
	static String PUBS = "Publications"
	static String STOS = "Stories"
	static String PHOS = "Photos"
	static List<String> linkCats = [ORGS,PUBS,PHO,OTH]
	static List<String> allCats = [ORGS,PUBS,PHO,OTH,PHOS,STOS]
	
    static constraints = {
		linkCat blank:false, nullable:false
		linkText blank:false, nullable:false
		linkHref blank:false, nullable:false, url:true
    }
	
	static mapping = {
		sort "linkText"
	}
}
