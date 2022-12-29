package cloning;

import java.io.*;

public class Main {
    public static void main(String[] args) {
        Car myCar = new Car("Hyundai", 25000, "Tucson");
        System.out.println("My car " + myCar);
            // Shallow cloning
        try {
            Car brothersCar = (Car) myCar.clone();
            System.out.println("Shallow cloning \nMy brother's car " + brothersCar);
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
            // Deep cloning. Constructor
        Car sistersCar = new Car(myCar);
        System.out.println("Deep cloning. Constructor \nMy sister's car " + sistersCar);
            // Deep cloning. Static fabric method
        Car mumsCar = Car.staticFabricMethod("Hyundai", 25000, "Tucson");
        System.out.println("Deep cloning. Static fabric method \nMy mum's car " + mumsCar);
            // Deep cloning. Reflection
        Car dadsCar = Car.reflectionCloning(myCar);
        System.out.println("Deep cloning. Reflection \nMy dad's car " + dadsCar);
            // Deep cloning. Serialization
        try {
            Car daughtersCar = Car.cloneCar(myCar);
            System.out.println("Deep cloning. Reflection. Ver. 2 \nMy daughter's car " + daughtersCar);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
