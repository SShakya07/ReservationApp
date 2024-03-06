package com.reservationapp.Entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Bus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long busId;
    @Column(name="bus_number",unique = true)
    private String busNumber;
    private String busType;
    private double price;
    private int  totalSeat;
    private int availableSeat;

    @OneToOne(mappedBy = "bus")
   private Route route;


}
