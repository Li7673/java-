package com.Components;

import java.io.Serializable;

public  class  Question  implements Comparable<Question>{

    protected String description;
    protected int  id,type,difficulty;
    public String right_ans;
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

    @Override
    public int compareTo(Question question) {

        return this.difficulty-question.difficulty;
    }

    public void setRight_ans(String right_ans) {
        this.right_ans = right_ans;
    }

    @Override
    public String toString() {
        return "Question{" +
                "description='" + description + '\'' +
                ", id=" + id +
                ", type=" + type +
                ", difficulty=" + difficulty +
                ", right_ans='" + right_ans + '\'' +
                '}';
    }

    public int getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }
}