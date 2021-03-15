package indi.cindy.xml.main;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.w3c.dom.ls.LSOutput;

import java.io.File;
import java.io.IOException;

/*
利用Jsoup解析students.xml
 */
public class MyJsoup {
    public static void main(String[] args) throws IOException {
        //1、获取students.xml的path
        String path = MyJsoup.class.getClassLoader().getResource("students.xml").getPath();
        //这里的students.xml默认是在src/目录下的
        /*
        需要注意的是！！！！！！！
        之前在Jsoup.parse(new File(path), "utf-8");这行老是报错 File not found
        但是我发现所在路径的文件在呀。。后来发现是绝对路径中有中文，所以报错
        而后我把中文全换为英文，就跑通了！！！
        */
        //2、利用Jsoup解析xml为Document
        File file = new File(path);
        Document dom = null;
        try {
            dom = Jsoup.parse(new File(path), "utf-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        //3、获取特定的Elements
        Elements elements = dom.getElementsByTag("name");
        //4、获取elements的数量
        System.out.println(elements.size());
        //5、获取第一个人的名字
        System.out.println(elements.get(0).text());

    }

}
