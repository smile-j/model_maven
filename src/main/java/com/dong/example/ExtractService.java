package com.dong.example;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/8/17.
 */
public class ExtractService {

    /**
     * @param rule
     * @return
     */
    public static List<LinkTypeData> extract(Rule rule) throws IOException {

        // 进行对rule的必要校验
        validateRule(rule);

        List<LinkTypeData> datas = new ArrayList<LinkTypeData>();
        LinkTypeData data = null;
        /**
         * 解析rule
         */
        String url = rule.getUrl();
        String[] params = rule.getParams();
        String[] values = rule.getValues();
        String resultTagName = rule.getResultTagName();
        int type = rule.getType();
        int requestType = rule.getRequestMoethod();

        Connection conn = Jsoup.connect(url);
        // 设置查询参数

        if (params != null)
        {
            for (int i = 0; i < params.length; i++)
            {
                conn.data(params[i], values[i]);
            }
        }

        // 设置请求类型
        Document doc = null;
            switch (requestType)
            {
                case Rule.GET:
                    doc = conn.timeout(100000).get();
                    break;
                case Rule.POST:
                    doc = conn.timeout(100000).post();
                    break;
            }
//        String result1 = getHtmlResourceByUrl(rule.getUrl(),"UTF-8");
//        doc = Jsoup.parse(result1);

        //处理返回数据
        Elements results = new Elements();
        switch (type)
        {
            case Rule.CLASS:
                results = doc.getElementsByClass(resultTagName);
                break;
            case Rule.ID:
                Element result = doc.getElementById(resultTagName);
                results.add(result);
                break;
            case Rule.SELECTION:
                results = doc.select(resultTagName);
                break;
            default:
                //当resultTagName为空时默认去body标签
                if (StringUtils.isEmpty(resultTagName))
                {
                    results = doc.getElementsByTag("body");
                }
        }

        for (Element result : results)
        {
            Elements links = result.getElementsByTag("a");

            for (Element link : links)
            {
                //必要的筛选
                String linkHref = link.attr("href");
                String linkText = link.text();

                data = new LinkTypeData();
                data.setLinkHref(linkHref);
                data.setLinkText(linkText);

                datas.add(data);
            }
        }

        return datas;
    }

    /**
     * 对传入的参数进行必要的校验
     */
    private static void validateRule(Rule rule)
    {
        String url = rule.getUrl();
        if (StringUtils.isEmpty(url))
        {
            throw new RuleException("url不能为空！");
        }
        if (!url.startsWith("http://"))
        {
            throw new RuleException("url的格式不正确！");
        }

        if (rule.getParams() != null && rule.getValues() != null)
        {
            if (rule.getParams().length != rule.getValues().length)
            {
                throw new RuleException("参数的键值对个数不匹配！");
            }
        }

    }



    public static String getHtmlResourceByUrl(String url,String encoding){

        StringBuffer buffer = new StringBuffer();
        InputStreamReader inputStream=null;
        try {
            //建立网络链接
            URL urlObj = new URL(url);
            //打开网络链接
            URLConnection uc = urlObj.openConnection();

            inputStream = new InputStreamReader(uc.getInputStream(),encoding);

            BufferedReader reader = new BufferedReader(inputStream);

            String line =null;

            while((line=reader.readLine())!= null){

                buffer.append(line);

            }


        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally{
            if(inputStream!=null){
                try {
                    inputStream.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }

        return buffer.toString();

    }
}
