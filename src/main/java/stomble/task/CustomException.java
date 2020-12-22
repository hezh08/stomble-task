package stomble.task;

// Generic Exception Handling Class

public class CustomException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    CustomException(String entity, Long id) {
        super("Could not find " + entity + " " + id);
    }

    CustomException(String entity, String city, String planet) {
        super("Could not find " + entity + " " + city + ", " + planet);
    }

    CustomException(String msg) {
        super(msg);
    }
}
