public class Main {
    public static void main(String[] args) {
        LogisticsSystem system = new LogisticsSystem();

        system.addLocation("Australia", "Earth", 3);
        system.addLocation("Elon", "Mars", 2);

        Location l1 = system.getLocationByCityAndPlanet("Australia", "Earth");
        Location l2 = system.getLocationByCityAndPlanet("Elon", "Mars");

        system.addSpaceship("Avalon", "Tesla", l1, "decommissioned");
        system.addSpaceship("Explorer", "Tesla", l2, "decommissioned");

        Spaceship s1 = system.getSpaceshipByNameAndModel("Avalon", "Tesla");
        Spaceship s2 = system.getSpaceshipByNameAndModel("Explorer", "Tesla");

        System.out.println(l1.toString());
        System.out.println(l2.toString());
        System.out.println(s1.toString());
        System.out.println(s2.toString());
    }
}
