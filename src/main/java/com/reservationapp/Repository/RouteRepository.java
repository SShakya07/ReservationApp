package com.reservationapp.Repository;

import com.reservationapp.Entity.Route;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RouteRepository extends JpaRepository<Route,Long> {
}
