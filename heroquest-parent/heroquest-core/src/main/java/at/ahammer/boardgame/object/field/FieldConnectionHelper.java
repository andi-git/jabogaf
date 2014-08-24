package at.ahammer.boardgame.object.field;

import at.ahammer.boardgame.cdi.GameContext;
import at.ahammer.boardgame.cdi.GameScoped;
import at.ahammer.boardgame.cdi.NewInstanceInGameContext;

import javax.enterprise.inject.spi.BeanManager;
import javax.inject.Inject;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by andreas on 8/22/14.
 */
@GameScoped
public class FieldConnectionHelper {

    @Inject
    private BeanManager beanManager;

//    private Set<Field> fields;
//
//    private Set<FieldConnection> fieldConnections;
//
//    public Set<Field> getFields() {
//        if (fields == null) {
//            fields = Collections.unmodifiableSet(GameContext.getNewInstancesInGameContext(beanManager).stream().filter(b -> b instanceof Field).map(b -> (Field) b).collect(Collectors.toSet()));
//        }
//        return fields;
//    }
//
//    public Set<FieldConnection> getFieldConnections() {
//        if (fieldConnections== null) {
//            fieldConnections = Collections.unmodifiableSet(GameContext.getNewInstancesInGameContext(beanManager).stream().filter(b -> b instanceof FieldConnection).map(b -> (FieldConnection) b).collect(Collectors.toSet()));
//        }
//        return fieldConnections;
//    }
//
//    public boolean isConnected(Field field1, Field field2) {
//        getFieldConnections().stream().filter()
//    }
}
