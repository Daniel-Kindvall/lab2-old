import java.util.ArrayList;
import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        
        final int X = 800;
        final int Y = 800;
        ArrayList<Car> carList = new ArrayList<>();
        carList.add(new Volvo240());
        carList.add(new Saab95());
        carList.add(new Scania());

        CarController controller = new CarController(carList, X, Y, 50);
    }
}
