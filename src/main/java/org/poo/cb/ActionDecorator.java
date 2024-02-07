package org.poo.cb;

public class ActionDecorator implements Action {
    protected Action action;

    public ActionDecorator(Action action) {
        this.action = action;
    }

    @Override
    public String getCompany() {
        return action.getCompany();
    }
}
