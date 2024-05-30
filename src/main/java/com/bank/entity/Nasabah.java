package com.bank.entity;

import jakarta.persistence.*;
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
