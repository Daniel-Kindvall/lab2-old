import java.util.ArrayList;
import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        
        final int X = 800;
        final int Y = 800;
        ArrayList<Car> carList = new ArrayList<>();
        CarFactory carFactory = new CarFactory();
        carList.add(carFactory.createVolvo240());
        carList.add(carFactory.createSaab95());
        carList.add(carFactory.createScania());

        CarController controller = new CarController(carList, X, Y, 50);
    }
}
