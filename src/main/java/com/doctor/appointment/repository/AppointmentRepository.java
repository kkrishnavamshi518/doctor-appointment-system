package com.doctor.appointment.repository;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.doctor.appointment.entities.Appointment;
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
	List<Appointment> findByPatientEmail(String patientEmail);
	List<Appointment> findByDoctorEmailAndDate(String doctorEmail, String date);
}