// locations to search for config files that get merged into the main config;
// config files can be ConfigSlurper scripts, Java properties files, or classes
// in the classpath in ConfigSlurper format

// grails.config.locations = [ "classpath:${appName}-config.properties",
//                             "classpath:${appName}-config.groovy",
//                             "file:${userHome}/.grails/${appName}-config.properties",
//                             "file:${userHome}/.grails/${appName}-config.groovy"]

// if (System.properties["${appName}.config.location"]) {
//    grails.config.locations << "file:" + System.properties["${appName}.config.location"]
// }

grails.project.groupId = appName // change this to alter the default package name and Maven publishing destination

// The ACCEPT header will not be used for content negotiation for user agents containing the following strings (defaults to the 4 major rendering engines)
grails.mime.disable.accept.header.userAgents = ['Gecko', 'WebKit', 'Presto', 'Trident']
grails.mime.types = [ // the first one is the default format
    all:           '*/*', // 'all' maps to '*' or the first available format in withFormat
    atom:          'application/atom+xml',
    css:           'text/css',
    csv:           'text/csv',
    form:          'application/x-www-form-urlencoded',
    html:          ['text/html','application/xhtml+xml'],
    js:            'text/javascript',
    json:          ['application/json', 'text/json'],
    multipartForm: 'multipart/form-data',
    rss:           'application/rss+xml',
    text:          'text/plain',
    hal:           ['application/hal+json','application/hal+xml'],
    xml:           ['text/xml', 'application/xml']
]

// URL Mapping Cache Max Size, defaults to 5000
//grails.urlmapping.cache.maxsize = 1000

// What URL patterns should be processed by the resources plugin
grails.resources.adhoc.patterns = ['/images/*', '/css/*', '/js/*', '/plugins/*']
grails.resources.adhoc.excludes = ['/WEB-INF/**']

// Legacy setting for codec used to encode data with ${}
grails.views.default.codec = "html"

// The default scope for controllers. May be prototype, session or singleton.
// If unspecified, controllers are prototype scoped.
grails.controllers.defaultScope = 'singleton'

// GSP settings
grails {
    views {
        gsp {
            encoding = 'UTF-8'
            htmlcodec = 'xml' // use xml escaping instead of HTML4 escaping
            codecs {
                expression = 'html' // escapes values inside ${}
                scriptlet = 'html' // escapes output from scriptlets in GSPs
                taglib = 'none' // escapes output from taglibs
                staticparts = 'none' // escapes output from static template parts
            }
        }
        // escapes all not-encoded output at final stage of outputting
        // filteringCodecForContentType.'text/html' = 'html'
    }
}

 
grails.converters.encoding = "UTF-8"
// scaffolding templates configuration
grails.scaffolding.templates.domainSuffix = 'Instance'

// Set to false to use the new Grails 1.2 JSONBuilder in the render method
grails.json.legacy.builder = false
// enabled native2ascii conversion of i18n properties files
grails.enable.native2ascii = true
// packages to include in Spring bean scanning
grails.spring.bean.packages = []
// whether to disable processing of multi part requests
grails.web.disable.multipart=false

// request parameters to mask when logging exceptions
grails.exceptionresolver.params.exclude = ['password']

// configure auto-caching of queries by default (if false you can cache individual queries with 'cache: true')
grails.hibernate.cache.queries = false

environments {
    development {
        grails.logging.jul.usebridge = true
		file.upload.directory = "C:\\fogalFiles"
		file.newFile.directory = "C:\\fogalFilesNew"
		imageUpload {
		  temporaryFile = "C:\\fogalFiles"// Path to where files will be uploaded
		}
    }
    production {
        grails.logging.jul.usebridge = false
		file.upload.directory = "C:\\fogalFiles"//"/fogalFiles"
		file.newFile.directory = "C:\\fogalFilesNew"
        // TODO: grails.serverURL = "http://www.changeme.com"
 		imageUpload {
		  temporaryFile = "C:\\fogalFiles"// Path to where files will be uploaded
		}
   }
}

// log4j configuration
log4j = {
    // Example of changing the log pattern for the default console appender:
    //
    //appenders {
    //    console name:'stdout', layout:pattern(conversionPattern: '%c{2} %m%n')
    //}
	
	root {
		info()
	}
	
	debug "grails.app"

    error  'org.codehaus.groovy.grails.web.servlet',        // controllers
           'org.codehaus.groovy.grails.web.pages',          // GSP
           'org.codehaus.groovy.grails.web.sitemesh',       // layouts
           'org.codehaus.groovy.grails.web.mapping.filter', // URL mapping
           'org.codehaus.groovy.grails.web.mapping',        // URL mapping
           'org.codehaus.groovy.grails.commons',            // core / classloading
           'org.codehaus.groovy.grails.plugins',            // plugins
           'org.codehaus.groovy.grails.orm.hibernate',      // hibernate integration
           'org.springframework',
           'org.hibernate',
           'net.sf.ehcache.hibernate'
}


