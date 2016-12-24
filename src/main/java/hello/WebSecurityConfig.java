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
				.antMatchers("/", "/home", "/homex").permitAll()
				//allow public access to one of Revenj routes - will be handled by Revenj PermissionManager
				.antMatchers("/Domain.svc/*").permitAll()
				.anyRequest().authenticated()
				.and()
				.formLogin()
				.loginPage("/login")
				.permitAll()
				.and()
				.logout()
				.permitAll();
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
