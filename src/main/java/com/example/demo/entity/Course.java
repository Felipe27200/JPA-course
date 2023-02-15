package com.example.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Course 
{
	@Id
	@SequenceGenerator(
		name = "course_sequence",
		sequenceName = "course_sequence",
		allocationSize = 1
	)
	@GeneratedValue(
		strategy = GenerationType.SEQUENCE,
		generator = "course_sequence"
	)
	private Long courseId;
	private String title;
	private Integer credits;
	
	/*
	 * +----------------------------+
	 * | BIDIRECTIONAL RELATIONSHIP |
	 * +----------------------------+
	 * */
	@OneToOne(
		/**
		 * +----------------------------+
		 * | SET THE RELATIONSHIP VALUE |
		 * +----------------------------+
		 * 
		 * No es necesario establecer que datos son
		 * los que se tienen que mapear para la FK,
		 * ya que esto ya se ha hecho en la entidad
		 * de la children table.
		 * 
		 * Aquí solo se apuntará al atributo de esa
		 * entidad que hace referencia a la FK y a
		 * la relación, en este caso, es course de
		 * la entidad CourseMaterial.
		 */	
		
		mappedBy = "course"
	)
	private CourseMaterial courseMaterial;
}
