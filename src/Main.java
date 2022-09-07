import balance.Balance;
import balance.CustomerBalance;
import balance.GiftCardBalance;
import category.Category;
import discount.Discount;

import java.util.*;


public class Main {

    public static void main(String[] args) { // throws Exception (we deleted wwe used try catch) we created exception in product class, so we handled in this class as well because we call getCategoryName() method here
        //WHY THROWS COULD NOT HANDLE THE EXCEPTION, WE ADDED TRY CATCH BLOCK LATER?
        DataGenerator.createCustomer();
        DataGenerator.createCategory(); // createCategory needs to be before createProduct because we used CATEGORY_LIST in the product constructor in data generator.
        DataGenerator.createProduct();
        DataGenerator.createBalance();
        DataGenerator.createDiscount();

        Scanner scanner = new Scanner(System.in);

        System.out.println("Select Customer");
        for (int i = 0; i < StaticConstants.CUSTOMER_LIST.size(); i++) {
            System.out.println("Type " + i + " for customer:" + StaticConstants.CUSTOMER_LIST.get(i).getUsername());
            //I DID NOT GET WHY WE DID THIS. WHO WILL SELECT THE CUSTOMER IN THE APP?
            // THESE ARE TWO DIFFERENT PEOPLE.
        }

        Customer customer = StaticConstants.CUSTOMER_LIST.get(scanner.nextInt());
        Cart cart = new Cart(customer);


        while (true) {
            System.out.println("What would you like to do> just type id for selection");
            //WHICH ID IS THIS?
            for (int i = 0; i < prepareMenuOptions().length; i++) {
                System.out.println(i + "-" + prepareMenuOptions()[i]);
            }
            int menuSelection = scanner.nextInt();

            switch (menuSelection) {
                case 0: //list categories
                    for (Category category : StaticConstants.CATEGORY_LIST) {
                        System.out.println("Category code: " + category.generateCategoryCode() + "category name: " + category.getName());
                    }
                    break;
                case 1: //list products //product name, product category name
                    try {//  we created exception in Product clas for getCategoryName() method so we use try cath here
                        for (Product product : StaticConstants.PRODUCT_LIST) {
                            System.out.println("Product name: " + product.getName() + "Product Category Name:" + product.getCategoryName()); // we created method later to get category name
                        }
                    } catch (Exception e) {
                        System.out.println("Product could not be printed because category not for product name:" + e.getMessage().split(",")[1]); // we got the name from exception object that we created, we got the message which is String then converted to array etc. ==>  throw new Exception("Category not found, " + getName()); in product class
                    }
                    break;
                case 2: //list discounts
                    for (Discount discount : StaticConstants.DISCOUNT_LIST) {
                        System.out.println("Discount name:" + discount.getName() + " Discount threshold amount:" + discount.getThresholdAmount());
                    }
                    break;
                case 3:// see balance
                    CustomerBalance cBalance = findCustomerBalance(customer.getId());
                    GiftCardBalance gBalance = findGiftCardBalance(customer.getId());
                    double totalBalance = cBalance.getBalance() + gBalance.getBalance();
                    System.out.println("Total Balance:" + totalBalance);
                    System.out.println("Customer Balance:" + cBalance.getBalance());
                    System.out.println("Gift Card Balance:" + gBalance.getBalance());
                    break;
                case 4://add balance
                    CustomerBalance customerBalance = findCustomerBalance(customer.getId());
                    GiftCardBalance giftCardBalance = findGiftCardBalance(customer.getId());
                    System.out.println("Which account would you like to add?");
                    System.out.println("Type 1 for Customer Balance: " + customerBalance.getBalance());
                    System.out.println("Type 2 for Gift Card Balance: " + giftCardBalance.getBalance());
                    int balanceSelection = scanner.nextInt();
                    System.out.println("How much would you like to add?");
                    double additionalAmount = scanner.nextInt(); // we kept nextInt() casting, I don't know why?
                    switch (balanceSelection) {
                        case 1:
                            customerBalance.addBalance(additionalAmount);
                            System.out.println("New Customer Balance:" + customerBalance.getBalance());
                            break;
                        case 2:
                            giftCardBalance.addBalance(additionalAmount);
                            System.out.println("New Gift Card Balance: " + giftCardBalance.getBalance());
                            break;
                    }
                    break;
                case 5: // see cart
                    Map<Product, Integer> map = new HashMap<>();
                    cart.setProductMap(map);
                    while (true) {//to add as much product as we want we made this infinite loop
                        System.out.println("Which product would you like to add? For exit product selection type: exit");
                        for (Product product : StaticConstants.PRODUCT_LIST) {
                            try {
                                System.out.println("id:" + product.getId() + "price:" + product.getPrice() +
                                        "product category:" + product.getCategoryName() +
                                        "stock:" + product.getRemainingStock() +
                                        "product delivery due:" + product.getDeliveryDueDate());
                            } catch (Exception e) {
                                System.out.println(e.getMessage());
                            }

                        }

                        String productId = scanner.next(); // user going to choose the product by typing productId?

                        try {
                            Product product = findProductById(productId);
                            if (!putItemToCartIfStockAvailable(cart, product)){
                                System.out.println("Stock is insufficient. Please try again.");
                                continue;
                            }
                        } catch (Exception e) {
                            System.out.println("Product does not exist. Please try again.");
                            continue; //to go back to beginning of the loop
                        }

                        System.out.println("Do you want to add more product. Type Y for adding more, N for exit.");
                        String decision = scanner.next();
                        if (!decision.equals("Y")){
                            break; //exit the while loop
                        }

                    }
                    break;
                case 6: // place an order
                    break;
                case 7:
                    break;
                case 8:
                    break;
                case 9:
                    break;

                default:
            }

        }


    }

