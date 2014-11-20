package at.ahammer.boardgame.core.stream;

import javax.enterprise.context.ApplicationScoped;
import java.util.NoSuchElementException;
import java.util.Optional;

@ApplicationScoped
public class OptionalDefault {

    public <T> T defaultGet(Optional<T> optional, T defaultValue) {
        try {
            return optional.get();
        } catch (NoSuchElementException e) {
            return defaultValue;
        }
    }
}
