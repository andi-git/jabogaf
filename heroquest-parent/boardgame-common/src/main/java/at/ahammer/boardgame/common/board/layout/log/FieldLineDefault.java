package at.ahammer.boardgame.common.board.layout.log;

import at.ahammer.boardgame.api.board.field.Field;
import at.ahammer.boardgame.api.cdi.GameContextBean;
import at.ahammer.boardgame.util.string.StringUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * The default line of a {@link FieldLine}.
 */
public class FieldLineDefault extends FieldLine {

    private final List<GameContextBean> gameContextBeanList = new ArrayList<>();

    public FieldLineDefault(Field field, int line) {
        super(field, line);
        gameContextBeanList.addAll(field.getGameSubjects().stream().sorted().collect(Collectors.toList()));
    }

    @Override
    public String text(int line, Field field, StringUtil stringUtil) {
        if (line == 1) {
            return fixedLengthString(field.getId(), stringUtil);
        } else if (line >= 2 && !gameContextBeanList.isEmpty() && line - 2 < gameContextBeanList.size()) {
            return fixedLengthString(gameContextBeanList.get(line - 2).getId(), stringUtil);
        }
        return fixedLengthString(defaultChar(), stringUtil);
    }

    @Override
    public char firstChar() {
        return '|';
    }

    @Override
    public char defaultChar() {
        return ' ';
    }

    @Override
    public char lastChar() {
        return '|';
    }
}
