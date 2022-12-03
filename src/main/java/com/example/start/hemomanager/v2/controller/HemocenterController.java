package com.example.start.hemomanager.v2.controller;

import com.example.start.hemomanager.v2.domain.Hemocenter;
import com.example.start.hemomanager.v2.domain.ScheduleHemocenter;
import com.example.start.hemomanager.v2.domain.dto.HemocenterSignInDTO;
import com.example.start.hemomanager.v2.domain.dto.LoginDTO;
import com.example.start.hemomanager.v2.request.ScheduleHemocenterRequest;
import com.example.start.hemomanager.v2.service.HemocenterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController @RequestMapping("/hemocenter")
public class HemocenterController {
    @Autowired
    HemocenterService hemocenterService;

    @PostMapping
    public ResponseEntity<Hemocenter> createHemocenter(@RequestBody HemocenterSignInDTO hemocenterDTO) {
        return hemocenterService.createHemocenter(hemocenterDTO);
    }

    @PostMapping("/current")
    public ResponseEntity<Hemocenter> loginHemocenter(@RequestBody LoginDTO hemocenterDTO) {
        return hemocenterService.loginHemocenter(hemocenterDTO);

    }

    @PutMapping("/{id}")
    public ResponseEntity<Hemocenter> updateHemocenter(@PathVariable int id, @RequestBody Hemocenter hemocenterDTO) {
        return hemocenterService.updateHemocenter(id, hemocenterDTO);

    }

    @GetMapping
    public ResponseEntity<List<Hemocenter>> getAllHemocenters() {
        return hemocenterService.getAllHemocenters();

    }

    @PostMapping("/scheduleHemocenter")
    public ResponseEntity<ScheduleHemocenter> insertScheduleTime (@RequestBody @Valid ScheduleHemocenterRequest scheduleHemocenterRequest){
        return hemocenterService.insertScheduleTime(scheduleHemocenterRequest);

    }

}
