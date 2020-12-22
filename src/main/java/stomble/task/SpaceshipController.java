package stomble.task;

import java.util.List;
import java.util.Iterator;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping; 
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

// Maps HTTP requests to backend. API Interface

@RestController
public class SpaceshipController {

    private final SpaceshipRepository spaceships;
    private final LocationRepository locations;


    SpaceshipController(SpaceshipRepository spaceships, LocationRepository locations) {
        this.spaceships = spaceships;
        this.locations = locations;
    }    


    @GetMapping("/spaceships")
	public List<Spaceship> getAllSpaceships() {
        return spaceships.findAll();
    }


    @GetMapping("/spaceships/{id}")
    public Spaceship getSpaceshipByIdentifier(@PathVariable Long id) {
        return spaceships.findById(id)
        .orElseThrow(() -> new CustomException("spaceship", id));
    }
    

    @PostMapping("/spaceships/add")
    public Spaceship addSpaceship(
                @RequestParam(value = "name") String name, 
                @RequestParam(value = "model") String model, 
                @RequestParam(value = "city") String city, 
                @RequestParam(value = "planet") String planet, 
                @RequestParam(value = "status") String status
                    ) {

        Location location = getLocationByCityAndPlanet(city, planet);
        if (location == null) 
            throw new CustomException("location", city, planet);
        
        if (! status.equals("decommissioned") && ! status.equals("maintenance") && ! status.equals("operational")) 
            throw new CustomException("Invalid input for status");

        Spaceship ss = new Spaceship(name, model, location, status);
        location.increaseCurrentCapacity();    
        return spaceships.save(ss);
    }

    @PutMapping("/spaceships/update-status")
    public Spaceship updateStatus(
                @RequestParam(value = "id") Long id,
                @RequestParam(value = "status") String status
                    ) {
        if (! status.equals("decommissioned") && ! status.equals("maintenance") && ! status.equals("operational")) 
            throw new CustomException("Invalid input for status");

        return spaceships.findById(id)
        .map(ss -> {
            ss.updateStatus(status);
            return spaceships.save(ss);
        })
        .orElseThrow(() -> new CustomException("spaceship", id));
    }

    @PutMapping("/spaceships/travel")
    public Spaceship travelToLocation(
                @RequestParam(value = "id") Long id,
                @RequestParam(value = "city") String city, 
                @RequestParam(value = "planet") String planet
                    ) {
        
        Spaceship ss = spaceships.findById(id)
                                 .orElseThrow(() -> new CustomException("spaceship", id));
        if (! ss.getStatus().equals("operational")) 
            throw new CustomException("Status must be operational");

        Location dest = getLocationByCityAndPlanet(city, planet);
        if (dest == null) 
            throw new CustomException("location", city, planet);

        if (dest.hasExtraCapacity()) {
            Location prev = getLocationByID(ss.getLocationIdentifier());

            prev.decreaseCurrentCapacity();
            dest.increaseCurrentCapacity();

            ss.setLocation(dest);
        } else {
            throw new CustomException("Capacity is full. Current capacity: " + dest.getCurrentCapacity() + " Spaceport capacity: " + dest.getSpaceportCapacity());
        }
        return spaceships.save(ss);
    } 

    @DeleteMapping("/spaceships/remove")
    public void removeSpaceship(@RequestParam(value = "id") Long id) {
        try {
            spaceships.deleteById(id);
        } catch (Exception e) {
            throw new CustomException(e.getMessage());
        }
    }


    // Helper function

    private Location getLocationByID(Long id) {
        return locations.findById(id)
        .orElseThrow(() -> new CustomException("location", id));
    }
    
    private Location getLocationByCityAndPlanet(String city, String planet) {
        Location loc = null;

        Iterator<Location> itr = locations.findAll().iterator();
        while (itr.hasNext()) {
            Location l = itr.next();
            if (l.getCity().equals(city) && l.getPlanet().equals(planet)) {
                loc = l;
            }
        }
        return loc;
    }
}
