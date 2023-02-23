package com.codingdojo.cynthia.controladores;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.codingdojo.cynthia.modelos.Usuario;

@Controller
public class ControladorUsuarios {
	
	@GetMapping("/")
	public String index() {
		return "index.jsp";
	}
	
	@GetMapping("/muestraUsuarios")
	public String muestraUsuarios(Model model) {
		
		model.addAttribute("titulo", "Usuarios");
		
		String hobbies[] = {"Nadar", "Leer", "Hacer ejercicio", "Jugar videojuegos"};
		
		model.addAttribute("hobbies", hobbies);
		
		HashMap<String, String> paises = new HashMap<String, String>();
		paises.put("Mexico", "CDMX");
		paises.put("Estados Unidos", "Washington DC");
		paises.put("Colombia", "Bogota");
		
		model.addAttribute("countries", paises);
		
		Usuario usuario1 = new Usuario("Elena", "De Troya", 1);
		Usuario usuario2 = new Usuario("Juana", "De Arco", 2);
		Usuario usuario3 = new Usuario("Pablo", "Picasso", 3);
		
		ArrayList<Usuario> listaUsuarios = new ArrayList<Usuario>();
		listaUsuarios.add(usuario1);
		listaUsuarios.add(usuario2);
		listaUsuarios.add(usuario3);
		
		model.addAttribute("listaUsuarios", listaUsuarios);
		
		return "usuarios.jsp";
	}
	
	@GetMapping("/ejemplo/{nombre}")
	public String ejemplo(@PathVariable("nombre") String name,
						  Model model,
						  HttpSession session) {
		
		model.addAttribute("nombre","ABC123");
		
		session.setAttribute("nombre", name);
		
		return "ejemploSesion.jsp";
	}
	
}
