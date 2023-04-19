package com.springcloud.sbsuite.eurekaserver.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Instead create public class SecurityConfig extends WebSecurityConfigurerAdapter
 *   - [How to fix error of WebSecurityConfigurerAdapter when upgrade to Spring Boot 3.0.0?](https://stackoverflow.com/questions/74666596/how-to-fix-error-of-websecurityconfigureradapter-when-upgrade-to-spring-boot-3-0)
 *     - WebSecurityConfigurerAdapter is deprecated and should use component-based security configuration.
 *     You'll have to create a SecurityFilterChain bean for HTTPSecurity and shouldn't extend WebSecurityConfigurerAdapter as other answer suggested.
 *     Please refer https://spring.io/blog/2022/02/21/spring-security-without-the-websecurityconfigureradapter for more details.
 *
 */
@Configuration
public class SecurityConfig{

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .anyRequest().authenticated()
                .and()
                .httpBasic();
        return http.build();
    }
}
