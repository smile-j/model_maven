package com.dong.base.test.reflect;

import org.springframework.beans.factory.FactoryBean;

/**
 * Created by Administrator on 2018/3/5.
 */
public class FactoryBeanPojo  implements FactoryBean {
    private String type;

    @Override
    public Object getObject() throws Exception {
        if("student".equals(type)){
            return new Student();
        }else{
            return new Person();
        }

    }

    @Override
    public Class getObjectType() {
        return Person.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
