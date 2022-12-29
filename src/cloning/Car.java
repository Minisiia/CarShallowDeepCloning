package cloning;

import java.io.*;
import java.lang.reflect.Field;

public class Car implements Cloneable, Serializable {
    private String brand;
    private int price;
    private String model;

    public Car() {
    }

    public Car (Car car) {                      // Deep cloning. Constructor
        this(car.brand, car.price, car.model);
    }

    public Car(String brand, int price, String model) {
        this.brand = brand;
        this.price = price;
        this.model = model;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public static Car staticFabricMethod (String brand, int price, String model) {  // Deep cloning. Static fabric method
        Car newCar = new Car();
        newCar.brand = brand;
        newCar.price = price;
        newCar.model = model;
        return newCar;
    }

    public static Car reflectionCloning (Car origCar) throws RuntimeException {       // Deep cloning. Reflection
        Car copyCar = origCar;
        try {
            Field brand = Car.class.getDeclaredField("brand");
            Field price = Car.class.getDeclaredField("price");
            Field model = Car.class.getDeclaredField("model");
            brand.setAccessible(true);
            price.setAccessible(true);
            model.setAccessible(true);
            brand.set(copyCar,origCar.brand);
            model.set(copyCar,origCar.model);
            price.set(copyCar,origCar.price);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        return copyCar;
    }

    public static Car cloneCar(Car car) throws Exception {       // Deep cloning. Serialization
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bos);
        oos.writeObject(car);
        byte[] bytes = bos.toByteArray();
        ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(bytes));
        return (Car) ois.readObject();
    }

    @Override
    public Object clone() throws CloneNotSupportedException {   // Shallow cloning
        return super.clone();
    }

    @Override
    public String toString() {
        return "Car{" +
                "brand='" + brand + '\'' +
                ", price=" + price +
                ", model='" + model + '\'' +
                '}';
    }
}
