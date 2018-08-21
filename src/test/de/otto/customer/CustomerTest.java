package de.otto.customer;

import de.otto.article.Article;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import static org.testng.AssertJUnit.assertFalse;


@Test
public class CustomerTest {
    @Test
    public void shouldReturnName() {
        // Dieser Test soll überprüfen, ob der Name, den ich im Konstruktor angegeben habe (1. Parameter, hier: "someName"),
        // wirklich im Customer gespeichert wird und zurückgegeben wird, wenn wir die Methode .getName() aufrufen
        // given
        Customer customer = new Customer("someName", 42, new Creditcard("123", "someDate"));

        // when
        String name = customer.getName();

        // then
        assertEquals(name, "someName");
    }

    @Test
    public void shouldReturnOtherName() {
        // Damit niemand auf die Idee kommt, in der Methode .getName() immer "someName" zurückzugeben, ist hier ein
        // zweiter Test mit einem anderen Namen
        // given
        Customer customer = new Customer("someOtherName", 42, new Creditcard("123", "someDate"));

        // when
        String name = customer.getName();

        // then
        assertEquals(name, "someOtherName");
    }

    @Test
    public void shouldReturnTrueIfUserIsFullAged() {
        // Dieser Test soll überprüfen, ob der Customer volljährig ist. Die Methode soll true zurückgeben, weil
        // der Customer 42 (2. Parameter) ist
        // given
        Customer customer = new Customer("someName", 42, new Creditcard("123", "someDate"));

        // when
        boolean fullAged = customer.isFullAged();

        // then
        assertTrue(fullAged);
    }

    @Test
    public void shouldReturnFalseIfUserIsFullAged() {
        // Dieser Test soll überprüfen, ob die Methode .isFullAged auch wirklich false zurückgibt, wenn der Customer
        // unter 18 (2. Parameter, hier: 17) ist
        // given
        Customer customer = new Customer("someName", 17, new Creditcard("123", "someDate"));

        // when
        boolean fullAged = customer.isFullAged();

        // then
        assertFalse(fullAged);
    }

    @Test
    public void shouldReturnTrueIfUserIsFullAgedAndHasACreditCard() {
        // Dieser Test soll überprüfen, ob die Methode .isValid() true zurückgibt, wenn der Customer min. 18 (2. Parameter, hier: 42)
        // ist und das Feld customer.creditcard gesetzt ist (d.h. der Wert des Feldes ist ungleich null)
        // given
        Customer customer = new Customer("someName", 42, new Creditcard("123", "someDate"));

        // when
        boolean valid = customer.isValid();

        // then
        assertTrue(valid);
    }

    @Test
    public void shouldReturnFalseIfUserIsNotFullAgedAndHasACreditCard() {
        // Dieser Test soll überprüfen, ob die Methode .isValid() false zurückgibt, wenn der Customer unter 18 (2. Parameter, hier: 17)
        // ist und das Feld customer.creditcard gesetzt ist (d.h. der Wert des Feldes ist ungleich null)
        // given
        Customer customer = new Customer("someName", 17, new Creditcard("123", "someDate"));

        // when
        boolean valid = customer.isValid();

        // then
        assertFalse(valid);
    }

    @Test
    public void shouldReturnFalseIfUserIsFullAgedAndHasNoCreditCard() {
        // Dieser Test soll überprüfen, ob die Methode .isValid() false zurückgibt, wenn der Customer zwar min. 18
        // (2. Parameter, hier: 42) aber keine Kreditkarte hinterlegt ist (customer.creditcard ist gleich null)
        // given
        Customer customer = new Customer("someName", 42, null);

        // when
        boolean valid = customer.isValid();

        // then
        assertFalse(valid);
    }

    @Test
    public void shouldReturnFalseIfUserIsFullAgedAndCreditCardNumberIsEmpty() {
        // Dieser Test soll überprüfen, ob die Methode .isValid() false zurückgibt, wenn der Customer zwar min. 18 (2. Parameter, hier: 42) ist
        // , er auch eine Kreditkarte besitzt, aber die Kreditkartennummer leer (="") ist
        // given
        Customer customer = new Customer("someName", 42, new Creditcard("", "someDate"));

        // when
        boolean valid = customer.isValid();

        // then
        assertFalse(valid);
    }

