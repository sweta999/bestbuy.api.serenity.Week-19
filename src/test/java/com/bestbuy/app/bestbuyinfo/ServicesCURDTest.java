package com.bestbuy.app.bestbuyinfo;

import com.bestbuy.app.constants.EndPoints;
import com.bestbuy.app.model.CategoriesPojo;
import com.bestbuy.app.model.ProductsPojo;
import com.bestbuy.app.model.ServicesPojo;
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
public class ServicesCURDTest extends TestBase {

    //declaring global variables - this will generate random
    static String name = "Best buy Laptop";
    static int serviceId;

    @Title("This will create a new Service")
    @Test
    public void test01() {

        ServicesPojo servicesPojo = new ServicesPojo();
        servicesPojo.setName(name);

        SerenityRest.rest().given().log().all()
                .header("Content-Type", "application/json")
                .body(servicesPojo)
                .when()
                .post()
                .then().log().all().statusCode(201);
    }

    @Title("Verify if the service was added to the application")
    @Test
    public void test02() {

        String p1 = "findAll{it.name=='";
        String p2 = "'}.get(0)";

        //Hashmap of Util
        HashMap<String, Object> value =
                SerenityRest.rest()
                        .given().log().all()
                        .when()
                        .get(EndPoints.GET_ALL_SERVICES)
                        .then()
                        .statusCode(200)
                        .extract()
                        //.path(p1 + name + p2); //this will bring response in hashmap
                        .path(p1 + name + p2); //this will bring response in hashmap
        assertThat(value, hasValue(name));
        System.out.println(value);
        serviceId = (int) value.get("id");
    }

    @Title("Update the user information and verify the updated information")
    @Test
    public void test03() {

        //variables declare in 2 parts from path below
        String p1 = "findAll{it.name=='";
        String p2 = "'}.get(0)";

        name = name + "Updated";

        ServicesPojo servicesPojo = new ServicesPojo();
        servicesPojo.setName(name);

        SerenityRest.rest().given().log().all()
                .header("Content-Type", "application/json")
                .pathParam("id", serviceId)
                .body(servicesPojo)
                .when()
                .put(EndPoints.UPDATE_SERVICE_BY_ID)  //pass pathParam above
                .then().log().all().statusCode(200);

        //Hashmap of Util
        HashMap<String, Object> value =
                SerenityRest.rest()
                        .given().log().all()
                        .when()
                        .get(EndPoints.GET_ALL_SERVICES)
                        .then()
                        .statusCode(200)
                        .extract()
                        .path(p1 + name + p2); //this will bring response in hashmap
        assertThat(value, hasValue(name));
        System.out.println(value);
        serviceId = (int) value.get("id");

    }

    @Title("Delete the category and verify if the category is deleted!")
    @Test
    public void test04() {
        SerenityRest.rest()
                .given()
                .pathParam("id", serviceId)
                .when()
                .delete(EndPoints.DELETE_PRODUCT_BY_ID)
                .then()
                .statusCode(204);

        SerenityRest.rest()
                .given()
                .pathParam("id", serviceId)
                .when()
                .delete(EndPoints.GET_SINGLE_PRODUCT_BY_ID)
                .then()
                .statusCode(404);


    }
}
