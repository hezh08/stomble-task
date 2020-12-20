
import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;


public class LogisticsSystem {
    private List<Spaceship> spaceships;
    private List<Location> locations;

    private static int spaceship_id = 0;
    private static int locations_id = 0;

    public LogisticsSystem() {
        this.spaceships = new ArrayList<>();
        this.locations = new ArrayList<>();
    }


    public void addSpaceship(String name, String model, Location location, String status) {    	
    	for (Spaceship s : spaceships) {
    		if (s.getName().equals(name) && s.getModel().equals(model)) { 
    			return;
			}
        }

        Spaceship ss = new Spaceship(spaceship_id++, name, model, location, status);        
        spaceships.add(ss);
    }


    public void addLocation(String city, String planet, int spaceportCapacity) {
        for (Location l : locations) {
    		if (l.getCity().equals(city) && l.getPlanet().equals(planet)) { 
    			return;
			}
        }

        Location loc = new Location(locations_id++, city, planet, spaceportCapacity);
    	locations.add(loc);
    }


    public void removeSpaceship(int id) {
        Iterator<Spaceship> itr = spaceships.iterator();
        while (itr.hasNext()) {
            Spaceship ss = itr.next();
            if (ss.getIdentifier() == id) {
                itr.remove();
            }
        }
    }


    public void removeLocation(int id) {
        Iterator<Location> itr = locations.iterator();
        while (itr.hasNext()) {
            Location loc = itr.next();
            if (loc.getIdentifier() == id) {
                itr.remove();
            }
        }
    }

    
    public void moveSpaceship(int spaceship_id, int destination_id) {
        Spaceship ss = getSpaceshipByIdentifier(spaceship_id);
        if (ss == null) return;

        if (! ss.getStatus().equals("operational")) return;

        Location dest = getLocationByIdentifier(destination_id);

        if (dest.addVisitor(ss)) {
            Location prev = getLocationByIdentifier(ss.getLocationIdentifier());

            prev.removeVisitor(ss);

            ss.setLocation(dest);
        }
    }


    private Spaceship getSpaceshipByIdentifier(int id) {
        Spaceship ss = null;

        Iterator<Spaceship> itr = spaceships.iterator();
        while (itr.hasNext()) {
            ss = itr.next();
            if (ss.getIdentifier() == id) {
                break;
            }
        }
        return ss;
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


    private Location getLocationByIdentifier(int id) {
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


    public Location getLocationByCityAndPlanet(String city, String planet) {
        Location loc = null;

        Iterator<Location> itr = locations.iterator();
        while (itr.hasNext()) {
            loc = itr.next();
            if (loc.getCity().equals(city) && loc.getPlanet().equals(planet)) {
                break;
            }
        }
        return loc;
    }
}
