package com.indi.design.strategy;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2019/3/24.
 * 策略模式：避免多重分支if...else和switch语句
 */
//促销活动--优惠策略
interface PromotionStrategy{
    void doPromotion();
}

//团购策略
class GroupbuyStrategy implements PromotionStrategy{
    @Override
    public void doPromotion() {
        System.out.println("拼团，满20人拼团，打八折");
    }
}

//返现策略
class CashbackStategy implements PromotionStrategy{
    @Override
    public void doPromotion() {
        System.out.println("返现促销，返回金额转到支付宝");
    }
}

//优惠券
class CouponStrategy implements PromotionStrategy{
    @Override
    public void doPromotion() {
        System.out.println("领取优惠券，价格直接减去优惠券面值进行抵扣");
    }
}

//原价策略
class EmptyStrategy implements PromotionStrategy{
    @Override
    public void doPromotion() {
        System.out.println("原价，无优惠");
    }
}

//活动
class PromotionAvtivity{
    PromotionStrategy strategy;
    public PromotionAvtivity(PromotionStrategy strategy) {
        this.strategy = strategy;
    }
    void execute(){
        this.strategy.doPromotion();
    }
}

public class StrategyTestX {
    public static void main(String[] args) {
        /*PromotionAvtivity avtivity618 = new PromotionAvtivity(new CouponStrategy());
        avtivity618.execute();

        PromotionAvtivity avtivity1111 = new PromotionAvtivity(new CashbackStategy());
        avtivity1111.execute();*/

        /*PromotionAvtivity avtivity = null;
        String promotionKey = "COUPON";

        if(promotionKey.equals("COUPON")){
            avtivity = new PromotionAvtivity(new CouponStrategy());
        }else if(promotionKey.equals("CASHBACK")){
            avtivity = new PromotionAvtivity(new CashbackStategy());
        }//...*/


        PromotionStrategy strategy = PromotionStrategyFactory.getPromotionStrategy(null);
        strategy.doPromotion();

        PromotionStrategy strategy1 = PromotionStrategyFactory.getPromotionStrategy("COUPON");
        strategy1.doPromotion();
        PromotionStrategy strategy2 = PromotionStrategyFactory.getPromotionStrategy("CASHBACK");
        strategy2.doPromotion();
        PromotionStrategy strategy3 = PromotionStrategyFactory.getPromotionStrategy("GROUPBUY");
        strategy3.doPromotion();
    }
}

//注册饿汉式单例
class PromotionStrategyFactory{
    private static Map<String, PromotionStrategy> PROMOTION_STRATEGY_MAP = new HashMap<>();
    static {
        PROMOTION_STRATEGY_MAP.put(PromotionKey.COUPON.toString(), new CouponStrategy());
        PROMOTION_STRATEGY_MAP.put(PromotionKey.CASHBACK.toString(), new CashbackStategy());
        PROMOTION_STRATEGY_MAP.put(PromotionKey.GROUPBUY.toString(), new GroupbuyStrategy());
    }
    private static final PromotionStrategy NONE = new EmptyStrategy();
    private enum PromotionKey{
        COUPON, CASHBACK, GROUPBUY
    }
    private PromotionStrategyFactory(){}
    public static PromotionStrategy getPromotionStrategy(String promotionKey){
        return promotionKey == null ? NONE : PROMOTION_STRATEGY_MAP.get(promotionKey);
    }
}
