package at.ahammer.boardgame.cdi;

import javax.enterprise.inject.spi.BeanManager;

/**
 * Created by andreas on 09.08.14.
 */
public class NewInstanceInGameContextBean extends NewInstanceInGameContext {

    public NewInstanceInGameContextBean() {
        super("NewInstanceInGameContextBean");
    }

    public NewInstanceInGameContextBean(String id) {
        super(id);
    }

    public String foo() {
        return "foo";
    }
}
