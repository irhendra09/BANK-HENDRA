package com.bank.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "m_nasabah")
public class Nasabah {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Pattern(regexp = "\\d{16}", message = "NIK harus terdiri dari 16 digit angka")
    @Column(unique=true, name = "NIK", nullable=false)
    private String nik;

    @Column(nullable = false)
    private String nama;

    private String alamat;

    @Column(name = "tempat_lahir", nullable=false)
    private String tempatLahir;

    @Column(nullable=false, name = "tanggal_lahir")
    private Date tanggalLahir;

    @Column(nullable=false, name="no_handpone")
    private String noHandpone;
}
