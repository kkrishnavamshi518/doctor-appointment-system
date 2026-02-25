package com.doctor.appointment.service;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.doctor.appointment.entities.Doctor;
import com.doctor.appointment.repository.DoctorRepository;
@Service
public class DoctorService {
    @Autowired
    private DoctorRepository doctorRepository;
    public List<Doctor> getAllDoctors() {
        return doctorRepository.findAll();
    }
}