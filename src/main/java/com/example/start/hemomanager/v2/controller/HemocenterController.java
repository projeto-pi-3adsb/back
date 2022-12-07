package com.example.start.hemomanager.v2.controller;

import com.example.start.hemomanager.v2.domain.Hemocenter;
import com.example.start.hemomanager.v2.domain.ScheduleHemocenter;
import com.example.start.hemomanager.v2.domain.dto.HemocenterDTO;
import com.example.start.hemomanager.v2.domain.dto.LoginDTO;
import com.example.start.hemomanager.v2.repository.HemocenterRepository;
import com.example.start.hemomanager.v2.repository.ScheduleHemocenterRepository;
import com.example.start.hemomanager.v2.request.ScheduleHemocenterRequest;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController @RequestMapping("/hemocenter")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class HemocenterController {
    @Autowired private HemocenterRepository hemocenterRepository;
    @Autowired private ScheduleHemocenterRepository scheduleHemocenterRepository;

    @PostMapping
    public ResponseEntity<Hemocenter> createHemocenter(@RequestBody HemocenterDTO hemocenterDTO) {
        if (hemocenterRepository.existsByEmailAndCnpj(hemocenterDTO.getEmail(), hemocenterDTO.getCnpj())) throw new ResponseStatusException(HttpStatus.CONFLICT, "Email ou CPF já cadastrados.");

        Hemocenter hemocenter = new Hemocenter();
        BeanUtils.copyProperties(hemocenterDTO, hemocenter);

        Hemocenter saved = hemocenterRepository.save(hemocenter);
        return ResponseEntity.status(201).body(saved);
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<Hemocenter> getHemocentro(@PathVariable int uuid){
        Hemocenter hemocenter = hemocenterRepository.findByUuid(uuid);

        return ResponseEntity.status(200).body(hemocenter);
    }

    @PostMapping("/current")
    public ResponseEntity<Hemocenter> loginHemocenter(@RequestBody LoginDTO hemocenterDTO) {
        Hemocenter hemocenter = hemocenterRepository.findByEmailAndPassword(hemocenterDTO.getEmail(), hemocenterDTO.getPassword());
        if (hemocenter == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Hemocentro não encontrado.");

        return ResponseEntity.status(200).body(hemocenter);
    }

    @PutMapping("/{uuid}")
    public ResponseEntity<Hemocenter> updateHemocenter(@PathVariable int uuid, @RequestBody Hemocenter hemocenterDTO) {
        if (!hemocenterRepository.existsById(uuid)) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado.");

        hemocenterDTO.setUuid(uuid);
        Hemocenter hemocenter = hemocenterDTO;
        hemocenterRepository.save(hemocenterDTO);
        return ResponseEntity.status(200).body(hemocenterDTO);
    }

    @GetMapping
    public ResponseEntity<List<Hemocenter>> getAllHemocenters() {
        List<Hemocenter> hemocenterList = hemocenterRepository.findAll();
        if (hemocenterList.isEmpty()) throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Nenhum hemocentro encontrado.");

        return ResponseEntity.status(200).body(hemocenterList);
    }

    @PostMapping("/scheduleHemocenter")
    public ResponseEntity<ScheduleHemocenter> insertScheduleTime (@RequestBody @Valid ScheduleHemocenterRequest scheduleHemocenterRequest){
        Hemocenter hemocenter = hemocenterRepository.findByUuid(scheduleHemocenterRequest.getHemocenterId());

        if (hemocenter == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Nenhum hemocentro encontrado.");
        if (hemocenter.getUuid() <= 0 || hemocenter.getUuid() > hemocenterRepository.count())
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Hemocentro inválido.");

        if (scheduleHemocenterRequest.getScheduledDate() == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Data inválida.");
        if (scheduleHemocenterRequest.getScheduledTime() == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Hora inválida.");

        ScheduleHemocenter scheduleHemocenter =
                new ScheduleHemocenter(hemocenter,scheduleHemocenterRequest.getScheduledDate(),scheduleHemocenterRequest.getScheduledTime());

        ScheduleHemocenter saved = scheduleHemocenterRepository.save(scheduleHemocenter);

        return ResponseEntity.status(201).body(saved);
    }

}
