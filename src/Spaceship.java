
public class Spaceship {
    private int id;
    private String name;
    private String model;
    private Location location;
    private String status;

    public Spaceship(int id, String name, String model, Location location, String status) {
        this.id = id;
        this.name = name;
        this.model = model;
        this.location = location;
        this.status = status;
    }

    public int getIdentifier() {
        return id;
    }

    public String getStatus() {
        return status;
    }

    public void updateStatus(String newStatus) {
        this.status = newStatus;
    }

    public int getLocationIdentifier() {
        return location.getIdentifier();
    }

    public void setLocation(Location newLocation) {
        this.location = newLocation;
    }


    
    @Override
    public String toString() {
        return name + "-" + model;
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