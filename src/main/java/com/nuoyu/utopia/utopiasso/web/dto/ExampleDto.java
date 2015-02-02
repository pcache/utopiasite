package com.nuoyu.utopia.utopiasso.web.dto;

import com.nuoyu.utopia.utopiasso.pojo.Example;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;


/**
 * Created by liuxin3 on 2015/1/14.
 */
@XStreamAlias("example")
public class ExampleDto  extends BaseDto {

    @XStreamAsAttribute
    private String name;
    @XStreamAsAttribute
    private int age;
    @XStreamAsAttribute
    private String sex;

    public ExampleDto() {}

    public ExampleDto(Example example) {
        this.name=example.getName();
        this.age=example.getAge();
        this.sex=example.getSex();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}
