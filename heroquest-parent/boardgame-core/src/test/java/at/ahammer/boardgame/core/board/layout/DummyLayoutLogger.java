package at.ahammer.boardgame.core.board.layout;

import at.ahammer.boardgame.api.board.layout.Layout;
import at.ahammer.boardgame.api.board.layout.log.LayoutLogger;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class DummyLayoutLogger implements LayoutLogger<DummyLayout> {

    @Override
    public boolean canHandle(Layout layout) {
        return layout != null && layout.getClass() == genericType();
    }

    @Override
    public Class genericType() {
        return DummyLayout.class;
    }

    @Override
    public String toString(DummyLayout layout) {
        return "String of DummyLayout";
    }
}
