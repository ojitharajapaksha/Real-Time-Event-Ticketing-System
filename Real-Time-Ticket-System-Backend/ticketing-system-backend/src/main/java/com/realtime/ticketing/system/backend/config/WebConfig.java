// Enabling CORS in the backend
package com.realtime.ticketing.system.backend.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    // Configuring CORS mapping
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // Allow CORS for all api endpoints
        registry.addMapping("/api/**")
                // Allow request from the React frontend
                .allowedOrigins("http://localhost:3000")
                // Specify the HTTP methods
                .allowedMethods("GET", "POST", "PUT", "DELETE")
                // Allow cookies and other credentials
                .allowCredentials(true);
    }
}
