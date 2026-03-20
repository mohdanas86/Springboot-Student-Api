package com.anasalam.restapi.RestApis.controller;

import com.anasalam.restapi.RestApis.DTO.StudentCreateRequestDto;
import com.anasalam.restapi.RestApis.DTO.StudentDto;
import com.anasalam.restapi.RestApis.DTO.StudentUpdateRequestDto;
import com.anasalam.restapi.RestApis.service.StudentServices;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/students")
@AllArgsConstructor
public class StudentController {
    private final StudentServices studentServices;

    /*
    * === GET ALL STUDENT (GET) ===
    * Status: 200 ok
    * */
    @GetMapping
    public ResponseEntity<List<StudentDto>> getAllStudents(){
        List<StudentDto> students = studentServices.getAllStudents();
        return ResponseEntity.ok(students);
    }

    /*
    * === GET STUDENT BY ID (GET/:id) ===
    * status: 200 ok or 404 Not Found
    * */
    @GetMapping("/{id}")
    public ResponseEntity<StudentDto> getStudentById(@PathVariable long id){
        Optional<StudentDto> student = studentServices.getStudentById(id);
        if (student.isPresent()) {
            return ResponseEntity.ok(student.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /*
    * === GET STUDENT BY EMAIL (GET?:email=xxx) ===
    * Status: 200 ok or 404 not found
    * */
    @GetMapping("/by-email")
    public ResponseEntity<StudentDto> getStudentByEmail(@RequestParam String email) {
        Optional<StudentDto> student = studentServices.getStudentByEmail(email);
        if(student.isPresent()){
            return ResponseEntity.ok(student.get());
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    /*
     * === CREATE A NEW STUDENT (POST) ===
     * Status: 201 created
     * Now with @Valid - validates before method execution
     * */
    @PostMapping
    public ResponseEntity<StudentDto> createStudent(@Valid @RequestBody StudentCreateRequestDto createRequestDto){
        StudentDto createStudent = studentServices.createStudent(createRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createStudent);
    }

    /*
     * === UPDATE STUDENT (PUT/:id) ===
     * Status: 200 ok or 404 not found
     * */
    @PutMapping("/{id}")
    public  ResponseEntity<StudentDto> updateStudent(
            @PathVariable long id,
            @Valid  @RequestBody StudentUpdateRequestDto updateRequestDto
    ){
        Optional<StudentDto> updateStudent = studentServices.updateStudent(id, updateRequestDto);
        if(updateStudent.isPresent()){
            return ResponseEntity.ok(updateStudent.get());
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    /*
     * === DELETE STUDENT (DELETE/:id) ===
     * Status: 204 NO CONTENT or 404 NOT FOUND
     * */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable long id){
        if(studentServices.deleteStudent(id)){
            return ResponseEntity.noContent().build();
        }else{
            return ResponseEntity.notFound().build();
        }
    }
}
