package org.jabogaf.core.board.layout;

import org.jabogaf.api.board.layout.Layout;
import org.jabogaf.api.board.layout.log.LayoutLogger;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class DummyLayoutLogger implements LayoutLogger<DummyLayout, DummyLayoutParameter> {

    @Override
    public boolean canHandle(Layout layout) {
        return layout != null && layout.getClass() == genericType();
    }

    @Override
    public Class genericType() {
        return DummyLayout.class;
    }

    @Override
    public String toString(DummyLayout layout, DummyLayoutParameter parameter) {
        return "String of DummyLayout";
    }
}
