package at.ahammer.boardgame.core.behavior.look;

import at.ahammer.boardgame.api.behavior.look.Lookable;
import at.ahammer.boardgame.api.board.BoardManager;
import at.ahammer.boardgame.api.board.field.Field;
import at.ahammer.boardgame.api.cdi.GameScoped;
import at.ahammer.boardgame.api.subject.GameSubject;
import at.ahammer.boardgame.core.util.cache.CachedValueMap;
import at.ahammer.boardgame.util.log.SLF4J;
import org.slf4j.Logger;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

/**
 * Create and cache all lookable {@link Field}s of a {@link Lookable}, i.e. a {@link GameSubject}.
 */
@GameScoped
public class LookableFields extends CachedValueMap<List<Field>, LookableFields.Parameter> {

    @Inject
    @SLF4J
    private Logger log;

    @Inject
    private BoardManager boardManager;

    @Override
    protected Function<Parameter, List<Field>> create() {
        return parameter -> {
            List<Field> lookableFields = new ArrayList<>();
            //noinspection ArraysAsListWithZeroOrOneArgument
            resolve(parameter.getLookable(), lookableFields, new ArrayList<>(), Arrays.asList(parameter.getLookable().getPosition()));
            return lookableFields;
        };
    }

    private void resolve(Lookable lookable, List<Field> lookableFields, List<Field> nonLookableFields, List<Field> unresolvedFields) {
        if (!unresolvedFields.isEmpty()) {
            List<Field> resolvedFields = new ArrayList<>();
            for (Field unresolvedField : unresolvedFields) {
                if (!lookableFields.contains(unresolvedField) && !nonLookableFields.contains(unresolvedField)) {
                    resolvedFields.add(unresolvedField);
                    if (lookable.canLook(unresolvedField).isPossible()) {
                        lookableFields.add(unresolvedField);
                        unresolvedField.getConnectedFields().stream().
                                filter(f -> !lookableFields.contains(f) && !unresolvedFields.contains(f)).
                                forEach(unresolvedFields::add);
                    } else {
                        nonLookableFields.add(unresolvedField);
                    }
                }
            }
            unresolvedFields.removeAll(resolvedFields);
        }
        if (!unresolvedFields.isEmpty()) {
            resolve(lookable, lookableFields, nonLookableFields, unresolvedFields);
        }
    }

    @Override
    protected Logger log() {
        return log;
    }

    public static class Parameter {

        private final Lookable lookable;

        public Parameter(Lookable lookable) {
            if (lookable == null) {
                throw new IllegalArgumentException("Lookable must not be null");
            }
            this.lookable = lookable;
        }

        public Lookable getLookable() {
            return lookable;
        }
    }
}
