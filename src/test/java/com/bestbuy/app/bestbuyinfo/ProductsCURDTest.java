package com.bestbuy.app.bestbuyinfo;

import com.bestbuy.app.constants.EndPoints;
import com.bestbuy.app.model.ProductsPojo;
import com.bestbuy.app.testbase.TestBase;
import com.bestbuy.app.utils.TestUtils;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Title;
import org.junit.Test;
import org.junit.runner.RunWith;


import java.util.HashMap;

import static org.hamcrest.Matchers.hasValue;
import static org.hamcrest.MatcherAssert.assertThat;


@RunWith(SerenityRunner.class)
public class ProductsCURDTest extends TestBase {

    //declaring global variables - this will generate random
    static String name = "Panasonic1" + TestUtils.getRandomValue();
    static String type = "HardGood" ;
    static double price = 6.49  ;
    static String upc = "0413334" + TestUtils.getRandomValue();
    static double shipping = 0 ;
    static String description = "Compatible with select electronic devices; AAA size; Panasonic Power Preserve technology; 4-pack" ;
    static String manufacturer = "Panasonic" ;
    static String model = "PS2400B4Z" + TestUtils.getRandomValue();
    static String url = "string" + TestUtils.getRandomValue();
    static String image = "string" + TestUtils.getRandomValue();
    static int productId;


    @Title("This will create a new Product")
    @Test
    public void test01() {

        ProductsPojo productsPojo = new ProductsPojo();
        productsPojo.setName(name);
        productsPojo.setType(type);
        productsPojo.setPrice(price);
        productsPojo.setUpc(upc);
        productsPojo.setShipping(shipping);
        productsPojo.setDescription(description);
        productsPojo.setManufacturer(manufacturer);
        productsPojo.setModel(model);
        productsPojo.setUrl(url);
        productsPojo.setImage(image);

        SerenityRest.rest().given().log().all()
                .header("Content-Type","application/json")
                .body(productsPojo)
                .when()
                .post()
                .then().log().all().statusCode(201);
    }

    @Title("Verify if the product was added to the application")
    @Test
    public void test02() {

        name=name+"_Updated";
        //HashMap<String, Object> value =

       /* String p1 = "findAll{it.name=='";
        String p2 = "'}.get(0)";

        //Hashmap of Util
        HashMap<String, Object> value =
                SerenityRest.rest()
                        .given().log().all()
                        .when()
                        .get(EndPoints.GET_ALL_PRODUCTS)
                        .then()
                        .statusCode(200)
                        .extract()
                        //.path(p1 + name + p2); //this will bring response in hashmap
                        .path(p1 + name + p2); //this will bring response in hashmap
        assertThat(value, hasValue(name));
        System.out.println(value);
        productId = (int) value.get("id");*/
    }

    @Title("Update the user information and verify the updated information")
    @Test
    public void test03() {

        //variables declare in 2 parts from path below
        String p1 = "findAll{it.name=='";
        String p2 = "'}.get(0)";

        name = name + "Updated";

        ProductsPojo productsPojo = new ProductsPojo();
        productsPojo.setName(name);
        productsPojo.setType(type);
        productsPojo.setPrice(price);
        productsPojo.setUpc(upc);
        productsPojo.setShipping(shipping);
        productsPojo.setDescription(description);
        productsPojo.setManufacturer(manufacturer);
        productsPojo.setModel(model);
        productsPojo.setUrl(url);
        productsPojo.setImage(image);

        SerenityRest.rest().given().log().all()
                .header("Content-Type", "application/json")
                .pathParam("id", productId )
                .body(productsPojo)
                .when()
                .put(EndPoints.UPDATE_PRODUCT_BY_ID)  //pass pathParam above
                .then().log().all().statusCode(200);

        //Hashmap of Util
        HashMap<String, Object> value =
                SerenityRest.rest()
                        .given().log().all()
                        .when()
                        .get(EndPoints.GET_ALL_PRODUCTS)
                        .then()
                        .statusCode(200)
                        .extract()
                        .path(p1 + name + p2); //this will bring response in hashmap
        assertThat(value, hasValue(name));
        System.out.println(value);
        productId = (int) value.get("id");

    }

    @Title("Delete the product and verify if the product is deleted!")
    @Test
    public void test04() {
        SerenityRest.rest()
                .given()
                .pathParam("id", productId)
                .when()
                .delete(EndPoints.DELETE_PRODUCT_BY_ID)
                .then()
                .statusCode(204);

        SerenityRest.rest()
                .given()
                .pathParam("id", productId)
                .when()
                .delete(EndPoints.GET_SINGLE_PRODUCT_BY_ID)
                .then()
                .statusCode(404);


    }

    }

