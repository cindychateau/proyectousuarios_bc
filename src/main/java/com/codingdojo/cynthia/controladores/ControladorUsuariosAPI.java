package com.codingdojo.cynthia.controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.codingdojo.cynthia.modelos.Usuario;
import com.codingdojo.cynthia.servicios.AppService;

@RestController
@RequestMapping("/api")
public class ControladorUsuariosAPI {
	
	@Autowired
	private AppService servicio;
	
	@GetMapping("/usuarios")
	public List<Usuario> muestraUsuarios() {
		return servicio.findUsuarios();
	}
	
	@PostMapping("/usuarios")
	public Usuario crear(@RequestParam("firstName") String firstName,
						 @RequestParam("lastName") String lastName,
						 @RequestParam("email") String email,
						 @RequestParam("password") String password) {
		
		Usuario nuevoUsuario = new Usuario(firstName, lastName, email, password);
		return servicio.saveUsuario(nuevoUsuario);
		
	}
	
	@GetMapping("/usuarios/{id}")
	public Usuario mostrar(@PathVariable("id") Long id){
		return servicio.findUsuario(id);
	}
	
	@DeleteMapping("/usuarios/{id}")
	public void borrar(@PathVariable("id") Long id){
		servicio.deleteUsuario(id);	
	}
	
	@PutMapping("/usuarios/{id}")
	public Usuario actualizar(@PathVariable("id") long id,
							  @RequestParam("firstName") String firstName,
							  @RequestParam("lastName") String lastName,
							  @RequestParam("email") String email,
							  @RequestParam("password") String password) {
		
		Usuario usuarioActualizado = new Usuario(id, firstName, lastName, email, password);
		return servicio.saveUsuario(usuarioActualizado);
		
	}
	
}
