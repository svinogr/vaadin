package com.example.demo.entity.cars.utils.search;

import java.util.Date;

public class OneDate implements Datable {
    private Date date;

    public OneDate(Date date) {
        this.date = date;
    }

    @Override
    public Date[] getDateForSearch() {
        return new Date[]{date};
    }
}
