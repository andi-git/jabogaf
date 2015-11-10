package org.jabogaf.api.board.layout;

import org.jabogaf.api.board.field.Field;

import java.util.Map;

/**
 * A class that holds 2 {@link Field}s and can be used as key, e. g. in a {@link Map}.
 */
public interface KeyTwoFields {

    Field getFrom();

    Field getTo();
}
