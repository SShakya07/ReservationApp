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
    @Column(name="from_Location")
    private String fromLocation;
    @Column(name="to_Location")
    private String toLocation;
    @Column(name="from_Date")
    private String fromDate;
    @Column(name="to_Date")
    private  String toDate;
    @Column(name="total_Duration")
    private String totalDuration;
    @Column(name="from_time")
    private String fromTime;
    @Column(name="to_time")
    private String toTime;

    @Column(name="bus_id",unique = true,nullable = false)
    private long busId;



//    @OneToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name="bus_id",referencedColumnName = "id")
//    private Bus bus;
//
//    @OneToMany(mappedBy = "route" ,fetch = FetchType.LAZY)
//    private List<SubRoute> subRoutes;
}
