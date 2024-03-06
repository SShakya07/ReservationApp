package com.reservationapp.payload;

import com.reservationapp.Entity.Driver;
import com.reservationapp.Entity.SubRoute;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BusDto {
    private Long busId;
    private String busNumber;
    private String busType;
    private double price;
    private int  totalSeat;
    private int availableSeat;

    private RouteDto route;
    private List<SubRouteDto> subRoutes;
}
