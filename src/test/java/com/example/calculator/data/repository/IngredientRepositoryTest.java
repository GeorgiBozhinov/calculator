//package com.example.calculator.data.repository;
//import org.junit.Assert;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import java.util.List;
//
//@RunWith(SpringRunner.class)
//@DataJpaTest
//public class IngredientRepositoryTest {
//
//    @Autowired
//    private TestEntityManager entityManager;
//
//    @Autowired
//    IngredientRepository ingredientRepository;
//
//    @Test
//    public void should_find_all_by_ingredient_type(){
//        List listOfIngredients = ingredientRepository.findAllByIngredientType("wax");
//        Assert.assertThat(listOfIngredients).isEmpty();
//    }
//
//}
