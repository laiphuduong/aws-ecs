package com.example.awsecs.repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.example.awsecs.dto.StudentGroupDTO;
import com.example.awsecs.entity.Student;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {
    //naming jpa
    List<Student> findAllByNameContains(String name);


    //native query
//    @Query(nativeQuery = true, value = "select * from student where id = :id")
//    Optional<Student> getStudentById(@Param("id") Integer integer);

    @Query(value = "select s from Student s where s.id = :id")
    Optional<Student> getStudentById(@Param("id") Integer integer);


    @Query("select new com.example.awsecs.dto.StudentGroupDTO(s.name , g.groupName)  from Student s join StudentGroup sg on s.id  = sg.student.id " +
            "join Grade g on sg.grade.id = g.id")
    List<StudentGroupDTO> getStudentAndGroup();

    @Query(value = "select s.id sid, s.name, s.age, s.gpa , g.id gid, g.group_name  from student s join student_group sg on s.id  = sg.student_id \n" +
            "join \"grade\" g on sg.group_id = g.id;", nativeQuery = true)
    List<Map<String, Object>> getStudentAndGroupNative();
}
