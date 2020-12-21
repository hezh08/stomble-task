package stomble.task;

import java.util.List;
import java.util.Iterator;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LogisticsSystem {

    private final SpaceshipRepository spaceships;
    private final LocationRepository locations;

    LogisticsSystem(SpaceshipRepository spaceships, LocationRepository locations) {
        this.spaceships = spaceships;
        this.locations = locations;
    }    

    @GetMapping("/spaceships")
	public List<Spaceship> getAllSpaceships() {
        return spaceships.findAll();
    }
    
    @PostMapping("/spaceships")
    public void addSpaceship(@RequestParam(value = "name") String name, 
                @RequestParam(value = "model") String model, 
                @RequestParam(value = "city") String city, 
                @RequestParam(value = "planet") String planet, 
                @RequestParam(value = "status") String status) {

        Location location = getLocationByCityAndPlanet(city, planet);
        if (location == null) return;

        Spaceship ss = new Spaceship(name, model, location, status);        
        spaceships.save(ss);
    }

    @DeleteMapping("/spaceships/{id}")
    public void removeSpaceship(@PathVariable Long id) {
        spaceships.deleteById(id);
    }


    @GetMapping("/locations")
    public List<Location> getAllLocations() {
        return locations.findAll();
    }

    @PostMapping("/locations")
    public Location newLocation(@RequestBody Location newLocation) {
    	return locations.save(newLocation);
    }

    @DeleteMapping("/locations/{id}")
    public void removeLocation(@PathVariable Long id) {
        locations.deleteById(id);
    }

    
    /* public void moveSpaceship(Long spaceship_id, Long destination_id) {
        Spaceship ss = getSpaceshipByIdentifier(spaceship_id);
        if (ss == null) return;

        if (! ss.getStatus().equals("operational")) return;

        Location dest = getLocationByIdentifier(destination_id);

        if (dest.addVisitor(ss)) {
            Location prev = getLocationByIdentifier(ss.getLocationIdentifier());

            prev.removeVisitor(ss);

            ss.setLocation(dest);
        }
    } */

    /* @GetMapping("/spaceships/{id}")
    public Spaceship getSpaceshipByIdentifier(@PathVariable Long id) {
        return spaceships.findById(id);
    }

    
    public Spaceship getSpaceshipByNameAndModel(String name, String model) {
        Spaceship ss = null;

        Iterator<Spaceship> itr = spaceships.iterator();
        while (itr.hasNext()) {
            ss = itr.next();
            if (ss.getName().equals(name) && ss.getModel().equals(model)) {
                break;
            }
        }
        return ss;
    }


    private Location getLocationByIdentifier(Long id) {
        Location loc = null;

        Iterator<Location> itr = locations.iterator();
        while (itr.hasNext()) {
            loc = itr.next();
            if (loc.getIdentifier() == id) {
                break;
            }
        }
        return loc;
    }

    */
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
