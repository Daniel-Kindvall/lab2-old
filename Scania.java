import java.awt.*;

public class Scania extends Car implements Truck{
    private double trimFactor = 0.5;
    private double cargoBedAngle = 0;
    public Scania(){
        super(2, 400, Color.blue, "Scania", 3);
    }

    @Override
    public double getCargoBedAngle() {
        return cargoBedAngle;
    }

    @Override
    public void raiseCargoBed(double amount){
        if(getCurrentSpeed() > 0){
            return;
        }
        cargoBedAngle = cargoBedAngle + Math.abs(amount);
        if(cargoBedAngle > 70){
            cargoBedAngle = 70;
        }
        addMovementHindrance("cargoBedIsRaised");
    }

    @Override
    public void lowerCargoBed(double amount){
        cargoBedAngle = cargoBedAngle - Math.abs(amount);
        if (cargoBedAngle <= 0) {
            cargoBedAngle = 0;
            removeMovementHindrance("cargoBedIsRaised");
        }
    }

    @Override
    public double speedFactor(){
        return getEnginePower() * 0.01 * trimFactor;
    }
}
