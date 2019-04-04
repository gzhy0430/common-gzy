package com.indi.design.proxy.dbroute;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DbRouteTestX{
    public static void main(String[] args) {
        Order order = new Order();
        order.setCreateTime(new Date().getTime());
        IOrderService service = new OrderServiceStaticProxy(new OrderService());
        service.createOrder(order);
    }
}

class OrderServiceStaticProxy implements IOrderService{
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
    private IOrderService orderService;

    public OrderServiceStaticProxy(IOrderService orderService) {
        this.orderService = orderService;
    }

    @Override
    public int createOrder(Order order) {
        Long time = order.getCreateTime();
        Integer dbRoute = Integer.valueOf(sdf.format(new Date(time)));
        System.out.println("静态代理类自动分配到[DB_" + dbRoute + "]数据源");
        DynamicDataSourceEntity.set(dbRoute);
        Integer i = this.orderService.createOrder(order);
        DynamicDataSourceEntity.restore();
        return i;
    }
}

class DynamicDataSourceEntity{
    private final static ThreadLocal<String> local = new ThreadLocal<String>();
    public final static String DEFAULT = null;
    private DynamicDataSourceEntity(){}
    public static String get(){
        return local.get();
    }
    public static void restore(){
        local.set(DEFAULT);
    }
//    DB_2018\DB_2019
    public static void set(String source){
        local.set(source);
    }
    public static void set(int year){
        local.set("DB_" + year);
    }
}

class OrderService implements IOrderService{
    private OrderDao dao;

    public OrderService() {
        this.dao = new OrderDao();
    }

    @Override
    public int createOrder(Order order) {
        System.out.println("OrderService调用dao创建订单");
        return dao.insert(order);
    }
}

interface IOrderService{
    int createOrder(Order order);
}

class OrderDao{
    int insert(Order order){
        System.out.println("Order创建Order成功");
        return 1;
    }
}

class Order {
    private Object orderInfo;
    private Long createTime;
    private String id;

    public Object getOrderInfo() {
        return orderInfo;
    }

    public void setOrderInfo(Object orderInfo) {
        this.orderInfo = orderInfo;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
