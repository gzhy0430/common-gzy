package com.indi.design.adapter.homework;

/**
 * Created by Administrator on 2019/3/25.
 * https://gper.gupaoedu.com/articleContent?id=684
 * 模板模式除了继承以外，还有哪些实现方式--JDK1.8之后接口提供默认实现方法，使用default关键字--com.indi.design.tmplate.InterfaceTemplateTestX
 * 使用适配器模式重构一段需要升级功能且兼容要系统的业务代码
 */

import java.util.ArrayList;
import java.util.List;

/**
 * 业务场景：公司原有权限系统，使用权限-菜单-人员三表关联实现，现在，对部分菜单权限做操作按钮级别优化，
 * 比如对某个数据页面具有导出，删除，修改，预览四种权限。现不采用三表关联来做，采用二进制位移操作来做
 * 因为原有系统还可以使用，只是对于按钮级别权限适配不好，现采用适配器适配按钮操作。
 */
public class AdapterTestX {
    public static void main(String[] args) {
//        boolean isauth = Button.isauth(11, 2);//11是否包含2
//        System.out.println(isauth);
        StaffMenu sm = new StaffMenu(1, 1);
        Staff s = new Staff(1, "小明", 11);
        AuthTarget at1 = new SrcAuthTarget();
        boolean mau = at1.isauththisobj(s, sm);//小明是否拥有菜单1权限
        AuthTarget at2 = new AuthAdapter(null);
        boolean upd = at2.isauththisobj(s, Button.UPD);//判断小明是否有修改权限
        boolean yul = at2.isauththisobj(s, Button.YUL);//判断小明是否有预览权限
        System.out.println(mau);
        System.out.println(upd);
        System.out.println(yul);
    }
}

class SrcAuthTarget implements AuthTarget{
    @Override
    public boolean isauththisobj(Staff staff, Object args) {
        if(args instanceof StaffMenu){//原有方法实现
            StaffMenu sm = (StaffMenu) args;
            return staff.getStaffId() == sm.getStaffid();
        }
        throw new RuntimeException("无法判断权限类型");
    }
}

class AuthAdapter implements AuthTarget{
    AuthTarget adaptee;
    public AuthAdapter(AuthTarget adaptee) {
        this.adaptee = adaptee;
    }
    @Override
    public boolean isauththisobj(Staff staff, Object args) {
        if(args instanceof StaffMenu){//原有方法实现
//            StaffMenu sm = (StaffMenu) args;
//            return staff.getStaffId() == sm.getStaffid();
            adaptee.isauththisobj(staff, args);
        }else if(args instanceof Integer){//适配方法实现
            Integer authnum = (Integer) args;
            return Button.isauth(staff.getButtonauth(), authnum);
        }
        throw new RuntimeException("无法判断权限类型");
    }
}

interface AuthTarget{//是否有权限判断
    boolean isauththisobj(Staff staff, Object args);
}

class StaffMenu{//人员菜单关联表类
    private int staffid;
    private int menuid;
    public int getStaffid() {
        return staffid;
    }
    public void setStaffid(int staffid) {
        this.staffid = staffid;
    }
    public int getMenuid() {
        return menuid;
    }
    public void setMenuid(int menuid) {
        this.menuid = menuid;
    }
    public StaffMenu(int staffid, int menuid) {
        this.staffid = staffid;
        this.menuid = menuid;
    }
}

class Staff{
    private String name;
    private int staffId;
    public int getStaffId() {
        return staffId;
    }
    public void setStaffId(int staffId) {
        this.staffId = staffId;
    }
    private int buttonauth;
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getButtonauth() {
        return buttonauth;
    }
    public void setButtonauth(int buttonauth) {
        this.buttonauth = buttonauth;
    }
    public Staff(int staffId, String name, int buttonauth) {
        this.staffId = staffId;
        this.name = name;
        this.buttonauth = buttonauth;
    }
}

class Button{
    public static final Integer DEL = 1;//删除
    public static final Integer UPD = 2;//修改
    public static final Integer YUL = 4;//预览
    public static final Integer EXP = 8;//导出
    public static boolean isauth(int target, int authnum){//某个用户权限值是否包含某个特定权限
        List<Integer> result = new ArrayList<>();//权限数组
        for (int i = 0; i < 32; i++){
            if ((target >>> i & 1) == 1){  //右移并与1进行&运算
                result.add(1<<i);
            }
        }
        return result.contains(authnum);
    }
}