package com.example.demo.services.search;

import java.util.Date;

public class TwoDate implements Datable {
    private Date dataFrom;
    private Date dataTo;

    public TwoDate(Date dataFrom, Date dataTo) {
        this.dataFrom = dataFrom;
        this.dataTo = dataTo;
    }

    @Override
    public Date[] getDateForSearch() {
        return new Date[]{dataFrom, dataTo};
    }
}
