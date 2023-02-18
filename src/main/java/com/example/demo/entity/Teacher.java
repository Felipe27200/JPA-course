package com.example.demo.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
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
public class Teacher {
	@Id
	@SequenceGenerator(
		name = "teacher_sequence",
		sequenceName = "teacher_sequence",
		allocationSize = 1
	)
	@GeneratedValue(
		strategy = GenerationType.SEQUENCE,
		generator = "teacher_sequence"
	)
	private Long teacherId;
	private String firstName;
	private String lastName;
	
	/*
	 * +-----------------+
	 * | SET ONE-TO-MANY |
	 * +-----------------+

	 * Teacher is the parent table
	 * 
	 * One teacher can teach in several
	 * courses, that's the reason whay the
	 * property is a List of courses.
	 * 
	 * All relationships must have
	 * a cascade type or others similars
	 * */
	@OneToMany(
		mappedBy = "teacher",
		fetch = FetchType.EAGER
	)
	private List<Course> courses;
}
