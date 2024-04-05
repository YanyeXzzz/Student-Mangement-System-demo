package com.yanye.springbootdemo.service;


import com.yanye.springbootdemo.pojo.Page;
import com.yanye.springbootdemo.pojo.Song;
import com.yanye.springbootdemo.pojo.Student;
import com.yanye.springbootdemo.pojo.User;

import java.io.FileOutputStream;
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

    void updatePwd(String username,String password);

    List<Student> select(String username, String id);

    List<Student> selectWithPage(Page page);

    Integer getPageTotal();

    Integer saveMusic(Song song);
}
