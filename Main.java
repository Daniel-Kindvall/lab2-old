public class Main {
    public static void main(String[] args) {
        final int X = 800;
        final int Y = 800;

        // Generic workshops for different car types
        CarFactory carFactory = new CarFactory();

        CarController controller = new CarController(X, Y, 10);

        CarWorkshop<Volvo240> volvoWorkshop = new CarWorkshop<Volvo240>(3);
        volvoWorkshop.setPosition(new double[] {300, 300});
        controller.addWorkshop(Volvo240.class, volvoWorkshop, "VolvoBrand.jpg");
        
        controller.addCar(carFactory.createVolvo240());
        controller.addCar(carFactory.createSaab95());
        controller.addCar(carFactory.createScania());
    }
}
