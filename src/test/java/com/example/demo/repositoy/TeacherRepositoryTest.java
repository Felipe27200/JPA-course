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
class TeacherRepositoryTest 
{
	@Autowired
	private TeacherRepository teacherRepository; 

	@Test
	public void saveTeacher()
	{
		Course courseDBA = Course.builder()
			.title("DBA")
			.credits(5)
			.build();

		Course courseJava = Course.builder()
				.title("Java")
				.credits(6)
				.build();

		Teacher teacher = Teacher.builder()
				.firstName("Qutub")
				.lastName("Khan")
				.courses(List.of(courseDBA, courseJava))
				.build();
		
		teacherRepository.save(teacher);
	}
}
