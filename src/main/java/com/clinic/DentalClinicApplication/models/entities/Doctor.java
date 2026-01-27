package com.clinic.DentalClinicApplication.models.entities;

import com.clinic.DentalClinicApplication.models.enums.DayOfWeek;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "doctors")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private User user;

    @Column(unique = true)
    private String doctorId; // DOC-00001

    @Column(nullable = false)
    private String specialization; // Orthodontist, Endodontist, Periodontist, etc.

    @Column(unique = true)
    private String licenseNumber;

    private Integer yearsOfExperience;

    @ElementCollection
    @CollectionTable(name = "doctor_qualifications", joinColumns = @JoinColumn(name = "doctor_id"))
    @Column(name = "qualification")
    private Set<String> qualifications = new HashSet<>();

    @Column(length = 1000)
    private String bio;

    private String consultationFee;

    @Column(nullable = false)
    private Boolean available = true;

    // Working Hours
    @ElementCollection
    @CollectionTable(name = "doctor_working_days", joinColumns = @JoinColumn(name = "doctor_id"))
    @Enumerated(EnumType.STRING)
    @Column(name = "day")
    private Set<DayOfWeek> workingDays = new HashSet<>();

    private String workingHoursStart; // 09:00
    private String workingHoursEnd; // 18:00

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime joinedAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "doctor", cascade = CascadeType.ALL)
    private Set<Appointment> appointments = new HashSet<>();
}

