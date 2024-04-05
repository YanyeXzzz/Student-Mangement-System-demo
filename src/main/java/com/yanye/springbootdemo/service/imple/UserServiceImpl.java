package com.yanye.springbootdemo.service.imple;

import com.yanye.springbootdemo.mapper.UserMapper;
import com.yanye.springbootdemo.pojo.*;
import com.yanye.springbootdemo.service.UserService;
import com.yanye.springbootdemo.util.SpiderUtil;
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

    @Override
    public void updatePwd(String username,String password) {
        userMapper.updatePwd(username,password);
    }

    @Override
    public List<Student> select(String username, String id) {
        return userMapper.select(username,id);
    }

    @Override
    public List<Student> selectWithPage(Page page) {
        Integer pageNo = page.getPageNumber();
        Integer pageSize = page.getPageSize();
        Integer startIndex = (pageNo-1) * pageSize;

        return userMapper.selectWithPage(startIndex,pageSize);
    }

    @Override
    public Integer getPageTotal() {
        return userMapper.getPageTotal();
    }


    /**
     * 保存歌曲
     * @param song 歌曲信息
     * @return 0-成功 -1-失败
     */
    @Override
    public Integer saveMusic(Song song) {
        String url = "http://music.163.com/song/media/outer/url?id=" + song.getSongId();
        String path = "src/main/resources/songs/" + song.getSongName() + ".mp3";
        return SpiderUtil.saveSongs(url, path);
    }
}
