package de.otto.customer;

public class Creditcard {
    private String number;
    private String expiryDate;

    public Creditcard(String number, String expiryDate) {
        this.number = number;
        this.expiryDate = expiryDate;
    }

    public String getNumber() {
        return number;
    }
}
