package com.dong.example;

import org.junit.Test;

import java.io.IOException;
import java.util.List;

/**
 * Created by Administrator on 2017/8/17.
 */
public class TestSpider {

    @Test
    public void getDatasByCssQueryUserBaidu()
    {
        System.out.println("-----------start----------");
        Rule rule = new Rule("http://news.baidu.com/ns",
                new String[] { "word" }, new String[] { "支付宝" },
                null, -1, Rule.GET);
        List<LinkTypeData> extracts = null;
        try {
            extracts = ExtractService.extract(rule);
        } catch (IOException e) {
            e.printStackTrace();
        }
        printf(extracts);
    }

    @Test
    public void TestgetDatasByClass()
    {
        Rule rule = new Rule(
                "http://www1.sxcredit.gov.cn/public/infocomquery.do?method=publicIndexQuery",
                new String[] { "query.enterprisename","query.registationnumber" }, new String[] { "兴网","" },
                "cont_right", Rule.CLASS, Rule.POST);
        List<LinkTypeData> extracts = null;
        try {
            extracts = ExtractService.extract(rule);
        } catch (IOException e) {
            e.printStackTrace();
        }
        printf(extracts);
    }


    @Test
    public void TestgetDatasByCssQuery()
    {
        Rule rule = new Rule("http://www.11315.com/search",
                new String[] { "name" }, new String[] { "兴网" },
                "div.g-mn div.con-model", Rule.SELECTION, Rule.GET);
        List<LinkTypeData> extracts = null;
        try {
            extracts = ExtractService.extract(rule);
        } catch (IOException e) {
            e.printStackTrace();
        }
        printf(extracts);
    }

    public void printf(List<LinkTypeData> datas)
    {
        for (LinkTypeData data : datas)
        {
            System.out.println(data.getLinkText());
            System.out.println(data.getLinkHref());
            System.out.println("***********************************");
        }

    }

    @Test
    public void testBase(){
        int [] ss = new int[2];
        double d = 0.1,dd=0.2 ;
        double sum = d+dd;
        System.out.println("--->"+sum);

        int i = Integer.MAX_VALUE;
        System.out.println("+++"+(i+1>i));
    }

}
