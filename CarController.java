import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

/*
* This class represents the Controller part in the MVC pattern.
* It's responsibilities is to listen to the View and responds in a appropriate manner by
* modifying the model state and the updating the view.
 */

public class CarController implements ControllerInputs {
    CarView frame;
    int frameWidth;
    int frameHeight;
    int workshopWidth = 100;
    int workshopHeight = 100;
    int carWidth = 100;
    int carHeight = 50;

    int maxCars;

    CarFactory carFactory = new CarFactory();
    ArrayList<Car> cars = new ArrayList<>();

    // Generic workshops for different car types
    // NOTE: Using a hashmap only allows for one object with the same key to exist. Perhaps a compund key could be used?
    HashMap<Class<? extends Car>, CarWorkshop<? extends Car>> workshops = new HashMap<>();

    public CarController(int screenWidth, int screenHeight, int timerDelay) {
        // The frame that represents this instance View of the MVC pattern
        this.frameWidth = screenWidth;
        this.frameHeight = screenHeight;
        this.maxCars = (this.frameHeight - 240) / 80;
        this.frame = new CarView("Car Simulator 1.0", (ControllerInputs)this);

        final Timer timer = new Timer(timerDelay, new TimerListener());
        // Start the timer
        timer.start();
    }

    //methods:
    /* Each step the TimerListener moves all the cars in the list and tells the
    * view to update its images. Change this method to your needs.
    * */

    @Override
    public int getFrameHeight() {
        return this.frameHeight;
    }

    @Override
    public int getFrameWidth() {
        return this.frameWidth;
    }

    public void addWorkshop(Class<? extends Car> workshopType, CarWorkshop<? extends Car> workshop, String image) {
        this.frame.drawPanel.addWorkshop(workshop, image);
        this.workshops.put(workshopType, workshop);
    }

    // Add and remove car functions
    public void addCar(Car car) {
        if (cars.size() < this.maxCars) {
            car.setPosition(new double[] {0, cars.size() * 75});
            this.cars.add(car);
            this.frame.drawPanel.addCar(car, car.getModelName() + ".jpg");
        }
    }
    public void removeCar(Car car) {
        this.cars.remove(car);
        this.frame.drawPanel.removeCar(car);
    }
    // Add and remove car functions for the buttons
    public void addCar() {
        if (cars.size() < this.maxCars) {
            Car car;

            // Create a random car
            switch ((int)(Math.random() * 3)) {
                case 0:
                    car = carFactory.createVolvo240();
                    break;
                case 1:
                    car = carFactory.createSaab95();
                    break;
                default:
                    car = carFactory.createScania();
                    break;
            }

            car.setPosition(new double[] {0, cars.size() * 75});
            this.cars.add(car);
            this.frame.drawPanel.addCar(car, car.getModelName() + ".jpg");
        }
    }
    public void removeCar() {
        Car car = this.cars.get(this.cars.size()-1);
        this.cars.remove(car);
        this.frame.drawPanel.removeCar(car);
    }

    private void checkCollision(Car car) {
        if (car.getPosition()[0] > frameWidth - carWidth || car.getPosition()[0] < 0) {
            car.stopEngine();
            double clampedValues = Math.min(Math.max(car.getPosition()[0], 0), frameWidth - carWidth);
            car.setPosition(new double[] {clampedValues, car.getPosition()[1]});
            car.turnRight();
            car.turnRight();
            car.startEngine();
        } else if (car.getPosition()[1] > frameHeight) {
            car.stopEngine();
            double clampedValues = Math.min(Math.max(car.getPosition()[1], frameWidth - carWidth), frameWidth - carHeight);
            car.setPosition(new double[] {car.getPosition()[0], clampedValues});
            car.turnRight();
            car.turnRight();
            car.startEngine();
        }
    }

    @SuppressWarnings("unchecked")
    private void checkWorkshopEntry(Car car) {
        for (CarWorkshop<? extends Car> workshop : workshops.values()) {
            if (car.getPosition()[0] >= workshop.getPosition()[0] && car.getPosition()[0] <= (workshop.getPosition()[0] + workshopWidth) &&
                car.getPosition()[1] >= workshop.getPosition()[1] && car.getPosition()[1] <= (workshop.getPosition()[1] + workshopHeight)) {

                // If the car's class is a key in workshops, and that workshop is the same as the current one, add the car to that workshop.
                if (workshops.get(car.getClass()) == workshop) {
                    // Safe cast since the workshop only stores cars of a specific type
                    CarWorkshop<Car> typedWorkshop = (CarWorkshop<Car>) workshop;
                    typedWorkshop.addCar(car);
                }
            }
        }
    }    

    private class TimerListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            for (Car car : cars) {
                checkCollision(car);
                checkWorkshopEntry(car);
                car.move();
            }
            
            // repaint() calls the paintComponent method of the panel
            frame.drawPanel.repaint();
        }
    }

    // Calls the gas method for each car once

    @Override
    public void gas(int amount) {
        double gas = ((double) amount) / 100;
        for (Car car : cars) {
            car.gas(gas);
        }
    }

    // Calls the brake method for each car once
    @Override
    public void brake(int amount) {
        double brake = ((double) amount) / 100;
        for (Car car : cars) {
            car.brake(brake);
        }
    }

    @Override
    public void turboOn() {
        for (Car car : cars) {
            if (car instanceof TurboCar) {
                TurboCar turboCar = (TurboCar)car;
                turboCar.setTurboOn();
            }
        }
    }

    @Override
    public void turboOff() {
        for (Car car : cars) {
            if (car instanceof TurboCar) {
                TurboCar turboCar = (TurboCar)car;
                turboCar.setTurboOff();
            }
        }
    }

    @Override
    public void raiseBed() {
        for (Car car : cars) {
            if (car instanceof Truck) {
                Truck truck = (Truck)car;
                truck.raiseCargoBed(70);
            }
        }
    }

    @Override
    public void lowerBed() {
        for (Car car : cars) {
            if (car instanceof Truck) {
                Truck truck = (Truck)car;
                truck.lowerCargoBed(70);
            }
        }
    }

    @Override
    public void startCars() {
        for (Car car : cars) {
            car.startEngine();
        }
    }

    @Override
    public void stopCars() {
        for (Car car : cars) {
            car.stopEngine();
        }
    }
}
