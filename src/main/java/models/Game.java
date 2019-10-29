package models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 * Assignment 1: Each of the blank methods below require implementation to get AcesUp to build/run
 */
public class Game {

    public java.util.List<Card> deck = new ArrayList<>();

    public java.util.List<java.util.List<Card>> cols = new ArrayList<>(4);


    public Game(){
        // initialize a new game such that each column can store cards
        for(int i = 0; i < 4; i++){
            cols.add(new ArrayList<Card>());
        }
    }

    public void buildDeck() {
        for(int i = 2; i < 15; i++){
            deck.add(new Card(i,Suit.Clubs));
            deck.add(new Card(i,Suit.Hearts));
            deck.add(new Card(i,Suit.Diamonds));
            deck.add(new Card(i,Suit.Spades));
        }

    }

    public void shuffle() {
        // shuffles the deck so that it is random

    }

    public void dealFour() {
        // remove the top card from the deck and add it to a column; repeat for each of the four columns
        for (int i = 0;i < 4 ;i++) {
          addCardToCol(i, deck.get(deck.size() - 1));
          deck.remove(deck.size()-1);
        }
        addCardToCol(0, new Card(2, Suit.Clubs));
    }



    public void remove(int columnNumber) {
        int col = columnNumber;
        // check if there are any cards to remove
        if( !columnHasCards(col)) { return; }

        Card target = getTopCard(col);
        Card index = getTopCard(0);
        // also check for pairs
        for(int i = 0; i < 4; i++) {
            if(!columnHasCards(i)){ return; }
            if(i != col){

                index = getTopCard(i);

                if(target.getSuit() == index.getSuit()){
                    break;
                }

            }
         }
        if(target.getSuit() != index.getSuit()){
            return;
        }

        for(int i = 0; i < 4; i++) {
            if(!columnHasCards(i)){ return; }
            if(i != col){

                index = getTopCard(i);

                if(target.getSuit() == index.getSuit()){
                    if(target.getValue() > index.getValue()){
                        return;
                    }
                }

            }
        }


        removeCardFromCol(columnNumber);

    }

    private boolean columnHasCards(int columnNumber) {
        // check indicated column for number of cards; if no cards return false, otherwise return true
        java.util.List<Card> target = this.cols.get(columnNumber);
        // don't do anything if column is empty
        if(target.isEmpty()){ return false; }

        return true;
    }

    private Card getTopCard(int columnNumber) {
        return this.cols.get(columnNumber).get(this.cols.get(columnNumber).size()-1);
    }


    public void move(int columnFrom, int columnTo) {
        // remove the top card from the columnFrom column, add it to the columnTo column
        if(columnHasCards(columnTo)){ return; } //Should return if columnTo is not empty
        if(! columnHasCards(columnFrom)){ return; } // Should return columnFrom is empty

        Card cardMoved =  getTopCard(columnFrom);

        removeCardFromCol(columnFrom);

        java.util.List<Card> colunm_to = cols.get(columnTo);

        colunm_to.add( cardMoved );
    }

    private void addCardToCol(int columnTo, Card cardToMove) {
        cols.get(columnTo).add(cardToMove);
    }

    private void removeCardFromCol(int colFrom) {
        this.cols.get(colFrom).remove(this.cols.get(colFrom).size()-1);
    }
}
