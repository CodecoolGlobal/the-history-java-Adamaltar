package com.codecool.thehistory;

import java.util.Arrays;

public class TheHistoryArray implements TheHistory {

    /**
     * This implementation should use a String array so don't change that!
     */
    private String[] wordsArray = new String[0];

    @Override
    public void add(String text) {
        String[] temp=text.split("\\s+");
//        for (int i=0;i<temp.length;i++)
//            System.out.println(temp[i]+" ");
        String[] newArray=new String[this.wordsArray.length+temp.length];
        System.arraycopy(wordsArray,0,newArray,0,wordsArray.length);
        System.arraycopy(temp,0,newArray,wordsArray.length,temp.length);
        wordsArray=newArray;
    }

    @Override
    public void removeWord(String wordToBeRemoved) {
        //TODO: check the TheHistory interface for more information
    }

    @Override
    public int size() {

        return wordsArray.length;
    }

    @Override
    public void clear() {
        //TODO: check the TheHistory interface for more information
    }

    @Override
    public void replaceOneWord(String from, String to) {
        //TODO: check the TheHistory interface for more information
    }

    @Override
    public void replaceMoreWords(String[] fromWords, String[] toWords) {
        //TODO: check the TheHistory interface for more information
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (String word : wordsArray) {
            sb.append(word).append(" ");
        }
        if (sb.length() > 0) sb.deleteCharAt(sb.length() - 1); // last space char
        return sb.toString();
    }

    public static void main (String[] args){
        TheHistoryArray theHistory=new TheHistoryArray();
        theHistory.add("newlines\nand tabs\ttoo");
        System.out.println(theHistory.size());
        System.out.println(theHistory.wordsArray[1]);


    }
}
