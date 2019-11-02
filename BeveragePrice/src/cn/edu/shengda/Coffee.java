package cn.edu.shengda;

abstract class Beverage {
    public abstract int Cost();
    int select = 0;
}

//配料
abstract class CondimentDecorator extends Beverage {
    Beverage beverage;
}

//咖啡
class Coffee extends Beverage {
    private int COFFEE_PRICE = 20;
    public Coffee() {
        super();
    }
    public int Cost() {
        select = 1;
        return COFFEE_PRICE;
    }
}
//奶茶
class MilkTea extends Beverage {
    private int MILKTEA_PRICE = 25;
    public MilkTea() {
        super();
    }
    public int Cost() {
        select = 2;
        return MILKTEA_PRICE;
    }
}

//摩卡
class Mocha extends CondimentDecorator {
    private int MOCHA_PRICE = 10;
    public Mocha(Beverage beverage) {
        this.beverage = beverage;
    }
    public int Cost() {
        this.select = beverage.select ^ (1 << 2);
        if (((this.select >> 2) & 1) != 0)
            return beverage.Cost() + MOCHA_PRICE;//勾选
        else return beverage.Cost() - MOCHA_PRICE;//取消
    }
}

//珍珠
class Pearl extends CondimentDecorator {
    private int PEARL_PRICE = 15;
    public Pearl(Beverage beverage) {
        this.beverage = beverage;
    }
    public int Cost() {
        this.select = beverage.select ^ (1 << 3);
        if (((this.select >> 3) & 1) != 0)
            return beverage.Cost() + PEARL_PRICE;
        else return beverage.Cost() - PEARL_PRICE;
    }
}

//奶泡
class Whip extends CondimentDecorator{
    private int WHIP_PRICE = 10;
    public Whip(Beverage beverage) {
        this.beverage = beverage;
    }
    public int Cost() {
        this.select = beverage.select ^ (1 << 4);
        if (((this.select >> 4) & 1) != 0)
            return beverage.Cost() + WHIP_PRICE;
        else return beverage.Cost() - WHIP_PRICE;
    }
}

//巧克力
class Chocolate extends CondimentDecorator{
    private int CHOCOLATE_PRICE = 20;
    public Chocolate(Beverage beverage) {

        this.beverage = beverage;
    }
    public int Cost() {
        this.select = beverage.select ^ (1 << 5);
        if (((this.select >> 5) & 1) != 0)
            return beverage.Cost() + CHOCOLATE_PRICE;
        else return beverage.Cost() - CHOCOLATE_PRICE;
    }
}
