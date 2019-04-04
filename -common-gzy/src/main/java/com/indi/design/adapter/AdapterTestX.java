package com.indi.design.adapter;

/**
 * Created by Administrator on 2019/3/22.
 * 适配器模式
 */
class AC220{
    int outputAC220V(){
        int output = 220;
        System.out.println("输出交流电220V");
        return output;
    }
}
//创建电源适配器
class PowerAdapter implements DC5{
    private AC220 ac220;
    public PowerAdapter(AC220 ac220) {
        this.ac220 = ac220;
    }

    @Override
    public int outputDC5() {
        int adapterinput = ac220.outputAC220V();
//        变压器...
        int adapteroutput = adapterinput / 44;
        System.out.println("使用PowerAdapter输入AC：" + adapterinput + "V, 输出DC：" + adapteroutput + "V");
        return adapteroutput;
    }
}
//5V直流电接口标准
interface DC5{
    int outputDC5();
}
public class AdapterTestX {
    public static void main(String[] args) {
        DC5 dc5 = new PowerAdapter(new AC220());
        dc5.outputDC5();
    }
}
