package com.Components;
public class Ans {
    public String ans;
    int type;
    int question_id;
    public  Ans (int id ,int type){
        this.type=type;
        this.question_id=id;
    }

    public Ans(String ans,int question_id){
        this.question_id=question_id;
    }
    public Ans(String ans ,int ans_id,int type){
        this.ans=ans;
        this.type=type;
        this.question_id=ans_id;
    }
    public Ans(int question_id){
        this.question_id=question_id;
    }
    public void setAns(String ans) {
        this.ans = ans;
    }
    public void setType(int type){
        this.type=type;
    }
    public int getType() {
        return type;
    }
    public String getAns() {
        return ans;
    }
    public int getQuestion_id() {
        return question_id;
    }

    @Override
    public String toString() {
        return "Ans{" +
                "ans='" + ans + '\'' +
                ", type=" + type +
                ", question_id=" + question_id +
                '}';
    }
}