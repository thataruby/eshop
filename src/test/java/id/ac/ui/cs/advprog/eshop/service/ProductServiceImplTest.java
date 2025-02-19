package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.repository.ProductRepository;
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
public class ProductServiceImplTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductServiceImpl productService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateProduct() {
        Product product = new Product();
        product.setProductId("1");
        product.setProductName("Test Product");
        product.setProductQuantity(10);

        when(productRepository.create(any(Product.class))).thenReturn(product);

        Product createdProduct = productService.create(product);

        assertNotNull(createdProduct);
        assertEquals("Test Product", createdProduct.getProductName());
        verify(productRepository, times(1)).create(product);
    }

    @Test
    void testFindAllProducts() {
        Product product1 = new Product();
        product1.setProductId("1");
        product1.setProductName("Product 1");

        Product product2 = new Product();
        product2.setProductId("2");
        product2.setProductName("Product 2");

        List<Product> productList = new ArrayList<>();
        productList.add(product1);
        productList.add(product2);

        Iterator<Product> productIterator = productList.iterator();
        when(productRepository.findAll()).thenReturn(productIterator);

        List<Product> result = productService.findAll();

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("Product 1", result.get(0).getProductName());
        assertEquals("Product 2", result.get(1).getProductName());
        verify(productRepository, times(1)).findAll();
    }

    @Test
    void testEditProduct() {
        Product product = new Product();
        product.setProductId("1");
        product.setProductName("Updated Product");

        doNothing().when(productRepository).edit("1", product);

        productService.edit("1", product);

        verify(productRepository, times(1)).edit("1", product);
    }

    @Test
    void testFindProductById() {
        Product product = new Product();
        product.setProductId("1");
        product.setProductName("Test Product");

        when(productRepository.findById("1")).thenReturn(product);

        Product foundProduct = productService.findById("1");

        assertNotNull(foundProduct);
        assertEquals("Test Product", foundProduct.getProductName());
        verify(productRepository, times(1)).findById("1");
    }

    @Test
    void testDeleteProduct() {
        doNothing().when(productRepository).delete("1");

        Product result = productService.delete("1");

        assertNull(result);
        verify(productRepository, times(1)).delete("1");
    }

}