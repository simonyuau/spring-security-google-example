package n.b.raspbeat.config;

import java.util.ArrayList;
import java.util.List;

import org.pac4j.core.authorization.AuthorizationGenerator;
import org.pac4j.core.client.Clients;
import org.pac4j.oauth.client.Google2Client;
import org.pac4j.oauth.profile.google2.Google2Profile;
import org.pac4j.springframework.security.authentication.ClientAuthenticationProvider;
import org.pac4j.springframework.security.web.ClientAuthenticationEntryPoint;
import org.pac4j.springframework.security.web.ClientAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.web.authentication.AnonymousAuthenticationFilter;

@Configuration
@EnableWebMvcSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter implements Globals {
	
	@Value("${app.check.route.path}")
	private String securedPath;
	
	@Value("${google.callback.path}")
	private String authPath;
	
	@Autowired
	private Clients clients;

	@Autowired
	private Google2Client googleClient;
	
	@Autowired
	private AuthorizationGenerator<Google2Profile> authGenerator;
		
	@Override
	protected void configure(final HttpSecurity http) throws Exception {
		final ClientAuthenticationFilter clientFilter = new ClientAuthenticationFilter(
				authPath);

		clientFilter.setClients(clients);
		clientFilter.setAuthenticationManager(authenticationManager());
		final ClientAuthenticationEntryPoint googleEntryPoint = new ClientAuthenticationEntryPoint();
		
		// specific authorization implementation
		List<AuthorizationGenerator<Google2Profile>> authGenerators = new ArrayList<>();
		authGenerators.add(authGenerator);
		googleClient.setAuthorizationGenerators(authGenerators);
		googleEntryPoint.setClient(googleClient);
		
		http
			.authorizeRequests()
				.antMatchers(HttpMethod.GET, "/public/**")
					.permitAll()
				.antMatchers(securedPath)
					.hasRole(SPRING_USER_ROLE)
				.and()
				.addFilterBefore(clientFilter, AnonymousAuthenticationFilter.class)
				.exceptionHandling().authenticationEntryPoint(googleEntryPoint)
				;
	}

	@Override
	protected void configure(final AuthenticationManagerBuilder auth)
			throws Exception {
		final ClientAuthenticationProvider clientProvider = new ClientAuthenticationProvider();
		clientProvider.setClients(clients);
		auth.authenticationProvider(clientProvider);
	}
	
	
}
