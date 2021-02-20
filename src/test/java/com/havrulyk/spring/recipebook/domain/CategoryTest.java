package com.havrulyk.spring.recipebook.domain;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import static org.junit.jupiter.api.Assertions.*;

public class CategoryTest {
    Category category;

    @Before
    public void setup(){
        category = new Category();
    }

    @Test
    public void getId(){
        category.setId(4L);
        Assertions.assertEquals(4L, category.getId());
    }

    @Test
    public void getRecipes(){

    }

    @Test
    public void getDescription(){

    }
}