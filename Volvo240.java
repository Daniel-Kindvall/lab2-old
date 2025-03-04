import java.awt.*;

public class Volvo240 extends Car{
    private double trimFactor = 1.25;
    public Volvo240(){
        super(4, 100, Color.black, "Volvo240", 1);
    }

    @Override
    protected double speedFactor(){
        return getEnginePower() * 0.01 * trimFactor;
    }
}