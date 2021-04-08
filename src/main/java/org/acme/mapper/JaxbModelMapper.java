package org.acme.mapper;

import org.acme.jaxb.BookForm;
import org.acme.model.Book;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "cdi", uses = org.acme.processor.Mapper.class, injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface JaxbModelMapper {
    @Mapping(source = "id", target = "key")
    @Mapping(source = "author", target = "author")
    @Mapping(source = "price", target = "price")
    @Mapping(source = "pubDate", target = "publicationDate")
    Book mapToModel(BookForm bookForm);
}
