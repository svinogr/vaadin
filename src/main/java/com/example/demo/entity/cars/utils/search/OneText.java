package com.example.demo.entity.cars.utils.search;

public class OneText implements Searchable {
    private String text;

    public OneText(String text) {
        this.text = text;
    }

    @Override
    public String[] getTextForSearch() {
        return new String[]{text};
    }
}
