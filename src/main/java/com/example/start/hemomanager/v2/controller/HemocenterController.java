package com.example.start.hemomanager.v2.controller;

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
import java.util.Optional;

@RestController @RequestMapping("/hemocenters")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class HemocenterController {
    @Autowired private HemocenterRepository hemocenterRepository;
    @Autowired private ScheduleHemocenterRepository scheduleHemocenterRepository;

    @PostMapping
    public ResponseEntity signIn(@RequestBody HemocenterSignInDTO hemocenterDTO) {
        if (hemocenterRepository.existsByEmailAndCnpj(
                        hemocenterDTO.getEmail(),
                        hemocenterDTO.getCnpj())
        ) return ResponseEntity.status(422).body("E-mail ou CNPJ já cadastrados.");

        Hemocenter hemocenter = new Hemocenter();
        BeanUtils.copyProperties(hemocenterDTO, hemocenter);

        Hemocenter saved = hemocenterRepository.save(hemocenter);
        return ResponseEntity.status(200).body(saved);
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody LoginDTO hemocenterDTO) {
        Hemocenter hemocenter = hemocenterRepository.findByEmailAndPassword(
                hemocenterDTO.getEmail(),
                hemocenterDTO.getPassword());
        if (hemocenter == null) return ResponseEntity.status(404).build();

        return ResponseEntity.status(200).build();
    }

    @PostMapping("/") @ResponseStatus(HttpStatus.CREATED)
    public Hemocenter insertHemocenter(@RequestBody @Valid Hemocenter hemocenter) {
        return hemocenterRepository.save(hemocenter);
    }

    @GetMapping("/")
    public Iterable<Hemocenter> getAllHemocenters() {
        return hemocenterRepository.findAll();
    }

    @PostMapping("/scheduleHemocenter")
    public ScheduleHemocenter insertSchedule (@RequestBody @Valid ScheduleHemocenterRequest scheduleHemocenterRequest){
        Optional<ScheduleHemocenter> hemocenterOptional =  scheduleHemocenterRepository.findById(scheduleHemocenterRequest.getHemocenterId());
        ScheduleHemocenter hemocenter = hemocenterOptional.get();

        ScheduleHemocenter scheduleHemocenter = new ScheduleHemocenter(hemocenter,scheduleHemocenterRequest.getScheduledDate(),scheduleHemocenterRequest.getScheduledTime());
        return scheduleHemocenterRepository.save(scheduleHemocenter);
    }


}
