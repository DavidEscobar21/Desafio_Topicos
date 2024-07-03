package com.alura.topicos.dto.topico;

public record DatosRespuestaTopicoDTO(
        Long id,
        String mensaje,
        String nombreCurso,
        String titulo,
        Long idUsuario
) {
}
