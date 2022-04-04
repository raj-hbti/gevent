package com.hackerrank.gevents.controller;


import org.springframework.http.RequestEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.util.*;
import com.hackerrank.gevents.model.*;
import com.hackerrank.gevents.repository.*;

@RestController
public class EventController {
    @Autowired
    EventRepository eventRepository;

    @RequestMapping(value = "/repos/{repoId}/events", method = RequestMethod.GET)
    ResponseEntity<List<Event>> getAllEventsByRepoId(@PathVariable("repoId") Integer repoId)
    {
        return new ResponseEntity<>(eventRepository.findAllByRepoId(repoId), HttpStatus.OK);
    }

    @RequestMapping(value = "/events", method = RequestMethod.GET)
    ResponseEntity<List<Event>> getAllEvents()
    {
        return new ResponseEntity<>(eventRepository.findAll(), HttpStatus.OK);
    }

    @RequestMapping(value = "/events/{eventId}", method = RequestMethod.GET)
    ResponseEntity<Event> getEventById(@PathVariable("eventId") Integer eventId)
    {
        List<Integer> ids = new ArrayList<Integer>();
        ids.add(eventId);
        List<Event> events =  eventRepository.findAllById(ids);
        if(events == null || events.size() == 0)
        {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(events.get(0), HttpStatus.OK);
    }

    @RequestMapping(value = "/events", method = RequestMethod.POST)
    ResponseEntity<Event> createEvent(RequestEntity<Event> event)
    {
        Event createdEvent = eventRepository.save(event.getBody());
        return new ResponseEntity<>(createdEvent, HttpStatus.CREATED) ;
    }
}
