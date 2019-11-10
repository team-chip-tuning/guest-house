package ch.ffhs.guesthouse.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Data
@Entity
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String rfId;

    @NotBlank
    @Size(min = 2, max = 128, message = "{validation-size}")
    private String name;

    @Column(length = 512)
    private String medicalCare;

    @Column(length = 512)
    private String specialFeeding;

    @Column(length = 512)
    private String commands;

    @NotBlank
    private LocalDate pickUpDate;

    @Column(length = 512)
    private String pickUpInformation;
}
