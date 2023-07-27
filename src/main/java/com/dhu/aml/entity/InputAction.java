package com.dhu.aml.entity;

public class InputAction {
    private String action;
    private String subject;
    private String object;
    private String cases;
    private String result;

    public InputAction() {
    }

    public InputAction(String action, String subject, String object, String cases, String result) {
        this.action = action;
        this.subject = subject;
        this.object = object;
        this.cases = cases;
        this.result = result;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getObject() {
        return object;
    }

    public void setObject(String object) {
        this.object = object;
    }

    public String getCases() {
        return cases;
    }

    public void setCases(String cases) {
        this.cases = cases;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
