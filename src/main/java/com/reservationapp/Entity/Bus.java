package com.reservationapp.Entity;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "bus")
public class Bus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long busId;

    @Column(name="bus_number")
    private String busNumber;

    @Column(name="bus_type")
    private String busType;

    @Column(name="price")
    private double price;

    @Column(name="total_seat")
    private int  totalSeat;

    @Column(name="available_seats")
    private int availableSeat;




}
