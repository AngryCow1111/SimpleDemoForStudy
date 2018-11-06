package com.angrycow1111.simpleioc.entity;

public class Robot {
    private Hand hand;
    private Mouth mouth;

    public void show() {
        hand.waveHand();
        mouth.speak();
    }
}
