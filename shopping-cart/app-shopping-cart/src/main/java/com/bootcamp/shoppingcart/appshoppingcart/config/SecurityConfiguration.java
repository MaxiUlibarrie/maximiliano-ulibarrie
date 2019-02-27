package com.bootcamp.shoppingcart.appshoppingcart.config;

import com.bootcamp.shoppingcart.appshoppingcart.service.CustomUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableWebSecurity
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

  @Autowired
  private CustomUserDetailService customUserDetailService;

  @Override
  public void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(customUserDetailService)
        .passwordEncoder(passwordEncoder());
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {

    http.csrf().disable()
        .authorizeRequests()
        .antMatchers("/v2/api-docs").permitAll()
        .antMatchers("/swagger-ui.html").permitAll()
        .antMatchers("/api/signup").anonymous()
        .antMatchers("/api/shoppingcart/**").authenticated()
        .antMatchers("/api/user/**").hasRole("ADMIN")
        .antMatchers("/api/role/**").hasRole("ADMIN")
        .antMatchers(HttpMethod.GET,"/api/product/**").authenticated()
        .antMatchers(HttpMethod.POST,"/api/product/**").hasAnyRole("EMPLOYEE","ADMIN")
        .antMatchers(HttpMethod.PUT,"/api/product/**").hasAnyRole("EMPLOYEE","ADMIN")
        .antMatchers("/api/category/**").hasAnyRole("EMPLOYEE","ADMIN")
        .and()
        .httpBasic();
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new PasswordEncoder() {
      @Override
      public String encode(CharSequence charSequence) {
        return charSequence.toString();
      }

      @Override
      public boolean matches(CharSequence charSequence, String s) {
        return charSequence.toString().equals(s);
      }
    };
  }

}
