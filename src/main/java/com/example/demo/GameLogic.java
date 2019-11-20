package com.example.demo;

import java.util.ArrayList;
import java.util.List;

public class GameLogic {

    private int numOfCards;
    private List<MemoryCard> listOfCards;
    private List<MemoryCard> subList1;
    private List<MemoryCard> subList2;
    private List<MemoryCard> subList3;
    private List<MemoryCard> subList4;

    public List<MemoryCard> getSubList1() {
        return subList1;
    }

    public List<MemoryCard> getSubList2() {
        return subList2;
    }

    public List<MemoryCard> getSubList3() {
        return subList3;
    }

    public List<MemoryCard> getSubList4() {
        return subList4;
    }

    public List<MemoryCard> getListOfCards() {
        return listOfCards;
    }

    public void setListOfCards(List<MemoryCard> listOfCards) {
        this.listOfCards = listOfCards;
    }

    public List<MemoryCard> createCards(int numOfCards){

        listOfCards = new ArrayList<>();
        int j = 1;

        for (int i = 0; i < (numOfCards); i++) {
            MemoryCard card;
            if(i< 8){
                card = new MemoryCard(false, (i+1), "card"+(i+1)+".png");
            }
            else{
                card = new MemoryCard(false, (i+1), "card"+(j)+".png");
                j++;
            }
            listOfCards.add(card);
        }
        return listOfCards;
    }

    public void splitListOfCards() {
        subList1 = listOfCards.subList(0, 4);
        subList2 = listOfCards.subList(4, 8);
        subList3 = listOfCards.subList(8, 12);
        subList4 = listOfCards.subList(12, 16);
    }

}
