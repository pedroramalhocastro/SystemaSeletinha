package com.system.seletinha.endpoint.services;


import com.system.seletinha.models.TeachersModel;
import com.system.seletinha.repository.TeacherRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class TeacherService {

    private final TeacherRepository teacherRepository;

    public String create(final TeachersModel teachersModel) {
        teacherRepository.save(teachersModel);
        return "SuccessFull";
    }


    public TeachersModel searchId(final String registry) {
        Optional<TeachersModel> teachers = teacherRepository.findById(Integer.valueOf(registry));
        return teachers.get();
    }

    public List<TeachersModel> searchAll() {
        return teacherRepository.findAll();
    }

    public String update(final TeachersModel studentModel) {
        teacherRepository.deleteById(studentModel.getRegistry());
        teacherRepository.save(studentModel);
        return "SuccessFull";
    }

    public String delete(final String registry) {
        teacherRepository.deleteById(Integer.valueOf(registry));
        return "SuccessFull";
    }



}
