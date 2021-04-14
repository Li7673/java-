package com.Components;

public  class  Question {
    protected String description;
    protected int  id,type,difficulty;

    public int getType() {
        return type;
    }
    public int getId() {
        return id;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public void setId(int id) {
        this.id = id;
    }
    public Question setType(int type) {
        this.type = type;

        return null;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }
}