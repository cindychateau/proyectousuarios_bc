package com.codingdojo.cynthia.repositorios;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.codingdojo.cynthia.modelos.Usuario;

@Repository
public interface RepositorioUsuarios extends CrudRepository<Usuario, Long> {
	
	List<Usuario> findAll(); //SELECT * FROM usuarios
	
	List<Usuario> findByFirstName(String firstName); //SELECT * FROM usuarios WHERE first_name = '<firstName>'
	
	List<Usuario> findById(long id); //SELECT * FROM usuarios WHERE id = '<ID>'
	
	//INSERT y UPDATE
	//Usuario save(Usuario nuevoUsuario);
	
	//void deleteById(long id);
	
}
