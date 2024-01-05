package com.yanye.springbootdemo.service.imple;

import com.yanye.springbootdemo.mapper.UserMapper;
import com.yanye.springbootdemo.pojo.Student;
import com.yanye.springbootdemo.pojo.User;
import com.yanye.springbootdemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public void add(Student student) {
        userMapper.add(student);
    }

    @Override
    public User login(String username) {
        return userMapper.login(username);
    }

    @Override
    public User findUserByName(String username) {
        return userMapper.findUserByName(username);
    }

    @Override
    public void delete(String id) {
        userMapper.delete(id);
    }

    @Override
    public List<Student> getAllStudent() {
        return userMapper.getAllStudent();
    }

    @Override
    public void register(String username, String password) {
        userMapper.register(username, password);
    }

    @Override
    public Student findStuById(String id) {
        return userMapper.findStuById(id);
    }

    @Override
    public void updateStu(Student student,String oldId) {
        userMapper.updateStu(student,oldId);
    }
}
