package com.example.start.hemomanager.v2.service;
import com.example.start.hemomanager.v2.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class ValidationService {
//    @Autowired
//    DonorRepository donorRepository;
//    @Autowired
//    HemocenterRepository hemocenterRepository;
//    @Autowired
//    ScheduleHemocenterRepository scheduleHemocenterRepository;
//    @Autowired
//    ScheduleRepository scheduleRepository;
//    @Autowired
//    StockRepository stockRepository;
//
//    @Transactional
//    public boolean existsDonor(Long idDonor){
//        return donorRepository.findById(idDonor).isEmpty();
//    }
//    @Transactional
//    public boolean existsPostagem(Long idPostagem){
//        return postagemRepository.findById(idPostagem).isEmpty();
//    }
//
//    @Transactional
//    public boolean existsComentario(Long idComentario){
//        return comentarioRepository.findById(idComentario).isEmpty();
//    }
//    @Transactional
//    public boolean existsTopico(Long idTopico){
//        return topicoRepository.findById(idTopico).isEmpty();
//    }
//
//    @Transactional
//    public boolean existsPontuacaoWithPostagemAndUsuario(Long idPostagem, Long idUsuario){
//        return pontuacaoPostagemRepository.existByPostagemAndUsuario(idPostagem, idUsuario).isPresent();
//    }
//    @Transactional
//    public boolean existsPontuacaoWithComentarioAndUsuario(Long idComentario, Long idUsuario){
//        return pontuacaoComentarioRepository.existByComentarioAndUsuario(idComentario, idUsuario).isPresent();
//    }

}
