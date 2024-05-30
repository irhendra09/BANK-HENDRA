package com.bank.dto.response;

import jakarta.persistence.Column;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NasabahResponse {
    private String id;
    private String nik;
    private String nama;
    private String alamat;
    private String tempatLahir;
    private Date tanggalLahir;
    private String noHandpone;
}
