package ca.ubc.cs304.model;

import java.sql.Date;
import java.sql.Timestamp;

public class ReservationModel {
    /**
     * The intent for this class is to update/store information about a single reservation
     */

    // private final Number confNo;
    private  final String location;
    private final String vtname;
    private final String name;
    private final Long cellphone;
    private final java.sql.Date fromDate;
    private final java.sql.Timestamp fromTime;
    private final java.sql.Date toDate;
    private final java.sql.Timestamp toTime;


    public ReservationModel(String location, String vtname, String name, Long cellphone, Date fromDate
            , Timestamp fromTime,
                            Date toDate, Timestamp toTime) {
        // this.confNo = confNo;
        this.location = location;
        this.vtname = vtname;
        this.name = name;
        this.cellphone = cellphone;
        this.fromDate = fromDate;
        this.fromTime = fromTime;
        this.toDate = toDate;
        this.toTime = toTime;
    }

    // public Number getConfNo() {
           // return confNo;
       // }

    public String getLocation() {
        return location;
    }

    public String getVtname() {
        return vtname;
    }

    public String getName() {return name; }

    public Long getCellphone() {
        return cellphone;
    }

    public java.sql.Date getFromDate() {
        return fromDate;
    }

    public Timestamp getFromTime() {
        return fromTime;
    }

    public java.sql.Date getToDate() {
    return toDate;
}

    public java.sql.Timestamp getToTime() {
        return toTime;
    }
}
