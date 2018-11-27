package com.example.celebyoutube;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
public class Hello implements Cloneable{

    private Date date;

    @Override
    protected Hello clone() throws CloneNotSupportedException {
/*
        Hello hello = (Hello) super.clone();
        hello.userList = (User[]) userList.clone();
        return hello;
*/
        return (Hello) super.clone();
    }

}
