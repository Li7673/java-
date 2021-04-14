package com.Components;

import java.util.ArrayList;

public class Choice_Question extends Question{
    ArrayList<String> choice_box;
    public  int type;
    public Choice_Question (String description ,int id , int type,ArrayList<String> choice_box){
        this.description=description;
        this.id=id;
       this.type=type;
        this.choice_box=choice_box;
    }
    public Choice_Question (String description ,int id ){
        this.description=description;
        this.id=id;
        this.type=0;
    }
    public void  setChoice_box(ArrayList<String> choice_box){
        this.choice_box=choice_box;
    }

    public ArrayList<String> getChoice_box() {
        return choice_box;
    }
}