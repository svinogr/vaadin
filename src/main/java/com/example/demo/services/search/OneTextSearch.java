package com.example.demo.services.search;

public class OneTextSearch implements Searchable {
    private String text;

    public OneTextSearch(String text) {
        this.text = text;
    }

    @Override
    public String[] getTextForSearch() {
        return new String[]{text};
    }
}
