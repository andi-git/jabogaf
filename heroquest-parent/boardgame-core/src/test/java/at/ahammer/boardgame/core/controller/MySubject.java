package at.ahammer.boardgame.core.controller;

import at.ahammer.boardgame.api.board.field.Field;
import at.ahammer.boardgame.core.subject.GameSubjectNull;

public class MySubject extends GameSubjectNull {

    public MySubject() {
        super("id", null);
    }

    public MySubject(String id, Field field) {
        super(id, field);
    }
}
