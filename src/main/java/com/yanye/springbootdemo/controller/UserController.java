package com.yanye.springbootdemo.controller;

import com.yanye.springbootdemo.pojo.Result;
import com.yanye.springbootdemo.pojo.Student;
import com.yanye.springbootdemo.pojo.User;
import com.yanye.springbootdemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
@CrossOrigin(origins = "*")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/add")
    public Result addStudent(@RequestBody Student student) {
        if (student.getUsername().isEmpty()) {
            return Result.error("用户名不能为空!");
        }
        Student stu = userService.findStuById(student.getId());
        if (stu != null) {
            return Result.error("学生已存在!");
        }
        userService.add(student);
        return Result.success("添加成功！");
    }

    @PostMapping("/login")
    public Result login(@RequestParam String username, String password) {
        if (username.isEmpty() || password.isEmpty()) {
            return Result.error("用户名或密码不能为空!");
        }
        User user = userService.login(username);
        if (user == null) {
            return Result.error("找不到用户");
        }else if (!password.equals(user.getPassword())){
            return Result.error("密码错误");
        }
        return Result.success("登录成功");
    }

    @DeleteMapping("/delete")
    public Result delete(@RequestParam String id) {
        Student stu = userService.findStuById(id);
        if (stu == null) {
            return Result.error("找不到该用户");
        }
        userService.delete(id);
        return Result.success("删除成功");
    }

    @GetMapping("/get")
    public Result getAllStudent() {
        List<Student> students = userService.getAllStudent();
        return Result.success(students);
    }

    @PostMapping("/register")
    public Result register(@RequestParam String username,String password) {
        if (username.isEmpty() || password.isEmpty()) {
            return Result.error("用户名或密码不能为空!");
        }
        User u = userService.findUserByName(username);
        if (u != null) {
            return Result.error("用户名已存在!");
        }
        userService.register(username,password);
        return Result.success("注册成功！");
    }

    @PutMapping
    public Result updateStu(@RequestBody Student student ,@RequestParam String oldId){
        userService.updateStu(student,oldId);
        return Result.success("修改成功！");
    }
}
