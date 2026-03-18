package com.anasalam.restapi.RestApis.controller;

import com.anasalam.restapi.RestApis.DTO.StudentDto;
import com.anasalam.restapi.RestApis.service.StudentServices;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/students")
@AllArgsConstructor
public class StudentController {
    private final StudentServices studentServices;

    /*
    * === GET ALL STUDENT ===
    * */
    @GetMapping
    public List<StudentDto> getAllStudents(){
        return studentServices.getAllStudents();
    }

    /*
    * === GET STUDENT BY ID ===
    * */
    @GetMapping("/{id}")
    public Optional<StudentDto> getStudentById(@PathVariable long id){
        return studentServices.getStudentById(id);
    }

    /*
    * === GET STUDENT BY EMAIL ===
    * */
    @GetMapping("/by-email")
    public Optional<StudentDto> getStudentByEmail(@RequestParam String email) {
        return studentServices.getStudentByEmail(email);
    }
}
