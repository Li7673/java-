package com.Components;

import java.util.ArrayList;

public class MultipleChoice_Question extends Question{
    ArrayList<String> choice_box;

    public MultipleChoice_Question(String description , int id , ArrayList<String> choice_box){
        this.description=description;
        this.id=id;
        this.type=1;
        this.choice_box=choice_box;
    }

    public void  setChoice_box(ArrayList<String> choice_box){
        this.choice_box=choice_box;
    }

    public ArrayList<String> getChoice_box() {
        return choice_box;
    }
}