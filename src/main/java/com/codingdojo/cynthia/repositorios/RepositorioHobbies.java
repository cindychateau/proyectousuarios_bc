package com.codingdojo.cynthia.repositorios;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.codingdojo.cynthia.modelos.Hobby;
import com.codingdojo.cynthia.modelos.Usuario;

@Repository
public interface RepositorioHobbies extends CrudRepository<Hobby, Long> {
	
	List<Hobby> findAll();
	
	//Seleccionamos aquellos Hobbies a los que el usuario no fue asignado
	List<Hobby> findByUsuariosNotContains(Usuario usuario);
	
	//findBy o findAll
	
}
