package id.ac.ui.cs.advprog.eshop.controller;

import id.ac.ui.cs.advprog.eshop.model.Car;
import id.ac.ui.cs.advprog.eshop.service.CarServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CarController.class)
@ExtendWith(MockitoExtension.class)
public class CarControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CarServiceImpl carservice; // FIX: Use @MockBean instead of @Mock

    @BeforeEach
    void setUp(@Autowired WebApplicationContext webApplicationContext) {
        MockitoAnnotations.openMocks(this);
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    void testCarListPage() throws Exception {
        when(carservice.findAll()).thenReturn(List.of());

        mockMvc.perform(get("/car/listCar"))
                .andExpect(status().isOk())
                .andExpect(view().name("carList"))
                .andExpect(model().attributeExists("cars"));

        verify(carservice, times(1)).findAll();
    }

    @Test
    void testCreateCarPage() throws Exception {
        mockMvc.perform(get("/car/createCar"))
                .andExpect(status().isOk())
                .andExpect(view().name("createCar"))
                .andExpect(model().attributeExists("car"));
    }

    @Test
    void testCreateCarPost() throws Exception {
        Car car = new Car();
        car.setCarName("Toyota Supra");
        car.setCarColor("Red");
        car.setCarQuantity(3);

        when(carservice.create(any(Car.class))).thenReturn(car);

        mockMvc.perform(post("/car/createCar")
                        .flashAttr("car", car))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("listCar"));

        verify(carservice, times(1)).create(any(Car.class));
    }

    @Test
    void testEditCarPage() throws Exception {
        Car car = new Car();
        car.setCarId("car-123");
        car.setCarName("Toyota Supra");

        when(carservice.findById("car-123")).thenReturn(car);

        mockMvc.perform(get("/car/editCar/car-123"))
                .andExpect(status().isOk())
                .andExpect(view().name("editCar"))
                .andExpect(model().attributeExists("car"));

        verify(carservice, times(1)).findById("car-123");
    }

    @Test
    void testEditCarPost() throws Exception {
        Car car = new Car();
        car.setCarId("car-123");
        car.setCarName("Toyota Supra");

        mockMvc.perform(post("/car/editCar")
                        .flashAttr("car", car))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("listCar"));

        verify(carservice, times(1)).update(eq("car-123"), any(Car.class));
    }

    @Test
    void testDeleteCar() throws Exception {
        mockMvc.perform(post("/car/deleteCar")
                        .param("carId", "car-123"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("listCar"));

        verify(carservice, times(1)).deleteCarById("car-123");
    }
}
