package com.qelaj.trockenbau.app.entity;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.Base64;

public class BlobSerializer extends StdSerializer<Blob> {

    public BlobSerializer() {
        super(Blob.class);
    }

    @Override
    public void serialize(Blob value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        if (value != null) {
            byte[] bytes;
            try {
                bytes = value.getBytes(1, (int) value.length());
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            gen.writeString(Base64.getEncoder().encodeToString(bytes));
        }
    }
}
