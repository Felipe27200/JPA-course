package com.example.demo.repositoy;


import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.example.demo.entity.Course;
import com.example.demo.entity.Student;
import com.example.demo.entity.Teacher;

@SpringBootTest
class CourseRepositoryTest 
{
	@Autowired
	private CourseRepository courseRepository;
	
	@Test
	public void printCourses()
 	{
		/*
		 * +-----------------------------+
		 * | UNIDIRECTIONAL RELATIONSHIP |
		 * +-----------------------------+
		 *
		 * The parent table only get its own data, 
		 * not of the related table.
		 *
		 * +-----------------------------+
		 * | BIDIRECTIONAL RELATIONSHIP |
		 * +-----------------------------+
		 *
		 * The parent table only get the both data, 
		 * yours and the children's.
		 *
		 * */
		List<Course> courses = courseRepository.findAll();
		
		System.out.println("Courses: \n\t" + courses);
	}

	@Test
	public void saveCourseWithTeacher()
	{
		Teacher teacher = Teacher.builder()
			.firstName("Priyanka")
			.lastName("Singh")
			.build();
		
		Course course = Course.builder()
			.title("Python")
			.credits(6)
			.teacher(teacher)
			.build();
		
		courseRepository.save(course);
	}
	
	@Test
	public void findAllPagination()
	{
		/*
		 * +-------------------+
		 * | ADDING PAGINATION |
		 * +-------------------+
		 *
		 *
		 * El primer parámetro define la página
		 * y el segundo el tamaño de esta.
		 * */
		Pageable firtPageWithThreeRecords = PageRequest.of(0, 3);
		Pageable secondPageWithTwoRecords = PageRequest.of(1, 2);
		
		List<Course> courses = 
				courseRepository
				  .findAll(firtPageWithThreeRecords)
				  /*
				   * Returns the page content as List.
				   * */
				  .getContent();
		
		Long totalElements = 
				courseRepository
				.findAll(firtPageWithThreeRecords)
				.getTotalElements(); // Retorna la cantidad total de elementos

		int totalPages = 
				courseRepository
				.findAll(firtPageWithThreeRecords)
				.getTotalPages(); // Retorna la cantidad total de pages

		System.out.println("Total Elements = \"" + totalElements + "\"");
		System.out.println("Total Pages = \"" + totalPages + "\"");
		System.out.println("Courses = \n\t" + courses);
	}
	
	@Test
	public void saveCourseWithStudentAndTeacher() 
	{
		Teacher teacher = Teacher.builder()
			.firstName("Lizzie")
			.lastName("Morgan")
			.build();
		
		Student student = Student.builder()
			.firstName("Abhishek")
			.lastName("Singh")
			.emailId("abhishek@gmail.com")
			.build();
		
		Course course = Course.builder()
			.title("AI")
			.credits(12)
			.teacher(teacher)
			.build();
		
		course.addStudents(student);
		
		courseRepository.save(course);
	}
}
