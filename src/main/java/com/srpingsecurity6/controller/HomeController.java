package com.srpingsecurity6.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.srpingsecurity6.entity.Student;

@RestController
public class HomeController {
	List<Student> list = new ArrayList<>();
	
	@GetMapping("/students")
	public List<Student> students() {

		Student student = new Student();
		student.setId(1);
		student.setFirstName("Akshay");
		student.setLastName("Raghuvanshi");

		Student student2 = new Student();
		student.setId(2);
		student.setFirstName("Amol");
		student.setLastName("Bari");

		list.add(student);
		list.add(student2);
		return list;
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping("/students")
	public Student createStudent(@RequestBody Student student){
		list.add(student);
		return student;
	}
}
