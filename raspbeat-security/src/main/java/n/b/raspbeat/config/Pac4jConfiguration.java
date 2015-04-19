package n.b.raspbeat.config;

import org.pac4j.core.client.Clients;
import org.pac4j.oauth.client.Google2Client;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Pac4jConfiguration {

  @Value("${google.client.id}")
  private String key;

  @Value("${google.client.secret}")
  private String secret;

  @Value("${google.callback.url}")
  private String callbackUrl;

  @Bean(initMethod = "init")
  public Clients clients() {
    return new Clients(callbackUrl, googleClient());
  }

  @Bean
  public Google2Client googleClient() {
    return new Google2Client(key, secret);
  }
}