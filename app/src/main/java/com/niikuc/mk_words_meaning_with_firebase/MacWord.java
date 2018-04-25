package com.niikuc.mk_words_meaning_with_firebase;

import android.app.Application;

/**
 * Created by Николче on 23.4.2018.
 */

public class MacWord   {
    public String word;
    public String description;

    public MacWord(){

    }

    public MacWord(String word,String description){
        this.word=word;
        this.description=description;
    }

    public String getWord() {
        return word;
    }

    public String getDescription() {
        return description;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
