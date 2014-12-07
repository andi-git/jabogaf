package at.ahammer.boardgame.core.action;

import at.ahammer.boardgame.api.action.GameActionParameter;

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
