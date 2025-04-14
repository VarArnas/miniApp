package com.example.miniapp.converters;

import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.faces.convert.FacesConverter;

import java.util.UUID;

@FacesConverter(value = "uuidConverter", forClass = UUID.class)
public class UUIDConverter implements Converter<UUID> {
    @Override
    public UUID getAsObject(FacesContext ctx, UIComponent comp, String value) {
        return (value == null || value.isEmpty()) ? null : UUID.fromString(value);
    }

    @Override
    public String getAsString(FacesContext ctx, UIComponent comp, UUID value) {
        return (value == null) ? "" : value.toString();
    }
}