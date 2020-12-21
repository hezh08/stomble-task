package stomble.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoadTestDatabase {
    private static final Logger log = LoggerFactory.getLogger(LoadTestDatabase.class);

    Location l1 = new Location("Sydney", "Earth", 3);
    Location l2 = new Location("Elon", "Mars", 1);

    @Bean
    CommandLineRunner initTestDatabase(LocationRepository locations, SpaceshipRepository spaceships) {
        return args -> {
        log.info("Preloading " + locations.save(l1));
        log.info("Preloading " + locations.save(l2));
        log.info("Preloading " + spaceships.save(new Spaceship("Avalon", "SpaceX", l1, "decommissioned")));
        log.info("Preloading " + spaceships.save(new Spaceship("Explorer", "SpaceX", l2, "operational")));
        l1.increaseCurrentCapacity();
        l2.increaseCurrentCapacity();
        log.info("Updating " + locations.save(l1));
        log.info("Updating " + locations.save(l2));
        };
    } 
}