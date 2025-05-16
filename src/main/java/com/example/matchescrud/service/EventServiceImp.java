package com.example.matchescrud.service;

import com.example.matchescrud.Mapper.EventRequestMapper;
import com.example.matchescrud.Mapper.EventResponseDTOMapper;
import com.example.matchescrud.dto.request.EventRequestDTO;
import com.example.matchescrud.dto.response.EventResponseDTO;
import com.example.matchescrud.exceptions.ApiException;
import com.example.matchescrud.exceptions.NotFoundExceptions.MatchNotFoundException;
import com.example.matchescrud.exceptions.NotFoundExceptions.TeamNotFoundException;
import com.example.matchescrud.exceptions.StadiumSizeException;
import com.example.matchescrud.model.entity.EventRequest;
import com.example.matchescrud.model.entity.Team;
import com.example.matchescrud.repository.EventRepository;
import com.example.matchescrud.repository.TeamRepository;
import com.example.matchescrud.service.interfaces.IEventRequestService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class EventServiceImp implements IEventRequestService {

    //Dependency injection
    EventRepository eventRepository;
    TeamRepository teamRepository;
    EventResponseDTOMapper eventResponseDTOMapper;
    EventRequestMapper eventRequestMapper;
   

    public EventServiceImp(EventRepository eventRepository, TeamRepository teamRepository,
			EventResponseDTOMapper eventResponseDTOMapper, EventRequestMapper eventRequestMapper) {
		super();
		this.eventRepository = eventRepository;
		this.teamRepository = teamRepository;
		this.eventResponseDTOMapper = eventResponseDTOMapper;
		this.eventRequestMapper = eventRequestMapper;
	}

	
  
    public void addMatchToTeams(Team homeTeam, Team awayTeam, EventRequest eventRequest){
        homeTeam.getHomeEvent().add(eventRequest);
        awayTeam.getAwayEvent().add(eventRequest);
    }

  //GET
    @Transactional
    @Override
	public List<EventResponseDTO> getAllEventRequest() {
		List<EventRequest> eventRequest = eventRepository.findAll();
        return eventResponseDTOMapper.eventListToEventResponseDTOList(eventRequest);
	}

    @Transactional
	@Override
	public EventResponseDTO getEventRequestByUUID(UUID uuid) throws ApiException {
    	EventRequest eventRequest = eventRepository.findById(uuid).orElseThrow(() -> new MatchNotFoundException(uuid));
          return eventResponseDTOMapper.eventToEventResponseDTO(eventRequest);
      }
	
    @Transactional
	@Override
	public EventResponseDTO createEventRequest(EventRequestDTO eventRequestDTO) throws ApiException {
    	EventRequest eventRequest = eventRequestMapper.eventRequestDTOtoevent(eventRequestDTO);

          //Search matches by their ID on th DB
          Optional<Team> optionalHomeTeam = teamRepository.findById(eventRequest.getHomeTeam().getId());
          Optional<Team> optionalAwayTeam = teamRepository.findById(eventRequest.getAwayTeam().getId());

          Team homeTeam = optionalHomeTeam.orElseThrow(() -> new TeamNotFoundException(eventRequest.getHomeTeam().getId()));
          Team awayTeam = optionalAwayTeam.orElseThrow(() -> new TeamNotFoundException(eventRequest.getAwayTeam().getId()));

          //Set Random UUID
          eventRequest.setUuid(UUID.randomUUID());
          //Set HomeTeam stadium as match stadium
          if(eventRequest.getSpectators() > homeTeam.getStadium().getCapacity()){
              throw new StadiumSizeException(eventRequest.getSpectators(), homeTeam.getStadium());
          }
          eventRequest.setRequesterName(eventRequestDTO.getRequesterName());
          eventRequest.setStadium(homeTeam.getStadium());
          eventRequest.setHomeTeam(homeTeam);
          eventRequest.setAwayTeam(awayTeam);
          eventRequest.setTime(eventRequestDTO.getTime());
          eventRequest.setDate(eventRequestDTO.getDate());
          
         
          //Converts spectators number to BigDecimal
          BigDecimal spectatorsBigDecimal = BigDecimal.valueOf(eventRequestDTO.getSpectators());
         

          EventRequest eventResponse = eventRepository.save(eventRequest);

          //Add match to HomeTeam and AwayTeam match lists.
          addMatchToTeams(homeTeam, awayTeam, eventResponse);
          return eventResponseDTOMapper.eventToEventResponseDTO(eventResponse);

	}

    @Transactional
	@Override
	public EventResponseDTO deleteEventRequest(UUID id) throws ApiException {
    	 Optional<EventRequest> eventRequest = eventRepository.findById(id);
         if (eventRequest.isPresent()) {
        	 eventRepository.delete(eventRequest.get());
             return eventResponseDTOMapper.eventToEventResponseDTO(eventRequest.get());
         }
         throw new MatchNotFoundException(id);
     }
}
