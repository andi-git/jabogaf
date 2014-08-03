package at.ahammer.heroquest.cdi;

import javax.annotation.PostConstruct;

/**
 * Created by andreas on 03.08.14.
 */
@GameScoped
public class BeanWithGameScoped {

    @PostConstruct
    private void init() {
        System.out.println("create " + this.getClass());
    }

    public String getString() {
        return "i'm in GameContext";
    }
}
