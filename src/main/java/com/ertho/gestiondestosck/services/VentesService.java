package com.ertho.gestiondestosck.services;

import com.ertho.gestiondestosck.dto.VentesDto;

import java.util.List;

public interface VentesService {

    VentesDto save(VentesDto dto);

    VentesDto findById(Integer id);

    VentesDto findVenteByCode(String code);

    List<VentesDto> findAll();

    void delete(Integer id);

}
