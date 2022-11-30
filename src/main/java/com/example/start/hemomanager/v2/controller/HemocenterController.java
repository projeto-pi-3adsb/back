package com.example.start.hemomanager.v2.controller;

import com.example.start.hemomanager.v2.domain.Donor;
import com.example.start.hemomanager.v2.domain.Hemocenter;
import com.example.start.hemomanager.v2.domain.ScheduleHemocenter;
import com.example.start.hemomanager.v2.dto.HemocenterSignInDTO;
import com.example.start.hemomanager.v2.dto.LoginDTO;
import com.example.start.hemomanager.v2.repository.HemocenterRepository;
import com.example.start.hemomanager.v2.repository.ScheduleHemocenterRepository;
import com.example.start.hemomanager.v2.request.ScheduleHemocenterRequest;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController @RequestMapping("/hemocenter")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class HemocenterController {
    @Autowired private HemocenterRepository hemocenterRepository;
    @Autowired private ScheduleHemocenterRepository scheduleHemocenterRepository;

    @PostMapping
    public ResponseEntity signIn(@RequestBody HemocenterSignInDTO hemocenterDTO) {
        if (hemocenterRepository.existsByEmailAndCnpj(
            hemocenterDTO.getEmail(), hemocenterDTO.getCnpj())
        ) return ResponseEntity.status(422).body("E-mail ou CNPJ já cadastrados.");

        Hemocenter hemocenter = new Hemocenter();
        BeanUtils.copyProperties(hemocenterDTO, hemocenter);

        Hemocenter saved = hemocenterRepository.save(hemocenter);
        return ResponseEntity.status(201).body(saved);
    }

    @PostMapping("/current")
    public ResponseEntity loginWithReturn(@RequestBody LoginDTO hemocenterDTO) {
        Hemocenter hemocenter = hemocenterRepository.findByEmailAndPassword(
            hemocenterDTO.getEmail(), hemocenterDTO.getPassword());
        if (hemocenter == null) return ResponseEntity.status(404).build();

        return ResponseEntity.status(200).body(hemocenter);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Hemocenter> updateHemocenter(@PathVariable int id, @RequestBody Hemocenter hemocenterDTO) {
        if (hemocenterRepository.existsById(id)) {
            hemocenterDTO.setUuid(id);
            hemocenterRepository.save(hemocenterDTO);
            return ResponseEntity.status(200).body(hemocenterDTO);
        }
        return ResponseEntity.status(404).build();
    }

    @GetMapping()
    public List<Hemocenter> getAllHemocenters() {
        return hemocenterRepository.findAll();
    }

    @PostMapping("/scheduleHemocenter")
    public ScheduleHemocenter insertSchedule (@RequestBody @Valid ScheduleHemocenterRequest scheduleHemocenterRequest){
        Optional<Hemocenter> hemocenterOptional = hemocenterRepository.findById(scheduleHemocenterRequest.getHemocenterId());
        Hemocenter hemocenter = hemocenterOptional.get();

        ScheduleHemocenter scheduleHemocenter = new ScheduleHemocenter(hemocenter,scheduleHemocenterRequest.getScheduledDate(),scheduleHemocenterRequest.getScheduledTime());
        return scheduleHemocenterRepository.save(scheduleHemocenter);
    }

}
