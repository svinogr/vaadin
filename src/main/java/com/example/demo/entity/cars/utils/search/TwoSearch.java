package com.example.demo.entity.cars.utils.search;

public class TwoSearch implements Searchable {
    private String textOne;
    private String textTwo;

    public TwoSearch(String textOne, String textTwo) {
        this.textOne = textOne;
        this.textTwo = textTwo;
    }

    @Override
    public String[] getTextForSearch() {
        return new String[]{textOne, textTwo};
    }
}
