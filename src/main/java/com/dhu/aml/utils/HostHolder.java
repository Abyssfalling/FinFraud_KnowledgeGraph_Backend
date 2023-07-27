package com.dhu.aml.utils;

import com.dhu.aml.entity.User;
import org.springframework.stereotype.Component;

@Component
public class HostHolder {

    //本地线程
    private ThreadLocal<User> users = new ThreadLocal<>();

    public void setUser(User user){
        this.users.set(user);
    }

    public User get(){
        return this.users.get();
    }

    public void clear(){
        this.users.remove();
    }
}
