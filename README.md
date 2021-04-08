# code-with-quarkus project

This project shows how quarkus-infinispan-client stops generating serialization context initializer
and marshallers when using mapstruct-processor which generates mappers from specific Interfaces
containing mapping directives.

Run the application as-is (without mapstruct-processor) with
```shell script
./mvnw compile quarkus:dev
```
1. jaxb stuff is generated through xjc (driven by maven-jaxb2-plugin)
2. the FileLoadRouter will parse the file books.xm located under test/resources
3. each record is unmarshalled into org.acme.jaxb.BookForm 
4. mapped to org.acme.model.Book (with @ProtoField annotations) by the bean org.acme.process.Mapper
5. finally printed to the console (no remote cache is defined for simplicity).

As said, the org.acme.model.Book fields are annotated with @ProtoField and the class itself is included
in the org.acme.model.CatalogSchema interface that extends SerializationContextInitializer.
Therefore, after compiling, you will find CatalogSchemaImpl.class generated into the target directory besides
the Book$___marshaller_

In order to recreate the issue:
1. open the org.acme.processor.Mapper source,
2. uncomment the constructor with mapper injection
3. comment the hardMap method call
4. uncomment the direct call to the mapstruct mapper
5. at the same time in the pom uncomment configuration of mapstruct-processor under maven-compiler-plugin
6. and execute
```shell script
./mvnw compile quarkus:dev
```

You will notice that everything keeps working fine, but CatalogSchemaImpl.class and Book$___marshaller_
will not be generated anymore. So in a real project if there was the remote cache in place it would not work
due to the lack of the necessary classes

