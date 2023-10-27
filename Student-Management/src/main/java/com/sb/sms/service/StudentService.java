package com.sb.sms.service;

import java.util.List;

import com.sb.sms.entity.Student;

public interface StudentService {
	List<Student> getAllStudents();
	
	Student saveStudent(Student student);
	
	Student getStudentById(Long id);
	Student updateStudentForm(Student student);
	void deleteStudentById(Long id);
}
