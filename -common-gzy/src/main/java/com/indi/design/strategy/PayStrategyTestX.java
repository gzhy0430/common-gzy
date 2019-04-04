package com.indi.design.strategy;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2019/3/24.
 */
abstract class Payment{
    public abstract String getName();
    protected abstract double queryBalance(String uid);
    public MsgResult pay(String uid, double amount){
        if(queryBalance(uid) < amount){//余额900，需要支付400
            return new MsgResult(500, "支付失败", "余额不足");
        }
        return new MsgResult(200, "支付成功", "支付金额"  + amount);
    }
}

class AliPay extends Payment{
    @Override
    public String getName() {
        return "支付宝";
    }

    @Override
    protected double queryBalance(String uid) {
        return 900;
    }
}

class Order {
    private String uid;
    private String orderid;
    private double amount;

    public Order(String uid, String orderid, double amount) {
        this.uid = uid;
        this.orderid = orderid;
        this.amount = amount;
    }
    public MsgResult pay(String paykey){
        Payment payment = PayStrategy.get(paykey);
        System.out.println("欢迎使用" + payment.getName());
        return payment.pay(uid,amount);
    }
}

class PayStrategy{
    public static final String ALI_PAY = "ALIPAY";
    private static final Map<String, Payment> map = new HashMap();
    static {
        map.put(ALI_PAY, new AliPay());
    }
    public static Payment get(String paykey){
        return map.get(paykey);
    }
}

class MsgResult{
    private int code;
    private Object data;
    private String msg;

    @Override
    public String toString() {
        return "支付状态{" + code +
                ", 交易详情=" + data +
                ", msg='" + msg +
                '}';
    }

    MsgResult(int code, String msg, Object data) {
        this.code = code;
        this.data = data;
        this.msg = msg;
    }
}

public class PayStrategyTestX {
    public static void main(String[] args) {
        Order order =  new Order("uid", "orderid", 1400);
        MsgResult msg = order.pay("ALIPAY");
        System.out.println(msg);
    }
}
