package n.b.raspbeat.web.controller;

import n.b.raspbeat.util.Fancy;

import org.pac4j.oauth.profile.google2.Google2Profile;
import org.pac4j.springframework.security.authentication.ClientAuthenticationToken;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {

    @RequestMapping("/")
    public String index() {

    	ClientAuthenticationToken token = (ClientAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
    	// user profile
    	Google2Profile userProfile = (Google2Profile)token.getUserProfile();
    	
        return new Fancy("Hello World! " + userProfile.getDisplayName()).getMessage();
    }
    
    
    @Secured("ROLE_GOOGLE_USER_KNOWN")	
    @RequestMapping("/foo")
    public String foo() {
    	return "foo";
    }
}