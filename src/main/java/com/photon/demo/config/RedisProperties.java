package com.photon.demo.config;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "spring.redis", ignoreUnknownFields = false)
public class RedisProperties {

    private static final String DEFAULT_PORT = "6379";

    private String host;

    private String port;

    private int database ;

    private String password;

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getPort() {
        if (this.port == null) {
            return DEFAULT_PORT;
        }
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public int getDatabase() {
        return database;
    }

    public void setDatabase(int database) {
        this.database = database;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
