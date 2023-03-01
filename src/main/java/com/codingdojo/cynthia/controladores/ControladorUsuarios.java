package com.codingdojo.cynthia.controladores;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.codingdojo.cynthia.modelos.UsuarioOld;

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
		
		UsuarioOld usuario1 = new UsuarioOld("Elena", "De Troya", 1);
		UsuarioOld usuario2 = new UsuarioOld("Juana", "De Arco", 2);
		UsuarioOld usuario3 = new UsuarioOld("Pablo", "Picasso", 3);
		
		ArrayList<UsuarioOld> listaUsuarios = new ArrayList<UsuarioOld>();
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
	
	@GetMapping("/registro")
	public String registro() {
		return "registro.jsp";
	}
	
	@GetMapping("/dashboard") 
	public String dashboard() {
		return "dashboard.jsp";
	}
	
	@PostMapping("/registrarUsuario")
	public String registrarUsuario(@RequestParam("nombre") String name, /*Recibe el parámetro con name "nombre"*/
								   @RequestParam("email") String email, /*Recibe el parámetro con name "email"*/
								   HttpSession session, /*Se agrega cuando vamos a usar sesión*/
								   RedirectAttributes flash /*Se agrega cuando queremos validar*/ ) {
		
		System.out.println(name);
		System.out.println(email);
		
		if(name.equals("")) {
			flash.addFlashAttribute("mensajeError", "Por favor proporciona tu nombre");
			return "redirect:/registro";
		}
		
		session.setAttribute("name", name);
		session.setAttribute("email", email);
		
		return "redirect:/dashboard"; //Hacemos la redirección a la ruta
	}
	
	
}
