package com.example.proxy;

/**
 * @author Cindy.H
 * @description: 卖联想电脑的真实对象
 * @date 2021-03-27 11:33:02
 */
public class Lenovo implements SaleComputer{
    @Override
    public String sale(double money) {
        String s = "花了"+money+"买了Lenovo电脑";
        return s;
    }

    @Override
    public void show() {
        System.out.println("show...");
    }
}
