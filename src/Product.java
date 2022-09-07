import category.Category;

import java.time.LocalDateTime;
import java.util.UUID;

public class Product {

    private UUID id;
    private String name;
    private Double price;
    private Integer stock;
    private Integer remainingStock;
    private UUID categoryID;


    public Product(UUID id, String name, Double price, Integer stock, Integer remainingStock, UUID categoryID) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.remainingStock = remainingStock;
        this.categoryID = categoryID;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Double getPrice() {
        return price;
    }

    public Integer getStock() {
        return stock;
    }

    public Integer getRemainingStock() {
        return remainingStock;
    }

    public UUID getCategoryID() {
        return categoryID;
    }

    public String getCategoryName() throws Exception { //CAN WE DO THIS STATIC WHY OR WHY NOT?
        //we have category id. go to database and check all the category ids
        //and find the matching one and return its name with the corresponding that category id

        for (Category category : StaticConstants.CATEGORY_LIST) {
            if (getCategoryID().toString().equals(category.getId().toString())) { // WHY DID WE USE toString()? CAN WE USE ==  AREN'T THEY SAME OBJs?
                return category.getName();
            }
        }
        //return null; ==> we do not return null because user cannot understand what it means
        //also, in main method we call this getCategoryName() method, and it returns string if we want to use any string method,
        //if it returns null, when we use string methods it is going to give NullPointerException
        throw new Exception("Category not found, " + getName()); // if it cannot find category name it does not exist throw exception
    }

    public LocalDateTime getDeliveryDueDate() throws Exception {
        for (Category category: StaticConstants.CATEGORY_LIST){
            if (getCategoryID().toString().equals(category.getId().toString())){
                return category.findDeliveryDueDate();
            }
        }

        throw new Exception("Category could not find");
    }
}
