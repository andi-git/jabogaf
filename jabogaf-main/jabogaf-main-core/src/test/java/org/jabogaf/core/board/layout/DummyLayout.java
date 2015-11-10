package org.jabogaf.core.board.layout;

import java.util.HashMap;
import java.util.HashSet;

public class DummyLayout extends LayoutBasic {

    protected DummyLayout() {
        super("DummyLayout" + randomId(), new HashSet<>(), new HashSet<>(), new HashSet<>(), new HashMap<>());
    }
}
