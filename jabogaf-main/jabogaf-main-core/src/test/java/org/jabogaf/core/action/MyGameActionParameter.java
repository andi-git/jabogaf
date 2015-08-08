package org.jabogaf.core.action;

import org.jabogaf.api.action.GameActionParameter;

public class MyGameActionParameter implements GameActionParameter {

    private String input;

    public MyGameActionParameter(String input) {
        this.input = input;
    }

    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }
}
