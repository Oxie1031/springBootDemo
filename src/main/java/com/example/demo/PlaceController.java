package com.example.demo;

import org.springframework.web.bind.annotation.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import javax.validation.Valid;
import org.springframework.validation.BindingResult;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.validation.ObjectError;



@RestController
public class PlaceController {
    private final ConcurrentMap<Long, Place> places = new ConcurrentHashMap<>();
    private final AtomicLong counter = new AtomicLong();

    public PlaceController() {
        places.put(1L, new Place("USA", "Statue of Liberty"));
        places.put(2L, new Place("Japan", "Mount Fuji"));
        places.put(3L, new Place("France", "Eiffel Tower"));
        counter.set(3);
    }

    @GetMapping("/places/{id}")
    public Response getPlace(@PathVariable Long id) {
        return new Response("Successfully retrieved place.", places.get(id));
    }

    @PostMapping("/places")
    public Response addPlace(@Valid @RequestBody Place place, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            List<String> errorMessages = bindingResult.getAllErrors().stream()
                    .map(ObjectError::getDefaultMessage)
                    .collect(Collectors.toList());
            return new Response("Invalid data provided. Errors: " + errorMessages, null);
        }
        Long id = counter.incrementAndGet();
        places.put(id, place);
        return new Response("Successfully added place.", id);
    }



    @PatchMapping("/places/{id}")
    public Response updatePlace(@PathVariable Long id, @Valid @RequestBody Place newPlace, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            List<String> errorMessages = bindingResult.getAllErrors().stream()
                    .map(ObjectError::getDefaultMessage)
                    .collect(Collectors.toList());
            return new Response("Invalid data provided. Errors: " + errorMessages, null);
        }
        if (!places.containsKey(id)) {
            throw new ResourceNotFoundException("Place with id " + id + " not found.");
        }
        places.put(id, newPlace);
        return new Response("Successfully updated place.", newPlace);
    }



    @DeleteMapping("/places/{id}")
    public Response deletePlace(@PathVariable Long id) {
        places.remove(id);
        return new Response("Successfully deleted place.", null);
    }
}
