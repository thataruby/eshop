package id.ac.ui.cs.advprog.eshop;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@SpringBootTest
public class EshopApplicationTests {

    @Test
    void testMainMethodRuns() {
        assertDoesNotThrow(() -> EshopApplication.main(new String[]{}));
    }
}
