package org.jabogaf.util.serialize;

/**
 * This is a helper-class to serialize and deserialize objects.
 */
public interface Serializer {

    /**
     * Serializes an object to a {@link String}.
     *
     * @param object - the object to serialize
     * @return the seralized object as {@link String}
     */
    String serialize(Object object);

    /**
     * Deserialize a {@link String} to an object.
     *
     * @param string - the serialized object as {@link String}
     * @param type   - the type of the resulting object
     * @param <T>    - the generic type of the resulting object
     * @return the deserilized {@link String} as object of type {@link T}
     */
    <T> T deserialize(String string, Class<T> type);
}
