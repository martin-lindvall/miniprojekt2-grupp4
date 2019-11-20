package com.example.demo;

import java.util.ArrayList;
import java.util.List;

public class GameLogic {

    private int numOfCards;
    private List<MemoryCard> listOfCards;

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

//        for (int j = 0; j < (numOfCards/2); j++) {
//            MemoryCard card = new MemoryCard(false, (j+9), "card"+(j+1)+".jpg");
//            listOfCards.add(card);
//        }

        return listOfCards;
    }


}
