package cn.edu.shengda;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CoffeeFrame extends JFrame implements ActionListener {
    JRadioButton radioButtonCoffe;
    JRadioButton radioButtonMilkTea;
    JCheckBox checkBoxMocha;
    JCheckBox checkBoxPearl;
    JCheckBox checkBoxWhip;
    JCheckBox checkBoxChocolate;
    JLabel labelPrice;
    JLabel totalPrice;
    JLabel ingredient;
    ButtonGroup group;
    Beverage beverage;
    String sle;
    String[] select = {"咖啡", "奶茶", ", 摩卡", ", 珍珠", ", 奶泡", ", 巧克力"};
    CoffeeFrame() {
        init();
        this.setTitle("饮料价格");
        this.setVisible(true);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setBounds(0, 0, 800, 600);
    }

    public static void main(String[] args) {
        CoffeeFrame coffeeFrame = new CoffeeFrame();
        coffeeFrame.setLayout(new FlowLayout(FlowLayout.LEFT, 30, 40));
    }

    public void init() {
        Font font = new Font("宋体", Font.BOLD, 18);
        radioButtonCoffe = new JRadioButton("咖啡 20");
        radioButtonMilkTea = new JRadioButton("奶茶 25");
        checkBoxMocha = new JCheckBox("摩卡 10");
        checkBoxPearl = new JCheckBox("珍珠 15");
        checkBoxWhip = new JCheckBox("奶泡 10");
        checkBoxChocolate = new JCheckBox("巧克力 20");
        labelPrice = new JLabel("价格：");
        totalPrice = new JLabel("0元");
        ingredient = new JLabel("成分");
        radioButtonCoffe.setFont(font);
        radioButtonMilkTea.setFont(font);
        checkBoxMocha.setFont(font);
        checkBoxPearl.setFont(font);
        checkBoxWhip.setFont(font);
        checkBoxChocolate.setFont(font);
        labelPrice.setFont(font);
        totalPrice.setFont(font);
        ingredient.setFont(font);
        radioButtonCoffe.addActionListener(this);
        radioButtonMilkTea.addActionListener(this);
        checkBoxMocha.addActionListener(this);
        checkBoxPearl.addActionListener(this);
        checkBoxWhip.addActionListener(this);
        checkBoxChocolate.addActionListener(this);
        radioButtonCoffe.getPreferredSize();
        add(radioButtonCoffe);
        add(radioButtonMilkTea);
        add(checkBoxMocha);
        add(checkBoxPearl);
        add(checkBoxWhip);
        add(checkBoxChocolate);
        add(labelPrice);
        add(totalPrice);
        add(ingredient);
        group = new ButtonGroup();
        group.add(radioButtonCoffe);
        group.add(radioButtonMilkTea);
    }
    public void Select() {
        checkBoxMocha.setSelected(false);
        checkBoxPearl.setSelected(false);
        checkBoxWhip.setSelected(false);
        checkBoxChocolate.setSelected(false);
    }
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(radioButtonCoffe)) {
            beverage = new Coffee();
            Select();
        }
        if (e.getSource().equals(radioButtonMilkTea)) {
            beverage = new MilkTea();
            Select();
        }
        if (e.getSource().equals(checkBoxMocha) && beverage != null) {
            beverage = new Mocha(beverage);
        }
        if (e.getSource().equals(checkBoxPearl) && beverage != null) {
            beverage = new Pearl(beverage);
        }
        if (e.getSource().equals(checkBoxWhip) && beverage != null) {
            beverage = new Whip(beverage);
        }
        if (e.getSource().equals(checkBoxChocolate) && beverage != null) {
            beverage = new Chocolate(beverage);
        }
        if (beverage != null) {
            sle = "";
            totalPrice.setText(Integer.toString(beverage.Cost()) + "元");
            for (int i = 0; i < 6; i++) {
                if (((beverage.select >> i) & 1) != 0)
                    sle += select[i];
            }
            ingredient.setText(sle);
        }
    }
}