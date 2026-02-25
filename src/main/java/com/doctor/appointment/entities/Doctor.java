package com.doctor.appointment.entities;
import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
@Entity
@Getter
@Setter
@Table(name="doctor_records")
public class Doctor {
	@Id
	private String email;
	private String name;
	private String specialization;
	private String degree;
	private String state;
	private String city;
}