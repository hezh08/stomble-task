package stomble.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// Loads a test database with 3 locations and 3 spaceships
// Is automatically run at start of the program

@Configuration
public class LoadTestDatabase {
    private static final Logger log = LoggerFactory.getLogger(LoadTestDatabase.class);

    Location l1 = new Location("Sydney", "Earth", 3);
    Location l2 = new Location("Elon", "Mars", 1);
    Location l3 = new Location("Atlanta", "Neptune", 2);

    @Bean
    CommandLineRunner initTestDatabase(LocationRepository locations, SpaceshipRepository spaceships) {
        return args -> {
        log.info("Preloading " + locations.save(l1));
        log.info("Preloading " + locations.save(l2));
        log.info("Preloading " + locations.save(l3));
        log.info("Preloading " + spaceships.save(new Spaceship("Avalon", "SpaceX", l1, "decommissioned")));
        log.info("Preloading " + spaceships.save(new Spaceship("Explorer", "SpaceX", l2, "operational")));
        log.info("Preloading " + spaceships.save(new Spaceship("Pioneer", "Serato", l3, "maintenance")));
        l1.increaseCurrentCapacity();
        l2.increaseCurrentCapacity();
        l3.increaseCurrentCapacity();
        log.info("Updating " + locations.save(l1));
        log.info("Updating " + locations.save(l2));
        log.info("Updating " + locations.save(l3));
        };
    }
}
