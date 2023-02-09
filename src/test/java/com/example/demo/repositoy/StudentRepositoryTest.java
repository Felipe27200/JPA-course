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
			.emailId("shivam@gmail.com")
			.lastName("Kumar")
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
	
	@Test
	public void printStudentByFirstName() 
	{
		List<Student> students = 
				studentRepository.findByFirstName("shivam");
		
		System.out.println("Student: " + students);
	}

	@Test
	public void printStudentByFirstNameContaining() 
	{
		List<Student> students = 
				studentRepository.findByFirstNameContaining("sh");
		
		System.out.println("Student: \n" + students);
	}
	
	@Test
	public void printStudentBylastNameNotNull() 
	{
		List<Student> students = 
				studentRepository.findByLastNameNotNull();
		
		System.out.println("Student: \n\t" + students);
	}
	
	// Find attributes of the embedded Entity
	@Test
	public void printGuardianName() 
	{
		List<Student> students = 
				studentRepository.findByGuardianName("Nikhil");
		
		System.out.println("Student: \n\t" + students);
	}
	
	@Test
	public void printStudentByFirstNameAndLastName() 
	{
		Student students = studentRepository.findByFirstNameAndLastName("shivam", "Kumar");
		
		System.out.println("Student: \n\t" + students);
	}
	
	@Test
	public void printgetStudentByEmailAddress() 
	{
		Student students = studentRepository.getStudentByEmailAddress("shabbir@gmail.com");
		
		imprimirStudent(students);
	}

	@Test
	public void printgetStudentFirstNameByEmailAddress() 
	{
		String firstNameStundent = studentRepository.getStudentFirstNameByEmailAddress("shivam@gmail.com");
		
		System.out.println("Student first name: " + firstNameStundent);
	}
	
	@Test
	public void printgetStudentByEmailAddressNative()
	{
		Student student = studentRepository.getStudentByEmailAddressNative("shivam@gmail.com");
		
		imprimirStudent(student);
	}
	
	@Test
	public void printgetStudentByEmailAddressNativeNamedParam()
	{
		Student student = studentRepository.getStudentByEmailAddressNativeNamedParam("shivam@gmail.com");
		
		imprimirStudent(student);
	}
	
	@Test
	public void updateStudentNameByEmailIdTest() 
	{
		studentRepository.updateStudentNameByEmailId("Shabbir Dawoodi", "shabbir@gmail.com");
	}
	
	/* --------------------------------------------------------- */
	
	public static void imprimirStudent(List<Student> students)
	{
		System.out.println("Student: \n\t" + students);		
	}

	public static void imprimirStudent(Student student)
	{
		System.out.println("Student: \n\t" + student);		
	}
}
