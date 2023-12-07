package com.shubham.swapsie.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.shubham.swapsie.exception.ResourceNotFoundException;
import com.shubham.swapsie.model.Product;
import com.shubham.swapsie.repository.ProductRepository;

import java.util.ArrayList;
import java.util.List;

import java.util.Optional;

import org.junit.jupiter.api.Disabled;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {ProductServiceImpl.class})
@ExtendWith(SpringExtension.class)
class ProductServiceImplTest {
    @MockBean
    private ProductRepository productRepository;

    @Autowired
    private ProductServiceImpl productServiceImpl;

    /**
     * Method under test: {@link ProductServiceImpl#addProduct(Product)}
     */
    @Test
    void testAddProduct() {
        Product product = new Product();
        when(productRepository.save((Product) any())).thenReturn(product);
        assertSame(product, productServiceImpl.addProduct(new Product()));
        verify(productRepository).save((Product) any());
    }

    /**
     * Method under test: {@link ProductServiceImpl#addProduct(Product)}
     */
    @Test
    void testAddProduct2() {
        when(productRepository.save((Product) any())).thenThrow(new ResourceNotFoundException("An error occurred"));
        assertThrows(ResourceNotFoundException.class, () -> productServiceImpl.addProduct(new Product()));
        verify(productRepository).save((Product) any());
    }

    /**
     * Method under test: {@link ProductServiceImpl#getAllProducts()}
     */
    @Test
    void testGetAllProducts() {
        ArrayList<Product> productList = new ArrayList<>();
        when(productRepository.findAll()).thenReturn(productList);
        List<Product> actualAllProducts = productServiceImpl.getAllProducts();
        assertSame(productList, actualAllProducts);
        assertTrue(actualAllProducts.isEmpty());
        verify(productRepository).findAll();
    }

    /**
     * Method under test: {@link ProductServiceImpl#getAllProducts()}
     */
    @Test
    void testGetAllProducts2() {
        when(productRepository.findAll()).thenThrow(new ResourceNotFoundException("An error occurred"));
        assertThrows(ResourceNotFoundException.class, () -> productServiceImpl.getAllProducts());
        verify(productRepository).findAll();
    }

