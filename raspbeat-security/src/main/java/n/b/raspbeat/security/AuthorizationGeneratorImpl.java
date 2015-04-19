package n.b.raspbeat.security;

import n.b.raspbeat.config.Globals;

import org.pac4j.core.authorization.AuthorizationGenerator;
import org.pac4j.oauth.profile.google2.Google2Profile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class AuthorizationGeneratorImpl implements AuthorizationGenerator<Google2Profile>, Globals {

	private static Logger logger = LoggerFactory.getLogger(AuthorizationGeneratorImpl.class);

	@Value("${app.valid.user}")
	private String checkForUser;
	
	@Override
	public void generate(Google2Profile profile) {
		logger.debug("Authorization - check if this is a valid user {}", profile);
		
		if(profile.getEmail().equals(checkForUser)) {
			profile.addRole(VALID_USER_ROLE);
		}
	}

	

}
