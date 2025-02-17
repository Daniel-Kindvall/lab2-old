import java.awt.*;

abstract class TurboCar extends Car {

    private boolean turboOn = false;

    public TurboCar(int nrDoors, double enginePower, Color color, String modelName, int carSize){
        super(nrDoors, enginePower, color, modelName, carSize);
    }

    public boolean getTurboStatus() {
        return turboOn;
    }

    public void setTurboOn(){
	    turboOn = true;
    }

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