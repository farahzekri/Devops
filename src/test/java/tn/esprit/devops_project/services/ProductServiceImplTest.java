package tn.esprit.devops_project.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.devops_project.entities.Product;
import tn.esprit.devops_project.entities.ProductCategory;
import tn.esprit.devops_project.entities.Stock;
import tn.esprit.devops_project.repositories.ProductRepository;
import tn.esprit.devops_project.repositories.StockRepository;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class ProductServiceImplTest {
    @ExtendWith(MockitoExtension.class)
    @Mock
    ProductRepository productRepository;

    @Mock
    StockRepository stockRepository;

    ProductServiceImpl productService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        productService = new ProductServiceImpl(productRepository, stockRepository);
    }

    @Test
    void addProduct() {
        // Given
        Product product = new Product();
        product.setTitle("Test Product");
        product.setPrice(10.0f);
        product.setQuantity(5);
        product.setCategory(ProductCategory.ELECTRONICS);

        Stock stock = new Stock();
        stock.setIdStock(1L);

        when(stockRepository.findById(1L)).thenReturn(java.util.Optional.of(stock));
        when(productRepository.save(product)).thenReturn(product);

        // When
        Product savedProduct = productService.addProduct(product, 1L);

        // Then
        assertEquals(product.getTitle(), savedProduct.getTitle());
        assertEquals(product.getPrice(), savedProduct.getPrice());
        assertEquals(product.getQuantity(), savedProduct.getQuantity());
        assertEquals(product.getCategory(), savedProduct.getCategory());
    }

    @Test
    void retrieveProduct() {
        // Given
        Long productId = 1L;
        Product product = new Product();
        product.setIdProduct(productId);
        product.setTitle("Test Product");

        when(productRepository.findById(productId)).thenReturn(java.util.Optional.of(product));

        // When
        Product retrievedProduct = productService.retrieveProduct(productId);

        // Then
        assertEquals(productId, retrievedProduct.getIdProduct());
        assertEquals(product.getTitle(), retrievedProduct.getTitle());
    }

    @Test
    void retreiveAllProduct() {
        // Given
        List<Product> productList = new ArrayList<>();
        productList.add(new Product());
        productList.add(new Product());
        productList.add(new Product());

        when(productRepository.findAll()).thenReturn(productList);

        // When
        List<Product> retrievedProducts = productService.retreiveAllProduct();

        // Then
        assertEquals(3, retrievedProducts.size());
    }

    // Les autres tests (retrieveProductByCategory, deleteProduct, retrieveProductStock) peuvent être implémentés de manière similaire.
}
