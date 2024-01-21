package edu.sru.thangiah.group2.fall2023registrationsystem.config.tests;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import edu.sru.thangiah.group2.fall2023registrationsystem.config.ContextConfig;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.CloseableHttpClient;

public class ContextConfigTest {

    @InjectMocks
    private ContextConfig contextConfig;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testHttpClient() {

        CloseableHttpClient httpClient = contextConfig.httpClient();


        assertNotNull(httpClient);
       
    }

    @Test
    public void testRequestConfig() {

        RequestConfig requestConfig = contextConfig.requestConfig();

        assertNotNull(requestConfig);
        
    }

}
