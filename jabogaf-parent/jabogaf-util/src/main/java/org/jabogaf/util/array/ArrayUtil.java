package org.jabogaf.util.array;

import javax.enterprise.context.ApplicationScoped;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.stream.IntStream;

@ApplicationScoped
public class ArrayUtil {

    public <T, U> U[][] convertTwoDimensionalArray(T[][] source, Class<U> targetType, Function<T, U> typeConverter) {
        U[][] result = initTwoDimensionalArray(targetType, source.length, source[0].length);
        for (int i = 0; i < result.length; i++) {
            for (int j = 0; j < result[i].length; j++) {
                result[i][j] = typeConverter.apply(source[i][j]);
            }
        }
        return result;
    }

    public <T> T[] initArray(Class<T> type, int size) {
        return (T[]) Array.newInstance(type, size);
    }

    public <T> T[][] initTwoDimensionalArray(Class<T> type, int dim1, int dim2) {
        return (T[][]) Array.newInstance(type, dim1, dim2);
    }
}
