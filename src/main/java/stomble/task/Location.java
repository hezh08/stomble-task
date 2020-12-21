package stomble.task;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Location {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private String city;
    private String planet;
    private int spaceportCapacity;
    private int currentCapacity;

    Location() {}
    
    Location(String city, String planet, int spaceportCapacity) {
        this.city = city;
        this.planet = planet;
        this.spaceportCapacity = spaceportCapacity;
        this.currentCapacity = 0;
    }

    public Long getIdentifier() {
        return id;
    }

    public String getCity() {
        return city;
    }

    public String getPlanet() {
        return planet;
    }

    public int getSpaceportCapacity() {
        return spaceportCapacity;
    }
    
    public boolean hasExtraCapacity() {
        return (currentCapacity + 1 <= spaceportCapacity);
    }

    public void increaseCurrentCapacity() {
        currentCapacity++;
    }

    public void decreaseCurrentCapacity() {
        currentCapacity--;
    }


    @Override
    public String toString() {
        return String.format(
        "Location[id=%d, city='%s', planet='%s', spaceportCapacity=%d, currentCapacity=%d]",
        id, city, planet, spaceportCapacity, currentCapacity);
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
