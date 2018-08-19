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
        // given
        Customer customer = new Customer("someName", 42, new Creditcard("123", "someDate"));

        // when
        String name = customer.getName();

        // then
        assertEquals(name, "someName");
    }

    @Test
    public void shouldReturnTrueIfUserIsFullAged() {
        // given
        Customer customer = new Customer("someName", 42, new Creditcard("123", "someDate"));

        // when
        boolean fullAged = customer.isFullAged();

        // then
        assertTrue(fullAged);
    }

    @Test
    public void shouldReturnFalseIfUserIsFullAged() {
        // given
        Customer customer = new Customer("someName", 17, new Creditcard("123", "someDate"));

        // when
        boolean fullAged = customer.isFullAged();

        // then
        assertFalse(fullAged);
    }

    @Test
    public void shouldReturnTrueIfUserIsFullAgedAndHasACreditCard() {
        // given
        Customer customer = new Customer("someName", 42, new Creditcard("123", "someDate"));

        // when
        boolean valid = customer.isValid();

        // then
        assertTrue(valid);
    }

    @Test
    public void shouldReturnFalseIfUserIsFullAgedAndHasNoCreditCard() {
        // given
        Customer customer = new Customer("someName", 42, null);

        // when
        boolean valid = customer.isValid();

        // then
        assertFalse(valid);
    }

    @Test
    public void shouldReturnFalseIfUserIsFullAgedAndCreditCardNumberIsEmpty() {
        // given
        Customer customer = new Customer("someName", 42, new Creditcard("", "someDate"));

        // when
        boolean valid = customer.isValid();

        // then
        assertFalse(valid);
    }

    @Test
    public void shouldReturnWishlistSize() {
        // given
        Customer customer = new Customer("someName", 42, new Creditcard("", "someDate"));

        // when
        customer.addToWishlist("someArticle1");
        customer.addToWishlist("someArticle2");
        customer.addToWishlist("someArticle3");
        customer.addToWishlist("someArticle3");
        int wishlistSize = customer.getWishlistSize();

        // then
        assertEquals(wishlistSize, 3);
    }

    @Test
    public void shouldReturnFalseIfArticleIsAlreadyInWishlist() {
        // given
        Customer customer = new Customer("someName", 42, new Creditcard("", "someDate"));

        // when
        boolean firstAdd = customer.addToWishlist("someArticle1");
        boolean secondAdd = customer.addToWishlist("someArticle1");
        int wishlistSize = customer.getWishlistSize();

        // then
        assertTrue(firstAdd);
        assertFalse(secondAdd);
        assertEquals(wishlistSize, 1);
    }

    @Test
    public void shouldNotAllowBuyingIfCustomerIsUnvalid() {
        // given
        Customer customer = new Customer("someName", 42, null);

        // when
        boolean bought = customer.buy(new Article("someNumber", "someCategory"));

        // then
        assertFalse(bought);
    }

    @Test
    public void shouldAddArticleToBoughtArticles() {
        // given
        Customer customer = new Customer("someName", 42, new Creditcard("someNumber", "someDate"));

        // when
        customer.buy(new Article("someNumber1", "someCategory1"));
        customer.buy(new Article("someNumber2", "someCategory1"));
        customer.buy(new Article("someNumber3", "someCategory2"));

        // then
        HashMap<String, List<String>> boughtProducts = new HashMap<>();
        ArrayList<String> articles = new ArrayList<>();
        articles.add("someNumber1");
        articles.add("someNumber2");
        boughtProducts.put("someCategory1", articles);
        boughtProducts.put("someCategory2", Collections.singletonList("someNumber3"));
        assertEquals(customer.getBoughtArticlesPerCategory(), boughtProducts);
    }

    @Test
    public void shouldCountCustomers() {
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
}