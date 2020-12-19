
import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;

public class Location {
    private int id;
    private String city;
    private String planet;
    private int spaceportCapacity;
    private List<Spaceship> visitors;
    
    public Location(int id, String city, String planet, int spaceportCapacity) {
        this.id = id;
        this.city = city;
        this.planet = planet;
        this.spaceportCapacity = spaceportCapacity;
        this.visitors = new ArrayList<>();
    }

    public int getIdentifier() {
        return id;
    }

    public boolean addVisitor(Spaceship spaceship) {
        if (visitors.size() + 1 > spaceportCapacity) return false;

        return visitors.add(spaceship);
    }

    public void removeVisitor(Spaceship spaceship) {
        Iterator<Spaceship> itr = visitors.iterator();
        while (itr.hasNext()) {
            Spaceship ss = itr.next();
            if (ss.equals(spaceship)) {
                itr.remove();
            }
        }
    }

    public int getSpaceportCapacity() {
        return spaceportCapacity;
    }



    @Override
    public String toString() {
        return city + "-" + planet;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (this == obj) return true;
        if (obj instanceof Location) {
            Location other = (Location) obj;
            if (this.id == other.getIdentifier()) return true;
        }
        return false;
    }
}
