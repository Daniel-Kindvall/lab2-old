import java.util.ArrayList;

public class Observealbe {
    private ArrayList<Observer> observers;

    public void AddObserver(Observer observer){
        observers.add(observer);
    }

    public void removeObserver(Observer observer){
        observers.remove(observer);
    }

    public void notifyObservers(){
        for (Observer observer : observers){
            observer.update();
        }
    }
}
