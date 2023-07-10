package barryspeanuts.helper;

import barryspeanuts.model.Address;
import barryspeanuts.model.Customer;
import barryspeanuts.model.Purchase;

public class helper {
    public static Address getAddress(){
        return new Address("1600 Pennsylvania Avenue NW.", "West Wing", "Washington", "D.C.", "20500","USA" );
    }

    public static Customer getCustomer(){
        return new Customer("Josiah", "Bartlet", "prez@whitehouse.gove", "202 456 1414", getAddress());
    }

    public static Purchase getPurchase(){
        return new Purchase(getCustomer(), 3, 12.99 ,5);
    }
}
