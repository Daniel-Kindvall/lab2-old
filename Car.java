import java.awt.*;
import java.util.Set;
import java.util.HashSet;

abstract class Car implements Movable {
    private int nrDoors; // Number of doors on the car
    private double enginePower; // Engine power of the car
    private double currentSpeed; // The current speed of the car
    private Color color; // Color of the car
    private String modelName; // The car model name

    private double[] position = {0, 0};
    private double[] direction = {1, 0};

    private int carSize;
    private Set<String> movementHindrances = new HashSet<>();

    public Car(int nrDoors, double enginePower, Color color, String modelName, int carSize) {
        this.nrDoors = nrDoors;
        this.enginePower = enginePower;
        this.color = color;
        this.modelName = modelName;
        this.carSize = carSize;
        stopEngine();
    }

    public void addMovementHindrance(String hindranceName) {
        this.movementHindrances.add(hindranceName);
    }

    public void removeMovementHindrance(String hindranceName) {
        this.movementHindrances.remove(hindranceName);
    }

    public Set<String> getMovementHindrances() {
        return this.movementHindrances;
    }

    public boolean isMoveable() {
        if (movementHindrances.size() > 0) {
            return false;
        }
        return true;
    }

    public int getSize() {
        return carSize;
    }

    public String getModelName() {
        return modelName;
    }

    public int getNrDoors(){
        return nrDoors;
    }

    protected void setNrDoors(int amount){
        nrDoors = amount;
    }

    public double getEnginePower(){
        return enginePower;
    }

    public double getCurrentSpeed(){
        return currentSpeed;
    }

    protected void setCurrentSpeed(double speed) {
        currentSpeed = speed;
    }

    public Color getColor(){
        return color;
    }

    protected void setColor(Color clr){
	    color = clr;
    }

    public void startEngine(){
        removeMovementHindrance("engineIsTurnedOff");
	    currentSpeed = 0.1;

    }

    public void stopEngine(){
        addMovementHindrance("engineIsTurnedOff");
	    currentSpeed = 0;
    }

    abstract double speedFactor();

    private void incrementSpeed(double amount){
        setCurrentSpeed(Math.min(getCurrentSpeed() + speedFactor() * amount, getEnginePower()));
    }

    private void decrementSpeed(double amount){
        setCurrentSpeed(Math.max(getCurrentSpeed() - speedFactor() * amount, 0));
    }

    // TODO fix this method according to lab pm
    public void gas(double amount){
        if (amount > 1.00) {
            amount = 1.00;
        }
        else if (amount < 0) {
            amount = 0;
        }
        incrementSpeed(amount);
    }

    // TODO fix this method according to lab pm
    public void brake(double amount){
        if (amount > 1.00) {
            amount = 1.00;
        }
        else if (amount < 0) {
            amount = 0;
        }
        decrementSpeed(amount);
    }

    @Override
    public void move() {
        if (isMoveable()) {
            position[0] = direction[0] * getCurrentSpeed() + position[0];
            position[1] = direction[1] * getCurrentSpeed() + position[1];
        }
    }

    @Override
    public void turnLeft() {
        double[] previousDirection = new double[2];
        System.arraycopy(direction, 0, previousDirection, 0, direction.length);
        direction[0] = -previousDirection[1];
        direction[1] = previousDirection[0];
    }

    @Override
    public void turnRight() {
        double[] previousDirection = new double[2];
        System.arraycopy(direction, 0, previousDirection, 0, direction.length);
        direction[1] = -previousDirection[0];
        direction[0] = previousDirection[1];
    }

    public double[] getPosition() {
        return position;
    }

    public void setPosition(double[] newPosition) {
        position = newPosition;
    }

    public double[] getDirection() {
        return direction;
    }
}