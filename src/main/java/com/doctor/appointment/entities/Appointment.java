package com.doctor.appointment.entities;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
@Entity
@Getter
@Setter
@Table(name = "appointment_records")
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long appId;
    private String patientEmail;
    private String doctorEmail;
    private String doctorName;
    private String doctorSpecialization;
    private String date;
    private String time;
    private String problem;
    private String status = "BOOKED";
}