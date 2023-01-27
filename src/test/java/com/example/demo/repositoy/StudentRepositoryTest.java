package com.example.demo.repositoy;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.entity.Guardian;
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
//				.guardianName("Nikhil")
//				.guardianEmail("nikhil@gmail.com")
//				.guardianMobile("999999999")
				.build();
		
		Student saveStudent = studentRepository.save(student);
	}
	
	@Test
	public void saveStudentWithGuardian() {
		// Crea la entidad guardian
		Guardian guardian = Guardian.builder()
			.name("Kumar")
			.email("nikhil@gmail.com")
			.mobile("9999956342")
			.build();
		
		Student student = Student.builder()
			.firstName("Shivam")
			.emailId("Shivam")
			.lastName("Shivam")
			/*
			 * Inicializa la entidad guardian en 
			 * la entidad Student y se la envía como
			 * argumento para que Studen almacene los datos
			 * de este en la tabla tbl_student.
			 * */
			.guardian(guardian)
			.build();
		
		studentRepository.save(student);
	}
	
	@Test
	public void printAllStudent()
	{
		/*
		 * Método findAll() para buscar todos los registros
		 * en la tabla de la BD.
		 * */
		List<Student> studentList = studentRepository.findAll();
		
		System.out.println("studenList: \n\t" + studentList);
 	}
	
}
