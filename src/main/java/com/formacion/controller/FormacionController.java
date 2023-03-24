package com.formacion.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.formacion.model.Formacion;
import com.formacion.service.FormacionService;


@CrossOrigin("*")
@RestController
@RequestMapping("formacion")
public class FormacionController {
	
	@Autowired
	FormacionService service;
	
	
	
	@GetMapping(value="/lista",produces=MediaType.APPLICATION_JSON_VALUE)
	public List<Formacion> ListaCursos() {
		
		return service.cursos();
	}
	
	@PostMapping(value="/alta",produces=MediaType.APPLICATION_JSON_VALUE,consumes=MediaType.APPLICATION_JSON_VALUE)
	public void AltaCurso(@RequestBody Formacion formacion) {
		
	 service.altaCurso(formacion);
	}
	
	
	
}
