package Calculator;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CalculatorFrame extends JFrame implements ActionListener {
    JLabel resultLabel = new JLabel(); //结果存放处
    JTextField inputText = new JTextField("0"); //输入框
    JButton clearEmpty = new JButton("CE"); //清除当前输入按钮
    JButton clear = new JButton("C"); //清除所有输入按钮
    JButton backSpace = new JButton("BackSpace"); //删除按钮
    JButton leftPer = new JButton("("); //左括号按钮
    JButton rightPer = new JButton(")"); //右括号按钮
    JButton divisor = new JButton("/"); //除号按钮
    JButton multiply = new JButton("*"); //乘号按钮
    JButton minus = new JButton("-"); //减号按钮
    JButton plus = new JButton("+"); //加号按钮
    JButton posNeg = new JButton("+/-"); //正负号按钮
    JButton decimalPoint = new JButton("."); //小数点按钮
    JButton radicalSign = new JButton("√"); //开根号按钮
    JButton equal = new JButton("="); //等于号按钮
    JButton[] button =  new JButton[10]; //0~9按钮
    StringBuffer s1 = new StringBuffer("0"); //记录运算数字，以及保留结果
    StringBuffer s2 = new StringBuffer(); //记录运算数字，保留上一个输入的数字或运算结果
    boolean operator = false; //判断上次输入的是否为运算符
    boolean start = true; //标记运算开始或结束,保证一次运算之后，第二次进行运算时能同时清空显示界面，即s1为空
    public static void main(String[] args) {
        new CalculatorFrame();
    }
    CalculatorFrame() {
        init();
        setLayout(null);
        setTitle("计算器");
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(400, 100, 365, 420);
        setVisible(true);
    }
    public void init() {
        //字体
        Font font = new Font("Courier new", Font.PLAIN, 20);

        //最终结果标签
        resultLabel.setBounds(20, 20, 310, 25); //设置位置和宽高
        resultLabel.setFont(font); //添加字体样式
        add(resultLabel); //添加到窗体

        //输入框样式
        LineBorder lineBorder = new LineBorder(Color.lightGray, 3, true);
        inputText.setBorder(lineBorder); //添加样式
        inputText.setEditable(false); //文本框是否可编辑
        inputText.setBounds(20, 45, 310, 25); //设置位置和宽高
        inputText.setFont(font); //添加字体样式
        add(inputText); //添加到窗体

        //CE按钮样式
        clearEmpty.setBounds(20, 90, 70, 35);
        clearEmpty.setFont(font);
        add(clearEmpty);
        clearEmpty.addActionListener(this); //设置监听器

        //C按钮样式
        clear.setBounds(100, 90, 70, 35);
        clear.setFont(font);
        add(clear);
        clear.addActionListener(this);

        //BackSpace按钮样式
        backSpace.setBounds(180, 90, 150, 35);
        backSpace.setFont(font);
        add(backSpace);
        backSpace.addActionListener(this);

        //除号按钮样式
        divisor.setBounds(260, 135, 70, 35);
        divisor.setFont(font);
        add(divisor);
        divisor.addActionListener(this);

        //乘号按钮样式
        multiply.setBounds(260, 180, 70, 35);
        multiply.setFont(font);
        add(multiply);
        multiply.addActionListener(this);

        //减号按钮样式
        minus.setBounds(260, 225, 70, 35);
        minus.setFont(font);
        add(minus);
        minus.addActionListener(this);

        //加号按钮样式
        plus.setBounds(260, 270, 70, 35);
        plus.setFont(font);
        add(plus);
        plus.addActionListener(this);
        int i = 1;

        //0号按钮样式
        button[0] = new JButton("0");
        button[0].setBounds(20, 270, 70, 35);
        button[0].setFont(font);
        add(button[0]);
        button[0].addActionListener(this);

        //正负号按钮样式
        posNeg.setBounds(100, 270, 70, 35);
        posNeg.setFont(font);
        add(posNeg);
        posNeg.addActionListener(this);

        //小数点按钮样式
        decimalPoint.setBounds(180, 270, 70, 35);
        decimalPoint.setFont(font);
        add(decimalPoint);
        decimalPoint.addActionListener(this);

        //1~9按钮样式
        for (int j = 225; j >= 135; j -= 45) {
            for (int k = 20; k <= 180; k += 80) {
                button[i] = new JButton(Integer.toString(i));
                button[i].setBounds(k, j, 70, 35);
                button[i].setFont(font);
                button[i].addActionListener(this);
                add(button[i++]);
            }
        }

        //左括号按钮样式
        leftPer.setBounds(20, 315, 70, 35);
        leftPer.setFont(font);
        add(leftPer);
        leftPer.addActionListener(this);

        //右括号按钮样式
        rightPer.setBounds(100, 315, 70, 35);
        rightPer.setFont(font);
        add(rightPer);
        rightPer.addActionListener(this);

        //根号按钮样式
        radicalSign.setBounds(180, 315, 70, 35);
        radicalSign.setFont(font);
        add(radicalSign);
        radicalSign.addActionListener(this);

        //等于号按钮样式
        equal.setBounds(260, 315, 70, 35);
        equal.setFont(font);
        add(equal);
        equal.addActionListener(this);
    }
    //监听器
    public void actionPerformed(ActionEvent e) {
        //判断是否为按钮0
        if (e.getSource().equals(button[0])) {
            if (!s1.toString().equals("0") && !s1.toString().equals("-0") && s1.charAt(s1.length() - 1) != ')') { //')'后面不能直接跟数字
                if (!start || s1.charAt(0) == 'E') { //如果是开始新一轮的运算或是有错误提示
                    //s1清零，保证可以重新输入数字
                    s1.delete(0, s1.length());
                    s2.delete(0, s2.length());
                }
                start = true;
                s1.append("0");
                operator = false;
            }
        }

        //判断是否为1~9按钮
        for (int i = 1; i <= 9; i++) {
            if (e.getSource().equals(button[i])) {
                if (!start || s1.charAt(0) == 'E') { //如果是开始新一轮的运算或是有错误提示
                    s1.delete(0, s1.length()); //清空
                    s2.delete(0, s2.length());
                }
                start = true;
                if (s1.toString().equals("0") || s1.toString().equals("-0"))
                    s1.deleteCharAt(s1.length() - 1); //去除开始的0
                if (s1.length() == 0 || s1.charAt(s1.length() - 1) != ')')
                    s1.append(i); //')'后面不能直接跟数字
                operator = false; //输入的不是运算符
            }
        }

        //判断小数点
        if (e.getSource().equals(decimalPoint)) {
            if (!start || s1.charAt(0) == 'E') {
                s1.delete(0, s1.length());
                s1.append("0");
                s2.delete(0, s2.length());
            }
            start = true;
            if (Character.isDigit(s1.charAt(s1.length() - 1)) && s1.indexOf(".") == -1)
                s1.append("."); //如果前面是数字且没出现过小数点
            operator = false; //输入的不是运算符
        }

        //判断正负号
        if (e.getSource().equals(posNeg)) {
            if (s1.charAt(0) == 'E') //如果是错误提示
                return ;
            if (!start) { //如果开始新的运算
                s1.delete(0, s1.length());
                s1.append("0");
                s2.delete(0, s2.length());
            }
            start = true;
            int i;
            for (i = s1.length() - 1; i > 0; --i) //从前往后找，直到遇到不是数字或小数点，在其添加负号
                if (!Character.isDigit(s1.charAt(i)) && s1.charAt(i) != '.')
                    break;
            if (Character.isDigit(s1.charAt(i))) //如果都是数字
                s1.insert(i, '-');
            else  if (s1.charAt(i) != '-') //如果此处不是负号，添加负号
                s1.insert(i + 1, '-');
            else s1.deleteCharAt(i); //否则已经有负号，删除
            operator = false; //输入的不是运算符
        }

        //判断退格Backspace
        if (e.getSource().equals(backSpace)) {
            //start = true;
            s1.deleteCharAt(s1.length() - 1); //删除最后一个
            if (s1.length() == 0)
                s1.append("0");
            operator = false;
        }

        //判断归零CE
        if (e.getSource().equals(clearEmpty)) {
            //清空当前输入，即s1清零
            //start = true;
            s1.delete(0, s1.length()); //清空
            s1.append("0");
            operator = false;
        }

        //判断清除C
        if (e.getSource().equals(clear)) {
            //清空所有，start标记设为true
            start = true;
            s1.delete(0, s1.length());
            s2.delete(0, s2.length());
            s1.append("0");
            operator = false;
        }

        //判断加号
        if (e.getSource().equals(plus)) {
            if (s1.charAt(0) == 'E') //如果是错误提示，无操作
                return ;
            if (!start)
                s2.delete(0, s2.length());
            //运算符前面不能是做括号和小数点，自动舍去
            while (s1.length() > 0 && (s1.charAt(s1.length() - 1) == '(' || s1.charAt(s1.length() - 1) == '.')) {
                s1.deleteCharAt(s1.length() - 1);
            }
            //如果上次是运算符，那么就可以替换上次输入的运算符
            if (operator && s2.length() > 0) {
                s2.deleteCharAt(s2.length() - 1);
                s2.append("+");
            }
            else if (s1.length() > 0) //如果s1还有数据
                s2.append(s1.toString() + "+");
            //s1清零，重新接收下一个数据
            s1.delete(0, s1.length());
            s1.append("0");
            start = true;
            operator = true;
        }

        //判断减号
        if (e.getSource().equals(minus)) {
            if (s1.charAt(0) == 'E')
                return ;
            if (!start)
                s2.delete(0, s2.length());
            //运算符前面不能是做括号和小数点，自动舍去
            while (s1.length() > 0 && (s1.charAt(s1.length() - 1) == '(' || s1.charAt(s1.length() - 1) == '.')) {
                s1.deleteCharAt(s1.length() - 1);
            }
            //如果上次是运算符，那么就可以替换上次输入的运算符
            if (operator && s2.length() > 0) {
                s2.deleteCharAt(s2.length() - 1);
                s2.append("-");
            }
            else if (s1.length() > 0) //如果s1还有数据
                s2.append(s1.toString() + "-");
            s1.delete(0, s1.length());
            s1.append("0");
            start = true;
            operator = true;
        }

        //判断乘号
        if (e.getSource().equals(multiply)) {
            if (s1.charAt(0) == 'E')
                return ;
            if (!start)
                s2.delete(0, s2.length());
            //运算符前面不能是做括号和小数点，自动舍去
            while (s1.length() > 0 && (s1.charAt(s1.length() - 1) == '(' || s1.charAt(s1.length() - 1) == '.')) {
                s1.deleteCharAt(s1.length() - 1);
            }
            //如果上次是运算符，那么就可以替换上次输入的运算符
            if (operator && s2.length() > 0) {
                s2.deleteCharAt(s2.length() - 1);
                s2.append("*");
            }
            else if (s1.length() > 0) //如果s1还有数据
                s2.append(s1.toString() + "*");
            s1.delete(0, s1.length());
            s1.append("0");
            start = true;
            operator = true;
        }

        //判断除号
        if (e.getSource().equals(divisor)) {
            if (s1.charAt(0) == 'E')
                return ;
            if (!start)
                s2.delete(0, s2.length());
            //运算符前面不能是做括号和小数点，自动舍去
            while (s1.length() > 0 && (s1.charAt(s1.length() - 1) == '(' || s1.charAt(s1.length() - 1) == '.')) {
                s1.deleteCharAt(s1.length() - 1);
            }
            //如果上次是运算符，那么就可以替换上次输入的运算符
            if (operator && s2.length() > 0) {
                s2.deleteCharAt(s2.length() - 1);
                s2.append("/");
            }
            else if (s1.length() > 0) //如果s1还有数据
                s2.append(s1.toString() + "/");

            s1.delete(0, s1.length());
            s1.append("0");
            start = true;
            operator = true;
        }

        //判断左括号
        if (e.getSource().equals(leftPer)) {
            if (!start || s1.charAt(0) == 'E') { //开始新一轮的运算或是错误信息
                s1.delete(0, s1.length()); //清空
                s1.append("0");
                s2.delete(0, s2.length());
            }
            start = true;
            //只能在初始状态的时候或者左边为左括号的时候才能添加左括号
            if (s1.toString().equals("0") || s1.toString().equals("-0")) { //如果是初始状态
                s1.deleteCharAt(s1.length() - 1);
                s1.append("(");
            }
            else if (s1.charAt(s1.length() - 1) == '(') //
                s1.append("(");
            operator = false;
        }

        //判断右括号
        if (e.getSource().equals(rightPer)) {
            if (!start || s1.charAt(0) == 'E') {
                s1.delete(0, s1.length());
                s1.append("0");
                s2.delete(0, s2.length());
            }
            start = true;
            //前面必须是数字或右括号才能添加
            if (Character.isDigit(s1.charAt(s1.length() - 1)) || s1.charAt(s1.length() - 1) == ')')
                s1.append(")");
            operator = false;
        }

        //判断根号
        if (e.getSource().equals(radicalSign)) {
            if (s1.charAt(0) == 'E')
                return ;
            start = false;
            //删除无用的数据
            while (s1.length() > 0 && (s1.charAt(s1.length() - 1) == '(' || s1.charAt(s1.length() - 1) == '.')) {
                s1.deleteCharAt(s1.length() - 1);
            }
            if (s1.length() == 0)
                s1.append("0");
            String str = new Calculator(s1.toString()).Calculated();//先计算出s1中的表达式
            s2.delete(0, s2.length());
            s2.append(s1);
            s1.delete(0, s1.length());
            s2.insert(0, "√(");
            if (str.equals("Error!") || Double.parseDouble(str) < 0) //如果表达式有错误或结果为负数
                s1.append("Error!");
            else {
                double s = Double.parseDouble(str);
                s1.append(Math.sqrt(s));
            }
            s2.append(")");
            operator = false;
        }

        //判断等号
        if (e.getSource().equals(equal)) {
            if (s1.charAt(0) == 'E')
                return ;
            start = false;
            //删除无用的数据
            while (s1.length() > 0 && (s1.charAt(s1.length() - 1) == '(' || s1.charAt(s1.length() - 1) == '.')) {
                s1.deleteCharAt(s1.length() - 1);
            }
            //如果s1为空，补0
            if (s1.length() == 0)
                s1.append("0");
            String str = new Calculator(s2.append(s1.toString()).toString()).Calculated(); //计算
            if (!str.equals("Error!")) //不是错误信息
                s2.append( "=" + str);
            else s2.append("=");
            s1.delete(0, s1.length());
            s1.append(str);
            operator = false;
        }
        inputText.setText(s1.toString()); //添加到输入框
        resultLabel.setText(s2.toString()); //添加到结果标签
    }
}
