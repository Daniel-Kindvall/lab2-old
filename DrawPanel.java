import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;
import javax.swing.*;

// This panel represents the animated part of the view with the car images.

public class DrawPanel extends JPanel{
    HashMap<Car, BufferedImage> cars = new HashMap<Car, BufferedImage>();
    HashMap<CarWorkshop<? extends Car>, BufferedImage> workshops = new HashMap<CarWorkshop<? extends Car>, BufferedImage>();

    // Initializes the panel and reads the images
    public DrawPanel(int x, int y) {
        this.setDoubleBuffered(true);
        this.setPreferredSize(new Dimension(x, y));
        this.setBackground(Color.green);
    }

    public void addWorkshop(CarWorkshop<? extends Car> workshop, String image) {
        BufferedImage worskhopImage = null;
        try {
            worskhopImage = ImageIO.read(new File(image));
            worskhopImage = ImageIO.read(DrawPanel.class.getResourceAsStream(image));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        workshops.put(workshop, worskhopImage);
    }

    public void addCar(Car car, String image) {
        BufferedImage carImage = null;
        try {
            carImage = ImageIO.read(new File(image));
            carImage = ImageIO.read(DrawPanel.class.getResourceAsStream(image));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        cars.put(car, carImage);
    }

    public void removeCar(Car car) {
        cars.remove(car);
    }

    // This method is called each time the panel updates/refreshes/repaints itself
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (Car car : cars.keySet()) {
            if (!car.getMovementHindrances().contains("isInWorkshop")) {
                Image image = cars.get(car);
                g.drawImage(image, (int)car.getPosition()[0], (int)car.getPosition()[1], null);
            }
        }
        
        for (CarWorkshop<? extends Car> workshop : workshops.keySet()) {
            Image image = workshops.get(workshop);
            g.drawImage(image, (int)workshop.getPosition()[0], (int)workshop.getPosition()[1], null);
        }
    }
}
