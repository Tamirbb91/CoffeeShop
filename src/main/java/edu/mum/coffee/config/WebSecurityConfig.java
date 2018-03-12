package edu.mum.coffee.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.provisioning.JdbcUserDetailsManagerConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Bean
	@ConfigurationProperties(prefix = "spring.datasource")
	public DataSource dataSource() {
		return DataSourceBuilder.create().build();
	}

	@Override
    protected void configure(HttpSecurity http) throws Exception {
        http
			.csrf().disable()
            .authorizeRequests()
				.antMatchers("/errors").permitAll()
				.antMatchers("/rest/**/**").permitAll()
				.antMatchers("/admin/**").permitAll()
                .antMatchers("/", "/home", "/login", "/products", "/register").permitAll()
				.antMatchers("/user/**").hasAuthority("USER")
				.antMatchers("/**").hasRole("ADMIN")
                .anyRequest().authenticated()
                .and()
            .formLogin()
				.loginPage("/login")
            	.permitAll()
            	.and()
            .logout()
            	.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
            	.logoutSuccessUrl("/")
                .permitAll();
    }

	@Bean
	public JdbcUserDetailsManager userDetailsManager() {
		JdbcUserDetailsManager mgr = new JdbcUserDetailsManager();
		mgr.setDataSource(dataSource());
		return mgr;
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth, JdbcUserDetailsManager userDetailsManager) throws Exception {

		auth.userDetailsService(userDetailsManager);
		JdbcUserDetailsManagerConfigurer<AuthenticationManagerBuilder> conf =
				new JdbcUserDetailsManagerConfigurer<>(userDetailsManager);
		auth.apply(conf);

		auth.jdbcAuthentication().dataSource(dataSource())
				.and()
				.inMemoryAuthentication()
				.withUser("super").password("pw").roles("ADMIN")
				.and()
				.withUser("tamir").password("password").roles("USER");

	}
}