package org.jabogaf.core.gamecontext.bean;

import org.jabogaf.api.gamecontext.GameScoped;

import javax.annotation.PostConstruct;

@GameScoped
public class BeanWithGameScoped {

    private int counter = 0;

    @PostConstruct
    private void init() {
        System.out.println("create " + this.getClass());
    }

    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }

    public String getString() {
        return "i'm in GameContext";
    }
}
