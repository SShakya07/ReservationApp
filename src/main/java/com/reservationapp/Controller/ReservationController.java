package com.reservationapp.Controller;

import com.reservationapp.Entity.Bus;
import com.reservationapp.Entity.Passenger;
import com.reservationapp.Entity.Route;
import com.reservationapp.Entity.SubRoute;
import com.reservationapp.Repository.BusRepository;
import com.reservationapp.Repository.PassengerRepository;
import com.reservationapp.Repository.RouteRepository;
import com.reservationapp.Repository.SubRouteRepository;
import com.reservationapp.util.EmailService;
import com.reservationapp.util.ExcelGeneratorService;
import com.reservationapp.util.PdfTicketGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/reservation")
public class ReservationController {

    @Autowired
    private ExcelGeneratorService excelGeneratorService;


    @Autowired
    private EmailService emailService;

    @Autowired
    private PdfTicketGeneratorService pdfTicketGeneratorService;

    @Autowired
    private PassengerRepository passengerRepository;

    @Autowired
    private RouteRepository routeRepository;
    @Autowired
    private SubRouteRepository subRouteRepository;
    @Autowired
    private BusRepository busRepository;
    //http:localhost:8080/api/reservation?busId=1&routeId=2
@PostMapping
    public ResponseEntity<String> bookTicket(@RequestBody Passenger passenger, @RequestParam long busId, @RequestParam long routeId){
        boolean busIsPresent = false;
        boolean routeIsPresent = false;
        boolean subRouteIsPresent = false;
        Optional<Bus> byId = busRepository.findById(busId);
        if(byId.isPresent()){
            busIsPresent= true;
            Bus bus = byId.get();
        }

        Optional<Route> byRouteId = routeRepository.findById(routeId);
        if(byRouteId.isPresent()){
            routeIsPresent = true;
            Bus bus = byId.get();
        }

        Optional<SubRoute> bySubRouteId = subRouteRepository.findById(routeId);
        if(bySubRouteId.isPresent()){
            subRouteIsPresent = true;
            Bus bus = byId.get();
        }
        if(busIsPresent&&routeIsPresent || busIsPresent&&subRouteIsPresent){
            //Add Passenger
            Passenger p = new Passenger();
            p.setFirstName(passenger.getFirstName());
            p.setLastName(passenger.getLastName());
            p.setEmail(passenger.getEmail());
            p.setMobile(passenger.getMobile());
            p.setRouteId(passenger.getRouteId());
            p.setBusId(busId);
            p.setRouteId(routeId);
            Passenger savePassenger = passengerRepository.save(p);
            byte[] pdfBytes = pdfTicketGeneratorService.generateTicket(savePassenger, byRouteId.get().getFromLocation(), byRouteId.get().getToLocation(), byRouteId.get().getFromDate());
              emailService.sendEmailWithAttachment(passenger.getEmail(),"Booking Confirm..","your reservation id"+savePassenger.getId(),pdfBytes,"ticket");

        }
        return new ResponseEntity<>("done..", HttpStatus.CREATED);

    }

    @GetMapping("/passenger/excel")
    public ResponseEntity<byte[]> generateExcel(){
      try{
          List<Passenger> passengers  = fetchPassengersFromDatabase();
          byte[] excelBytes = excelGeneratorService.generateExcel(passengers);

          HttpHeaders headers = new HttpHeaders();
          headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
          headers.setContentDispositionFormData("attachment","passenger_data.xlsx");

          return new ResponseEntity<>(excelBytes,headers,HttpStatus.OK);
      }catch(Exception e){
          e.printStackTrace();
          return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

      }
    }

    private List<Passenger> fetchPassengersFromDatabase() {
      return passengerRepository.findAll();
    }
}
