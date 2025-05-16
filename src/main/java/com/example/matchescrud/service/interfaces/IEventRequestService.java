package com.example.matchescrud.service.interfaces;


import com.example.matchescrud.dto.request.EventRequestDTO;
import com.example.matchescrud.dto.response.EventResponseDTO;
import com.example.matchescrud.exceptions.ApiException;

import java.util.List;
import java.util.UUID;

public interface IEventRequestService {

    //Get
    List<EventResponseDTO> getAllEventRequest();
    EventResponseDTO getEventRequestByUUID(UUID uuid) throws ApiException;

    //Post
    EventResponseDTO createEventRequest(EventRequestDTO eventRequestDTO) throws ApiException;

    //Delete Match
    EventResponseDTO deleteEventRequest(UUID id) throws ApiException;
}
