package com.Components;

public class   Blank_Question extends Question{
    public   Blank_Question(String description ,int id ){
        this.description=description;
        this.id=id;
        this.type=2;
    }
    public Blank_Question(){
    this.type=2;
    }

}