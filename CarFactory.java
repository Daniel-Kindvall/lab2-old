public class CarFactory {
    public Volvo240 createVolvo240() {
        return new Volvo240();
    }

    public Saab95 createSaab95() {
        return new Saab95();
    }

    public Scania createScania() {
        return new Scania();
    }

    public CarTransport createCarTransport() {
        return new CarTransport();
    }
}