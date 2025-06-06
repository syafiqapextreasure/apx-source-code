package com.ppk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.client.SimpleClientHttpRequestFactory;

import javax.net.ssl.*;
import java.security.cert.X509Certificate;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

@SpringBootApplication
public class Application extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	@Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(Application.class);
    }
	    @Bean
	    public RestTemplate restTemplate() throws NoSuchAlgorithmException, KeyManagementException {
	        // Create a trust manager that does not validate certificate chains
	        TrustManager[] trustAllCerts = new TrustManager[] {
	            new X509TrustManager() {
	                public X509Certificate[] getAcceptedIssuers() {
	                    return new X509Certificate[0];
	                }
	                public void checkClientTrusted(X509Certificate[] certs, String authType) {}
	                public void checkServerTrusted(X509Certificate[] certs, String authType) {}
	            }
	        };

	        // Install the all-trusting trust manager
	        SSLContext sslContext = SSLContext.getInstance("TLS");
	        sslContext.init(null, trustAllCerts, new SecureRandom());

	        // Create an HttpClient that uses the custom SSL context
	        HttpsURLConnection.setDefaultSSLSocketFactory(sslContext.getSocketFactory());

	        // Create a hostname verifier that accepts all hostnames (use with caution)
	        HostnameVerifier allHostsValid = (hostname, session) -> true;
	        HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);

	        // Use SimpleClientHttpRequestFactory to apply the custom SSL settings
	        SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
	        // You might need to configure other properties like readTimeout or connectTimeout
	        // requestFactory.setReadTimeout(5000); 
	        // requestFactory.setConnectTimeout(5000);

	        return new RestTemplate(requestFactory);
	    }
}
