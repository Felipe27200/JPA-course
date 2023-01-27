package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity

/*
 * Es una anotación de Lombok que permite generar
 * los getters and setters de las columnas.
 * Y otros métodos adicionales.
 * */
@Data

/*
 * Crea los constructores necesarios y sin ningún
 * parámetro. 
 * */
@AllArgsConstructor
@NoArgsConstructor

/*
 * Ayuda a la creación de instancias de la clase.
 * */
@Builder

@Table(name = "tbl_student",
  /*Definir las llaves únicas de la tabla*/
  uniqueConstraints = @UniqueConstraint(
		  name = "emailid_unique",
		  columnNames = "email_address"))
public class Student {
	
	@Id
	
	/*
	 * Definir el tipo de generación para la primary key
	 * y el valor por defecto de la misma también será ese.
	 * 
	 * Esto es una propiedad propia de la BD.
	 * En MySQL crea una tabla aparte para llevar el registro
	 * de este tipo de generación.
	 * */
	@SequenceGenerator(
	  name = "student_sequence", 
	  sequenceName = "student_sequence",
	  allocationSize = 1)
	/* Se definen los valores para generar las PK*/
	@GeneratedValue(
	  strategy = GenerationType.SEQUENCE,
	  generator = "student_sequence")
	private Long studentId;
	private String firstName;
	private String lastName;
	
	@Column(name = "email_address",
			nullable = false)
	private String emailId;
	
	/*
	 * Así se genera la relación a la tabla
	 * Embeddable (guardian)
	 * */
	@Embedded
	Guardian guardian;
	
}
