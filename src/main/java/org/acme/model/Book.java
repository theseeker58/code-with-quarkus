package org.acme.model;

import org.infinispan.protostream.annotations.ProtoField;

import java.util.Date;


public class Book {

    @ProtoField(number = 1, required = true)
    String key;

    @ProtoField(number = 2, required = true)
    String author;

    @ProtoField(number = 3, required = true)
    Date publicationDate;

    @ProtoField(number = 4, required = true)
    float price;

    public Book() {
        super();
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Date getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(Date publicationDate) {
        this.publicationDate = publicationDate;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "I'm a Book{" +
                "key='" + key + '\'' +
                ", author='" + author + '\'' +
                ", publicationDate=" + publicationDate +
                ", price=" + price +
                '}';
    }
}
