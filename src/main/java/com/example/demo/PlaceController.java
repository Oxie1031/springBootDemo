package com.example.demo;

import org.springframework.web.bind.annotation.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicLong;

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
    public Response addPlace(@RequestBody Place place) {
        Long id = counter.incrementAndGet();
        places.put(id, place);
        return new Response("Successfully added place.", id);
    }

    @PatchMapping("/places/{id}")
    public Response updatePlace(@PathVariable Long id, @RequestBody Place newPlace) {
        places.put(id, newPlace);
        return new Response("Successfully updated place.", newPlace);
    }

    @DeleteMapping("/places/{id}")
    public Response deletePlace(@PathVariable Long id) {
        places.remove(id);
        return new Response("Successfully deleted place.", null);
    }
}
