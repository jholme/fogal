grails.servlet.version = "3.0" // Change depending on target container compliance (2.5 or 3.0)
grails.project.class.dir = "target/classes"
grails.project.test.class.dir = "target/test-classes"
grails.project.test.reports.dir = "target/test-reports"
grails.project.work.dir = "target/work"
grails.project.target.level = 1.7
grails.project.source.level = 1.7
//grails.project.war.file = "target/${appName}-${appVersion}.war"

grails.project.fork = [
    // configure settings for compilation JVM, note that if you alter the Groovy version forked compilation is required
    //  compile: [maxMemory: 256, minMemory: 64, debug: false, maxPerm: 256, daemon:true],

    // configure settings for the test-app JVM, uses the daemon by default
    test: [maxMemory: 768, minMemory: 64, debug: false, maxPerm: 256, daemon:true],
    // configure settings for the run-app JVM
    run: [maxMemory: 768, minMemory: 64, debug: false, maxPerm: 256, forkReserve:false],
    // configure settings for the run-war JVM
    war: [maxMemory: 768, minMemory: 64, debug: false, maxPerm: 256, forkReserve:false],
    // configure settings for the Console UI JVM
    console: [maxMemory: 768, minMemory: 64, debug: false, maxPerm: 256]
]

grails.project.dependency.resolver = "maven" // or ivy
grails.project.dependency.resolution = {
    // inherit Grails' default dependencies
    inherits("global") {
        // specify dependency exclusions here; for example, uncomment this to disable ehcache:
        excludes 'ehcache', 'ehcache-core'
    }
    log "error" // log level of Ivy resolver, either 'error', 'warn', 'info', 'debug' or 'verbose'
    checksums true // Whether to verify checksums on resolve
    legacyResolve false // whether to do a secondary resolve on plugin installation, not advised and here for backwards compatibility

    repositories {
        inherits true // Whether to inherit repository definitions from plugins

        grailsPlugins()
        grailsHome()
        mavenLocal()
        grailsCentral()
        mavenCentral()
		mavenRepo 'http://repo.spring.io/milestone'
        // uncomment these (or add new ones) to enable remote dependency resolution from public Maven repositories
        //mavenRepo "http://repository.codehaus.org"
        //mavenRepo "http://download.java.net/maven/2/"
        //mavenRepo "http://repository.jboss.com/maven2/"
    }

    dependencies {
        // specify dependencies here under either 'build', 'compile', 'runtime', 'test' or 'provided' scopes e.g.
        // runtime 'mysql:mysql-connector-java:5.1.27'
        // runtime 'org.postgresql:postgresql:9.3-1100-jdbc41'
		compile 'org.imgscalr:imgscalr-lib:4.2' // supports bootstrap-file-upload plugin
		compile 'com.drewnoakes:metadata-extractor:2.9.1'
		compile 'com.adobe.xmp:xmpcore:5.1.2'
		compile 'commons-io:commons-io:2.4'
//		// tika
//		runtime ('org.apache.tika:tika-core:1.14') {
//			excludes: 'ehcache'
//			excludes: 'ehcache-core'
//			export = false
//		}
//		runtime ('org.apache.tika:tika-parsers:1.14') {
//			excludes: 'ehcache'
//			excludes: 'ehcache-core'
//			export = false
//		}
		runtime 'org.postgresql:postgresql:9.4.1212'
    }

    plugins {
        // plugins for the build system only
//        build ":tomcat:7.0.50.1"
//        build ":tomcat:7.0.55.2"
        build ":tomcat:8.0.33"
		build ":release:3.1.1"
		build ":rest-client-builder:2.1.1" // {export = false}

        // plugins for the compile step
//        compile ":scaffolding:2.0.2"
//        compile ':cache:1.1.1'
        compile ":scaffolding:2.1.2"
        //compile ':cache:1.1.8'
		//compile ':cache-ehcache:1.0.5'
		compile ':spring-security-core:2.0-RC5'
		compile ':asset-pipeline:2.1.5'
		
        // plugins needed at runtime but not for compilation
//        runtime ":hibernate:3.6.10.8" // or ":hibernate4:4.3.1.1"
        runtime (":hibernate4:4.3.8.1") { // or ":hibernate4:4.3.1.1"
			//excludes 'ehcache'
			//excludes 'ehcache-core'
			//excludes 'hibernate-ehcache'
			//export = false
        }
        runtime(":database-migration:1.4.0") {
			excludes 'ehcache'
			excludes 'ehcache-core'
		}
        runtime ":jquery:1.11.1"
    }
}
