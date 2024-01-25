package edu.sru.thangiah.group2.fall2023registrationsystem.config;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ContextConfig {
	
	//creating HTTP client
	@Bean
	public CloseableHttpClient httpClient() {
		//creating configured instance of CloseableHttpClient (apache)
	    return HttpClientBuilder.create().build();
	}
	
	//request configuration
	@Bean
	public RequestConfig requestConfig() {
		//creating default RequestConfig instance (apache)
	    return RequestConfig.custom().build();
	}
}