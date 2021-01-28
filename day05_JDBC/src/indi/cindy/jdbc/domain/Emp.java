package indi.cindy.jdbc.domain;

public class Emp {
    public int number;
    public String username;
    public int age;

    public Emp(int number, String username, int age) {
        this.number = number;
        this.username = username;
        this.age = age;
    }

    public void print(){
        String str = "number:"+number+", username:"+username+", age:"+age;
        System.out.println(str);
    }
}
