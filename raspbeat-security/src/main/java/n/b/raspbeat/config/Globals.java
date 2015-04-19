package n.b.raspbeat.config;

public interface Globals {

	/**
	 * the role assigned to a valid user
	 */
	String VALID_USER_ROLE = "ROLE_GOOGLE_USER_KNOWN";
	
	/**
	 * name of the role used by spring-security
	 * ROLE_ prefix is appended by spring
	 */
	String SPRING_USER_ROLE = "GOOGLE_USER_KNOWN";
}
