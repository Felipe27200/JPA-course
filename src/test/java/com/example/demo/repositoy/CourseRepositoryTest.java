package com.example.demo.repositoy;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.entity.Course;
import com.example.demo.entity.Teacher;

@SpringBootTest
class CourseRepositoryTest {
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
}
