package stomble.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoadDatabase {
    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    Location l1 = new Location("Australia", "Earth", 3);
    Location l2 = new Location("Elon", "Mars", 2);

    @Bean
    CommandLineRunner initLocationDatabase(LocationRepository locations) {
        return args -> {
        log.info("Preloading " + locations.save(l1));
        log.info("Preloading " + locations.save(l2));
        };
    }
    
    @Bean
    CommandLineRunner initSpaceshipDatabase(SpaceshipRepository spaceships) {
        return args -> {
        log.info("Preloading " + spaceships.save(new Spaceship("Avalon", "SpaceX", l1, "decommissioned")));
        log.info("Preloading " + spaceships.save(new Spaceship("Explorer", "SpaceX", l2, "decommissioned")));
        };
    }
}
