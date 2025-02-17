import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.*;

// This panel represents the animated part of the view with the car images.

public class DrawPanel extends JPanel{

    // Just a single image, TODO: Generalize
    BufferedImage volvoImage;
    BufferedImage saabImage;
    BufferedImage scaniaImage;
    BufferedImage[] carImages = new BufferedImage[]{volvoImage, saabImage, scaniaImage};
    // To keep track of a single car's position
    Point volvoPoint = new Point(0, 300);
    Point saabPoint = new Point(0, 150);
    Point scaniaPoint = new Point(0, 0);
    Point[] carPoints = new Point[]{volvoPoint, saabPoint, scaniaPoint};

    BufferedImage volvoWorkshopImage;
    Point volvoWorkshopPoint = new Point(300,300);

    void moveit(int carIndex, int x, int y){
        carPoints[carIndex].x = x;
        carPoints[carIndex].y = y;
    }

    // Initializes the panel and reads the images
    public DrawPanel(int x, int y) {
        this.setDoubleBuffered(true);
        this.setPreferredSize(new Dimension(x, y));
        this.setBackground(Color.green);
        // Print an error message in case file is not found with a try/catch block
        try {
            // You can remove the "pics" part if running outside of IntelliJ and
            // everything is in the same main folder.
            carImages[0] = ImageIO.read(new File("Volvo240.jpg"));
            carImages[1] = ImageIO.read(new File("Saab95.jpg"));
            carImages[2] = ImageIO.read(new File("Scania.jpg"));

            // Rememember to rightclick src New -> Package -> name: pics -> MOVE *.jpg to pics.
            // if you are starting in IntelliJ.
            carImages[0] = ImageIO.read(DrawPanel.class.getResourceAsStream("Volvo240.jpg"));
            carImages[1] = ImageIO.read(DrawPanel.class.getResourceAsStream("Saab95.jpg"));
            carImages[2] = ImageIO.read(DrawPanel.class.getResourceAsStream("Scania.jpg"));
            volvoWorkshopImage = ImageIO.read(DrawPanel.class.getResourceAsStream("VolvoBrand.jpg"));
        } catch (IOException ex)
        {
            ex.printStackTrace();
        }

    }

    // This method is called each time the panel updates/refreshes/repaints itself
    // TODO: Change to suit your needs.
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (int i = 0; i < carImages.length; i++) {
            if (
                carPoints[i].getX() >= volvoWorkshopPoint.getX() && carPoints[i].getX() <= (volvoWorkshopPoint.getX() + 100) &&
                carPoints[i].y >= volvoWorkshopPoint.getY() && carPoints[i].y <= (volvoWorkshopPoint.getY() + 100)
                ) {
                    
                } else {
                    g.drawImage(carImages[i], carPoints[i].x, carPoints[i].y, null); // see javadoc for more info on the parameters
                }
        }
        g.drawImage(volvoWorkshopImage, volvoWorkshopPoint.x, volvoWorkshopPoint.y, null);
    }
}
