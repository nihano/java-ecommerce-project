import balance.Balance;
import balance.CustomerBalance;
import balance.GiftCardBalance;
import category.Category;
import category.Electronic;
import category.Furniture;
import category.SkinCare;
import discount.AmountBasedDiscount;
import discount.Discount;
import discount.RateBasedDiscount;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class DataGenerator {

    public static void createCustomer() {

        Address address1Customer1 = new Address("7925", "Jones Branch Dr", "Suite 3300", "22102", "VA");
        Address address2Customer1 = new Address("825", "GeorgeTown Pky", "Suite 5355", "22036", "VA");
        Address address1Customer2 = new Address("5924", "Lee Hwy", "House", "22044", "VA");

        List<Address> customer1AddressList = new ArrayList<>();
        customer1AddressList.add(address1Customer1);
        customer1AddressList.add(address2Customer1);

        Customer customer1 = new Customer(UUID.randomUUID(), "Ozzy", "ozzy@cydeo.com", customer1AddressList);
        Customer customer2 = new Customer(UUID.randomUUID(), "Mike", "mike@gmail.com");

        StaticConstants.CUSTOMER_LIST.add(customer1); //we created customer list in static constants class so we are adding customers to database
        StaticConstants.CUSTOMER_LIST.add(customer2);

    }

    public static void createCategory() {
        Category category1 = new Electronic(UUID.randomUUID(), "Electronic");
        Category category2 = new Furniture(UUID.randomUUID(), "Furniture");
        Category category3 = new SkinCare(UUID.randomUUID(), "SkinCare");

        StaticConstants.CATEGORY_LIST.add(category1);
        StaticConstants.CATEGORY_LIST.add(category2);
        StaticConstants.CATEGORY_LIST.add(category3);
    }

    public static void createProduct() {

        Product product1 =
                new Product(UUID.randomUUID(), "PS5", 230.72, 7, 7, StaticConstants.CATEGORY_LIST.get(0).getId()); // REVIEW AGAIN ==> I did not understand why we passed Electronic UUID id here instead of creating category code with overridden method (Which is String but argument here is UUID which does not make sense). I guess I did not understand the requirement. If requirement is the product id is same as the electronic category id (UUID) it is ok. What is the category code for?
//What I understood is, this is database, we write all this information manually and specific category id to this PS5 product.
//It is Electronic product, so we get the categoryId from CATEGORY_LIST, since it's ArrayList we retrieve the id via the index number.
//and first element of the CATEGORY_LIST is Electronic obj, so we get categoryId from its obj by using its getter
        Product product2 =
                new Product(UUID.randomUUID(), "XBOX", 120.34, 15, 15, StaticConstants.CATEGORY_LIST.get(0).getId());
        Product product3 =
                new Product(UUID.randomUUID(), "Chair", 30.87, 85, 85, StaticConstants.CATEGORY_LIST.get(1).getId());
        Product product4 =
                new Product(UUID.randomUUID(), "Milk", 2.87, 185, 85, UUID.randomUUID()); //we gave wrong id on purpose to see the logic of throwing an exception in Product class to protect our program from crashing if this happens

        StaticConstants.PRODUCT_LIST.add(product1);
        StaticConstants.PRODUCT_LIST.add(product2);
        StaticConstants.PRODUCT_LIST.add(product3);
        StaticConstants.PRODUCT_LIST.add(product4);

    }

    public static void createBalance(){

        Balance customerBalance = new CustomerBalance(StaticConstants.CUSTOMER_LIST.get(0).getId(), 450.00); // WHERE DOES THIS INITIAL BALANCE COME FROM?
        Balance giftCardBalance = new GiftCardBalance(StaticConstants.CUSTOMER_LIST.get(1).getId(), 500.00); // WHERE DOES THIS INITIAL BALANCE COME FROM?


        StaticConstants.CUSTOMER_BALANCE_LIST.add(customerBalance);
        StaticConstants.GIFT_CARD_BALANCE_LIST.add(giftCardBalance);
    }

    public static void createDiscount(){

        Discount amountBasedDiscount = new AmountBasedDiscount(UUID.randomUUID(), "Buy 250 Free 50", 250.00, 50.00);
        Discount rateBasedDiscount = new RateBasedDiscount(UUID.randomUUID(), "Buy 500 Free 15%", 500.00, 15.00);

        StaticConstants.DISCOUNT_LIST.add(amountBasedDiscount);
        StaticConstants.DISCOUNT_LIST.add(rateBasedDiscount);

    }






}
