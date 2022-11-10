package com.tcs.School.service;

import com.tcs.School.configuration.ItemService;
import com.tcs.School.entity.CourseEntity;
import com.tcs.School.entity.Item;
import com.tcs.School.exception.StudentNotFound;
import com.tcs.School.model.StudentModel;
import com.tcs.School.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class StudentService implements ItemService {

    @Autowired
    private StudentRepository repository;

    @Autowired
    private RestTemplate clientRest;

    public List<StudentModel> getStudents(){
        return repository.findAll();
    }

    public Optional<StudentModel> getStudent(Long id){
        return repository.findById(id);
    }

    public StudentModel saveStudent(StudentModel student){
        return repository.save(student);
    }

    public StudentModel updateStudent(StudentModel student,Long id){
        return repository.findById(id).map(
                user -> {
                    user.setName(student.getName());
                    user.setAge(student.getAge());
                    user.setSemester(student.getSemester());
                    return repository.save(user);
                }
        ).orElseThrow(() -> new StudentNotFound("There is not student with the id " + id));
    }

    public String deleteStudent(Long id){
        try{
            repository.deleteById(id);
            return "Deleted";
        }
        catch(Exception e) {
            return "Sorry sire, there's not student";
        }
    }

    public Item getStudentsAndCourses(Long id){
        Item item = new Item();
        item.setStudent(repository.findById(id).orElseThrow(() -> new RuntimeException("There's an exception sire")));
        item.getStudent().setCourses(getCourseById(id));
        return item;
    }

    @Override
    public CourseEntity[] getCourseById(Long id) {
        Map<String,Long> pathVariables = new HashMap<String,Long>();
        pathVariables.put("id",id);
        CourseEntity[] course = clientRest.getForObject("http://localhost:8081/api-course/courseForStudent/{id}", CourseEntity[].class,pathVariables);
        return course;
    }
}
