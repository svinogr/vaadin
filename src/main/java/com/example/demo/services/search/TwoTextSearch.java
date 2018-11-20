package com.example.demo.services.search;

public class TwoTextSearch implements Searchable {
    private String textOne;
    private String textTwo;

    public TwoTextSearch(String textOne, String textTwo) {
        this.textOne = textOne;
        this.textTwo = textTwo;
    }

    @Override
    public String[] getTextForSearch() {
        return new String[]{textOne, textTwo};
    }
}
