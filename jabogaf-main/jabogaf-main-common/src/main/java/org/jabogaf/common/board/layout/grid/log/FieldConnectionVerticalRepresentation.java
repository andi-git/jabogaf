package org.jabogaf.common.board.layout.grid.log;

import org.jabogaf.api.board.field.FieldConnection;
import org.jabogaf.api.object.GameObject;
import org.jabogaf.util.string.StringUtil;

import javax.inject.Inject;

/**
 * Vertical representation of a {@link GameObject}.
 */
@SuppressWarnings("CdiManagedBeanInconsistencyInspection")
public class FieldConnectionVerticalRepresentation extends FieldConnectionRepresentation {

    private String stringRepresentation;

    @Inject
    private StringUtil stringUtil;

    public FieldConnectionVerticalRepresentation(FieldConnection fieldConnection) {
        super(fieldConnection);
    }

    private String getStringRepresentation() {
        if (stringRepresentation == null) {
            StringBuilder sb = new StringBuilder();
            sb.append("-");
            GameObject fieldConnectionObject = getFirstFieldConnectionObject();
            if (fieldConnectionObject != null) {
                sb.append(stringUtil.padLeftFixSize(fieldConnectionObject.getId(), FieldLine.Representation.HEIGHT - 4));
            } else {
                sb.append(stringUtil.repeatedString(' ', FieldLine.Representation.HEIGHT - 4));
            }
            sb.append(hiddenChar());
            sb.append(openCloseChar());
            sb.append("-");
            this.stringRepresentation = sb.toString();
        }
        return stringRepresentation;
    }

    @Override
    public String toString(int line, GridLayoutLoggerParameter parameter) {
        return String.valueOf(getStringRepresentation().charAt(line));
    }
}
