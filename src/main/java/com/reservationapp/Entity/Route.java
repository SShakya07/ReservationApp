package com.reservationapp.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@NoArgsConstructor
@Data
@AllArgsConstructor
public class Route {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String fromLocation;
    private String toLocation;
    private String fromDate;
    private  String toDate;
    private String totalDuration;
    private String fromTime;
    private String toTime;

    @OneToOne
    @JoinColumn(name="bus_id")
    private Bus bus;

    @OneToMany(mappedBy = "route")
    private List<SubRoute> subRoutes;
}
