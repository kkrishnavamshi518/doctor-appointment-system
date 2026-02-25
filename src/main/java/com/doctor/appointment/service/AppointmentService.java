package com.doctor.appointment.service;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.doctor.appointment.entities.Appointment;
import com.doctor.appointment.repository.AppointmentRepository;
@Service
public class AppointmentService {
	@Autowired
	private AppointmentRepository appointmentRepository;
	public void save(Appointment appointment) {
		appointmentRepository.save(appointment);
	}
	public List<Appointment> getHistory(String email) {
		return appointmentRepository.findByPatientEmail(email);
	}
	public void cancelByPatient(Long id, String patientEmail) {
        Appointment appointment = appointmentRepository.findById(id).orElse(null);
        if (appointment != null && appointment.getPatientEmail().equals(patientEmail) && !"Cancelled by Patient".equals(appointment.getStatus())) {
            appointment.setStatus("Cancelled by Patient");
            appointmentRepository.save(appointment);
        }
    }
	// ---------------- SLOT VALIDATION ----------------
	public String validateAppointment(String doctorEmail, String date, String time) {
	    LocalDate selectedDate = LocalDate.parse(date);
	    LocalDate today = LocalDate.now();
	    LocalTime requestedTime = LocalTime.parse(time);
	    LocalTime currentTime = LocalTime.now();
	    LocalTime start = LocalTime.of(10, 0);
	    LocalTime end = LocalTime.of(16, 0);
	    // ❌ 1. Past Date
	    if (selectedDate.isBefore(today)) {
	        return "PAST_DATE";
	    }
	    // ❌ 2. Same Day + Past Time
	    if (selectedDate.equals(today) && requestedTime.isBefore(currentTime)) {
	        return "PAST_TIME_TODAY";
	    }
	    // ❌ 3. Same Day Booking Closed After 4 PM
	    if (selectedDate.equals(today) && currentTime.isAfter(end)) {
	        return "BOOKING_CLOSED_TODAY";
	    }
	    // ❌ 4. Time Range Validation (10AM – 4PM)
	    if (requestedTime.isBefore(start) || requestedTime.isAfter(end)) {
	        return "INVALID_TIME";
	    }
	    // ❌ 5. Slot Availability (10 min buffer)
	    List<Appointment> existing = appointmentRepository.findByDoctorEmailAndDate(doctorEmail, date);
	    for (Appointment a : existing) {
	        LocalTime bookedTime = LocalTime.parse(a.getTime());
	        if (!requestedTime.isBefore(bookedTime.minusMinutes(10)) && !requestedTime.isAfter(bookedTime.plusMinutes(10))) {
	            return "SLOT_NOT_AVAILABLE";
	        }
	    }
	    return "VALID";
	}
}