package com.formacion.service;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.formacion.model.Curso;
import com.formacion.model.Formacion;



@Service
public class FormacionServiceImpl implements FormacionService{

	@Autowired
	RestTemplate template;
	
	List<Curso> cursos;
	List<Formacion> formacion;
	private String url="http://servicio-cursos/curso";
	
	@Override
	public List<Formacion> cursos() {
		cursos=  Arrays.asList(template.getForObject(url+"/lista", Curso[].class));
		formacion = new ArrayList<Formacion>();
		for(Curso curso: cursos) {
			Formacion form = new Formacion();
			form.setPrecio(curso.getPrecio());
        	form.setCurso(curso.getNombre());
        	if (curso.getDuracion() >= 50) {
                form.setAsignaturas(10);
                }
            else {
            	form.setAsignaturas(5);
            }
            formacion.add(form);
		}
        	
        	
		return formacion;
	}

	@Override
	public void altaCurso(Formacion formacion) {
		boolean alta = true;
		Curso curso = new Curso();
		curso.setDuracion(formacion.getAsignaturas()*10);
		curso.setCodCurso(formacion.getCurso().substring(0, 3)+curso.getDuracion());
		curso.setNombre(formacion.getCurso());
		curso.setPrecio(formacion.getPrecio());
		cursos=  Arrays.asList(template.getForObject(url+"/lista", Curso[].class));
		for(Curso c: cursos) {
			if(c.getNombre().equals(curso.getNombre())) {
				alta =  false;
			}
		}
		if(alta) {
		template.postForLocation(url+"/alta", curso);
		}
		
	}
    
	
	
	
	
}
