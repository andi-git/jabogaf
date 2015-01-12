package at.ahammer.boardgame.core.board.layout;

import at.ahammer.boardgame.api.board.layout.log.AbstractLayoutLogger;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class DummyLayoutLogger extends AbstractLayoutLogger<DummyLayout> {

    @Override
    public Class genericType() {
        return DummyLayout.class;
    }

    @Override
    public String toString(DummyLayout layout) {
        return "String of DummyLayout";
    }
}
