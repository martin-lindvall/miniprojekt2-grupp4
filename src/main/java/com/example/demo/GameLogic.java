package com.example.demo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GameLogic {

    private int numOfCards;
    private List<MemoryCard> listOfCards;
    private List<MemoryCard> subList1;
    private List<MemoryCard> subList2;
    private List<MemoryCard> subList3;
    private List<MemoryCard> subList4;
    private List<Integer> matchList = new ArrayList<>();
    private List<MemoryCard> drawnCards = new ArrayList<>();

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

    //Generate memory cards
    public List<MemoryCard> createCards(int numOfCards) {

        listOfCards = new ArrayList<>();
        int j = 1;

        for (int i = 0; i < (numOfCards); i++) {
            MemoryCard card;
            if (i < 8) {
                card = new MemoryCard(false, (i + 1), "card" + (i + 1) + ".png"); //creates eight unique cardsId and filenames
            } else {
                card = new MemoryCard(false, (i + 1), "card" + (j) + ".png"); //creates additional eight unique cardId but duplicates of filenames
                j++;
            }
            listOfCards.add(card);
        }

        return listOfCards;
    }

    //Splits listOfCards into four sublists, representing the rows in the table
    public void splitListOfCards() {
        subList1 = listOfCards.subList(0, 4);
        subList2 = listOfCards.subList(4, 8);
        subList3 = listOfCards.subList(8, 12);
        subList4 = listOfCards.subList(12, 16);
    }

    //Shuffles cards
    public void shuffleCards() {
        Collections.shuffle(listOfCards);
    }

    //Flips selected card
    public void turnCard(int cardId) {
        for (MemoryCard card : listOfCards) {
            if (card.getCardId() == cardId) {
                card.flip();
            }
        }
    }

    public MemoryCard findCardByCardId(int cardId){
        MemoryCard returnCard = null;

        for (MemoryCard card : listOfCards) {
            if(card.getCardId() == cardId){
                returnCard = card;
            }
        }
        return returnCard;
    }

//
//    private String filename11 = "1";
//    private String filename22 = "2";
//
//    public void matchCards2(int cardId){
//        drawnCards.add(cardId);
//
//
//
//        if(drawnCards.size() % 2 == 0){
//
//
//            for (MemoryCard card : listOfCards) {
//                if (card.getCardId() == drawnCards.get(drawnCards.size()-1)){
//                    filename11 = card.getFilename();
//                }
//                if (card.getCardId() == drawnCards.get(drawnCards.size()-2)) {
//                    filename22 = card.getFilename();
//                }
//            }
//            }
//
//
//        }
//
//
//
//
//    public void matchCards(int cardId) {
//        matchList.add(cardId);
//        if (matchList.size() == 2) {
//
//            String filename1 = "1";
//            String filename2 = "2";
//
//            for (MemoryCard card : listOfCards) {
//                if (card.getCardId() == matchList.get(0)) {
//                    filename1 = card.getFilename();
//                }
//                if (card.getCardId() == matchList.get(1)) {
//                    filename2 = card.getFilename();
//                }
//            }
//
//            if (!filename1.equals(filename2)) {
//                turnCard(matchList.get(0));
//                turnCard(matchList.get(1));
//            }
//            matchList = new ArrayList<>();
//        }
//        }
//
//    public void ifCardsNotEqual() {
//        if(drawnCards.size() > 1) {
//            if (!filename11.equals(filename22)) {
//                turnCard(drawnCards.get(drawnCards.size() - 1));
//                turnCard(drawnCards.get(drawnCards.size() - 2));
//            }
//        }
//    }

    public void matchCards(MemoryCard card) {

        drawnCards.add((card));

        if(drawnCards.size() < 4) {
            if (drawnCards.size() % 3 == 0) {
                if (!drawnCards.get(drawnCards.size() - 2).getFilename().equals(drawnCards.get(drawnCards.size() - 3))) {
                    turnCard(drawnCards.get(drawnCards.size() - 2).getCardId());
                    turnCard(drawnCards.get(drawnCards.size() - 3).getCardId());
                }
            }

        }else if (drawnCards.size() % 2 == 0) {
            if (!drawnCards.get(drawnCards.size() - 2).getFilename().equals(drawnCards.get(drawnCards.size() - 3))) {
                turnCard(drawnCards.get(drawnCards.size() - 2).getCardId());
                turnCard(drawnCards.get(drawnCards.size() - 3).getCardId());
            }
        }
    }
}



