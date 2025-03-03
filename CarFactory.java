public class CarFactory {
    public Car createCar(String carType) {
        switch (carType) {
            case "Volvo240":
                return new Volvo240();
            case "Saab95":
                return new Saab95();
            case "Scania":
                return new Scania();

            default:
                throw new java.lang.RuntimeException("Not an existing car");
        }
    }

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