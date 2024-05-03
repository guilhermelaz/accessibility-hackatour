package com.accessibility.accessibility.core.interestpoint.controller;

import com.accessibility.accessibility.core.interestpoint.Service.InterestPointService;
import com.accessibility.accessibility.core.interestpoint.domain.entities.InterestPoint;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/interest-point")
public class InterestPointController {

    private final InterestPointService interestPointService;

    public InterestPointController(InterestPointService interestPointService) {
        this.interestPointService = interestPointService;
    }


    @PostMapping
    public ResponseEntity<InterestPoint> createInterestPoint(@RequestBody InterestPoint interestPoint) {
        interestPointService.createInterestPoint(interestPoint);
        return ResponseEntity.ok(interestPoint);
    }

    @GetMapping("/{id}")
    public ResponseEntity<InterestPoint> getInterestPoint(@PathVariable Long id) {
        return ResponseEntity.ok(interestPointService.findById(id));
    }



    @GetMapping()
    public ResponseEntity<List<InterestPoint>> getAllInterestPoints() {
        return ResponseEntity.ok(interestPointService.findAll());
    }

    @PostMapping("/process-url")
    public ResponseEntity<List<String>> getAccessibilities(@RequestBody Map<String, String> requestBody) {
        String place = requestBody.get("place");
        System.out.println("Nome: " + place);
        return ResponseEntity.ok(interestPointService.findAccessibilities(place));
    }




}
