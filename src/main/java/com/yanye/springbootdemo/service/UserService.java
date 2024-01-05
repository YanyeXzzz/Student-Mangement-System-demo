package com.yanye.springbootdemo.service;


import com.yanye.springbootdemo.pojo.Student;
import com.yanye.springbootdemo.pojo.User;

import java.util.List;

public interface UserService {
    void add(Student student);

    User login(String username);

    User findUserByName(String username);

    void delete(String id);

    List<Student> getAllStudent();

    void register(String username, String password);

    Student findStuById(String id);

    void updateStu(Student student,String oldId);
}