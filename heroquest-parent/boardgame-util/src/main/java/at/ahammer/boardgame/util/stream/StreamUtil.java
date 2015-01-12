package at.ahammer.boardgame.util.stream;

import javax.enterprise.context.ApplicationScoped;
import java.util.Arrays;
import java.util.stream.Stream;

@ApplicationScoped
public class StreamUtil {

    public <T> Stream<T> getArrayAsStream(T[] array) {
        return Arrays.stream(array);
    }

    public <T> Stream<T> getTwoDimensionalArrayAsStream(T[][] twoDimensionalArray) {
        return Arrays.stream(twoDimensionalArray).flatMap(firstDimensionAsStream -> Arrays.stream(firstDimensionAsStream));
    }

}
