package stomble.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

//
// Testing of essential (not all) test cases
// Is automatically run at start of the program
// Results are displayed to stdout for inspection.
//

@Configuration
public class TaskTests {
    private static final Logger log = LoggerFactory.getLogger(TaskTests.class);

	@Bean
    @Order(2)
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
    }
}
