package com.example.demo.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

/*
 * Esta anotación (Lombok), excluye el Course
 * del método toString(), ya que al buscar los
 * datos con Fetch.LAZY, los datos de este ya
 * no se traen, todo para esta entidad.
 * */
@ToString
public class CourseMaterial 
{
	@Id
	@SequenceGenerator(
		name = "course_material_sequence",
		sequenceName = "course_material_sequence",
		allocationSize = 1
	)
	@GeneratedValue(
		strategy = GenerationType.SEQUENCE,
		generator = "course_material_sequence"
	)
	private Long courseMaterialId;
	private String url;
	
	/*
	 * +------------+
	 * | ONE TO ONE |
	 * +------------+
	 *
	 * Se pueden establecer más características
	 * para la relación entre ambas tablas. 
	 * 
	 *  ********************************
	 *  * UNI DIRECTIONAL RELATIONSHIP *
	 *  ********************************
	 * -> Si la relación se deja así y solo esta
	 *   entidad tiene la relación, será la única
	 *   que podrá acceder a las propiedades de la
	 *   relación.
	 * 
	 *   Mientras, que la BD tendrá la relación,
	 *   pero la otra entidad no podrá hacer uso
	 *   de las características de la relación
	 *   ofrecidas por JPA.	 *   
	 * */
	@OneToOne(
		/*
		 * Para que la relación sea funcional
		 * se debe definir como se tratarán las
		 * relaciones y su modificación.
		 * */
		cascade = CascadeType.ALL,
		
		/*
		 * +-------+
		 * | FETCH |
		 * +-------+

		 * Define la forma en que se van a recuperar 
		 * los datos de la BD, esto para las relaciones.
		 * 
		 * LAZY -> indica que solo se traerán los datos
		 * de una tabla en la relación.
		 * 
		 * EAGER -> Se refiere a que siempre se traeran
		 * los datos de ambas tablas relacionados.
		 * */
		fetch = FetchType.EAGER, 
		
		/*
		 * +-------------------------------------+
		 * | REQUIRED RELATIONSHIP - NOT NULL FK |
		 * +-------------------------------------+
		 * 
		 * optional -> si es true, se puede insertar datos 
		 * de las tablas relacionadas de forma individual
		 * sin necesidad de almacenar directamente los datos
		 * de la otra tabla relacionada.
		 * 
		 * Si es false indica que se deben insertar los datos 
		 * de ambas tablas para poder almacenar la relación.
		 * 
		 * Esto quiere decir que no se puede insertar un 
		 * courseMaterial sino se inserta también un course.
		 * 
		 * En pocas palabras, hace que la relación sea obligatoria,
		 * estableciendo el NOT NULL para la FK.
		 * */
		optional = false
	)
	
	/*
	 * Permite establecer la relación de la FK.
	 * */
	@JoinColumn(
		/*
		 * Hace referencia al nombre de la FK.
		 * */
		name = "couse_id",
		/*
		 * El nombre de la columna del padre
		 * a la que la FK hace referencia.
		 * */
		referencedColumnName = "courseId"
	)
	/*
	 * Referenciado a la entidad sabe de donde
	 * tomará la referencia para la FK.
	 * 
	 * Se excluye para que no haya una referencia
	 * múltiple al mismo objeto en el método
	 * toString(), generando una sobrecarga de la pila.
	 * */
	@ToString.Exclude
	private Course course;
}
