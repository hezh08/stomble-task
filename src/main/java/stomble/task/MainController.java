package stomble.task;

import java.util.List;
import java.util.Iterator;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping; 
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

    private final SpaceshipRepository spaceships;
    private final LocationRepository locations;


    MainController(SpaceshipRepository spaceships, LocationRepository locations) {
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
    

    @PostMapping("/spaceships")
    public Spaceship addSpaceship(
                @RequestParam(value = "name") String name, 
                @RequestParam(value = "model") String model, 
                @RequestParam(value = "city") String city, 
                @RequestParam(value = "planet") String planet, 
                @RequestParam(value = "status") String status
                    ) {

        Location location = getLocationByCityAndPlanet(city, planet);
        if (location == null) throw new CustomException("location", city, planet);
        
        if (! status.equals("decommissioned") && ! status.equals("maintenance") && ! status.equals("operational")) 
            throw new CustomException(status);

        Spaceship ss = new Spaceship(name, model, location, status);
        location.increaseCurrentCapacity();    
        return spaceships.save(ss);
    }

    @PutMapping("/spaceships/status/{id}")
    public Spaceship updateStatus(@RequestParam(value = "status") String status, @PathVariable Long id) {
        if (! status.equals("decommissioned") && ! status.equals("maintenance") && ! status.equals("operational")) 
            throw new CustomException(status);

        return spaceships.findById(id)
        .map(ss -> {
            ss.updateStatus(status);
            return spaceships.save(ss);
        })
        .orElseThrow(() -> new CustomException("spaceship", id));
    }

    @PutMapping("/spaceships/travel/{id}")
    public Spaceship travelToLocation(@RequestParam(value = "city") String newCity, @RequestParam(value = "planet") String newPlanet, @PathVariable Long id) {
        
        Spaceship ss = spaceships.findById(id)
                        .orElseThrow(() -> new CustomException("spaceship", id));
        if (! ss.getStatus().equals("operational")) throw new CustomException(ss.getStatus());

        Location dest = getLocationByCityAndPlanet(newCity, newPlanet);
        if (dest == null) throw new CustomException("location", newCity, newPlanet);

        if (dest.hasExtraCapacity()) {
            Location prev = getLocationByIdentifier(ss.getLocationIdentifier());

            prev.decreaseCurrentCapacity();
            dest.increaseCurrentCapacity();

            ss.setLocation(dest);
        }
        return spaceships.save(ss);
    } 

    @DeleteMapping("/spaceships/{id}")
    public void removeSpaceship(@PathVariable Long id) {
        spaceships.deleteById(id);
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


    @PostMapping("/locations")
    public Location newLocation(@RequestBody Location newLocation) {
    	return locations.save(newLocation);
    }


    @DeleteMapping("/locations/{id}")
    public void removeLocation(@PathVariable Long id) {
        locations.deleteById(id);
    }

    
    private Location getLocationByCityAndPlanet(String city, String planet) {
        Location loc = null;

        Iterator<Location> itr = locations.findAll().iterator();
        while (itr.hasNext()) {
            loc = itr.next();
            if (loc.getCity().equals(city) && loc.getPlanet().equals(planet)) {
                break;
            }
        }
        return loc;
    }
}