    /**
     * Method under test: {@link ProductServiceImpl#getProductById(long)}
     */
    @Test
    void testGetProductById() {
        Product product = new Product();
        when(productRepository.findById((Long) any())).thenReturn(Optional.of(product));
        assertSame(product, productServiceImpl.getProductById(1L));
        verify(productRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link ProductServiceImpl#getProductById(long)}
     */
    @Test
    void testGetProductById2() {
        when(productRepository.findById((Long) any())).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> productServiceImpl.getProductById(1L));
        verify(productRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link ProductServiceImpl#getProductById(long)}
     */
    @Test
    void testGetProductById3() {
        when(productRepository.findById((Long) any())).thenThrow(new ResourceNotFoundException("An error occurred"));
        assertThrows(ResourceNotFoundException.class, () -> productServiceImpl.getProductById(1L));
        verify(productRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link ProductServiceImpl#getAllProductsByUserId(long)}
     */
    @Test
    void testGetAllProductsByUserId() {
        ArrayList<Product> productList = new ArrayList<>();
        when(productRepository.findAllByUserId(anyLong())).thenReturn(productList);
        List<Product> actualAllProductsByUserId = productServiceImpl.getAllProductsByUserId(1L);
        assertSame(productList, actualAllProductsByUserId);
        assertTrue(actualAllProductsByUserId.isEmpty());
        verify(productRepository).findAllByUserId(anyLong());
    }

    /**
     * Method under test: {@link ProductServiceImpl#getAllProductsByUserId(long)}
     */
    @Test
    void testGetAllProductsByUserId2() {
        when(productRepository.findAllByUserId(anyLong())).thenThrow(new ResourceNotFoundException("An error occurred"));
        assertThrows(ResourceNotFoundException.class, () -> productServiceImpl.getAllProductsByUserId(1L));
        verify(productRepository).findAllByUserId(anyLong());
    }

    /**
     * Method under test: {@link ProductServiceImpl#deleteProduct(long)}
     */
    @Test
    void testDeleteProduct() {
        doNothing().when(productRepository).delete((Product) any());
        when(productRepository.findById((Long) any())).thenReturn(Optional.of(new Product()));
        assertEquals("Product null with id : 0 deleted successfully", productServiceImpl.deleteProduct(1L));
        verify(productRepository).findById((Long) any());
        verify(productRepository).delete((Product) any());
    }

    /**
     * Method under test: {@link ProductServiceImpl#deleteProduct(long)}
     */
    @Test
    void testDeleteProduct2() {
        doThrow(new ResourceNotFoundException("An error occurred")).when(productRepository).delete((Product) any());
        when(productRepository.findById((Long) any())).thenReturn(Optional.of(new Product()));
        assertThrows(ResourceNotFoundException.class, () -> productServiceImpl.deleteProduct(1L));
        verify(productRepository).findById((Long) any());
        verify(productRepository).delete((Product) any());
    }

    /**
     * Method under test: {@link ProductServiceImpl#deleteProduct(long)}
     */
    @Test
    void testDeleteProduct3() {
        Product product = mock(Product.class);
        when(product.getName()).thenThrow(new ResourceNotFoundException("An error occurred"));
        when(product.getId()).thenThrow(new ResourceNotFoundException("An error occurred"));
        Optional<Product> ofResult = Optional.of(product);
        doNothing().when(productRepository).delete((Product) any());
        when(productRepository.findById((Long) any())).thenReturn(ofResult);
        assertThrows(ResourceNotFoundException.class, () -> productServiceImpl.deleteProduct(1L));
        verify(productRepository).findById((Long) any());
        verify(productRepository).delete((Product) any());
        verify(product).getName();
    }

    /**
     * Method under test: {@link ProductServiceImpl#deleteProduct(long)}
     */
    @Test
    void testDeleteProduct4() {
        doNothing().when(productRepository).delete((Product) any());
        when(productRepository.findById((Long) any())).thenReturn(Optional.empty());
        new ResourceNotFoundException("An error occurred");
        new ResourceNotFoundException("An error occurred");
        assertThrows(ResourceNotFoundException.class, () -> productServiceImpl.deleteProduct(1L));
        verify(productRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link ProductServiceImpl#updateProduct(long, Product)}
     */
    @Test
    void testUpdateProduct() {
        when(productRepository.save((Product) any())).thenReturn(new Product());
        Product product = new Product();
        when(productRepository.findById((Long) any())).thenReturn(Optional.of(product));
        Product actualUpdateProductResult = productServiceImpl.updateProduct(1L, new Product());
        assertSame(product, actualUpdateProductResult);
        assertNull(actualUpdateProductResult.getDescription());
        assertNull(actualUpdateProductResult.getName());
        assertNull(actualUpdateProductResult.getImgLink());
        verify(productRepository).save((Product) any());
        verify(productRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link ProductServiceImpl#updateProduct(long, Product)}
     */
    @Test
    void testUpdateProduct2() {
        when(productRepository.save((Product) any())).thenThrow(new ResourceNotFoundException("An error occurred"));
        when(productRepository.findById((Long) any())).thenReturn(Optional.of(new Product()));
        assertThrows(ResourceNotFoundException.class, () -> productServiceImpl.updateProduct(1L, new Product()));
        verify(productRepository).save((Product) any());
        verify(productRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link ProductServiceImpl#updateProduct(long, Product)}
     */
    @Test
    void testUpdateProduct3() {
        Product product = mock(Product.class);
        doThrow(new ResourceNotFoundException("An error occurred")).when(product).setDescription((String) any());
        doThrow(new ResourceNotFoundException("An error occurred")).when(product).setImgLink((String) any());
        doThrow(new ResourceNotFoundException("An error occurred")).when(product).setName((String) any());
        Optional<Product> ofResult = Optional.of(product);
        when(productRepository.save((Product) any())).thenReturn(new Product());
        when(productRepository.findById((Long) any())).thenReturn(ofResult);
        assertThrows(ResourceNotFoundException.class, () -> productServiceImpl.updateProduct(1L, new Product()));
        verify(productRepository).findById((Long) any());
        verify(product).setName((String) any());
    }

    /**
     * Method under test: {@link ProductServiceImpl#updateProduct(long, Product)}
     */
    @Test
    void testUpdateProduct4() {
        when(productRepository.save((Product) any())).thenReturn(new Product());
        when(productRepository.findById((Long) any())).thenReturn(Optional.empty());
        new ResourceNotFoundException("An error occurred");
        new ResourceNotFoundException("An error occurred");
        new ResourceNotFoundException("An error occurred");
        assertThrows(ResourceNotFoundException.class, () -> productServiceImpl.updateProduct(1L, new Product()));
        verify(productRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link ProductServiceImpl#updateProduct(long, Product)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testUpdateProduct5() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   java.lang.NullPointerException: Cannot invoke "com.shubham.swapsie.model.Product.getName()" because "product" is null
        //       at com.shubham.swapsie.service.impl.ProductServiceImpl.updateProduct(ProductServiceImpl.java:54)
        //   See https://diff.blue/R013 to resolve this issue.

        Product product = mock(Product.class);
        doThrow(new ResourceNotFoundException("An error occurred")).when(product).setDescription((String) any());
        doThrow(new ResourceNotFoundException("An error occurred")).when(product).setImgLink((String) any());
        doThrow(new ResourceNotFoundException("An error occurred")).when(product).setName((String) any());
        Optional<Product> ofResult = Optional.of(product);
        when(productRepository.save((Product) any())).thenReturn(new Product());
        when(productRepository.findById((Long) any())).thenReturn(ofResult);
        productServiceImpl.updateProduct(1L, null);
    }
}

