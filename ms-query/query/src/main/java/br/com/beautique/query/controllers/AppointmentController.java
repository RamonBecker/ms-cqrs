package br.com.beautique.query.controllers;


import br.com.beautique.query.dtos.appointments.FullAppointmentsDTO;
import br.com.beautique.query.services.IAppointmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/appointment")
public class AppointmentController {

    private final IAppointmentService appointmentService;

    public AppointmentController(IAppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @GetMapping
    ResponseEntity<List<FullAppointmentsDTO>> listAllAppointment() {
        return ResponseEntity.ok(appointmentService.listAllAppointments());
    }

    @GetMapping("/customer/{customerId}")
    ResponseEntity<List<FullAppointmentsDTO>> listAppointmentsByCustomer(@PathVariable Long customerId) {
        return ResponseEntity.ok(appointmentService.listAppointmentsByCustomer(customerId));
    }

    @GetMapping("/beauty-procedure/{beautyProcedureId}")
    ResponseEntity<List<FullAppointmentsDTO>> listAppointmentsByBeautyProcedure(@PathVariable Long beautyProcedureId) {
        return ResponseEntity.ok(appointmentService.listAppointmentsByBeautyProcedure(beautyProcedureId));
    }


}
