package de.otto.article;

public class Article {
    private String number;
    private String category;

    public Article(String number, String category) {
        this.number = number;
        this.category = category;
    }

    public String getCategory() {
        return category;
    }

    public String getNumber() {
        return number;
    }
}
