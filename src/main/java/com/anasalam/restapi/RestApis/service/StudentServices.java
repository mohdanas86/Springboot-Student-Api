package com.anasalam.restapi.RestApis.service;

import com.anasalam.restapi.RestApis.DTO.StudentCreateRequestDto;
import com.anasalam.restapi.RestApis.DTO.StudentDto;
import com.anasalam.restapi.RestApis.DTO.StudentUpdateRequestDto;
import com.anasalam.restapi.RestApis.ResourceNotFoundException;
import com.anasalam.restapi.RestApis.ValidationException;
import com.anasalam.restapi.RestApis.entity.StudentEntity;
import com.anasalam.restapi.RestApis.repository.StudentRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class StudentServices {
    private final StudentRepository studentRepository;
    private final ModelMapper modelMapper;

    /*
     * === GET ALL STUDENT ===
     * */
    public List<StudentDto> getAllStudents() {
        return studentRepository.findAll()
                .stream()
                .map(studentEntity -> modelMapper.map(studentEntity, StudentDto.class))
                .toList();
    }

    /*
     * === GET STUDENT BY ID ===
     * */
    public StudentDto getStudentById(long id) {
        return studentRepository.findById(id)
                .map(studentEntity -> modelMapper.map(studentEntity, StudentDto.class))
                .orElseThrow(()-> new ResourceNotFoundException("Student with ID " + id + " not Found"));
    }

    /*
     * === GET STUDENT BY EMAIL ===
     * */
    public StudentDto getStudentByEmail(String email) {
        return studentRepository.findByEmail(email)
                .map(studentEntity -> modelMapper.map(studentEntity, StudentDto.class))
                .orElseThrow(()-> new ResourceNotFoundException("Student with email '" + email + "' not found"));
    }

    /*
     * === CREATE WITH DUPLICATE EMAIL CHECK ===
     * */
    public StudentDto createStudent(StudentCreateRequestDto requestDto){
        // Check if email already exists
        Optional<StudentEntity> existingStudent = studentRepository.findByEmail(requestDto.getEmail());

        if(existingStudent.isPresent()){
            throw new ValidationException("Email '" + requestDto.getEmail() + "' is already in use");
        }

        StudentEntity studentEntity = new StudentEntity();
        studentEntity.setEmail(requestDto.getEmail());
        studentEntity.setName(requestDto.getName());

        StudentEntity savedEntity = studentRepository.save(studentEntity);
        return modelMapper.map(savedEntity, StudentDto.class);
    }

    /*
     * === UPDATE WITH NOT FOUND AND DUPLICATE EMAIL CHECK ===
     * */
    public StudentDto updateStudent(long id, StudentUpdateRequestDto requestDto) {
        StudentEntity existingEntity = studentRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Student with Id " + id + " not found"));

        Optional<StudentEntity> emailExists = studentRepository.findByEmail(requestDto.getEmail());
        if(emailExists.isPresent() && emailExists.get().getId() != id){
            throw new ValidationException("Email '" + requestDto.getEmail() + "' is already in use");
        }

        existingEntity.setName(requestDto.getName());
        existingEntity.setEmail(requestDto.getEmail());
        StudentEntity updateEntity = studentRepository.save(existingEntity);
        return modelMapper.map(updateEntity, StudentDto.class);
    }

    /*
     * === DELETE WITH NOT FOUND EXCEPTION ===
     * */
    public void deleteStudent(long id){
        StudentEntity student = studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Student with ID " + id + " not found"));

        studentRepository.deleteById(id);
    }
}
