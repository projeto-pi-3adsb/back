package com.example.start.hemomanager.v2.service;

import com.example.start.hemomanager.v2.repository.DonorRepository;
import com.example.start.hemomanager.v2.repository.HemocenterRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class ScheduleService {
    @Autowired private DonorRepository donorRepository;
    @Autowired private HemocenterRepository hemocenterRepository;
}
