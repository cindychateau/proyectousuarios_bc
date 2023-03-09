package com.codingdojo.cynthia.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codingdojo.cynthia.modelos.Hobby;
import com.codingdojo.cynthia.modelos.Salon;
import com.codingdojo.cynthia.modelos.Usuario;
import com.codingdojo.cynthia.repositorios.RepositorioHobbies;
import com.codingdojo.cynthia.repositorios.RepositorioSalones;
import com.codingdojo.cynthia.repositorios.RepositorioUsuarios;

@Service
public class AppService {
	
	@Autowired
	private RepositorioUsuarios repoUsuarios;
	
	@Autowired
	private RepositorioSalones repoSalones;
	
	@Autowired
	private RepositorioHobbies repoHobbies;
	
	/*
	 private final RepositorioUsuarios repoUsuarios
	 
	 public AppService(RepositorioUsuarios repoUsuarios) {
	 	this.repoUsuarios = repoUsuarios;
	 }
	 
	 */
	
	
	//Me regresa una lista con todos los usuarios
	public List<Usuario> findUsuarios() {
		return repoUsuarios.findAll();
	}
	
	//Guardamos un usuario
	public Usuario saveUsuario(Usuario nuevoUsuario) {
		return repoUsuarios.save(nuevoUsuario);
	}
	
	//Me regresa un usuario en base a su ID
	public Usuario findUsuario(Long id) {
		return repoUsuarios.findById(id).orElse(null);
	}
	
	//Borrar usuario en base a su ID
	public void deleteUsuario(Long id){
		repoUsuarios.deleteById(id);
	}
	
	//Me regrese una lista con todos los Salones
	public List<Salon> findSalones() {
		return repoSalones.findAll();
	}
	
	//Me regresa un salón en base a su ID
	public Salon findSalon(Long id) {
		return repoSalones.findById(id).orElse(null);
	}
	
	//Me regrese un listado de Usuarios que NO tengan Salón
	public List<Usuario> findUsuariosSinSalon() {
		return repoUsuarios.findBySalonIdIsNull();
	}
	
	//Me regresa el listado de todos los Hobbies
	public List<Hobby> findHobbies() {
		return repoHobbies.findAll();
	}
	
	//Me regresa un hobby en base a su ID
	public Hobby findHobby(Long id) {
		return repoHobbies.findById(id).orElse(null);
	}
	
	public void saveUsuarioHobby(Long user_id, Long hobby_id) {
		//Obtengo el objeto del usuario al que queremos agregar el hobby
		Usuario usuario = findUsuario(user_id);
		//Obtengo el hobby
		Hobby hobby = findHobby(hobby_id);
		
		//Lista de Hobbies
		List<Hobby> listaHobbies = usuario.getHobbies();
		listaHobbies.add(hobby);
		
		/*
		 * List <Usuario> listaUsuarios = hobby.getUsuarios();
		 * listaUsuarios.add(usuario);
		 * repoHobbies.save(hobby);
		 */
		
		repoUsuarios.save(usuario);
	}
	
	//Me regrese el listado de hobbies que un usuario específico NO tiene
	public List<Hobby> findHobbiesSinUsuario(Long user_id) {
		//Obtenemos el objeto de usuario
		Usuario usuario = findUsuario(user_id);
		
		return repoHobbies.findByUsuariosNotContains(usuario);
	}
	
}
