package com.alura.topicos.controller;

import com.alura.topicos.dto.topico.DatosActualizaTopicoDTO;
import com.alura.topicos.dto.topico.DatosListadoTopicoDTO;
import com.alura.topicos.dto.topico.DatosRegistroTopicoDTO;
import com.alura.topicos.dto.topico.DatosRespuestaTopicoDTO;
import com.alura.topicos.infra.errores.ValidacionDeIntegridad;
import com.alura.topicos.model.Topico;
import com.alura.topicos.repository.TopicoRepository;
import com.alura.topicos.service.TopicoService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/topicos")
@SecurityRequirement(name = "bearer-key")
public class TopicosController {

    @Autowired
    private TopicoService service;

    @Autowired
    private TopicoRepository topicoRepository;


    @PostMapping
    @Transactional
    public ResponseEntity ingresaTopico(@RequestBody @Valid DatosRegistroTopicoDTO datos) throws ValidacionDeIntegridad {
        var response = service.ingresaTopico(datos);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<Page<DatosListadoTopicoDTO>> listadoTopico(@PageableDefault(size = 2) Pageable paginacion) {
        return ResponseEntity.ok(topicoRepository.findByActivoTrue(paginacion).map(DatosListadoTopicoDTO::new));
    }

    @PutMapping
    @Transactional
    public ResponseEntity actualizarTopico(@RequestBody @Valid DatosActualizaTopicoDTO datosActualizaTopicoDTO) {
        Topico topico = topicoRepository.getReferenceById(datosActualizaTopicoDTO.id());
        topico.actualizarDatos(datosActualizaTopicoDTO);
        return ResponseEntity.ok(new DatosRespuestaTopicoDTO(topico.getId(), topico.getMensaje(),
                topico.getNombreCurso(), topico.getTitulo(), topico.getUsuario().getId()));
    }


    // DELETE LOGICO
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity eliminarTopico(@PathVariable Long id) {
        Topico topico = topicoRepository.getReferenceById(id);
        topico.desactivar();
        return ResponseEntity.noContent().build();
    }


}
