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
    // A list of cars, modify if needed
    ArrayList<Car> cars = new ArrayList<>();
    int frameWidth;
    int frameHeight;

    CarWorkshop<Volvo240> volvoWorkshop = new CarWorkshop<>(3);
    int workshopX = 300;
    int workshopY = 300;
    int workshopWidth = 100;
    int workshopHeight = 100;

    CarView frame;

    public CarController(ArrayList<Car> carList, int screenWidth, int screenHeight, int timerDelay) {
        for (int index = 0; index < carList.size(); index++) {
            this.cars.add(carList.get(index));
            carList.get(index).setPosition(new double[] {0, 150 * index});
        }
        this.frameWidth = screenWidth;
        this.frameHeight = screenHeight;

        // The frame that represents this instance View of the MVC pattern
        this.frame = new CarView("Car Simulator 1.0", this);

        final Timer timer = new Timer(timerDelay, new TimerListener());
        // Start the timer
        timer.start();
    }

    //methods:
    /* Each step the TimerListener moves all the cars in the list and tells the
    * view to update its images. Change this method to your needs.
    * */
    private class TimerListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
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
            if (car.getClass().getSuperclass() == TurboCar.class) {
                TurboCar turboCar = (TurboCar)car;
                turboCar.setTurboOn();
            }
        }
    }

    void turboOff() {
        for (Car car : cars) {
            if (car.getClass().getSuperclass() == TurboCar.class) {
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
