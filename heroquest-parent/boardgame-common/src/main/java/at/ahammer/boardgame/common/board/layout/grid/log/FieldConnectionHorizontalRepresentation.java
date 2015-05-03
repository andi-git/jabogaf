package at.ahammer.boardgame.common.board.layout.grid.log;

import at.ahammer.boardgame.api.board.field.FieldConnection;
import at.ahammer.boardgame.api.board.field.FieldConnectionObject;
import at.ahammer.boardgame.util.string.StringUtil;

import javax.inject.Inject;

/**
 * Horizontal representation of a {@link at.ahammer.boardgame.api.board.field.FieldConnectionObject}.
 */
@SuppressWarnings("CdiManagedBeanInconsistencyInspection")
public class FieldConnectionHorizontalRepresentation extends FieldConnectionRepresentation {

    private String stringRepresentation;

    @Inject
    private StringUtil stringUtil;

    public FieldConnectionHorizontalRepresentation(FieldConnection fieldConnection) {
        super(fieldConnection);
    }

    private String getStringRepresentation() {
        if (stringRepresentation == null) {
            StringBuilder sb = new StringBuilder();
            sb.append("|");
            FieldConnectionObject fieldConnectionObject = getFirstFieldConnectionObject();
            if (fieldConnectionObject != null) {
                sb.append(stringUtil.padLeftFixSize(fieldConnectionObject.getId(), FieldLine.Representation.WIDTH - 4));
            } else {
                sb.append(stringUtil.repeatedString(' ', FieldLine.Representation.WIDTH - 4));
            }
            sb.append(hiddenChar());
            sb.append(openCloseChar());
            sb.append("|");
            this.stringRepresentation = sb.toString();
        }
        return stringRepresentation;
    }

    @Override
    public String toString(int line, GridLayoutLoggerParameter parameter) {
        return getStringRepresentation();
    }
}
