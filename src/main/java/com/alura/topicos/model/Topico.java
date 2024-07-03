package com.alura.topicos.model;


import com.alura.topicos.dto.topico.DatosActualizaTopicoDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Table(name = "topicos")
@Entity(name = "Topico")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Topico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String mensaje;
    private String nombreCurso;
    private String titulo;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;
    private boolean activo;
    private LocalDateTime fechaCreacion;

    public void actualizarDatos(DatosActualizaTopicoDTO datosActualizaTopicoDTO) {
        if (datosActualizaTopicoDTO.mensaje() != null) {
            this.mensaje = datosActualizaTopicoDTO.mensaje();
        }
        if (datosActualizaTopicoDTO.nombreCurso() != null) {
            this.nombreCurso = datosActualizaTopicoDTO.nombreCurso();
        }
        if (datosActualizaTopicoDTO.titulo() != null) {
            this.titulo = datosActualizaTopicoDTO.titulo();
        }
    }

    public void desactivar() {
        this.activo=false;
    }

}
