package com.mkyong.config;

import com.mkyong.services.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    CustomUserDetailsService customUserDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/authenticated/**").authenticated().and().formLogin().defaultSuccessUrl("/home").loginPage("/login").usernameParameter("username").permitAll().and().logout().logoutUrl("/logout").logoutSuccessUrl("/login").permitAll();
        //http.authorizeRequests().antMatchers("**/css/**", "**/js/**", "**/images/**").permitAll();
        //http.authorizeRequests().antMatchers("/login","/webjars/**", "/css/**", "/images/**","/js/**").permitAll();
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        /*ShaPasswordEncoder encoder = new ShaPasswordEncoder();
        auth.userDetailsService(customUserDetailsService).passwordEncoder(encoder);*/
        //auth.inMemoryAuthentication().withUser("user")

        auth.userDetailsService(customUserDetailsService).passwordEncoder(new BCryptPasswordEncoder());
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/authenticated/webjars/bootstrap/4.2.1/css/**", "/authenticated/webjars/bootstrap/4.2.1/js/**", "/authenticated/webjars/bootstrap/4.2.1/images/**");
    }

    @Bean
    public BCryptPasswordEncoder encodePWD(){
        return new BCryptPasswordEncoder();
    }
}
