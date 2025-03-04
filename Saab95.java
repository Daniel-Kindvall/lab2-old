import java.awt.*;

public class Saab95 extends Car  implements TurboCar{
    private boolean turboOn = false;
    public Saab95(){
        super(2, 125, Color.red, "Saab95", 1);
    }

    @Override
    public boolean getTurboStatus() {
        return turboOn;
    }

    @Override
    public void setTurboOn(){
	    turboOn = true;
    }

    @Override
    public void setTurboOff(){
	    turboOn = false;
    }
    
    @Override
    protected double speedFactor(){
        double turbo = 1;
        if(turboOn) turbo = 1.3;
        return getEnginePower() * 0.01 * turbo;
    }
}