import java.util.ArrayList;

public class CarWorkshop<T extends Car> {
    private ArrayList<T> cars = new ArrayList<T>();
    private int capacity;
    private double[] position = {0, 0};

    public CarWorkshop(int carCapacity) {
        this.capacity = carCapacity;
    }

    public double[] getPosition() {
        return position;
    }

    public void setPosition(double[] newPosition) {
        position = newPosition;
    }

    public void addCar(T car) {
        for (T loadedCar : cars) {
            if (loadedCar == car) {
                return;
            }
        }
        if (cars.size() < capacity) {
            cars.add(car);
            car.addMovementHindrance("isInWorkshop");
        } else {
            throw new Error("Verkstaden är full!");
        }
    }  

    public T removeCar(T car) {
        if (cars.contains(car)) {
            cars.remove(car);
            car.removeMovementHindrance("isInWorkshop");
        } else {
            throw new Error("Vi hittade inte din bil!");
        }

        return car;
    }
}