package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class GameLogic {

    @Autowired
    private userRepository repository;

    //Class variables
    private List<MemoryCard> listOfCards;
    private List<MemoryCard> subList1;
    private List<MemoryCard> subList2;
    private List<MemoryCard> subList3;
    private List<MemoryCard> subList4;
    private String filename1;
    private String filename2;
    private int count = -1;
    private boolean cardsEqual = false;
    private boolean cardsNotEqual = false;
    private List<Integer> playerHighScoreList;
    private List<String> gameHighScoreList;


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

    public void matchCards(int cardId){
        matchList.add(cardId);
        if(matchList.size() % 2 == 0){

            for (MemoryCard card : listOfCards) {
                if (card.getCardId() == matchList.get(matchList.size()-1)){
                    filename1 = card.getFilename();
                }
                if (card.getCardId() == matchList.get(matchList.size()-2)) {
                    filename2 = card.getFilename();
                }
            }
        }
    }

    public void ifCardsNotEqual() {
        if(matchList.size() > 1) {
            if (!filename1.equals(filename2)) {
                turnCard(matchList.get(matchList.size() - 1));
                turnCard(matchList.get(matchList.size() - 2));
                setCardsEqual(false);
                setCardsNotEqual(true);

            } else {
                setCardsEqual(true);
                setCardsNotEqual(false);
            }
        }
    }

    public void setCount() {
        count++;
    }
    public int getCount() {
        return this.count;
    }


    public boolean checkWinCondition(UserInfo player) {

        for (MemoryCard card : listOfCards) {
            if(!card.isVisible()){
                return false;
            }
        }
        System.out.println(getCount());
        int score = getCount();
        repository.saveScoreToDB(score);
        return true;
    }

    public void setCountZero(){
        this.count = -1;
    }

    public void generatePlayerHighScore(UserInfo player) {
        playerHighScoreList = repository.getPlayerHighScoreFromDB(player);
    }

    public void generateGameHighScore() {
        List<UserHighscore> getScores = repository.getGameHighScoreFromDB();
        gameHighScoreList = new ArrayList<>();
        for (UserHighscore h :
                getScores) {
            gameHighScoreList.add(h.getUserName() + ": " + h.getScore());
        }
    }



    //Getters and Setters
    public List<Integer> getPlayerHighScoreList() {
        return playerHighScoreList;
    }

    public void setPlayerHighScoreList(List<Integer> playerHighScoreList) {
        this.playerHighScoreList = playerHighScoreList;
    }

    public List<String> getGameHighScoreList() {
        return gameHighScoreList;
    }

    public void setGameHighScoreList(List<String> gameHighScoreList) {
        this.gameHighScoreList = gameHighScoreList;
    }

    private List<Integer> matchList = new ArrayList<>();

    public List<Integer> getMatchList() {
        return matchList;
    }

    public void setMatchList(List<Integer> matchList) {
        this.matchList = matchList;
    }

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

    public boolean isCardsEqual() {
        return cardsEqual;
    }

    public void setCardsEqual(boolean cardsEqual) {
        this.cardsEqual = cardsEqual;
    }

    public boolean isCardsNotEqual() {
        return cardsNotEqual;
    }

    public void setCardsNotEqual(boolean cardsNotEqual) {
        this.cardsNotEqual = cardsNotEqual;
    }

    public void setListOfCards(List<MemoryCard> listOfCards) {
        this.listOfCards = listOfCards;
    }

}



