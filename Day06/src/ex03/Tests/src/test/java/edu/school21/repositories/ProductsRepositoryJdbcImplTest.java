package edu.school21.repositories;

import edu.school21.models.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class ProductsRepositoryJdbcImplTest {
    private DataSource dataSource;
    private ProductsRepository productsRepository;
    final List<Product> EXPECTED_FIND_ALL_PRODUCTS = new ArrayList<>(Arrays.asList(new Product(1, "nuts", 123)
            , new Product(2, "snickers", 90)
            , new Product(3, "bounty", 111)
            , new Product(4, "kitkat", 222)
            , new Product(5, "twix", 82)));
    final Product EXPECTED_FIND_BY_ID_PRODUCT = new Product(4, "kitkat", 222);
    final Product EXPEXTED_UPDATED_PRODUCT = new Product(4, "katok", 555);
    final Product EXPECTED_SAVE_PRODUCT = new Product(6, "picnic", 321);
    final List<Product> EXPECTED_ALL_PRODUCTS_AFTER_DELETE = new ArrayList<>(Arrays.asList(new Product(1, "nuts", 123)
            , new Product(2, "snickers", 90)
//            , new Product(3, "bounty", 111)
            , new Product(4, "kitkat", 222)
            , new Product(5, "twix", 82)));

    @BeforeEach
    public void init() {
        dataSource = new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.HSQL)
                .addScript("schema.sql")
                .addScript("data.sql")
                .build();
        productsRepository = new ProductsRepositoryJdbcImpl(dataSource);
    }

    @Test
    public void findAllTest() {
        Assertions.assertEquals(productsRepository.findAll(), EXPECTED_FIND_ALL_PRODUCTS);
    }

    @ParameterizedTest
    @ValueSource(longs = {4})
    public void findByIdTest(long argument) {
        Optional<Product> foundProduct = productsRepository.findById(argument);
        Assertions.assertTrue(foundProduct.isPresent());
        Assertions.assertEquals(foundProduct.get(), EXPECTED_FIND_BY_ID_PRODUCT);
    }

    @ParameterizedTest
    @ValueSource(longs = {4})
    public void updateTest(long argument) {
        Optional<Product> foundProduct = productsRepository.findById(argument);
        Assertions.assertTrue(foundProduct.isPresent());
        productsRepository.update(new Product((int) argument, "katok", 555));
        Assertions.assertEquals(productsRepository.findById(argument).get(), EXPEXTED_UPDATED_PRODUCT);
    }

    @Test
    public void saveTest() {
        productsRepository.save(new Product(6, "picnic", 321));
        Assertions.assertEquals(productsRepository.findById(6L).get(), EXPECTED_SAVE_PRODUCT);
    }

    @ParameterizedTest
    @ValueSource(longs = {3})
    public void deleteTest(long argument) {
        productsRepository.delete(argument);
        Assertions.assertEquals(productsRepository.findAll(), EXPECTED_ALL_PRODUCTS_AFTER_DELETE);
    }

}
