package com.reservationapp.payload;

import lombok.Data;

@Data
public class SearchListOfBusesDto {
    private Long busId;

    private String busNumber;

     private String busType;


    private double price;


    private int  totalSeat;


    private int availableSeat;
    private Long routeId;
    private String fromLocation;
    private String toLocation;
    private String fromDate;
    private String toDate;
    private String totalDuration;
    private String fromTime;
    private String toTime;

}
