package org.odk.g1.pankuru.SecurityConfig;

import lombok.AllArgsConstructor;
import org.odk.g1.pankuru.Entity.Visitor.VisitorLogger;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@AllArgsConstructor
public class AppConfig implements WebMvcConfigurer {

    private VisitorLogger visitorLogger;

    @Override
    public void addInterceptors(@SuppressWarnings("null") InterceptorRegistry registry) {
        registry.addInterceptor(visitorLogger);
    }
}
