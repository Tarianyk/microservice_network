package com.epam.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    @Qualifier("userDetailsService")
    private UserDetailsService userDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();

        http.authorizeRequests()
                    .antMatchers(HttpMethod.GET, "/{\\d+}/timelines").hasRole("ADMIN")
                    .antMatchers(HttpMethod.GET, "/{\\d+}/friends").hasRole("ADMIN")
                    .antMatchers(HttpMethod.GET, "/{\\d+}/friends/{\\d+}/timelines").hasRole("ADMIN")
                    .antMatchers("/").hasRole("USER")
                    .antMatchers("/{\\d+}/timelines").hasRole("USER")
                    .antMatchers("/{\\d+}/friends").hasRole("USER")
                    .antMatchers("/{\\d+}/friends/{\\d+}/timelines").hasRole("USER")
                .and()
                    .formLogin().loginPage("/login")
                    .usernameParameter("username")
                    .passwordParameter("password")
                    .defaultSuccessUrl("/loginsuccess")
                    .failureUrl("/loginerror");

    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }


}