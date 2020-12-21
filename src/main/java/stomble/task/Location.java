package stomble.task;

import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="locations")
public class Location {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private String city;
    private String planet;
    private int spaceportCapacity;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "spaceships_id")
    private List<Long> visitors;
    
    public Location(String city, String planet, int spaceportCapacity) {
        this.city = city;
        this.planet = planet;
        this.spaceportCapacity = spaceportCapacity;
        this.visitors = new ArrayList<>();
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

    public boolean addVisitor(Long spaceship_id) {
        if (visitors.size() + 1 > spaceportCapacity) return false;

        return visitors.add(spaceship_id);
    }

    public void removeVisitor(Long spaceship_id) {
        Iterator<Long> itr = visitors.iterator();
        while (itr.hasNext()) {
            Long ss = itr.next();
            if (ss.equals(spaceship_id)) {
                itr.remove();
            }
        }
    }



    @Override
    public String toString() {
        return String.format(
        "Location[id=%d, city='%s', planet='%s', spaceportCapacity=%d]",
        id, city, planet, spaceportCapacity);
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
