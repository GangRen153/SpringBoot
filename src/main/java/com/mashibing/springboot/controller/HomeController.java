//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.mashibing.springboot.controller;

import com.mashibing.springboot.base.Result;
import com.mashibing.springboot.entity.User;
import com.mashibing.springboot.repository.BaseRepository;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"/home"})
public class HomeController {
    @Autowired
    private BaseRepository repository;

    public HomeController() {
    }

    @GetMapping({"/createIndex"})
    public Result createIndex() {
        this.repository.createIndex(new String[]{"user"});
        return Result.newResult();
    }

    @GetMapping({"insert"})
    public Result insert(User user) {
        this.repository.insert(this.createUser(user));
        return new Result(true);
    }

    @GetMapping({"update"})
    public Result update(User user) {
        this.repository.update(new User[]{user});
        return new Result(true);
    }

    @GetMapping({"delete"})
    public Result delete(User user) {
        this.repository.delete(this.createUser(user));
        return new Result(true);
    }

    @GetMapping({"list"})
    public Result list(User user) {
        this.repository.list(1, 3);
        return new Result(true);
    }

    public User[] createUser(User user) {
        User[] users = new User[3];

        for(int i = 0; i < 3; ++i) {
            User user1 = new User();
            user1.setId(user.getId());
            user1.setBrand(user.getBrand());
            user1.setContent(user.getContent());
            user1.setModel(user.getModel());
            user1.setPrice(user.getPrice());
            user1.setRam(user.getRam());
            user1.setIntroduce(user.getIntroduce());
            user1.setManufactureDate(new Date());
            users[i] = user1;
        }

        return users;
    }
}
