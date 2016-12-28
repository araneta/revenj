package hello;

import org.revenj.patterns.DataContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.provisioning.InMemoryUserDetailsManagerConfigurer;
import org.springframework.security.config.annotation.authentication.configurers.provisioning.UserDetailsManagerConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import security.User;

import java.util.List;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
				.authorizeRequests()
				.antMatchers("/", "/home").permitAll()
				.anyRequest().authenticated()
				//allow logged in access to one of Revenj routes - will be handled by Revenj PermissionManager
				//while more idiomatic way to write this security configuration is to just use Spring security...
				//this way basic Revenj setup used ouside of Spring is shown
				.antMatchers("/Domain.svc/*").permitAll()
				.anyRequest().authenticated()
				.and()
				.formLogin()
				.loginPage("/login")
				.permitAll()
				.and()
				.logout()
				.permitAll();
		http.csrf().disable();
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth, DataContext context) throws Exception {
		List<User> users = context.search(User.class);
		//for simplicity setup in memory auth instead of providing alternative authentication manager
		InMemoryUserDetailsManagerConfigurer<AuthenticationManagerBuilder> inMemory =
				auth.inMemoryAuthentication();
		for (User user : users) {
			UserDetailsManagerConfigurer.UserDetailsBuilder builder =
					inMemory.withUser(user.getName()).password(new String(user.getPassword(), "UTF-8"));
			for (String role : user.getRoles()) {
				builder.roles(role);
			}
		}
	}
}
