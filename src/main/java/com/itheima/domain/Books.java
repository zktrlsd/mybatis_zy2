package com.itheima.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;
@Setter(AccessLevel.PUBLIC)
@Getter(AccessLevel.PUBLIC)
@ToString
public class Books {
    private int Bid;
    private String Bname;
    private String press;
    private Date Pubdate;
    private double money;
    private String Author;
}
