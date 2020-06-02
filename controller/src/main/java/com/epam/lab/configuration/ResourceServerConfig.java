package com.epam.lab.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    private static final String RESOURCE_ID = "news";
    private static final String SECURED_READ_SCOPE = "#oauth2.hasScope('read')";
    private static final String SECURED_CREATE_SCOPE = "#oauth2.hasScope('create')";
    private static final String SECURED_DELETE_SCOPE = "#oauth2.hasScope('delete')";
    private static final String SECURED_UPDATE_SCOPE = "#oauth2.hasScope('update')";
    private static final String SECURED_PATTERN = "/news/**";

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) {
        resources.resourceId(RESOURCE_ID);
    }
    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.requestMatchers()
                .antMatchers(SECURED_PATTERN).and().authorizeRequests()

                    .antMatchers("/news").permitAll()
                    .antMatchers("/news/count").permitAll()
                    .antMatchers("/news/criteriaCount").permitAll()
                    .antMatchers(HttpMethod.POST, "/users").permitAll()
                    .antMatchers(HttpMethod.GET, "/oauth/authorize**").permitAll()
                    .antMatchers(HttpMethod.POST).access(SECURED_CREATE_SCOPE)
                    .antMatchers(HttpMethod.DELETE).access(SECURED_DELETE_SCOPE)
                    .antMatchers(HttpMethod.PUT).access(SECURED_UPDATE_SCOPE)
                    .antMatchers(HttpMethod.GET).access(SECURED_READ_SCOPE);
    }
}