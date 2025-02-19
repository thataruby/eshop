package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class ProductRepositoryTest {


    @InjectMocks
    ProductRepository productRepository;

    @BeforeEach
    void setUp() {
    }
    @Test
    void testCreateAndFind() {
        Product product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);
        productRepository.create(product);

        Iterator<Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());
        Product savedProduct = productIterator.next();
        assertEquals(product.getProductId(), savedProduct.getProductId());
        assertEquals(product.getProductName(), savedProduct.getProductName());
        assertEquals(product.getProductQuantity(), savedProduct.getProductQuantity());
    }

    @Test
    void testFindAllIfEmpty() {
        Iterator<Product> productIterator = productRepository.findAll();
        assertFalse(productIterator.hasNext());
    }

    @Test
    void testFindAllIfMoreThanOneProduct() {
        Product product1 = new Product();
        product1.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product1.setProductName("Sampo Cap Bambang");
        product1.setProductQuantity(100);
        productRepository.create(product1);

        Product product2 = new Product();
        product2.setProductId("a0f9de46-90b1-437d-a0bf-d0821dde9096");
        product2.setProductName("Sampo Cap Usep");
        product2.setProductQuantity(50);
        productRepository.create(product2);

        Iterator<Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());
        Product savedProduct = productIterator.next();
        assertEquals(product1.getProductId(), savedProduct.getProductId());
        savedProduct = productIterator.next();
        assertEquals(product2.getProductId(), savedProduct.getProductId());
        assertFalse(productIterator.hasNext());
    }

    @Test
    void testEditExistingProduct() {
        Product product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);
        productRepository.create(product);

        Product updatedProduct = new Product();
        updatedProduct.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        updatedProduct.setProductName("Sampo Cap Usep");
        updatedProduct.setProductQuantity(50);
        productRepository.edit(updatedProduct.getProductId(), updatedProduct);

        Product result = productRepository.findById("eb558e9f-1c39-460e-8860-71af6af63bd6");
        assertNotNull(result);
        assertEquals("Sampo Cap Usep", result.getProductName());
        assertEquals(50, result.getProductQuantity());
        assertEquals("eb558e9f-1c39-460e-8860-71af6af63bd6", result.getProductId());
    }

    @Test
    void testDeleteExistingProduct() {
        Product product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);
        productRepository.create(product);

        assertNotNull(productRepository.findById("eb558e9f-1c39-460e-8860-71af6af63bd6"));

        productRepository.delete("eb558e9f-1c39-460e-8860-71af6af63bd6");
        assertNull(productRepository.findById("eb558e9f-1c39-460e-8860-71af6af63bd6"));
    }

    @Test
    void testDeleteNonExistentProduct() {
        productRepository.delete("no-id");
        assertNull(productRepository.findById("no-id"));
    }

    @Test
    void testCreateProductGeneratesUUID() {
        Product product1 = new Product();
        product1.setProductName("Test Product 1");
        product1.setProductQuantity(10);

        Product createdProduct1 = productRepository.create(product1);

        assertNotNull(createdProduct1.getProductId());
        assertFalse(createdProduct1.getProductId().isEmpty());

        Product product2 = new Product();
        product2.setProductId("");
        product2.setProductName("Test Product 2");
        product2.setProductQuantity(15);

        Product createdProduct2 = productRepository.create(product2);

        assertNotNull(createdProduct2.getProductId());
        assertFalse(createdProduct2.getProductId().isEmpty());
    }


    @Test
    void testFindByIdWithNull() {
        assertNull(productRepository.findById(null));
    }

    @Test
    void testEditNonExistentProduct() {
        Product updatedProduct = new Product();
        updatedProduct.setProductName("Non-existent");
        updatedProduct.setProductQuantity(20);

        productRepository.edit("non-existent-id", updatedProduct);
        assertNull(productRepository.findById("non-existent-id"));
    }

}