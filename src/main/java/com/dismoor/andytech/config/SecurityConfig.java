package com.dismoor.andytech.config;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.dismoor.andytech.services.StockService;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

//	@Bean
//	@Override
//	protected UserDetailsService userDetailsService() {
//		List<UserDetails> users = new ArrayList<>();
//		users.add(User.withDefaultPasswordEncoder().username("andres").password("1234").roles("USER").build());
//		return new InMemoryUserDetailsManager(users);
//	}
	@Autowired
	private UserDetailsService userService;

//	@Autowired
//	private UserService uS;

	@Autowired
	StockService stockService;

	@Bean
	public AuthenticationProvider authProvider() throws IOException {
//		uS.addUser(new User("Andres", "$2y$12$Hsf4GcHCmCC7jVavhNs8T.s1SLNHCtSHTPsWZYSN41i2lFNqZka5O",
//				"amtamusic@hotmail.com", new ArrayList<>()));
//		BufferedReader br = new BufferedReader(new FileReader("stocks.csv"));
//		String line = "";
//		while ((line = br.readLine()) != null) {
//			Stock temp = new Stock();
//			String[] data = line.split(",");
//			temp.setSymbol(data[0]);
//			temp.setCompanyName(data[1]);
//			double price = new java.util.Random().nextDouble() * 500 + 500;
//			temp.setPrice(price);
//			System.out.println(temp.toString());
//			stockService.saveStock(temp);
//		}
//		br.close();
//		System.out.println("Done inserting");
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setUserDetailsService(userService);
		provider.setPasswordEncoder(new BCryptPasswordEncoder());
		return provider;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().authorizeRequests().antMatchers("/login").permitAll().antMatchers("/register").permitAll()
				.antMatchers("/register/submit").permitAll().anyRequest().authenticated().and().formLogin()
				.loginPage("/login").permitAll().and().logout().invalidateHttpSession(true).clearAuthentication(true)
				.logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/logout-successful")
				.permitAll();

	}

}
