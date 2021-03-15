package indi.cindy.xml.main;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;

/*
Jsoup+selector
 */
public class MyJsoup1 {
    public static void main(String[] args) {
        String path = MyJsoup.class.getClassLoader().getResource("students.xml").getPath();
        File file = new File(path);
        Document dom = null;
        try {
            dom = Jsoup.parse(new File(path), "utf-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        //查询标签
        Elements elements1 = dom.select("name");
        System.out.println(elements1);
        System.out.println();
        //查询id
        Elements elements2 = dom.select("#student1");
        System.out.println(elements2);
        System.out.println();
        //查询属性值为xxx的记录
        Elements elements3 = dom.select("student[id=\"student2\"]");
        System.out.println(elements3);
        System.out.println();
        //查询属性值为xxx的记录的age标签的值
        Elements elements4 = dom.select("student[id=\"student2\"] > age");
        System.out.println(elements4);
        System.out.println();
    }
}
