import java.awt.*;

abstract class TrimCar extends Car {

    private final double trimFactor;

    public TrimCar(int nrDoors, double enginePower, Color color, String modelName, int carSize, double trimFactor){
        super(nrDoors, enginePower, color, modelName, carSize);
        this.trimFactor = trimFactor;
    }

    @Override
    protected double speedFactor(){
        return getEnginePower() * 0.01 * trimFactor;
    }

}