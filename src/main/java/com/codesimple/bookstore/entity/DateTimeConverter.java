package com.codesimple.bookstore.entity;

        import jakarta.persistence.*;
        import org.joda.time.DateTime;

@Converter(autoApply = true)
public class DateTimeConverter implements AttributeConverter<DateTime, java.sql.Timestamp> {

    @Override
    public java.sql.Timestamp convertToDatabaseColumn(DateTime attribute) {
        return (attribute != null) ? new java.sql.Timestamp(attribute.getMillis()) : null;
    }

    @Override
    public DateTime convertToEntityAttribute(java.sql.Timestamp dbData) {
        return (dbData != null) ? new DateTime(dbData.getTime()) : null;
    }
}