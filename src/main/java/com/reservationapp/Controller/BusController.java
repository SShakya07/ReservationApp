package com.reservationapp.Controller;

import com.reservationapp.Entity.Route;
import com.reservationapp.Repository.RouteRepository;
import com.reservationapp.Service.BusService;
import com.reservationapp.payload.BusDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

import java.util.List;

@RestController
@RequestMapping("/api/v1/bus")
public class BusController {
    @Autowired
    private RouteRepository routeRepository;
    @Autowired
    private BusService busService;

    //http://localhost:8080/api/v1/bus/add
    @PostMapping("/add")
    public ResponseEntity<String> addBus(@RequestBody BusDto busDto) throws ParseException {
//        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
//        Date fromDate = format.parse(busDto.getFromDate());
//        Date toDate = format.parse(busDto.getToDate());

         busService.addBus(busDto);
        return new ResponseEntity<>("Bus Detail Added", HttpStatus.CREATED);

    }

}
