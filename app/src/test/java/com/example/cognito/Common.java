package com.example.cognito;

import com.boss.poetrydb2.model.PoemModel;

import java.util.ArrayList;
import java.util.List;

class Common {

    public static PoemModel getCommonPoemModel() {
        PoemModel poemModel = new PoemModel();
        poemModel.setAuthor("Ali baba");
        poemModel.setTitle("adventures of Ali baba");
        List<String> listLines = new ArrayList<>();
        listLines.add("first line");
        listLines.add("second line");
        listLines.add("third line");
        poemModel.setLines(listLines);
        poemModel.setLinecount("3");

        return poemModel;
    }
}
