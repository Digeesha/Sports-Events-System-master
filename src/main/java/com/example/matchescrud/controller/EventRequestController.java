package com.example.matchescrud.controller;


import com.example.matchescrud.dto.request.EventRequestDTO;
import com.example.matchescrud.dto.response.EventResponseDTO;
import com.example.matchescrud.exceptions.ApiException;
import com.example.matchescrud.service.EventServiceImp;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin("http://localhost:4200")
public class EventRequestController {

    //Dependency injection
    EventServiceImp eventServiceImp;
   

    public EventRequestController(EventServiceImp eventServiceImp) {
		super();
		this.eventServiceImp = eventServiceImp;
	}

	//Get all matches
    @GetMapping("/eventRequest")
    public ResponseEntity<List<EventResponseDTO>> getAllEventRequest(){
        return new ResponseEntity<>(eventServiceImp.getAllEventRequest(), HttpStatus.OK);
    }

    //Get match by UUID
    @GetMapping("/eventRequest/{uuid}")
    public ResponseEntity<EventResponseDTO> getEventRequestByUUID(@PathVariable UUID uuid) throws ApiException {
        return new ResponseEntity<>(eventServiceImp.getEventRequestByUUID(uuid), HttpStatus.OK);
    }


    //Delete match
    @DeleteMapping("/eventRequest/{uuid}")
    public ResponseEntity<?> deleteEventRequest(@PathVariable UUID uuid) throws ApiException {
        return new ResponseEntity<>(eventServiceImp.deleteEventRequest(uuid), HttpStatus.OK);
    }

    //Create match
    @PostMapping("/eventRequest")
    public ResponseEntity<?> createEventRequest(@Valid @RequestBody EventRequestDTO eventRequestDTO) throws ApiException {
        return new ResponseEntity<>(eventServiceImp.createEventRequest(eventRequestDTO), HttpStatus.CREATED);
    }
}
