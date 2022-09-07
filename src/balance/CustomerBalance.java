package balance;

import java.util.UUID;

public class CustomerBalance extends Balance{


    public CustomerBalance(UUID customerId, double balance) {// we called constructor from Balance
        super(customerId, balance);
    }

    @Override
    public Double addBalance(Double additionalBalance) { // actually we do not need to return type we're just updating the balance
        setBalance(getBalance() + additionalBalance);
        return getBalance();
    }

}
