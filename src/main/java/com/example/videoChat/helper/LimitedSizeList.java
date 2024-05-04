package com.example.videoChat.helper;

import java.util.LinkedList;

public class LimitedSizeList<E> extends LinkedList {

    private int maxSize;
    public LimitedSizeList(int maxSize){
        this.maxSize = maxSize;
    }

    public synchronized boolean addTheThing(E e){
        boolean added = super.add(e);
        if(maxSize < this.size()){
            super.removeFirst();
        }
        return added;
    }
}
