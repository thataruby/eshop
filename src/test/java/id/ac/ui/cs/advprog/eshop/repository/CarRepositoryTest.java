package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Car;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class CarRepositoryTest {

    @InjectMocks
    private CarRepository carRepository;

    @BeforeEach
    void setUp() {
        carRepository = new CarRepository();
    }

    @Test
    void testCreateCar() {
        Car car = new Car();
        car.setCarName("Toyota Supra");
        car.setCarColor("Red");
        car.setCarQuantity(3);

        Car createdCar = carRepository.create(car);

        assertNotNull(createdCar.getCarId());
        assertEquals("Toyota Supra", createdCar.getCarName());
        assertEquals("Red", createdCar.getCarColor());
        assertEquals(3, createdCar.getCarQuantity());
    }

    @Test
    void testFindAllIfEmpty() {
        Iterator<Car> carIterator = carRepository.findAll();
        assertFalse(carIterator.hasNext());
    }

    @Test
    void testFindAllWithCars() {
        Car car1 = new Car();
        car1.setCarName("Toyota Supra");
        car1.setCarColor("Red");
        car1.setCarQuantity(3);
        carRepository.create(car1);

        Car car2 = new Car();
        car2.setCarName("Honda Civic");
        car2.setCarColor("Blue");
        car2.setCarQuantity(5);
        carRepository.create(car2);

        Iterator<Car> carIterator = carRepository.findAll();
        assertTrue(carIterator.hasNext());

        Car firstCar = carIterator.next();
        assertEquals("Toyota Supra", firstCar.getCarName());

        Car secondCar = carIterator.next();
        assertEquals("Honda Civic", secondCar.getCarName());

        assertFalse(carIterator.hasNext());
    }

    @Test
    void testFindByIdExistingCar() {
        Car car = new Car();
        car.setCarName("Toyota Supra");
        car.setCarColor("Red");
        car.setCarQuantity(3);
        Car createdCar = carRepository.create(car);

        Car foundCar = carRepository.findById(createdCar.getCarId());
        assertNotNull(foundCar);
        assertEquals(createdCar.getCarId(), foundCar.getCarId());
    }

    @Test
    void testFindByIdNonExistentCar() {
        Car foundCar = carRepository.findById("non-existent-id");
        assertNull(foundCar);
    }

    @Test
    void testUpdateExistingCar() {
        Car car = new Car();
        car.setCarName("Toyota Supra");
        car.setCarColor("Red");
        car.setCarQuantity(3);
        Car createdCar = carRepository.create(car);

        Car updatedCar = new Car();
        updatedCar.setCarName("Honda Civic");
        updatedCar.setCarColor("Blue");
        updatedCar.setCarQuantity(5);

        Car result = carRepository.update(createdCar.getCarId(), updatedCar);
        assertNotNull(result);
        assertEquals("Honda Civic", result.getCarName());
        assertEquals("Blue", result.getCarColor());
        assertEquals(5, result.getCarQuantity());
    }

    @Test
    void testUpdateNonExistentCar() {
        Car updatedCar = new Car();
        updatedCar.setCarName("Non-existent");
        updatedCar.setCarColor("Black");
        updatedCar.setCarQuantity(10);

        Car result = carRepository.update("non-existent-id", updatedCar);
        assertNull(result);
    }

    @Test
    void testDeleteExistingCar() {
        Car car = new Car();
        car.setCarName("Toyota Supra");
        car.setCarColor("Red");
        car.setCarQuantity(3);
        Car createdCar = carRepository.create(car);

        assertNotNull(carRepository.findById(createdCar.getCarId()));

        carRepository.delete(createdCar.getCarId());
        assertNull(carRepository.findById(createdCar.getCarId()));
    }

    @Test
    void testDeleteNonExistentCar() {
        carRepository.delete("non-existent-id");
        assertNull(carRepository.findById("non-existent-id"));
    }
}
