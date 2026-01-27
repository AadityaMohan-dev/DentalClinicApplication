package com.clinic.DentalClinicApplication.models.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "medications")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Medication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "prescription_id", nullable = false)
    private Prescription prescription;

    @Column(nullable = false)
    private String medicineName;

    @Column(nullable = false)
    private String dosage; // 500mg

    @Column(nullable = false)
    private String frequency; // Twice daily, Three times daily, etc.

    @Column(nullable = false)
    private Integer duration; // in days

    @Column(length = 500)
    private String instructions; // Take after meals, etc.

    private String timing; // Morning, Afternoon, Night
}