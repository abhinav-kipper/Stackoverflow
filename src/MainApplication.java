import repositories.IRepository;
import repositories.InMemoryRepository;

public class MainApplication {


    public static void main(String[] args) {

        System.out.println("Application started");
        Driver driver = new Driver();
        driver.test();

    }

    private static void setup() {
        IRepository repo = new InMemoryRepository();

    }
}