// Added by the Spring Security Core plugin:
grails.plugin.springsecurity.userLookup.userDomainClassName = 'com.iii.fogal.User'
grails.plugin.springsecurity.userLookup.authorityJoinClassName = 'com.iii.fogal.UserRole'
grails.plugin.springsecurity.authority.className = 'com.iii.fogal.Role'
//grails.plugin.springsecurity.controllerAnnotations.staticRules = [
//	'/':                              ['permitAll'],
//	'/index':                         ['permitAll'],
//	'/index.gsp':                     ['permitAll'],
//	'/**/js/**':                      ['permitAll'],
//	'/**/css/**':                     ['permitAll'],
//	'/**/images/**':                  ['permitAll'],
//	'/**/favicon.ico':                ['permitAll']
//]

// spring-security
grails.plugin.springsecurity.securityConfigType = "InterceptUrlMap"
grails.plugin.springsecurity.rejectIfNoRule = true
grails.plugin.springsecurity.fii.rejectPublicInvocations = false
grails.plugin.springsecurity.logout.postOnly = false
// static rules
grails.plugin.springsecurity.controllerAnnotations.staticRules = [
	'/':					['permitAll'],
	'/index':				['permitAll'],
	'/index.gsp':			['permitAll'],
	'/assets/**':			['permitAll'],
	'/**/js/**':			['permitAll'],
	'/**/css/**':			['permitAll'],
	'/**/images/**':		['permitAll'],
	'/**/favicon.ico':		['permitAll']//,
//	'/photo/create/**':		['ROLE_ADMIN'],
//	'/photo/edit/**':		['ROLE_ADMIN'],
//	'/photo/show/**':		['permitAll'],
//	'/gallery/index':		['permitAll'],
//	'/gallery/create/**':	['ROLE_ADMIN'],
//	'/gallery/edit/**':		['ROLE_ADMIN'],
//	'/gallery/show/**':		['permitAll']
 ]
// intercept url map
grails.plugin.springsecurity.interceptUrlMap = [
	'/':								['permitAll'],
	'/index':							['permitAll'],
	'/index.gsp':						['permitAll'],
	'/**/js/**':						['permitAll'],
	'/**/css/**':						['permitAll'],
	'/**/images/**':					['permitAll'],
	'/**/favicon.ico':					['permitAll'],
	// login/out
	'/login/**':						['permitAll'],
	'/logout/**':						['permitAll'],
	// category
	'/category/index':					['permitAll'],
	'/category/create/**':				['ROLE_ADMIN'],
	'/category/delete/**':				['ROLE_ADMIN'],
	'/category/edit/**':				['ROLE_ADMIN'],
	'/category/save/**':				['ROLE_ADMIN'],
	'/category/show/**':				['permitAll'],
	'/category/lanThum/**':				['permitAll'],
	'/category/porThum/**':				['permitAll'],
	'/category/thumbnail/**':			['permitAll'],
	'/category/update/**':				['ROLE_ADMIN'],
	// photo
	'/photo/index':						['permitAll'],
	'/photo/create/**':					['ROLE_ADMIN'],
	'/photo/delete/**':					['ROLE_ADMIN'],
	'/photo/edit/**':					['ROLE_ADMIN'],
	'/photo/save/**':					['ROLE_ADMIN'],
	'/photo/show/**':					['permitAll'],
	'/photo/renderMainImage/**':		['permitAll'],
	'/photo/renderThumbnailImage/**':	['permitAll'],
	'/photo/update/**':					['ROLE_ADMIN'],
	// gallery
	'/gallery/index':					['permitAll'],
	'/gallery/create/**':				['ROLE_ADMIN'],
	'/gallery/delete/**':				['ROLE_ADMIN'],
	'/gallery/edit/**':					['ROLE_ADMIN'],
	'/gallery/galleryThum/**':			['permitAll'],
	'/gallery/save/**':					['ROLE_ADMIN'],
	'/gallery/show/**':					['permitAll'],
	'/gallery/update/**':				['ROLE_ADMIN'],
	'/gallery/uploadPhoto/**':			['ROLE_ADMIN']
 ]
//
grails.plugin.springsecurity.controllerAnnotations.staticRules = [
	'/category/index':			['permitAll'],
	'/category/show/**':		['permitAll'],
	'/photo/index':				['permitAll'],
	'/photo/show/**':			['permitAll'],
	//'/gallery/galleryThum':		['permitAll'],
	'/gallery/index':			['permitAll'],
	'/gallery/show/**':			['permitAll']
 ]

