package com.alura.topicos.dto.topico;

import com.alura.topicos.model.Topico;

import java.time.LocalDateTime;

public record DatosListadoTopicoDTO(Long id, String mensaje, String nombreCurso, String titulo, Long idUsuario, LocalDateTime fechaCreacion) {

    public DatosListadoTopicoDTO(Topico topico) {
        this(topico.getId(), topico.getMensaje(), topico.getNombreCurso(), topico.getTitulo(), topico.getUsuario().getId(),topico.getFechaCreacion());
    }

}
