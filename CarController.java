import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.cert.TrustAnchor;
import java.util.ArrayList;

/*
* This class represents the Controller part in the MVC pattern.
* It's responsibilities is to listen to the View and responds in a appropriate manner by
* modifying the model state and the updating the view.
 */

public class CarController {
    // member fields:

    // The delay (ms) corresponds to 20 updates a sec (hz)
    private final int delay = 50;
    // The timer is started with a listener (see below) that executes the statements
    // each step between delays.
    private Timer timer = new Timer(delay, new TimerListener());

    // The frame that represents this instance View of the MVC pattern
    CarView frame;
    // A list of cars, modify if needed
    ArrayList<Car> cars = new ArrayList<>();

    CarWorkshop<Volvo240> volvoWorkshop = new CarWorkshop<>(3);
    int workshopX = 300;
    int workshopY = 300;
    int workshopWidth = 100;
    int workshopHeight = 100;

    //methods:

    public static void main(String[] args) {
        // Instance of this class
        CarController cc = new CarController();

        cc.cars.add(new Volvo240());
        cc.cars.getLast().setPosition(new double[] {0, 300});
        cc.cars.add(new Saab95());
        cc.cars.getLast().setPosition(new double[] {0, 150});
        cc.cars.add(new Scania());
        cc.cars.getLast().setPosition(new double[] {0, 0});

        // Start a new view and send a reference of self
        cc.frame = new CarView("CarSim 1.0", cc);

        // Start the timer
        cc.timer.start();
    }

    /* Each step the TimerListener moves all the cars in the list and tells the
    * view to update its images. Change this method to your needs.
    * */
    private class TimerListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            double frameWidth = frame.getContentPane().getSize().getWidth();
            double frameHeight = frame.getContentPane().getSize().getHeight();
            int carIndex = 0;
            for (Car car : cars) {

                if (car.getPosition()[0] > frameWidth - 100 || car.getPosition()[0] < 0) {
                    car.stopEngine();
                    double clampedValues = Math.min(Math.max(car.getPosition()[0], 0), frameWidth - 100);
                    car.setPosition(new double[] {clampedValues, car.getPosition()[1]});
                    car.turnRight();
                    car.turnRight();
                    car.startEngine();
                } else if (car.getPosition()[1] > frameHeight) {
                    car.stopEngine();
                    double clampedValues = Math.min(Math.max(car.getPosition()[1], frameWidth - 100), frameWidth - 50);
                    car.setPosition(new double[] {car.getPosition()[0], clampedValues});
                    car.turnRight();
                    car.turnRight();
                    car.startEngine();
                }

                if (
                    car.getPosition()[0] >= workshopX && car.getPosition()[0] <= (workshopX + workshopWidth) &&
                    car.getPosition()[1] >= workshopY && car.getPosition()[1] <= (workshopY + workshopHeight) &&
                    car.getClass() == Volvo240.class
                ) {
                    volvoWorkshop.addCar((Volvo240)car);
                }

                car.move();
                int x = (int) Math.round(car.getPosition()[0]);
                int y = (int) Math.round(car.getPosition()[1]);
                frame.drawPanel.moveit(carIndex, x, y);
                carIndex += 1;
                // repaint() calls the paintComponent method of the panel
                frame.drawPanel.repaint();
            }
        }
    }

    // Calls the gas method for each car once
    void gas(int amount) {
        double gas = ((double) amount) / 100;
        for (Car car : cars) {
            car.gas(gas);
        }
    }

    // Calls the brake method for each car once
    void brake(int amount) {
        double brake = ((double) amount) / 100;
        for (Car car : cars) {
            car.brake(brake);
        }
    }

    void turboOn() {
        for (Car car : cars) {
            if (car.getClass() == Saab95.class) {
                TurboCar turboCar = (TurboCar)car;
                turboCar.setTurboOn();
            }
        }
    }

    void turboOff() {
        for (Car car : cars) {
            if (car.getClass() == Saab95.class) {
                TurboCar turboCar = (TurboCar)car;
                turboCar.setTurboOff();
            }
        }
    }

    void raiseBed() {
        for (Car car : cars) {
            if (car.getClass() == Scania.class) {
                Scania scania = (Scania)car;
                scania.raiseCargoBed(70);
            }
        }
    }

    void lowerBed() {
        for (Car car : cars) {
            if (car.getClass() == Scania.class) {
                Scania scania = (Scania)car;
                scania.lowerCargoBed(70);
            }
        }
    }

    void startCars() {
        for (Car car : cars) {
            car.startEngine();
        }
    }

    void stopCars() {
        for (Car car : cars) {
            car.stopEngine();
        }
    }
}
