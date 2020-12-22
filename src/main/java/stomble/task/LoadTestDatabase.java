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

    /* @Bean
    CommandLineRunner runTests(LocationRepository locations, SpaceshipRepository spaceships) {

        SpaceshipController sc = new SpaceshipController(spaceships, locations);
        LocationController lc = new LocationController(locations);

        return args -> {
            log.info("");
            log.info("");
            log.info("");
            log.info("Running Tests");
            log.info("==========================================================================================");
            log.info("Get all locations: ");
            log.info(lc.getAllLocations().toString());
            log.info("Get location #2: "); 
            log.info(lc.getLocationByIdentifier((long) 2).toString());
            log.info("Add a location: "); 
            log.info(lc.addLocation("Newer York", "Saturn", 1).toString());
            log.info("Remove a location: "); 
            lc.removeLocation((long) 7);
            log.info("==========================================================================================");
            log.info("Get all spaceships: "); 
            log.info(sc.getAllSpaceships().toString());
            log.info("Get spaceship #4: "); 
            log.info(sc.getSpaceshipByIdentifier((long) 4).toString());
            log.info("Add spaceship #8: "); 
            log.info(sc.addSpaceship("#8", "Mambo", "Sydney", "Earth", "maintenance").toString());
            log.info("Update status spaceship #8: "); 
            log.info(sc.updateStatus((long) 8, "operational").toString());
            log.info("Spaceship #8 travel fails when destination is full: ");
            try {
                sc.travelToLocation((long) 8, "Elon", "Mars");
            } catch (Exception e) {
                log.info(e.getMessage());
            }
            log.info("Add a location: "); 
            log.info(lc.addLocation("Newest York", "Saturn", 1).toString());
            log.info("Spaceship #8 travel succeeds when destination is not full: "); 
            log.info(sc.travelToLocation((long) 8, "Newest York", "Saturn").toString());
            log.info("==========================================================================================");
        };
    } */
}
