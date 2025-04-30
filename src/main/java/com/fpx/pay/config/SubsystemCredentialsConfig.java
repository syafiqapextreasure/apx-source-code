package com.fpx.pay.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import java.util.HashMap;
import java.util.Map;
import lombok.Data;

@Data
@Configuration
@ConfigurationProperties(prefix = "subsystem")
public class SubsystemCredentialsConfig {
    private Map<String, Credentials> credentials = new HashMap<>();

    @Data
    public static class Credentials {
        private String username;
        private String password;
    }

    public boolean validateCredentials(String subsysId, String password) {
        Credentials creds = credentials.get(subsysId.toLowerCase());
        return creds != null && creds.getPassword().equals(password);
    }
} 