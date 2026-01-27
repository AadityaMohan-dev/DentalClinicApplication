package com.clinic.DentalClinicApplication.repositories;

import com.clinic.DentalClinicApplication.models.entities.Appointment;
import com.clinic.DentalClinicApplication.models.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

}
