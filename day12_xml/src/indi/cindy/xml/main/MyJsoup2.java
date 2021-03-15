package indi.cindy.xml.main;

import cn.wanghaomiao.xpath.exception.XpathSyntaxErrorException;
import cn.wanghaomiao.xpath.model.JXDocument;
import cn.wanghaomiao.xpath.model.JXNode;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.util.List;

/*
Jsoup+XPath
 */
public class MyJsoup2 {
    public static void main(String[] args) throws XpathSyntaxErrorException {
        String path = MyJsoup.class.getClassLoader().getResource("students.xml").getPath();
        File file = new File(path);
        Document dom = null;
        try {
            dom = Jsoup.parse(new File(path), "utf-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        //创建JXDocument对象
        JXDocument jxDocument = new JXDocument(dom);
        //查询所有student标签
        List<JXNode> elements1 = jxDocument.selN("//student");
        System.out.println(elements1);
        System.out.println();
        //查询student标签下的name标签
        List<JXNode> elements2 = jxDocument.selN("//student/name");
        System.out.println(elements2);
        System.out.println();
        //查询student标签下的name标签中number属性为2的
        List<JXNode> elements3 = jxDocument.selN("//student/name[@number='2']");
        System.out.println(elements3);
        System.out.println();
    }
}
