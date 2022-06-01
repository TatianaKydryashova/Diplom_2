package requests;
import java.util.ArrayList;

public class CreateOrderRequest {
    public final ArrayList<String> ingredients;

    public CreateOrderRequest(ArrayList<String> ingredients) {
        this.ingredients = ingredients;
    }

    public static CreateOrderRequest generateOrderData(ArrayList<String> ingredientsOrderList) {
        return new CreateOrderRequest(ingredientsOrderList);
    }

    public static CreateOrderRequest generateOrderData() {
        return new CreateOrderRequest(null);
    }
}
