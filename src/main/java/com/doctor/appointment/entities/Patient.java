package com.doctor.appointment.entities;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
@Entity
@Getter
@Setter
@Table(name = "patient_records")
public class Patient {
    @Id
    private String email;
    private String name;
    private String contactNo;
    private String password;
}