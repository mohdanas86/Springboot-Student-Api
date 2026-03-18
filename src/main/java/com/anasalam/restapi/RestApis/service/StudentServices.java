package com.anasalam.restapi.RestApis.service;

import com.anasalam.restapi.RestApis.DTO.StudentCreateRequestDto;
import com.anasalam.restapi.RestApis.DTO.StudentDto;
import com.anasalam.restapi.RestApis.DTO.StudentUpdateRequestDto;
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
     * === GET ALL STUDENT ===
     * */
    public Optional<StudentDto> getStudentById(long id) {
        return studentRepository.findById(id)
                .map(studentEntity -> modelMapper.map(studentEntity, StudentDto.class));
    }

    /*
     * === GET STUDENT BY EMAIL ===
     * */
    public Optional<StudentDto> getStudentByEmail(String email) {
        return studentRepository.findByEmail(email)
                .map(studentEntity -> modelMapper.map(studentEntity, StudentDto.class));
    }

    /*
     * === CREATE NEW STUDENT (POST) ===
     * */
    public StudentDto createStudent(StudentCreateRequestDto requestDto) {
        StudentEntity studentEntity = new StudentEntity();
        studentEntity.setName(requestDto.getName());
        studentEntity.setEmail(requestDto.getEmail());
        studentEntity.setPassword(requestDto.getPassword());

        StudentEntity saveEntity = studentRepository.save(studentEntity);
        return modelMapper.map(saveEntity, StudentDto.class);
    }

    /*
     * === UPDATE STUDENT (PUT) ====
     * */
    public Optional<StudentDto> updateStudent(long id, StudentUpdateRequestDto requestDto) {
        return studentRepository.findById(id)
                .map(existingEntity -> {
                    existingEntity.setName(requestDto.getName());
                    existingEntity.setEmail(requestDto.getEmail());
                    existingEntity.setEmail(requestDto.getPassword());
                    StudentEntity updateEntity = studentRepository.save(existingEntity);
                    return modelMapper.map(updateEntity, StudentDto.class);
                });
    }

    /*
    * === DELETE STUDENT (DELETE) ===
    * */
    public boolean deleteStudent(long id){
        if(studentRepository.existsById(id)){
            studentRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
