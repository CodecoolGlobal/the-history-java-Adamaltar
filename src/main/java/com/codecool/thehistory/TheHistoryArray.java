package com.codecool.thehistory;

import java.util.ArrayList;
import java.util.Arrays;

public class TheHistoryArray implements TheHistory {

    /**
     * This implementation should use a String array so don't change that!
     */
    private String[] wordsArray = new String[0];

    @Override
    public void add(String text) {
        String[] temp = text.split("\\s+");

        String[] newArray = new String[this.wordsArray.length + temp.length];
        System.arraycopy(wordsArray, 0, newArray, 0, wordsArray.length);
        System.arraycopy(temp, 0, newArray, wordsArray.length, temp.length);
        wordsArray = newArray;
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
        int[] indexes=getIndexesOfSentence(fromWords);
        String[] newArray=new String[wordsArray.length-indexes.length*(fromWords.length-toWords.length)];
        int i=0;
        int ct=0;
        for (int index:
             indexes) {
            while (i<index)
                newArray[ct++]=wordsArray[i++];
            for (int j=0;j<toWords.length;j++)
                newArray[ct++]=toWords[j];
            i+=fromWords.length;

        }

        while (i<wordsArray.length)
            newArray[ct++] = wordsArray[i++];

        wordsArray=newArray;

    }

    public int[] getIndexesOfSentence(String[] sentence) {
        ArrayList<Integer> indexes = new ArrayList<>();
        int i=0;
        while (i < wordsArray.length-(sentence.length-1)) {
            if (wordsArray[i].equals(sentence[0])) {
                boolean mismatch = false;
                int j = 1;
                while (j < sentence.length && wordsArray[i + j].equals(sentence[j]))
                    j++;
                if (j == sentence.length) {
                    indexes.add(i);
                    i = i + j;
                } else
                    i++;
            } else
                i++;

        }
        int[] res=new int[indexes.size()];
        for (i=0;i<res.length;i++)
            res[i]=indexes.get(i);

        return res;
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

    public static void main(String[] args) {
        TheHistoryArray theHistory = new TheHistoryArray();
        theHistory.add("ca ca nd a a fost odata a fost a fost o data a fost");
        theHistory.replaceMoreWords(new String[]{"a","fost","odata"},new String[]{"bla","ble"});
        System.out.println(theHistory);
        //System.out.println(x.length);


    }
}
