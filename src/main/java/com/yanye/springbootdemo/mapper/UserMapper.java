package com.yanye.springbootdemo.mapper;


import com.yanye.springbootdemo.pojo.Student;
import com.yanye.springbootdemo.pojo.User;
import org.apache.ibatis.annotations.*;

import java.util.List;


@Mapper
public interface UserMapper {

    @Insert("insert into student(id,username,age,sex,class_name,create_time,update_time) values (#{id},#{username},#{age},#{sex},#{className},now(),now())")
    void add(Student student);

    @Select("select * from user where username=#{username}")
    User login(String username);

    @Select("select * from user where username=#{username}")
    User findUserByName(String username);

    @Delete("delete from student where id=#{id}")
    void delete(String id);

    @Select("select id,username,age,sex,class_name from student")
    List<Student> getAllStudent();

    @Insert("insert into user(username,password,create_time,update_time) values (#{username},#{password},now(),now())")
    void register(String username, String password);

    @Select("select * from student where id=#{id}")
    Student findStuById(String id);

    @Update("update student set id=#{student.id},username=#{student.username},age=#{student.age},sex=#{student.sex},class_name=#{student.className},update_time=now() where id=#{oldId}")
    void updateStu(Student student,String oldId);
}
