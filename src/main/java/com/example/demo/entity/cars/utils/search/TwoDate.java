package com.example.demo.entity.cars.utils.search;

import javax.xml.crypto.Data;
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
