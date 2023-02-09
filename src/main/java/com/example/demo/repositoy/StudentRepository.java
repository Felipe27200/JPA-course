package com.example.demo.repositoy;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Student;

import jakarta.transaction.Transactional;

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
	
	/*  +------+
	 *  | JPQL |
	 *  +------+
	 * 
	 * Se usa para formar queries, pero estos
	 * se basan en la estructura de la entidad y
	 * no en la tabla en la BD. Aún así los 
	 * datos se obtienen de ahí.
	 * 
	 * "emailId" -> es cómo se llama la propiedad
	 * en la entidad, mas no en la tabla.
	 * */
	@Query("SELECT s FROM Student AS s WHERE s.emailId = ?1")
	public Student getStudentByEmailAddress(String emailId);
	
	// Como solo se obtiene un valor se puede tomar como String
	@Query("SELECT s.firstName FROM Student AS s WHERE s.emailId = ?1")
	public String getStudentFirstNameByEmailAddress(String emailId);
	
	/*
	 * +--------------+
	 * | Native Query |
	 * +--------------+
	 * 
	 * Se usan los queries propios de la BD y los
	 * mismos se basan en su estructura.
	 * */
	@Query(
		value = "SELECT * FROM tbl_student AS s WHERE s.email_address = ?1",
		nativeQuery = true
	)
	// El parámetro se sigue basando en la "entidad"
	public Student getStudentByEmailAddressNative(String emailId); 
	
	/*
	 * +-------------------+
	 * | Query Named Params|
	 * +-------------------+
	 * 
	 * Permite enviar varios parámetros en un query.
	 * 
	 * Con los dos puntos después del "=" en la condición
	 * se establece que se le enviarán varios datos y se 
	 * hace referencia a la propiedad de la entidad de la 
	 * que se obtendra.
	 * */
	@Query(
		value = "SELECT * FROM tbl_student AS s WHERE s.email_address = :emailId",
		nativeQuery = true
	)
	// El parámetro se sigue basando en la "entidad"
	public Student getStudentByEmailAddressNativeNamedParam(
		@Param("emailId") String emailId
	);
	
	/*
	 * Update Data with @Query
	 * 
	 * Se da la sentencia SQL para el UPDATE y se define(n) los datos a actualizar,
	 * así como el parámetro para definir que valores se actualizaran, como se ve
	 * los números después de ?, son la numeración de los parámetros.
	 * 
	 * Se necesita la anotación @Modifying para establecer que este método,
	 * modificará los datos en la BD.
	 * */
	@Modifying
	@Transactional
	@Query(
		value = "UPDATE tbl_student SET first_name = ?1 WHERE email_address = ?2",
		nativeQuery = true
	)
	// Los párametros del método hacen referencia a las propiedad de la entidad.
	int updateStudentNameByEmailId(String firstName, String emailId);
}
