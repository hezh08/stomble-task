package stomble.task;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "spaceships")
public class Spaceship {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private String name;
    private String model;
    private Location location;
    private String status;

    public Spaceship(String name, String model, Location location, String status) {
        this.name = name;
        this.model = model;
        this.location = location;
        this.status = status;
    }

    public Long getIdentifier() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getModel() {
        return model;
    }

    public String getStatus() {
        return status;
    }

    public void updateStatus(String newStatus) {
        this.status = newStatus;
    }

    public Long getLocationIdentifier() {
        return location.getIdentifier();
    }

    public void setLocation(Location newLocation) {
        this.location = newLocation;
    }


    
    @Override
    public String toString() {
        return String.format(
            "Customer[id=%d, name='%s', model='%s', location='%s', status='%s']",
            id, name, model, location, status);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (this == obj) return true;
        if (obj instanceof Spaceship) {
            Spaceship other = (Spaceship) obj;
            if (this.id == other.getIdentifier()) return true;
        }
        return false;
    }
}