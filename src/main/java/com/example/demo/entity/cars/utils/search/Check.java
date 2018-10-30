package com.example.demo.entity.cars.utils.search;

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
