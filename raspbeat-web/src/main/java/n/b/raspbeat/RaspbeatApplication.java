package n.b.raspbeat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("n.b.raspbeat")
public class RaspbeatApplication {

	private static Logger logger = LoggerFactory.getLogger(RaspbeatApplication.class);
	
    public static void main(String[] args) {
        
    	logger.info("Booting with " + RaspbeatApplication.class.getSimpleName());
    	
    	SpringApplication application = new SpringApplication(RaspbeatApplication.class);
        application.setShowBanner(false);
        application.run(args);
        
        logger.info("Successfully started application: " + RaspbeatApplication.class.getSimpleName());
                
    }

}