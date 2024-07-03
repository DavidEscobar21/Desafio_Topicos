package com.alura.topicos.service;

import com.alura.topicos.dto.topico.DatosListadoTopicoDTO;
import com.alura.topicos.dto.topico.DatosRegistroTopicoDTO;
import com.alura.topicos.infra.errores.ValidacionDeIntegridad;
import com.alura.topicos.model.Topico;
import com.alura.topicos.repository.TopicoRepository;
import com.alura.topicos.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;



@Service
public class TopicoService {

    @Autowired
    private TopicoRepository topicoRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;

    public DatosListadoTopicoDTO ingresaTopico(DatosRegistroTopicoDTO datos){
        if(!usuarioRepository.findById(datos.idUsuario()).isPresent()){
            throw new ValidacionDeIntegridad("este id para el usuario no fue encontrado");
        }
        //validadores.forEach(v-> v.validar(datos));
        var usuario = usuarioRepository.findById(datos.idUsuario()).get();
        var topico = new Topico(null, datos.mensaje(), datos.nombreCurso(), datos.titulo(), usuario, true,null);
        topicoRepository.save(topico);
        return new DatosListadoTopicoDTO(topico);
    }


}
