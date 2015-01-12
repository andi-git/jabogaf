package at.ahammer.boardgame.common.board.layout.log;

import at.ahammer.boardgame.api.board.field.Field;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Any;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;

@ApplicationScoped
public class FieldLineFactory {

    @Inject
    @Any
    private Instance<FieldLineUsage<? extends FieldLine>> fieldLineUsages;

    public FieldLine get(Field field, int line) {
        FieldLine result = null;
        for (FieldLineUsage fieldLineUsage : fieldLineUsages) {
            if (fieldLineUsage.canBeUsedFor(line)) {
                result = fieldLineUsage.create(field, line);
                break;
            }
        }
        return result == null ? getDefault(field, line) : result;
    }

    private FieldLine getDefault(Field field, int line) {
        FieldLine defaultFieldLine = null;
        for (FieldLineUsage fieldLineUsage : fieldLineUsages) {
            if (fieldLineUsage.isDefault()) {
                defaultFieldLine = fieldLineUsage.create(field, line);
                break;
            }
        }
        return defaultFieldLine;
    }
}
