package com.reservationapp.Repository;

import com.reservationapp.Entity.Route;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RouteRepository extends JpaRepository<Route,Long> {

   // List<Route> findByFromLocationAndToLocationAndFromDate(String fromLocation , String toLocation, String fromDate);

    Route findByBusId(long busId);
    List<Route> findByFromLocationAndToLocationAndFromDate(String fromLocation, String toLocation, String fromDate);
}
