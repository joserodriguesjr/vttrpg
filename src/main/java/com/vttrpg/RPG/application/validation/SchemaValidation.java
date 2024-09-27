package com.vttrpg.RPG.application.validation;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.vttrpg.RPG.application.form.FieldForm;
import org.everit.json.schema.ValidationException;
import org.everit.json.schema.loader.SchemaLoader;
import org.everit.json.schema.Schema;
import java.io.InputStream;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.springframework.stereotype.Component;

@Component
public class SchemaValidation {

    private final ObjectMapper objectMapper;
    private final Schema fieldsSchema;

    public SchemaValidation(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
        this.objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        this.objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);

        this.fieldsSchema = loadSchema("fields.schema.json");
    }

    private Schema loadSchema(String schemaPath) {
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(schemaPath)) {
            if (inputStream == null) {
                throw new RuntimeException("Schema file not found: " + schemaPath);
            }
            JSONObject rawSchema = new JSONObject(new JSONTokener(inputStream));
            return SchemaLoader.load(rawSchema);
        } catch (Exception e) {
            throw new RuntimeException("Failed to load schema", e);
        }
    }

    public <T extends FieldForm> void validate(T fieldForm) throws ValidationException {
        try {
            JSONObject fieldFormJson = new JSONObject(objectMapper.writeValueAsString(fieldForm));
            fieldsSchema.validate(fieldFormJson);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failed to validate field", e);
        }
    }
}
