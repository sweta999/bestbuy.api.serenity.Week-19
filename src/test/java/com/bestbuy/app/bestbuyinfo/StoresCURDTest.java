package com.bestbuy.app.bestbuyinfo;

import com.bestbuy.app.constants.EndPoints;
import com.bestbuy.app.model.ProductsPojo;
import com.bestbuy.app.model.StoresPojo;
import com.bestbuy.app.testbase.TestBase;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Title;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.HashMap;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasValue;

//@RunWith(SerenityRunner.class)
public class StoresCURDTest extends TestBase {

    //declaring global variables - this will generate random
    StoresPojo storesPojo = new StoresPojo();
    static String name = "Rosewood";
    static String type = "BigBox";
    static String address = "14321 Ridge Dr";
    static String address2 = "";
    static String city = "Duluth";
    static String state = "MN";
    static String zip = "44309";
    static double lat = 0;
    static double lng = 0;
    static String hours = "Mon: 10-9; Tue: 10-9; Wed: 10-9; Thurs: 10-8; Fri: 10-9; Sat: 10-9; Sun: 10-8";
    static HashMap<String, Object> services = new HashMap<>();
    static int storesId;

    @Title("This will create a new Product")
    @Test
    public void test01() {

        StoresPojo storesPojo = new StoresPojo();
        storesPojo.setName(name);
        storesPojo.setType(type);
        storesPojo.setAddress(address);
        storesPojo.setAddress2(address2);
        storesPojo.setCity(city);
        storesPojo.setState(state);
        storesPojo.setZip(zip);
        storesPojo.setLat(lat);
        storesPojo.setLng(lng);
        storesPojo.setHours(hours);
        storesPojo.setServices(services);

        SerenityRest.rest().given().log().all()
                .header("Content-Type","application/json")
                .body(storesPojo)
                .when()
                .post()
                .then().log().all().statusCode(201);
    }

    @Title("Verify if the product was added to the application")
    @Test
    public void test02() {

        String p1 = "findAll{it.name=='";
        String p2 = "'}.get(0)";

        //Hashmap of Util
        HashMap<String, Object> value =
                SerenityRest.rest()
                        .given().log().all()
                        .when()
                        .get(EndPoints.GET_ALL_STORES)
                        .then()
                        .statusCode(200)
                        .extract()
                        //.path(p1 + name + p2); //this will bring response in hashmap
                        .path(p1 + name + p2); //this will bring response in hashmap
        assertThat(value, hasValue(name));
        System.out.println(value);
         storesId = (int) value.get("id");
    }

    @Title("Update the user information and verify the updated information")
    @Test
    public void test03() {

        //variables declare in 2 parts from path below
        String p1 = "findAll{it.name=='";
        String p2 = "'}.get(0)";

        name = name + "Updated";

        StoresPojo storesPojo = new StoresPojo();
        storesPojo.setName(name);
        storesPojo.setType(type);
        storesPojo.setAddress(address);
        storesPojo.setAddress2(address2);
        storesPojo.setCity(city);
        storesPojo.setState(state);
        storesPojo.setZip(zip);
        storesPojo.setLat(lat);
        storesPojo.setLng(lng);
        storesPojo.setHours(hours);

        SerenityRest.rest().given().log().all()
                .header("Content-Type", "application/json")
                .pathParam("id", storesId)
                .body(storesPojo)
                .when()
                .put(EndPoints.UPDATE_STORE_BY_ID)  //pass pathParam above
                .then().log().all().statusCode(200);

        //Hashmap of Util
        HashMap<String, Object> value =
                SerenityRest.rest()
                        .given().log().all()
                        .when()
                        .get(EndPoints.GET_ALL_STORES)
                        .then()
                        .statusCode(200)
                        .extract()
                        .path(p1 + name + p2); //this will bring response in hashmap
        assertThat(value, hasValue(name));
        System.out.println(value);
        storesId = (int) value.get("id");

    }

    @Title("Delete the product and verify if the product is deleted!")
    @Test
    public void test04() {
        SerenityRest.rest()
                .given()
                .pathParam("id", storesId)
                .when()
                .delete(EndPoints.DELETE_STORE_BY_ID)
                .then()
                .statusCode(204);

        SerenityRest.rest()
                .given()
                .pathParam("id", storesId)
                .when()
                .delete(EndPoints.GET_SINGLE_STORE_BY_ID)
                .then()
                .statusCode(404);


    }


}

