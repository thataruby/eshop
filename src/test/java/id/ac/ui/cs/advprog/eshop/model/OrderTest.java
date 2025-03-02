import id.ac.ui.cs.advprog.eshop.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OrderTest {
    private List<Product> products;

    @BeforeEach
    void setUp() {
        this.products = new ArrayList<>();

        Product product1 = new Product();
        product1.setProductId("eb5589ef-1c39-4608-8868-71af6af3bd6d");
        product1.setProductName("Sampo Cap Bambang");
        product1.setProductQuantity(2);

        Product product2 = new Product();
        product2.setProductId("ac262328-4a37-4664-83c7-f32db8620155");
        product2.setProductName("Sabun Cap Usep");
        product2.setProductQuantity(1);

        this.products.add(product1);
        this.products.add(product2);
    }

    @Test
    void testCreateOrderEmptyProduct() {
        this.products.clear();

        assertThrows(IllegalArgumentException.class, () -> {
            Order order = new Order(
                    "13652556-012a-4c07-b546-54eb1394679b",
                    this.products,
                    1708560000L,
                    "Safira Sudrajat"
            );
        });
    }

    @Test
    void testCreateOrderDefaultStatus() {
        Order order = new Order(
                "13652556-012a-4c07-b546-54eb1394679b",
                this.products,
                1708560000L,
                "Safira Sudrajat"
        );

        assertSame(this.products, order.getProducts());
        assertEquals(2, order.getProducts().size());
        assertEquals("Sampo Cap Bambang", order.getProducts().get(0).getProductName());
        assertEquals("Sabun Cap Usep", order.getProducts().get(1).getProductName());

        assertEquals("13652556-012a-4c07-b546-54eb1394679b", order.getId());
        assertEquals(1708560000L, order.getOrderTime());
        assertEquals("Safira Sudrajat", order.getAuthor());
        assertEquals("WAITING_PAYMENT", order.getStatus());
    }

    @Test
    void testCreateOrderSuccessStatus() {
        Order order = new Order(
                "13652556-012a-4c07-b546-54eb1394679b",
                this.products,
                1708560000L,
                "Safira Sudrajat",
                "SUCCESS"
        );

        assertEquals("SUCCESS", order.getStatus());
    }

    @Test
    void testCreateOrderInvalidStatus() {
        assertThrows(IllegalArgumentException.class, () -> {
            Order order = new Order(
                    "13652556-012a-4c07-b546-54eb1394679b",
                    this.products,
                    1708560000L,
                    "Safira Sudrajat",
                    "MEOW"
            );
        });
    }

    @Test
    void testSetStatusToCancelled() {
        Order order = new Order(
                "13652556-012a-4c07-b546-54eb1394679b",
                this.products,
                1708560000L,
                "Safira Sudrajat"
        );

        order.setStatus("CANCELLED");
        assertEquals("CANCELLED", order.getStatus());
    }

    @Test
    void testSetStatusToInvalidStatus() {
        Order order = new Order(
                "13652556-012a-4c07-b546-54eb1394679b",
                this.products,
                1708560000L,
                "Safira Sudrajat"
        );

        assertThrows(IllegalArgumentException.class, () -> order.setStatus("MEOW"));
    }
}
