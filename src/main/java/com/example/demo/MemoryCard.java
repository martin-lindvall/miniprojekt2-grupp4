package com.example.demo;

public class MemoryCard {

    private boolean isVisible;
    private int cardId;
    private String filename;

    public MemoryCard(boolean isVisible, int cardId, String filename) {
        this.isVisible = isVisible;
        this.cardId = cardId;
        this.filename = filename;
    }

    //Changes the status of isVisible
    public void flip() {
        isVisible = !isVisible;
    }

    public boolean isVisible() {
        return isVisible;
    }

    public void setVisible(boolean visible) {
        isVisible = visible;
    }

    public int getCardId() {
        return cardId;
    }

    public void setCardId(int cardId) {
        this.cardId = cardId;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }
}