    @Test
    public void shouldReturnWishlistSize() {
        // Dieser Test soll überprüfen, ob die .getWishlistSize() 3 zurückgibt, wenn ich vorher 4 Artikel zur Wishlist
        // hinzugefügt habe, aber die letzten beiden identisch sind. Wir legen also fest, dass man jeden Artikel nur
        // einmal zur Wishlist hinzufügen kann
        // given
        Customer customer = new Customer("someName", 42, new Creditcard("", "someDate"));
        customer.addToWishlist("someArticle1");
        customer.addToWishlist("someArticle2");
        customer.addToWishlist("someArticle3");
        customer.addToWishlist("someArticle3");

        // when
        int wishlistSize = customer.getWishlistSize();

        // then
        assertEquals(wishlistSize, 3);
    }

    @Test
    public void shouldReturnTrueIfArticleIsAlreadyInWishlist() {
        // Dieser Test soll überprüfen, ob die Methode .addToWishlist(articleNumber) true zurückgibt, wenn der Artikel
        // vorher nicht in der Wishlist war
        // given
        Customer customer = new Customer("someName", 42, new Creditcard("", "someDate"));

        // when
        customer.addToWishlist("someArticle1");
        boolean secondAdd = customer.addToWishlist("someArticle1");

        // then
        assertTrue(secondAdd);
    }

    @Test
    public void shouldReturnFalseIfArticleIsAlreadyInWishlist() {
        // Dieser Test soll überprüfen, ob die Methode .addToWishlist(articleNumber) false zurückgibt, wenn der Artikel
        // bereits vorher in der Wishlist war
        // given
        Customer customer = new Customer("someName", 42, new Creditcard("", "someDate"));

        // when
        customer.addToWishlist("someArticle1");
        boolean secondAdd = customer.addToWishlist("someArticle1");

        // then
        assertFalse(secondAdd);
    }

    @Test
    public void shouldNotAllowBuyingIfCustomerIsUnvalid() {
        // Dieser Test soll überprüfen, ob die Methode .buy(article) false zurückgibt, wenn der Customer nicht valide ist.
        // Hier ist seine Kreditkarte nicht gesetzt (customer.creditcard == null).
        // Tipp: Nutzt die bereits geschriebene Funktion .isValid()
        // given
        Customer customer = new Customer("someName", 42, null);

        // when
        boolean bought = customer.buy(new Article("someNumber", "someCategory"));

        // then
        assertFalse(bought);
    }

    @Test
    public void shouldAddArticleToBoughtArticles() {
        // Dieser Test soll überprüfen, ob die Methode .buy(article) die gekauften Artikel auch wirklich zur
        // Map customer.boughtArticlesPerCategory hinzufügt. Die Map ist so aufgebaut, dass wir uns merken, welche
        // Artikel der Customer in welcher Kategorie gekauft hat.
        // Beispiel:
        // {
        //   "mode": ["1234", "567"],
        //   "technik": ["8910"]
        // }
        // Der Key ist also der Name der Kategorie und der Value ist die Liste der gekauften Artikelnummern
        // given
        Customer customer = new Customer("someName", 42, new Creditcard("someNumber", "someDate"));
        HashMap<String, List<String>> expectedBoughtProducts = new HashMap<>();
        ArrayList<String> articles = new ArrayList<>();
        articles.add("someNumber1");
        articles.add("someNumber2");
        expectedBoughtProducts.put("someCategory1", articles);
        expectedBoughtProducts.put("someCategory2", Collections.singletonList("someNumber3"));

        // when
        customer.buy(new Article("someNumber1", "someCategory1"));
        customer.buy(new Article("someNumber2", "someCategory1"));
        customer.buy(new Article("someNumber3", "someCategory2"));

        // then
        assertEquals(customer.getBoughtArticlesPerCategory(), expectedBoughtProducts);
    }

    @Test
    public void shouldCountCustomers() {
        // Dieser Test soll überprüfen, ob die statische Methode .getCustomerCount() die richtige Anzahl an erzeugten
        // Customern (hier: 3) ausgibt.
        // given
        Customer.resetCustomerCount();
        new Customer("someName", 42, null);
        new Customer("someName", 42, null);
        new Customer("someName", 42, null);

        // when
        int customerCount = Customer.getCustomerCount();

        // then
        assertEquals(customerCount, 3);
    }

    @Test
    public void shouldCountCustomers2() {
        // Damit niemand auf die Idee kommt, immer 3 zurückzugeben, ist hier ein Test mit 2 Customern
        // given
        Customer.resetCustomerCount();
        new Customer("someName", 42, null);
        new Customer("someName", 42, null);

        // when
        int customerCount = Customer.getCustomerCount();

        // then
        assertEquals(customerCount, 2);
    }
}
