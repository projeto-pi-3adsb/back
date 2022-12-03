package com.example.start.hemomanager.v2.service;

import com.example.start.hemomanager.v2.domain.Donor;
import com.example.start.hemomanager.v2.domain.dto.DonorSignInDTO;
import com.example.start.hemomanager.v2.domain.dto.LoginDTO;
import com.example.start.hemomanager.v2.repository.DonorRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Service
public class DonorService {

    @Autowired
    DonorRepository donorRepository;

    @Transactional
    public ResponseEntity<Donor> createDonor(@RequestBody @Valid DonorSignInDTO donorDTO) {
        if (donorDTO != null) {
            if (donorRepository.existsByEmailAndCpf(donorDTO.getEmail(), donorDTO.getCpf()))
                throw new ResponseStatusException(HttpStatus.CONFLICT, "Email ou CPF já cadastrados.");

            Donor donor = new Donor();
            BeanUtils.copyProperties(donorDTO, donor);

            Donor saved = donorRepository.save(donor);
            return ResponseEntity.status(201).body(saved);
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Preencha corretamente.");
        }
    }

    @Transactional
    public ResponseEntity<Donor> loginDonor(@RequestBody LoginDTO donorDTO) {
        if (donorDTO != null) {
            Donor donor = donorRepository.findByEmailAndPassword(donorDTO.getEmail(), donorDTO.getPassword());
            if (donor == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado.");

            return ResponseEntity.status(200).body(donor);
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Preencha corretamente.");
        }
    }

    @Transactional
    public Donor insertDonor(@RequestBody Donor donor) {
        return donorRepository.save(donor);
    }

    @Transactional
    public ResponseEntity<List<Donor>> getAllDonors() {
        List<Donor> donorList = donorRepository.findAll();
        if (donorList.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Nenhum usuário cadastrado.");
        }
        return ResponseEntity.status(200).body(donorList);
    }

    @Transactional
    public ResponseEntity<Long> qttyMaleDonors() {
        Long counter = donorRepository.countBySexMale();
        if (counter == 0)
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Nenhum usuário do sexo informado foi encontrado.");

        return ResponseEntity.status(200).body(counter);
    }

    @Transactional
    public ResponseEntity<Long> qttyFemaleDonors() {
        Long counter = donorRepository.countBySexFemale();
        if (counter == 0)
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Nenhum usuário do sexo informado foi encontrado.");

        return ResponseEntity.status(200).body(counter);
    }

    @Transactional
    public ResponseEntity<Donor> updateDonor(@PathVariable Long id, @RequestBody Donor donorDTO) {
        if (!donorRepository.existsById(id))
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado.");

        donorDTO.setId(id);
        donorRepository.save(donorDTO);
        return ResponseEntity.status(200).body(donorDTO);
    }

    @Transactional
    public ResponseEntity<Optional<Donor>> findDonor(@PathVariable Long id) {
        if (!donorRepository.existsById(id))
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado.");

        Optional<Donor> donor = donorRepository.findById(id);
        return ResponseEntity.status(200).body(donor);
    }


}
