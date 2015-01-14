package at.ahammer.boardgame.common.board.layout.log;

import at.ahammer.boardgame.api.board.field.FieldConnection;
import at.ahammer.boardgame.api.board.field.FieldConnectionObject;
import at.ahammer.boardgame.util.string.StringUtil;

import javax.inject.Inject;

/**
 * Vertical representation of a {@link at.ahammer.boardgame.api.board.field.FieldConnectionObject}.
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
            FieldConnectionObject fieldConnectionObject = getFirstFieldConnectionObject();
            if (fieldConnectionObject != null) {
                sb.append(stringUtil.padLeftFixSize(fieldConnectionObject.getId(), FieldLine.HEIGHT - 4));
            } else {
                sb.append(stringUtil.repeatedString(' ', FieldLine.HEIGHT - 4));
            }
            sb.append(hiddenChar());
            sb.append(openCloseChar());
            sb.append("-");
            this.stringRepresentation = sb.toString();
        }
        return stringRepresentation;
    }

    @Override
    public String toString(int line) {
        return String.valueOf(getStringRepresentation().charAt(line));
    }
}
