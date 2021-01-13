package com.example.quanlybanhang.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.example.quanlybanhang.service.UserService;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserService userService;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		http
		.authorizeRequests()
        .antMatchers(
        	"/trang-chu/**",
        	"/danh-sach-san-pham/**",
        	"/san-pham/**",
        	"/tin-tuc/**",
        	"/tin-tuc-one/**",
        	"/product-one/**",
        	"/home-admin",
        	"/admin/danhsach/products/**",
        	"/admin/danhsach/tin-tuc/**",
        	"/admin/danhsach/productnew/**",
        	"/product-Delete/**",
            "/registration/**",
            "/images/**",
            "/static/**",
            "/admin/**",
            "/user/**",
            "/theme/**",
            "/webjars/**").permitAll()
        .anyRequest().authenticated()
        .and()
        .formLogin()
        .loginPage("/login")
        .permitAll()
        .and()
        .logout()
        .invalidateHttpSession(true)
        .clearAuthentication(true)
        .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
        .logoutSuccessUrl("/login?logout")
        .permitAll();
	}
	
//	 @Autowired
//	    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//	        auth
//	            .inMemoryAuthentication()
//	                .withUser("user").password("password").roles("USER").and()
//	                .withUser("user").password("password").roles("USER", "ADMIN");
//	    }
	
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	 @Bean
	    public DaoAuthenticationProvider authenticationProvider() {
	        DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
	        auth.setUserDetailsService(userService);
	        auth.setPasswordEncoder(passwordEncoder());
	        return auth;
	    }

	    @Override
	    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	        auth.authenticationProvider(authenticationProvider());
	    }
}
