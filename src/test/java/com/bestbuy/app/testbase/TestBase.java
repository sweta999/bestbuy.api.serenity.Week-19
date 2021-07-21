package com.bestbuy.app.testbase;

import com.bestbuy.app.constants.Path;
import com.bestbuy.app.utils.PropertyReader;
import io.restassured.RestAssured;
import org.junit.BeforeClass;

public class TestBase {

    //public static PropertyReader
    public static PropertyReader propertyReader;

    @BeforeClass
    public static void init(){
        propertyReader = PropertyReader.getInstance();
        RestAssured.baseURI = propertyReader.getProperty("baseUrl");
        RestAssured.port = Integer.parseInt(propertyReader.getProperty("port"));
        RestAssured.basePath = Path.PRODUCTS;
        //RestAssured.basePath = Path.STORES;
        //RestAssured.basePath = Path.SERVICES;
        //RestAssured.basePath = Path.CATEGORIES;


    }
}
