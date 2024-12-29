package br.com.beautique.controllers;


import br.com.beautique.dtos.AppointmentDTO;
import br.com.beautique.services.IAppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/appointments")
public class AppointmentController {

    @Autowired
    private IAppointmentService appointmentService;

    @PostMapping
    ResponseEntity<AppointmentDTO> create(@RequestBody AppointmentDTO appointment) {
        return ResponseEntity.ok(appointmentService.create(appointment));
    }

   @PatchMapping
    ResponseEntity<AppointmentDTO> update(@RequestBody AppointmentDTO appointment){
        return ResponseEntity.ok(appointmentService.update(appointment));
   }

}
