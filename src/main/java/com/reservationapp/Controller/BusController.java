package com.reservationapp.Controller;

import com.reservationapp.Entity.Bus;
import com.reservationapp.Entity.Route;
import com.reservationapp.Entity.SubRoute;
import com.reservationapp.Exception.ResourceNotFound;
import com.reservationapp.Repository.BusRepository;
import com.reservationapp.Repository.RouteRepository;
import com.reservationapp.Repository.SubRouteRepository;
import com.reservationapp.Service.BusService;
import com.reservationapp.payload.BusDto;
import com.reservationapp.payload.SearchListOfBusesDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/bus")
public class BusController {
    @Autowired
    private BusRepository busRepository;
    @Autowired
    private RouteRepository routeRepository;
    @Autowired
    private BusService busService;

    @Autowired
    private SubRouteRepository subRouteRepository;

    //http://localhost:8080/api/v1/bus/add
    @PostMapping("/add")
    public ResponseEntity<String> addBus(@RequestBody BusDto busDto) throws ParseException {
//        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
//        Date fromDate = format.parse(busDto.getFromDate());
//        Date toDate = format.parse(busDto.getToDate());

         busService.addBus(busDto);
        return new ResponseEntity<>("Bus Detail Added", HttpStatus.CREATED);

    }
    //http://localhost:8080/api/v1/bus?fromLocation=&toLocation=&fromDate
    @GetMapping
    public List<SearchListOfBusesDto> getAllBuses(@RequestParam String fromLocation,@RequestParam String toLocation,@RequestParam String fromDate){
        List<Route> routes = routeRepository.findByFromLocationAndToLocationAndFromDate(fromLocation, toLocation, fromDate);
        List<SubRoute> subRoutes = subRouteRepository.findByFromLocationAndToLocationAndFromDate(fromLocation, toLocation, fromDate);
        //     System.out.println(routes);
        //return null;
        List<SearchListOfBusesDto> buses = new ArrayList<>();
        if(routes!=null) {
            for (Route route : routes) {

                // System.out.println(route.getBusId());
                Bus bus = busRepository.findById(route.getBusId()).get();
                SearchListOfBusesDto searchListOfBusesDto = maptoSearchListOfBusesDto(bus, route);
                buses.add(searchListOfBusesDto);

            }
            return buses;
        }
        if(subRoutes!=null) {
            for (SubRoute route : subRoutes) {

                // System.out.println(route.getBusId());
                Bus bus = busRepository.findById(route.getBusId()).get();
                SearchListOfBusesDto searchListOfBusesDto = maptoSearchListOfBusesDto(bus, route);
                buses.add(searchListOfBusesDto);

            }
            return buses;
        }
         return null ;
      //  return null;

    }
   SearchListOfBusesDto maptoSearchListOfBusesDto(Bus bus,Route route){
        SearchListOfBusesDto searchListOfBusesDto = new SearchListOfBusesDto();
        searchListOfBusesDto.setBusId(bus.getBusId());
        searchListOfBusesDto.setBusNumber(bus.getBusNumber());
        searchListOfBusesDto.setBusType(bus.getBusType());
        searchListOfBusesDto.setPrice(bus.getPrice());
        searchListOfBusesDto.setAvailableSeat(bus.getAvailableSeat());
        searchListOfBusesDto.setTotalSeat(bus.getTotalSeat());
        searchListOfBusesDto.setFromLocation(route.getFromLocation());
        searchListOfBusesDto.setFromDate(route.getFromDate());
        searchListOfBusesDto.setToDate(route.getToDate());
        searchListOfBusesDto.setToLocation(route.getToLocation());
        searchListOfBusesDto.setRouteId(route.getId());
        searchListOfBusesDto.setTotalDuration(route.getTotalDuration());
        searchListOfBusesDto.setFromTime(route.getFromTime());
        searchListOfBusesDto.setToTime(route.getToTime());

        return searchListOfBusesDto;
    }

    SearchListOfBusesDto maptoSearchListOfBusesDto(Bus bus,SubRoute route){
        SearchListOfBusesDto searchListOfBusesDto = new SearchListOfBusesDto();
        searchListOfBusesDto.setBusId(bus.getBusId());
        searchListOfBusesDto.setBusNumber(bus.getBusNumber());
        searchListOfBusesDto.setBusType(bus.getBusType());
        searchListOfBusesDto.setPrice(bus.getPrice());
        searchListOfBusesDto.setAvailableSeat(bus.getAvailableSeat());
        searchListOfBusesDto.setTotalSeat(bus.getTotalSeat());
        searchListOfBusesDto.setFromLocation(route.getFromLocation());
        searchListOfBusesDto.setFromDate(route.getFromDate());
        searchListOfBusesDto.setToDate(route.getToDate());
        searchListOfBusesDto.setToLocation(route.getToLocation());
        searchListOfBusesDto.setRouteId(route.getId());
        searchListOfBusesDto.setTotalDuration(route.getTotalDuration());
        searchListOfBusesDto.setFromTime(route.getFromTime());
        searchListOfBusesDto.setToTime(route.getToTime());

        return searchListOfBusesDto;
    }

}
