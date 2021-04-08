package org.acme.model;

import org.infinispan.protostream.SerializationContextInitializer;
import org.infinispan.protostream.annotations.AutoProtoSchemaBuilder;

@AutoProtoSchemaBuilder(
    includeClasses = {
        Book.class,
    },
    schemaPackageName = "catalog")
public interface CatalogSchema extends SerializationContextInitializer {
}
