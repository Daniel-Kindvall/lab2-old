import java.util.ArrayList;
import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        
        final int X = 800;
        final int Y = 800;
        ArrayList<Car> carList = new ArrayList<>();
        CarFactory carFactory = new CarFactory();
        carList.add(carFactory.createCar("Volvo240"));
        carList.add(carFactory.createCar("Saab95"));
        carList.add(carFactory.createCar("Scania"));

        CarController controller = new CarController(carList, X, Y, 50);
    }
}
