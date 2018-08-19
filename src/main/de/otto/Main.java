package de.otto;

import de.otto.customer.Creditcard;
import de.otto.customer.Customer;

public class Main {

    public static void main(String[] args) {
        Customer customer = new Customer("Miri", 37, new Creditcard("123", "07-12-2018"));
        System.out.println("Hello " + customer.getName() + "!");
    }
}