    private static boolean putItemToCartIfStockAvailable(Cart cart, Product product) {
        System.out.println("Please provide the product count:");
        Scanner scanner = new Scanner(System.in);
        int count = scanner.nextInt();
        //how many existing product in your cart?
        Integer cartCount =cart.getProductMap().get(product);
        if (cartCount!=null && product.getRemainingStock()>cartCount+count){
            cart.getProductMap().put(product, cartCount+count);
            return true;

        }else if (product.getRemainingStock()>=count){
            cart.getProductMap().put(product, count);
            return true;
        }
        return false;
    }

    private static Product findProductById(String productId) throws Exception {
        for (Product product: StaticConstants.PRODUCT_LIST){
            if (product.getId().toString().equals(productId)){
                return product;
            }
        }
        throw new Exception("Product not found");
    }


    private static CustomerBalance findCustomerBalance(UUID customerId) {
        for (Balance customerBalance : StaticConstants.CUSTOMER_BALANCE_LIST) {
            if (customerBalance.getCustomerId().toString().equals(customerId.toString())) {
                return (CustomerBalance) customerBalance;
            }
        }
        CustomerBalance customerBalance = new CustomerBalance(customerId, 0d);
        StaticConstants.CUSTOMER_BALANCE_LIST.add(customerBalance); // this is not in the DB so we added.
        return customerBalance;
    }

    private static GiftCardBalance findGiftCardBalance(UUID customerId) {
        for (Balance giftCardBalance : StaticConstants.GIFT_CARD_BALANCE_LIST) {
            if (giftCardBalance.getCustomerId().toString().equals(customerId.toString())) {
                return (GiftCardBalance) giftCardBalance;
            }
        }
        GiftCardBalance giftCardBalance = new GiftCardBalance(customerId, 0d);
        StaticConstants.GIFT_CARD_BALANCE_LIST.add(giftCardBalance);
        return giftCardBalance;
    }


    private static String[] prepareMenuOptions() { // method static because we are going to call in main method. main method is static. static method only can call static methods.

        return new String[]{"List Categories", "List Products", "List Discounts", "See Balance",
                "Add Balance", "Place an order", "See cart", "See order details", "See your address", "Close App"};
        //I DID NOT GET WHO IS LOGGING INTO THIS APP IS IT CUSTOMER OR WHO?
        // WE DID NOT CREATE ANYTHING RELATED TO LOG IN. HOW ARE WE GOING TO CLOSE THE APP.


    }

}

/*
Requirements:

Design DB(Database)

Create menu items
    -Choose your customer
    -Buy product

Write business logic for these menu items

-----------------------------

Create Customer

-with username, email
-with username, email, address (can have more than one address)

-Address should be created with these infos:
    -Name, Street, Zipcode, Additional Line, State

-------------------------------

Create Product

- id, name, price, stock, remaining stock, category

Create Category

-All categories can have some common things
-Every category can have something unique  as well

-Electronic : id, name, calculate delivery time (plus 4 days), create category code (unique)
-Furniture :  id, name, calculate delivery time (plus 1 day), create category code (same)
-SkinCare :  id, name, calculate delivery time (now), create category code (same)

----------------------------------
BALANCE
Customer can have 2 kinds of balance in their account
1) Customer Balance: customerId, balance, increase balance with additional amount
2) Gift Card Balance: customerId, balance, increase balance with additional amount
+ 10%  additional amount to what customer put

----------------------------------
DISCOUNT

Amount Base Discount: discount id, discount name, threshold amount, discount amount, need
calculate final amount after discount

Rate Based Discount: discount id, name, discount rate, threshold amount, need to calculate final
amount after discount




 */