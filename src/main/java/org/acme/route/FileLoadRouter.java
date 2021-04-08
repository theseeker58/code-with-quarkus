package org.acme.route;

import org.acme.jaxb.ObjectFactory;
import org.acme.processor.Mapper;
import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.converter.jaxb.JaxbDataFormat;
import org.apache.camel.spi.DataFormat;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.xml.bind.JAXBContext;

@ApplicationScoped
public class FileLoadRouter extends RouteBuilder {

    @ConfigProperty(name = "code-with-quarkus.inputFolder")
    String inputFolder;

    private Mapper bookMapper;

    public FileLoadRouter() {
    }

    @Inject
    public FileLoadRouter(Mapper mapper) {
        this.bookMapper = mapper;
    }

    @Override
    public void configure() throws Exception {

        DataFormat jaxb = new JaxbDataFormat(JAXBContext.newInstance(ObjectFactory.class));

        from(String.format("file:%s?noop=true&moveFailed=${file:name}.error&include=.*.xml", inputFolder))
                .streamCaching()
                .log("Starting file process ${file:onlyname} " + "${file:size}")
                .split().tokenizeXML("book", "*").streaming()
                .unmarshal(jaxb)
                .bean(bookMapper)
                .log("${body.toString()}")
                .choice()
                    .when(header(Exchange.SPLIT_COMPLETE))
                        .log("File ${file:onlyname} completed")
                .endChoice();
    }

}
