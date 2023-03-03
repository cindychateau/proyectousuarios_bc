package com.codingdojo.cynthia.controladores;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.codingdojo.cynthia.modelos.Usuario;
import com.codingdojo.cynthia.modelos.UsuarioOld;
import com.codingdojo.cynthia.servicios.AppService;

@Controller
public class ControladorUsuarios {
	
	@Autowired
	private AppService servicio;
	
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
	public String dashboard(Model model) {
		
		//Creamos una variable para recibir la lista de usuarios
		List<Usuario> usuarios = servicio.findUsuarios();
		
		model.addAttribute("usuarios", usuarios); //Enviamos atributo usuarios a dashboard
		
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
	
	@GetMapping("/new")
	public String newUser(@ModelAttribute("usuario") Usuario usuario) {
		return "new.jsp";
	}
	
	@PostMapping("/create")
	public String create(@Valid @ModelAttribute("usuario") Usuario usuario,
						 BindingResult result /*Encargado de regresar los mensajes de valid*/) {
		
		if(result.hasErrors()) {
			return "new.jsp";
		} else {
			servicio.saveUsuario(usuario);
			return "redirect:/dashboard";
		}
		
	}
	
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Long id,
					   Model model,
					   @ModelAttribute("usuario") Usuario usuario) {
		
		//Estamos obteniendo el objecto de usuario al que pertenece ese ID
		Usuario usuarioEdit = servicio.findUsuario(id);
		
		if(usuarioEdit == null) {
			return "redirect:/dashboard";
		}
		
		model.addAttribute("usuario", usuarioEdit);
		
		return "edit.jsp";
		
	}
	
	@PutMapping("/edit/{id}")
	public String update(@Valid @ModelAttribute("usuario") Usuario usuario,
						 BindingResult result) {
		if(result.hasErrors()) {
			return "edit.jsp";
		} else {
			servicio.saveUsuario(usuario);
			return "redirect:/dashboard";
		}
	}
	
	@DeleteMapping("/delete/{id}")
	public String deleteUser(@PathVariable("id") Long id) {
		servicio.deleteUsuario(id);
		return "redirect:/dashboard";
	}
	
}
