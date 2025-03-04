public interface ControllerInputs {
    void gas(int amount);
    void brake(int amount);
    void turboOn();
    void turboOff();
    void raiseBed();
    void lowerBed();
    void startCars();
    void stopCars();
    void addCar();
    void removeCar();
    int getFrameWidth();
    int getFrameHeight();
}