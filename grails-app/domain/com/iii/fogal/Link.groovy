package com.iii.fogal

class Link {

	String groupName
	String linkText
	String linkHref
	
	static String OTH = "Other"
	static String ORGS = "Organizations"
	static String PHO = "Photography"
	static String PUBS = "Publications"
	static List<String> groupNames = [ORGS,PUBS,PHO,OTH]

    static constraints = {
		groupName blank:false, nullable:false
		linkText blank:false, nullable:false
		linkHref blank:false, nullable:false, url:true
    }
	
	static mapping = {
		sort "linkText"
	}
}
