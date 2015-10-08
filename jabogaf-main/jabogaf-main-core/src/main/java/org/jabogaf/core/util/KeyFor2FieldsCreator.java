package org.jabogaf.core.util;

import org.jabogaf.api.board.field.Field;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class KeyFor2FieldsCreator {

    public String generateKey(Field field1, Field field2) {
        if (field1.getId().compareTo(field2.getId()) >= 0) {
            return field1.getId() + field2.getId();
        } else {
            return field2.getId() + field1.getId();
        }
    }
}
