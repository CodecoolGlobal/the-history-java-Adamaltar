package com.codecool.thehistory;

import java.util.*;

public class TheHistoryLinkedList implements TheHistory {
    /**
     * This implementation should use a String LinkedList so don't change that!
     */
    private List<String> wordsLinkedList = new LinkedList<>();

    @Override
    public void add(String text) {
        String[] temp = text.split("\\s+");
        for (String word:
             temp) {
            wordsLinkedList.add(word);
        }

    }

    @Override
    public void removeWord(String wordToBeRemoved) {
        ListIterator<String> it=wordsLinkedList.listIterator();
        while (it.hasNext())
            if (it.next().equals(wordToBeRemoved))
                it.remove();


    }

    @Override
    public int size() {
        return wordsLinkedList.size();

    }

    @Override
    public void clear() {
        wordsLinkedList.clear();
    }

    @Override
    public void replaceOneWord(String from, String to) {
        ListIterator<String> it=wordsLinkedList.listIterator();
        while (it.hasNext())
            if (it.next().equals(from))
                it.set(to);
    }

    @Override
    public void replaceMoreWords(String[] fromWords, String[] toWords) {
        ListIterator<String> it=wordsLinkedList.listIterator();
        int[] indexes=getIndexesOfSentence(fromWords);
        LinkedList<String> newList=new LinkedList<>();
        for (int index:indexes){
            while (it.nextIndex()<index)
                newList.add(it.next());
            for (int j=0;j<toWords.length;j++)
                newList.add(toWords[j]);
            while (it.nextIndex()<index+fromWords.length)
                it.next();
        }
        while (it.hasNext())
            newList.add(it.next());
        wordsLinkedList=newList;
    }

    public int[] getIndexesOfSentence(String[] sentence) {
        ArrayList<Integer> indexes = new ArrayList<>();
        int i = 0;
        ListIterator<String> it = wordsLinkedList.listIterator();
        while (i < wordsLinkedList.size() - (sentence.length - 1)) {
            int j = 0;
            while (j < sentence.length && it.next().equals(sentence[j]))
                j++;
            if (j == sentence.length) {
                indexes.add(i);
                i = i + j;
            } else {
                while (j > 0) {
                    it.previous();
                    j--;
                }
                i++;
            }
        }
        int[] res=new int[indexes.size()];
        for (i=0;i<res.length;i++)
            res[i]=indexes.get(i);

        return res;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (String word : wordsLinkedList) {
            sb.append(word).append(" ");
        }
        if (sb.length() > 0) sb.deleteCharAt(sb.length() - 1); // last space char
        return sb.toString();
    }


    public static void main(String[] args) {
        TheHistoryLinkedList theHistory = new TheHistoryLinkedList();
        theHistory.add("ac ca nd a a a a fost odata a fost a a a fost odata a fost");
        theHistory.replaceMoreWords(new String[]{"a","a","a","fost","odata"},new String[]{"bla","ble"});
        System.out.println(theHistory);
        //System.out.println(x.length);


    }

}
