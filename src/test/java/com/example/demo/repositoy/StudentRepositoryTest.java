package com.example.demo.repositoy;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.entity.Student;

/* Establece que se usará como un test */
@SpringBootTest
class StudentRepositoryTest {

	/* Esta anotación facilita la inyección de 
	 * dependencias como por ejemplo el StudentTepository */
	@Autowired
	StudentRepository studentRepository;
	
	@Test
	void saveStudent()
	{
		/*
		 * builder() es un método de Lombok
		 * qeu permite crear las instancias con mayor
		 * sencilles, build() al final es necesario.
		 * */
		Student student = Student.builder()
				.emailId("shabbir@gmail.com")
				.firstName("Shabbir")
				.lastName("Dawoodi")
				.guardianName("Nikhil")
				.guardianEmail("nikhil@gmail.com")
				.guardianMobile("999999999")
				.build();
		
		Student saveStudent = studentRepository.save(student);
	}
	
}
