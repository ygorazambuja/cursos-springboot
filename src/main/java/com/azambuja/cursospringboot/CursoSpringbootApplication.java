package com.azambuja.cursospringboot;

import com.azambuja.cursospringboot.domain.Category;
import com.azambuja.cursospringboot.domain.City;
import com.azambuja.cursospringboot.domain.Product;
import com.azambuja.cursospringboot.domain.State;
import com.azambuja.cursospringboot.repository.CategoryRepository;
import com.azambuja.cursospringboot.repository.CityRepository;
import com.azambuja.cursospringboot.repository.ProductRepository;
import com.azambuja.cursospringboot.repository.StateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

@SpringBootApplication
public class CursoSpringbootApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(CursoSpringbootApplication.class, args);
    }

    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private StateRepository stateRepository;


    @Override
    public void run(String... args) throws Exception {


        Category category1 = new Category(null, "Informatics");
        Category category2 = new Category(null, "Office");

        Product product1 = new Product(null, "Computer", 2000.00);
        Product product2 = new Product(null, "Printer", 800.00);
        Product product3 = new Product(null, "Mouse", 80.00);

        category1.getProducts().addAll(Arrays.asList(product1, product2, product3));
        category2.getProducts().addAll(Arrays.asList(product2));

        product1.getCategories().addAll(Arrays.asList(category1));
        product2.getCategories().addAll(Arrays.asList(category1, category2));
        product3.getCategories().addAll(Arrays.asList(category1));

        State state1 = new State(null, "Minas Gerais");
        State state2 = new State(null, "São Paulo");

        City city1 = new City(null, "Uberlândia");
        City city2 = new City(null, "São Paulo");
        City city3 = new City(null, "Campinas");


        state1.getCityList().add(city1);
        state2.getCityList().addAll(Arrays.asList(city2, city3));

        city1.setState(state1);
        city2.setState(state2);
        city3.setState(state2);



        categoryRepository.saveAll(Arrays.asList(category1, category2));
        productRepository.saveAll(Arrays.asList(product1, product2, product3));
        stateRepository.saveAll(Arrays.asList(state1, state2));
        cityRepository.saveAll(Arrays.asList(city1, city2, city3));


    }
}
