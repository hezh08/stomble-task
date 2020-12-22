package stomble.task;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

// Maps HTTP requests to backend. API Interface

@RestController
public class LocationController {

    private final LocationRepository locations;

    LocationController(LocationRepository locations) {
        this.locations = locations;
    }

    @GetMapping("/locations")
    public List<Location> getAllLocations() {
        return locations.findAll();
    }


    @GetMapping("/locations/{id}")
    public Location getLocationByIdentifier(@PathVariable Long id) {
        return locations.findById(id)
                        .orElseThrow(() -> new CustomException("location", id));
    }


    @PostMapping("/locations/add")
    public Location addLocation(
            @RequestParam(value = "city") String city, 
            @RequestParam(value = "planet") String planet,
            @RequestParam(value = "spaceportCapacity") int spaceportCapacity
                    ) {
        if (spaceportCapacity < 0) 
            return null;
    	return locations.save(new Location(city, planet, spaceportCapacity));
    }


    @DeleteMapping("/locations/remove")
    public void removeLocation(@RequestParam(value = "id") Long id) {
        try {
            locations.deleteById(id);
        } catch (Exception e) {
            throw new CustomException("Cannot delete location while spaceship is occupying" + e.getMessage());
        }
    }
}
