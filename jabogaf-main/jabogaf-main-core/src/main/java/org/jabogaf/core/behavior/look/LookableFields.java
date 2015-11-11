package org.jabogaf.core.behavior.look;

import org.jabogaf.api.behavior.look.CanLookReport;
import org.jabogaf.api.behavior.look.Lookable;
import org.jabogaf.api.board.BoardManager;
import org.jabogaf.api.board.field.Field;
import org.jabogaf.api.gamecontext.GameScoped;
import org.jabogaf.api.resource.ResourceHolder;
import org.jabogaf.api.subject.GameSubject;
import org.jabogaf.core.state.CachedValueMap;
import org.jabogaf.util.log.SLF4J;
import org.slf4j.Logger;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import static com.google.common.base.Preconditions.checkNotNull;

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
            List<Field> availableFields = new ArrayList<>(boardManager.getFields());
            availableFields.stream().sorted().forEach(f -> {
                if (!parameter.getLookable().getPosition().equals(f)) {
                    CanLookReport canLookReport = parameter.getLookable().canLook(f);
                    if (canLookReport.isPossible()) {
                        lookableFields.add(f);
                    }
                }
            });
            return lookableFields;
        };
    }

    @Override
    protected Logger log() {
        return log;
    }

    public static class Parameter {

        private final Lookable lookable;

        private final ResourceHolder resourceHolder;

        public Parameter(Lookable lookable, ResourceHolder resourceHolder) {
            checkNotNull(lookable);
            checkNotNull(resourceHolder);
            this.lookable = lookable;
            this.resourceHolder = resourceHolder;
        }

        public Lookable getLookable() {
            return lookable;
        }

        public ResourceHolder getResourceHolder() {
            return resourceHolder;
        }
    }
}
