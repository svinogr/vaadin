package com.example.demo.services.search;

public class Check implements Checkable {
private boolean chek;
    public Check(boolean chek) {
        this.chek = chek;
    }

    @Override
    public boolean isCheck() {
        return chek;
    }
}
