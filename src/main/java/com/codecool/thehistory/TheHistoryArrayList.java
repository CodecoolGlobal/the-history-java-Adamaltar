package com.codecool.thehistory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ListIterator;

public class TheHistoryArrayList implements TheHistory {
    /**
     * This implementation should use a String ArrayList so don't change that!
     */
    private List<String> wordsArrayList = new ArrayList<>();

    @Override
    public void add(String text) {
        String[] temp = text.split("\\s+");
        for (String word:
                temp) {
            wordsArrayList.add(word);
        }
    }

    @Override
    public void removeWord(String wordToBeRemoved) {
        int ct=0;
        int n=wordsArrayList.size();
        ArrayList<String> newList=new ArrayList<>();

        for (int i=0;i< wordsArrayList.size();i++)
            if (wordsArrayList.get(i).equals(wordToBeRemoved))
                ct++;
            else
                wordsArrayList.set(i-ct,wordsArrayList.get(i));
        while (wordsArrayList.size()>n-ct)
            wordsArrayList.remove(wordsArrayList.size()-1);



    }

    @Override
    public int size() {
        return wordsArrayList.size();
    }

    @Override
    public void clear() {
        wordsArrayList.clear();
    }

    @Override
    public void replaceOneWord(String from, String to) {
        for (int i=0;i<wordsArrayList.size();i++)
            if (wordsArrayList.get(i).equals(from))
                wordsArrayList.set(i,to);
    }


    /**
     * the original but slower version of replaceMoreWords; the algorithm for Array and LinkedList is the same
     */

    public void replaceMoreWords2(String[] fromWords, String[] toWords) {
        int[] indexes=getIndexesOfSentence(fromWords);
        ArrayList<String> newArray=new ArrayList<>();
        int i=0;
        int ct=0;
        for (int index:
                indexes) {
            while (i<index)
                newArray.add(wordsArrayList.get(i++));
            for (int j=0;j<toWords.length;j++)
                newArray.add(toWords[j]);
            i+=fromWords.length;

        }

        while (i<wordsArrayList.size())
            newArray.add(wordsArrayList.get(i++));

        wordsArrayList=newArray;
    }

    public int[] getIndexesOfSentence(String[] sentence) {
        ArrayList<Integer> indexes = new ArrayList<>();
        int i=0;
        while (i < wordsArrayList.size()-(sentence.length-1)) {
            if (wordsArrayList.get(i).equals(sentence[0])) {
                int j = 1;
                while (j < sentence.length && wordsArrayList.get(i + j).equals(sentence[j]))
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

    /**
     * A faster version of replaceMoreWords, same idea could be implemented for LinkedList and ArrayList
     */
    @Override
    public void replaceMoreWords(String[] fromWords, String[] toWords) {
        ArrayList<String> replaced = new ArrayList<>();
        int i=0;
        while (i < wordsArrayList.size()-(fromWords.length-1)) {
            int j = 0;
            while (j < fromWords.length && wordsArrayList.get(i + j).equals(fromWords[j]))
                j++;
            if (j == fromWords.length) {
                i += j;
                for (String word : toWords)
                    replaced.add(word);
            } else {
                replaced.add(wordsArrayList.get(i));
                i++;
            }
        }
        while (i<wordsArrayList.size())
            replaced.add(wordsArrayList.get(i++));

        wordsArrayList=replaced;
    }





    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (String word : wordsArrayList) {
            sb.append(word).append(" ");
        }
        if (sb.length() > 0) sb.deleteCharAt(sb.length() - 1); // last space char
        return sb.toString();
    }

}
