package com.example.demo.repositoy;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Student;

/*
 * Esto provee los métodos necesarios para el manejo de los datos
 * en la BD, los parámetros de tipo, encerrados en <>, hacen 
 * referencia al tipo de la entidad (Student) y al tipo del id de esa entidad (id),
 * respectivamente.
 * 
 * Como se recordará no es necesario implementar está interfaz en ninguna
 * clase externa para hacer uso de ella.
 * */
@Repository
public interface StudentRepository extends JpaRepository <Student, Long> {
	
	/*
	 * De esta froma se podrá buscar un registro según
	 * una de los valores en sus columnas seguido por la siguiente
	 * nomenclatura:
	 * 		findBy--Nombre de la propiedad--(Valor a buscar);
	 * 
	 * En este caso, se buscará a un student basado en su firstName.
	 * 
	 * Estos métodos deben ser declarados en el repositorio de esa entidad.
	 * */
	public List<Student> findByFirstName(String firstName);
	
	/*
	 * Buscar datos si contienen o hacen math con
	 * cierta información, deben cumplir con la nomenclatura
	 * similar y el  sufijo Containing
	 * */	
	public List<Student> findByFirstNameContaining(String firsName);
	
	public List<Student> findByLastNameNotNull();
	
	/*
	 * Obtener datos de la entidad Embedded:
	 * 
	 * Se debe basar la propiedad a buscar en los
	 * atributos y data de la entidad Embedded.
	 * 
	 * Trae todos los datos de ese registro en el que está 
	 * el guardian incluido los datos del estudiante.
	 * */
	public List<Student> findByGuardianName(String guardianName);
	
	// Usa el operador lógico AND de SQL
	public Student findByFirstNameAndLastName(String firstName, String lastName);
	
}
