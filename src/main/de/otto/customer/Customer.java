package de.otto.customer;

import de.otto.article.Article;

import java.util.*;

public class Customer {
    private static int customerCount = 0;
    private String name;
    private int age;
    private Creditcard creditcard;
    private Set<String> wishlist;

    private Map<String, List<String>> boughtArticlesPerCategory;

    public Customer(String name, int age, Creditcard creditcard) {
        this.name = name;
        this.age = age;
        this.creditcard = creditcard;
        this.wishlist = new HashSet<>();
        this.boughtArticlesPerCategory = new HashMap<>();
    }

    public static void resetCustomerCount() {
    }

    public static int getCustomerCount() {
        return 0;
    }

    public Map<String, List<String>> getBoughtArticlesPerCategory() {
        return Collections.emptyMap();
    }

    public int getWishlistSize() {
        return 0;
    }

    public boolean addToWishlist(String articleNumber) {
        return false;
    }

    public boolean isFullAged() {
        return false;
    }

    public String getName() {
        return "";
    }

    public boolean buy(Article article) {
        return false;
    }

    public boolean isValid() {
        return false;
    }
}
