dataSource {
    pooled = true
}
hibernate {
    cache.use_second_level_cache = true
    cache.use_query_cache = false
//    cache.region.factory_class = 'net.sf.ehcache.hibernate.EhCacheRegionFactory' // Hibernate 3
    cache.region.factory_class = 'grails.plugin.cache.ehcache.hibernate.BeanEhcacheRegionFactory4' // Hibernate 4
    singleSession = true // configure OSIV singleSession mode
    flush.mode = 'manual' // OSIV session flush mode outside of transactional context
}
grails {
    /*
    mongo {
        databaseName = "pantaudb" // the default database name
        host = "localhost" // the host to connect to
        port = 27017 // the port to connect to
        //username = ".." // the username to connect with
        //password = ".." // the password to connect with
        stateless = false // whether to use stateless sessions by default

// Alternatively, using 'replicaSet' or 'connectionString' // replicaSet = [ "localhost:27017", "localhost:27018"] // connectionString = "mongodb://localhost/mydb"

        options {
            connectionsPerHost = 10 // The maximum number of connections allowed per host
            threadsAllowedToBlockForConnectionMultiplier = 5
            maxWaitTime = 120000 // Max wait time of a blocking thread for a connection.
            connectTimeout = 0 // The connect timeout in milliseconds. 0 == infinite
            socketTimeout = 0 // The socket timeout. 0 == infinite
            socketKeepAlive = false // Whether or not to have socket keep alive turned on
            writeNumber = 0 // This specifies the number of servers to wait for on the write operation
            writeTimeout = 0 // The timeout for write operations in milliseconds
            writeFsync = false // Whether or not to fsync
            autoConnectRetry = false // Whether or not the system retries automatically on a failed connect
            maxAutoConnectRetryTime = 0 // The maximum amount of time in millisecons to spend retrying
            slaveOk = false // Specifies if the driver is allowed to read from secondaries or slaves
            ssl = false // Specifies if the driver should use an SSL connection to Mongo
            // sslSocketFactory = … // Specifies the SSLSocketFactory to use for creating SSL connections
        }
    }*/
}
