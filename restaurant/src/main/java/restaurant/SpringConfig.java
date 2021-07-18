package restaurant;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan("restaurant")
@PropertySource("classpath:restaurant.properties")
public class SpringConfig {

}
