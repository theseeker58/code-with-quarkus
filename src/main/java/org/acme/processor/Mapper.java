package org.acme.processor;

import org.acme.jaxb.BookForm;
import org.acme.jaxb.BooksForm;
import org.acme.mapper.JaxbModelMapper;
import org.acme.model.Book;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;

@ApplicationScoped
@Named
public class Mapper {
    private JaxbModelMapper jaxbModelMapper;

    public Mapper() {
        super();
    }

    /*@Inject
    public Mapper(JaxbModelMapper mapper) {
        this.jaxbModelMapper = mapper;
    }*/

    public Book map(BooksForm books ) {
        BookForm bookForm = books.getBook().get(0);
        Book response;
        response = hardMap(bookForm);
        //response = jaxbModelMapper.mapToModel(bookForm);
        return response;
    }

    private Book hardMap(BookForm bookForm) {
        Book book = new Book();
        book.setKey(bookForm.getId());
        book.setAuthor(bookForm.getAuthor());
        book.setPrice(bookForm.getPrice());
        book.setPublicationDate(bookForm.getPubDate());
        return book;
    }

}
