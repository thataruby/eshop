package id.ac.ui.cs.advprog.eshop.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CarTest {
    private Car car;

    @BeforeEach
    void setUp() {
        car = new Car();
        car.setCarId("12345");
        car.setCarName("Toyota Supra");
        car.setCarColor("Red");
        car.setCarQuantity(3);
    }

    @Test
    void testGetCarId() {
        assertEquals("12345", car.getCarId());
    }

    @Test
    void testGetCarName() {
        assertEquals("Toyota Supra", car.getCarName());
    }

    @Test
    void testGetCarColor() {
        assertEquals("Red", car.getCarColor());
    }

    @Test
    void testGetCarQuantity() {
        assertEquals(3, car.getCarQuantity());
    }

}
