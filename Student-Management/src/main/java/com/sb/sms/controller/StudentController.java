package com.sb.sms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.sb.sms.entity.Student;
import com.sb.sms.service.StudentService;

@Controller
public class StudentController {

	private StudentService studentService;

	public StudentController(StudentService studentService) {
		super();
		this.studentService = studentService;
	}
	
//	handle method
	@GetMapping("/students")
	public String listStudents(Model model) {
		model.addAttribute("students",studentService.getAllStudents());
		return "students";
	}
	
	@GetMapping("/hello")
	public String getnews(Model model) {
		model.getAttribute("hello");
		return "hello";
	}
	
	@GetMapping("/students/new")
	public String createStudentForm(Model model) {
		Student student =new Student();
		model.addAttribute("student",student);
		return "create_student";
	}
	
	@PostMapping("/students")
	public String saveStudent(@ModelAttribute("student")Student student) {
		studentService.saveStudent(student);
		return "redirect:/students";
	}
	@GetMapping("/students/edit/{id}")
	public String editStudentFrom(@PathVariable Long id,Model model) {
		model.addAttribute("student",studentService.getStudentById(id));
		return "edit_student";
	}
	@PostMapping("/students/{id}")
	public String updateStudent(@PathVariable Long id,
			@ModelAttribute("student") Student student,
			Model model) {
		Student existingStudent=studentService.getStudentById(id);
		existingStudent.setId(id);
		existingStudent.setFirstName(student.getFirstName());
		existingStudent.setLastName(student.getLastName());
		existingStudent.setEmail(student.getEmail());
		
		studentService.updateStudentForm(existingStudent);
		return "redirect:/students";
	}
	@GetMapping("/students/{id}")
	public String deleteStudent(@PathVariable Long id) {
		 studentService.deleteStudentById(id);
		 return "redirect:/students";
	}
	
}
