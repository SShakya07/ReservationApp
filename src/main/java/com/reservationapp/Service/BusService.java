package com.reservationapp.Service;



import com.reservationapp.Entity.Bus;
import com.reservationapp.Entity.Route;
import com.reservationapp.Entity.SubRoute;
import com.reservationapp.Repository.BusRepository;
import com.reservationapp.Repository.DriverRepository;
import com.reservationapp.Repository.RouteRepository;
import com.reservationapp.Repository.SubRouteRepository;
import com.reservationapp.payload.BusDto;
import com.reservationapp.payload.SubRouteDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class BusService {
    @Autowired
    private BusRepository busRepository;
    @Autowired
    private RouteRepository routeRepository;
    @Autowired
    private SubRouteRepository subRouteRepository;

   // @Transactional
    public void addBus(BusDto busDto) {
        Route route = new Route();
        route.setFromLocation(busDto.getRoute().getFromLocation());
        route.setTotalDuration(busDto.getRoute().getTotalDuration());
        route.setFromDate(busDto.getRoute().getFromDate());
        route.setToDate(busDto.getRoute().getToDate());
        route.setTotalDuration(busDto.getRoute().getTotalDuration());
        route.setFromTime(busDto.getRoute().getFromTime());
        route.setToTime(busDto.getRoute().getToTime());

        Route saveRoute = routeRepository.save(route);


        Bus bus = new Bus();
        bus.setBusNumber(busDto.getBusNumber());
        bus.setBusType(busDto.getBusType());
        bus.setPrice(busDto.getPrice());
        bus.setTotalSeat(busDto.getTotalSeat());
        bus.setAvailableSeat(busDto.getAvailableSeat());

       // route.setBus(bus);

        bus.setRoute(route);

        Bus saveBus = busRepository.save(bus);


        Route routeUpdate = routeRepository.findById(saveRoute.getId()).get();
        routeUpdate.setBus(saveBus);

        routeRepository.save(routeUpdate);


        if (busDto.getSubRoutes() != null && !busDto.getSubRoutes().isEmpty()) {
            for (SubRouteDto subRouteDto : busDto.getSubRoutes()) {
                SubRoute subRoute = new SubRoute();
                subRoute.setFromLocation(subRouteDto.getFromLocation());
                subRoute.setToLocation(subRouteDto.getToLocation());
                subRoute.setToDate(subRouteDto.getToDate());
                subRoute.setFromDate(subRouteDto.getFromDate());
                subRoute.setFromTime(subRouteDto.getFromTime());
                subRoute.setToTime(subRouteDto.getToTime());
                subRoute.setTotalDuration(subRouteDto.getTotalDuration());

                subRoute.setRoute(route);

                subRouteRepository.save(subRoute);

            }
        }
    }
}

