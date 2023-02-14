package com.example.demo.repositoy;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.entity.Course;
import com.example.demo.entity.CourseMaterial;

@SpringBootTest

class CourseMaterialRepositoryTest 
{
	@Autowired
	private CourseMaterialRepository repository;

	@Test
	public void saveCourseMaterial ()
	{
		Course course = Course.builder()
				.title("DSA")
				.credits(6)
				.build();
				
		CourseMaterial courseMaterial = 
			CourseMaterial.builder()
				.url("www.google.com")
				.course(course)
				.build();
		
		/*
		 * +-----------------------------+
		 * | SAVE DATA INTO RELATIONSHIP |
		 * +-----------------------------+
		 * 
		 * Como en courseMaterial se almacena una 
		 * referencia a la entidad Course y ambas están
		 * relacionadas, entonces, al no existir ese registro
		 * en la BD, se insertan primero esos en la BD.
		 * 
		 * Luego, se guardan los datos de CourseMaterial y
		 * así ya se puede hacer una llave foránea con los
		 * datos, ya que course ya ha sido previamente insertado.
		 * */
		repository.save(courseMaterial);
	}
	
	@Test
	public void printAllCourseMaterials()
	{
		List<CourseMaterial> courseMaterials = repository.findAll();
		
		System.out.println("All Courses Materials: \n\t" + courseMaterials);
	}
}
