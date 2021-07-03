package com.nistagram.authenticationmicroservice.config;

import com.nistagram.authenticationmicroservice.security.RequestFilter;
import com.nistagram.authenticationmicroservice.security.RestAuthenticationEntryPoint;
import com.nistagram.authenticationmicroservice.security.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsServiceImpl userDetailsService;
    @Autowired
    private RequestFilter requestFilter;
    @Autowired
    private RestAuthenticationEntryPoint restAuthenticationEntryPoint;


    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }

    // Manager that does authentication for us
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                // Disable Cross Site Request Forgery security
                .csrf().disable()
                // Entry point for Unauthorized requests
                .exceptionHandling().authenticationEntryPoint(restAuthenticationEntryPoint)
                // Enable anyone to access methods of Quoted mapping (without Authorisation)
                .and().authorizeRequests()
                .antMatchers("/userCredentials/reset_password/{jwt}").permitAll()
                .antMatchers("/userCredentials/verify/{username}").permitAll()
                .antMatchers("/userCredentials/login").permitAll()
                .antMatchers("/userCredentials/getUsers").permitAll()
                .antMatchers("/userCredentials/deactivate/{username}").permitAll()
                .antMatchers("/userCredentials/login_google").permitAll()
                .antMatchers("/userCredentials/add").permitAll()
                .antMatchers("/agentRequest/add").permitAll()
                .antMatchers("/agentRequest/confirm/{id}").permitAll()
                .antMatchers("/agentRequest/reject/{id}").permitAll()
                .antMatchers("/agentRequest").permitAll()
                .antMatchers("/userCredentials/send_email/{email}").permitAll()
                .antMatchers("**/user/add").permitAll()
                .antMatchers("**/user/{username}").permitAll()
                .antMatchers("**/user/**").permitAll()
                // Every other request needs Authorisation
                .anyRequest().authenticated()
                // Enable CORS layer (WebMvcConfig class)
                .and().cors()
                // Disables sessions for spring security (We use jwt to manage session)
                .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                // Makes RequestFilter class to execute before each controller method
                .and().addFilterBefore(requestFilter, BasicAuthenticationFilter.class);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        // Ignores security configurations for Quoted mappings
        web.ignoring().antMatchers(HttpMethod.GET, "/", "/webjars/**", "/*.html", "/favicon.ico", "/**/*.html",
                "/**/*.css", "/**/*.js","/userCredentials/verify/{username}","/agentRequest","/userCredentials/getUsers","/userCredentials/send_email/{email}",
                "/userCredentials/**","/userCredentials/add","**/user/{username}");
        web.ignoring().antMatchers(HttpMethod.POST,
                "**/user/add",
                "/userCredentials/**","/userCredentials/add","/agentRequest/add","/agentRequest/confirm/{id}","/agentRequest/reject/{id}",
                "**/user/**",
                "/userCredentials/login_google/");
    }
}
