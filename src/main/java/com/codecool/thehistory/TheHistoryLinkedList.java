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
        //TODO: check the TheHistory interface for more information
    }

    @Override
    public int size() {
        return wordsLinkedList.size();

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
        int i=0;
        ListIterator<String> it=wordsLinkedList.listIterator();
        while (i < wordsLinkedList.size()-(sentence.length-1)) {
            if (it.next().equals(sentence[0])) {
                //System.out.println("starting from "+i);
                boolean mismatch = false;
                int j = 1;
                while (j < sentence.length && it.next().equals(sentence[j]))
                    j++;
                if (j == sentence.length) {
                    indexes.add(i);

                    //System.out.println("found from "+i+", to "+(i+j-1));
                    i=i+j;
                } else{
                    while (it.previousIndex()!=i)
                        it.previous();
                    i++;
                    }




            } else {
                i++;
                //System.out.println("no, moving on to "+i);
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
        theHistory.add("ac ca nd a a fost odata a fost a fost odata a fost");
        theHistory.replaceMoreWords(new String[]{"a","fost","odata"},new String[]{"bla","ble"});
        System.out.println(theHistory);
        //System.out.println(x.length);


    }

}
