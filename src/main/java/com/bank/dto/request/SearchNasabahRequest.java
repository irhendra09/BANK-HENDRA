package com.bank.dto.request;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SearchNasabahRequest {
    private Integer size;
    private Integer page;
    private String nik;
    private String nama;
}
