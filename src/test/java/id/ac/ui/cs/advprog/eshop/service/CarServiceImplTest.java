package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Car;
import id.ac.ui.cs.advprog.eshop.repository.CarRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CarServiceImplTest {

    @InjectMocks
    private CarServiceImpl carService;

    @Mock
    private CarRepository carRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateCar() {
        Car car = new Car();
        car.setCarName("Toyota Supra");
        car.setCarColor("Red");
        car.setCarQuantity(3);

        when(carRepository.create(any(Car.class))).thenReturn(car);

        Car createdCar = carService.create(car);

        assertNotNull(createdCar);
        assertEquals("Toyota Supra", createdCar.getCarName());
        assertEquals("Red", createdCar.getCarColor());
        assertEquals(3, createdCar.getCarQuantity());
        verify(carRepository, times(1)).create(car);
    }

    @Test
    void testFindAllIfEmpty() {
        when(carRepository.findAll()).thenReturn(new ArrayList<Car>().iterator());

        List<Car> cars = carService.findAll();

        assertTrue(cars.isEmpty());
        verify(carRepository, times(1)).findAll();
    }

    @Test
    void testFindAllWithCars() {
        Car car1 = new Car();
        car1.setCarName("Toyota Supra");
        car1.setCarColor("Red");
        car1.setCarQuantity(3);

        Car car2 = new Car();
        car2.setCarName("Honda Civic");
        car2.setCarColor("Blue");
        car2.setCarQuantity(5);

        List<Car> mockCarList = List.of(car1, car2);
        Iterator<Car> carIterator = mockCarList.iterator();

        when(carRepository.findAll()).thenReturn(carIterator);

        List<Car> cars = carService.findAll();

        assertEquals(2, cars.size());
        assertEquals("Toyota Supra", cars.get(0).getCarName());
        assertEquals("Honda Civic", cars.get(1).getCarName());
        verify(carRepository, times(1)).findAll();
    }

    @Test
    void testFindByIdExistingCar() {
        Car car = new Car();
        car.setCarId("car-123");
        car.setCarName("Toyota Supra");
        car.setCarColor("Red");
        car.setCarQuantity(3);

        when(carRepository.findById("car-123")).thenReturn(car);

        Car foundCar = carService.findById("car-123");

        assertNotNull(foundCar);
        assertEquals("Toyota Supra", foundCar.getCarName());
        verify(carRepository, times(1)).findById("car-123");
    }

    @Test
    void testFindByIdNonExistentCar() {
        when(carRepository.findById("non-existent-id")).thenReturn(null);
        Car foundCar = carService.findById("non-existent-id");

        assertNull(foundCar);
        verify(carRepository, times(1)).findById("non-existent-id");
    }

    @Test
    void testUpdateExistingCar() {
        Car car = new Car();
        car.setCarId("car-123");
        car.setCarName("Toyota Supra");
        car.setCarColor("Red");
        car.setCarQuantity(3);

        Car updatedCar = new Car();
        updatedCar.setCarName("Honda Civic");
        updatedCar.setCarColor("Blue");
        updatedCar.setCarQuantity(5);

        when(carRepository.update("car-123", updatedCar)).thenReturn(updatedCar);
        carService.update("car-123", updatedCar);
        verify(carRepository, times(1)).update("car-123", updatedCar);
    }

    @Test
    void testUpdateNonExistentCar() {
        Car updatedCar = new Car();
        updatedCar.setCarName("Non-existent");
        updatedCar.setCarColor("Black");
        updatedCar.setCarQuantity(10);
        when(carRepository.update("non-existent-id", updatedCar)).thenReturn(null);
        carService.update("non-existent-id", updatedCar);
        verify(carRepository, times(1)).update("non-existent-id", updatedCar);
    }

    @Test
    void testDeleteExistingCar() {
        doNothing().when(carRepository).delete("car-123");
        carService.deleteCarById("car-123");
        verify(carRepository, times(1)).delete("car-123");
    }

    @Test
    void testDeleteNonExistentCar() {
        doNothing().when(carRepository).delete("non-existent-id");
        carService.deleteCarById("non-existent-id");
        verify(carRepository, times(1)).delete("non-existent-id");
    }
}
