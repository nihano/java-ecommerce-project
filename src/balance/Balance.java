package balance;

import java.util.UUID;

public abstract class Balance {

    private UUID customerId; //WHAT IS THIS ID FOR?
    /* --> what I understood is variable type is UUID, and we are going to
     get Customer UUID and pass into balance obj constructor.
     since we have CUSTOMER_LIST we can get id from the list.
     */
    private double balance;

    public Balance(UUID customerId, double balance) {
        this.customerId = customerId;
        this.balance = balance;
    }

    public abstract Double addBalance(Double additionalBalance);
    //first concrete class needs to override this method. if there is any abstract class which extends Balance it does not need to override.
    //abstract class can have abstract method.


    public UUID getCustomerId() {
        return customerId;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(double balance) { // we need to update balance with additionalBalance per requirement, so we need setter
        this.balance = balance;
    }
}
