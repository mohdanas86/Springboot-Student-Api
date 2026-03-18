package com.anasalam.restapi.RestApis.service;

import com.anasalam.restapi.RestApis.DTO.StudentDto;
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
    public List<StudentDto> getAllStudents(){
        return studentRepository.findAll()
                .stream()
                .map(studentEntity -> modelMapper.map(studentEntity, StudentDto.class))
                .toList();
    }

    /*
     * === GET ALL STUDENT ===
     * */
    public Optional<StudentDto> getStudentById(long id){
        return studentRepository.findById(id)
                .map(studentEntity -> modelMapper.map(studentEntity, StudentDto.class));
    }

    /*
    * === GET STUDENT BY EMAIL ===
    * */
    public Optional<StudentDto> getStudentByEmail(String email){
        return studentRepository.findByEmail(email)
                .map(studentEntity-> modelMapper.map(studentEntity, StudentDto.class));
    }
}
