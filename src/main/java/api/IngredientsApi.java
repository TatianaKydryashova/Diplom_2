package api;

import io.qameta.allure.Step;
import org.apache.commons.lang3.RandomStringUtils;

import static io.restassured.RestAssured.given;

import java.util.ArrayList;

public class IngredientsApi extends Base  {
    @Step("Get all ingredients list")
    public ArrayList<String> getAllIngredientsList() {
        return given()
                .spec(getBaseSpec())
                .when()
                .get(EndpointsOrderApi.GET_INGREDIENTS)
                .then()
                .extract()
                .body()
                .path("data._id");
    }

    @Step("Create list ingredient for creating an order")
    public ArrayList<String> getIngredientsOrderList(ArrayList<String> ingredientsList) {
        ArrayList<String> orderIngredients = new ArrayList<>();
        orderIngredients.add(ingredientsList.get(0));
        orderIngredients.add(ingredientsList.get(1));
        orderIngredients.add(ingredientsList.get(2));
        return orderIngredients;
    }

    @Step("Create list ingredient for creating an order with incorrect id")
    public ArrayList<String> getIngredientsOrderListIncorrect() {
        ArrayList<String> orderIngredients = new ArrayList<>();
        orderIngredients.add(RandomStringUtils.randomAlphabetic(10));
        orderIngredients.add(RandomStringUtils.randomAlphabetic(10));
        orderIngredients.add(RandomStringUtils.randomAlphabetic(10));
        return orderIngredients;
    }
}
