dataSource {
    pooled = true
    jmxExport = true
//    driverClassName = "org.h2.Driver"
//    username = "sa"
//    password = ""
    driverClassName = "org.postgresql.Driver"
    username = "fogal"
    password = "Sm0!3nsk@y@1941"
	hibernate.default_schema = "fogal"
}
hibernate {
    cache.use_second_level_cache = false
    cache.use_query_cache = false
    //cache.region.factory_class = 'net.sf.ehcache.hibernate.EhCacheRegionFactory' // Hibernate 3
    //cache.region.factory_class = 'org.hibernate.cache.ehcache.EhCacheRegionFactory' // Hibernate 4 *
	//cache.region.factory_class = 'grails.plugin.cache.ehcache.hibernate.BeanEhcacheRegionFactory4'
}

// environment specific settings
environments {
    development {
        dataSource {
//            dbCreate = "create-drop" // one of 'create', 'create-drop', 'update', 'validate', ''
//			println "dbCreate: ${dbCreate}"
//            url = "jdbc:h2:mem:devDb;MVCC=TRUE;LOCK_TIMEOUT=10000;DB_CLOSE_ON_EXIT=FALSE"//;INIT=CREATE SCHEMA IF NOT EXISTS
            dbCreate = "update" // one of 'create', 'create-drop', 'update', 'validate', ''
			println "dbCreate: ${dbCreate}"
            url = "jdbc:postgresql://localhost:5432/postgres"
            properties {
               // Documentation for Tomcat JDBC Pool
               // http://tomcat.apache.org/tomcat-7.0-doc/jdbc-pool.html#Common_Attributes
               // https://tomcat.apache.org/tomcat-7.0-doc/api/org/apache/tomcat/jdbc/pool/PoolConfiguration.html
               jmxEnabled = true
               initialSize = 5
               maxActive = 50
               minIdle = 5
               maxIdle = 25
               maxWait = 10000
               maxAge = 10 * 60000
               timeBetweenEvictionRunsMillis = 5000
               minEvictableIdleTimeMillis = 60000
               validationQuery = "SELECT 1"
               validationQueryTimeout = 3
               validationInterval = 15000
               testOnBorrow = true
               testWhileIdle = true
               testOnReturn = false
               ignoreExceptionOnPreLoad = true
               // http://tomcat.apache.org/tomcat-7.0-doc/jdbc-pool.html#JDBC_interceptors
               jdbcInterceptors = "ConnectionState;StatementCache(max=200)"
               defaultTransactionIsolation = java.sql.Connection.TRANSACTION_READ_COMMITTED // safe default
               // controls for leaked connections 
               abandonWhenPercentageFull = 100 // settings are active only when pool is full
               removeAbandonedTimeout = 120000
               removeAbandoned = true
               // use JMX console to change this setting at runtime
               logAbandoned = false // causes stacktrace recording overhead, use only for debugging
            }
        }
    }
    test {
        dataSource {
            dbCreate = "update"
            url = "jdbc:h2:mem:testDb;MVCC=TRUE;LOCK_TIMEOUT=10000;DB_CLOSE_ON_EXIT=FALSE"
        }
    }
    production {
        dataSource {
//            dbCreate = "create-drop" // one of 'create', 'create-drop', 'update', 'validate', ''
//			println "dbCreate: ${dbCreate}"
//            url = "jdbc:h2:mem:devDb;MVCC=TRUE;LOCK_TIMEOUT=10000;DB_CLOSE_ON_EXIT=FALSE"//;INIT=CREATE SCHEMA IF NOT EXISTS
            dbCreate = "update" // one of 'create', 'create-drop', 'update', 'validate', ''
			println "dbCreate: ${dbCreate}"
            url = "jdbc:postgresql://localhost:5432/postgres"
            properties {
               // Documentation for Tomcat JDBC Pool
               // http://tomcat.apache.org/tomcat-7.0-doc/jdbc-pool.html#Common_Attributes
               // https://tomcat.apache.org/tomcat-7.0-doc/api/org/apache/tomcat/jdbc/pool/PoolConfiguration.html
               jmxEnabled = true
               initialSize = 5
               maxActive = 50
               minIdle = 5
               maxIdle = 25
               maxWait = 10000
               maxAge = 10 * 60000
               timeBetweenEvictionRunsMillis = 5000
               minEvictableIdleTimeMillis = 60000
               validationQuery = "SELECT 1"
               validationQueryTimeout = 3
               validationInterval = 15000
               testOnBorrow = true
               testWhileIdle = true
               testOnReturn = false
               ignoreExceptionOnPreLoad = true
               // http://tomcat.apache.org/tomcat-7.0-doc/jdbc-pool.html#JDBC_interceptors
               jdbcInterceptors = "ConnectionState;StatementCache(max=200)"
               defaultTransactionIsolation = java.sql.Connection.TRANSACTION_READ_COMMITTED // safe default
               // controls for leaked connections 
               abandonWhenPercentageFull = 100 // settings are active only when pool is full
               removeAbandonedTimeout = 120000
               removeAbandoned = true
               // use JMX console to change this setting at runtime
               logAbandoned = false // causes stacktrace recording overhead, use only for debugging
            }
        }
    }
}
