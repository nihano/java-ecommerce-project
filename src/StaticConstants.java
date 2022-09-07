import balance.Balance;
import category.Category;
import discount.Discount;

import java.util.ArrayList;
import java.util.List;

public class StaticConstants {

    public static final List<Customer> CUSTOMER_LIST = new ArrayList<>();  // this is kind of database it is not gonna change so it is static and final
    public static final List<Category> CATEGORY_LIST = new ArrayList<>();
    public static final List<Product> PRODUCT_LIST = new ArrayList<>();
    public static final List<Balance> CUSTOMER_BALANCE_LIST = new ArrayList<>();
    public static final List<Balance> GIFT_CARD_BALANCE_LIST = new ArrayList<>();
    public static final List<Discount> DISCOUNT_LIST = new ArrayList<>(); // we did not specify discount types  here
}
