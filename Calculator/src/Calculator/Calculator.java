package Calculator;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Stack;

public class Calculator {
    private String expression; //存储表达式
    private Map<String, Integer> priority = new HashMap<>();
    /**
     * @构造方法
     * @param expression
     */
    public Calculator(String expression) {
        init();
        this.expression = expression;
    }
    /**
     * @初始化
     */
    public void init() {
        priority.put("/", 1);
        priority.put("*", 1);
        priority.put("-", 0);
        priority.put("+", 0);
        priority.put("(", -1);
        priority.put(")", -1);
        priority.put("#", -2);
    }
    /**
     * @表达式的合法性检测
     * @return boolean
     */
    public boolean Check() {
        //首先在负号前面补0
        for (int i = 0; i < expression.length(); i++) {
            if (expression.charAt(i) == '-' && ((i > 0 && !Character.isDigit(expression.charAt(i - 1))) || i == 0)) {
                StringBuilder exp = new StringBuilder(expression);
                exp.insert(i, "0");
                expression = exp.toString();System.out.println(expression);
            }
        }
        //判断括号是否匹配，'('加1，')'减1
        int backets = 0;
        //从左向右扫描
        for (int i = 0; i < expression.length(); i++) {
            switch (expression.charAt(i)) {
                case '(': ++backets; break; //'('加1
                case ')': --backets; //')'减1
                    if (backets < 0) //中间出现'('少于')'的情况
                        return false;
                    break;
                case '/':
                case '*':
                case '+':
                    if (i == 0) return false; // '/'、'*'、'+'不能出现首位
                    if (i > 0 && (expression.charAt(i - 1) == '/' || expression.charAt(i - 1) == '*' || expression.charAt(i - 1) == '+' || expression.charAt(i - 1) == '-' || expression.charAt(i - 1) == '('))
                        return false; //运算符不能连续出现且前面不能为'('
                case '-':
                    if (i == expression.length() - 1) return false; //运算符不能出现在尾部
                    if (expression.charAt(i + 1) == '/' || expression.charAt(i + 1) == '*' || expression.charAt(i + 1) == '+' || expression.charAt(i + 1) == '-' || expression.charAt(i + 1) == ')')
                        return false; //运算符不能连续出现且后面不能为')'
            }
        }
        if (backets != 0) //如果括号不匹配
            return false;
        return true;
    }

    /**
     * @中缀表达式转换为后缀表达式
     * @return Stack
     */
    public Stack<String> Conversion() {
        Stack<String> s1 = new Stack<>();
        Stack<String> s2 = new Stack<>();
        s1.push("#"); //将最低优先级的#符号放入S1栈，为了方便统一后续操作
        for (int i = 0; i < expression.length(); i++) { //循环遍历表达式
            switch (expression.charAt(i)) {
                case '(': s1.push("("); break; //读取到左括号，直接压入S1栈
                case ')': //若取出的字符是")"，则将距离S1栈栈顶最近的"("之间的运算符，逐个出栈，依次送入S2栈，此时抛弃"("。
                    while (!s1.peek().equals("(")) {
                        s2.push(s1.pop());
                    }
                    s1.pop(); break;
                case '/':
                case '*':
                case '-':
                case '+':
                    /*
                    * 若取出的字符是运算符，则将该运算符与S1栈栈顶元素比较，
                    * 如果该运算符优先级(不包括括号运算符)大于S1栈栈顶运算符优先级，则将该运算符进S1栈，
                    * 否则，将S1栈的栈顶运算符弹出，送入S2栈中，直至S1栈栈顶运算符低于(不包括等于)该运算符优先级，
                    * 最后将该运算符送入S1栈。
                    * */
                    if (priority.get(expression.charAt(i) + "") > priority.get(s1.peek())) {
                        s1.push(expression.charAt(i) + "");
                    }
                    else {
                        while (!(priority.get(expression.charAt(i) + "") > priority.get(s1.peek()))) {
                            s2.push(s1.pop());
                        }
                        s1.push(expression.charAt(i) + "");
                    }
                    break;
                default:
                    //若取出的字符是操作数，则分析出完整的运算数
                    StringBuilder num = new StringBuilder();
                    while (Character.isDigit(expression.charAt(i)) || expression.charAt(i) == '.') {
                        num.append(expression.charAt(i));
                        if (i + 1 < expression.length() && (Character.isDigit(expression.charAt(i + 1)) || expression.charAt(i + 1) == '.'))
                            ++i;
                        else break;
                    }
                    //该操作数直接送入S2栈
                    s2.push(num.toString()); break;
            }
        }
        //将S1栈内所有运算符(不包括"#")，逐个出栈，依次送入S2栈。
        while (!s1.peek().equals("#"))
            s2.push(s1.pop());
        //由于栈的特性，S2应做一下逆序处理
        Stack<String> stack = new Stack<>();
        while (!s2.empty()) {
            stack.push(s2.pop());
        }
        return stack; //返回S2的逆序栈
    }

    /**
     * @逆波兰式的求值
     * @return String
     */
    public String Calculated() {
        if (!this.Check()) //合法性检验
            return "Error!"; //不合法返回"Error!"
        Stack<String> stack = Conversion(); //得到逆波兰表达式
        Stack<Double> tmp = new Stack<>(); //声明一个栈
        while (!stack.empty()) {
            String s = stack.pop(); //取出逆波兰中的值
            if (Character.isDigit(s.charAt(0))) //如果是操作数
                tmp.push(Double.parseDouble(s)); //直接进入tmp栈
            else {//如果是运算符，取出两个数进行计算
                double b = tmp.pop();
                double a = tmp.pop();
                switch (s) {
                    case "/":
                        if (b == 0.0) //如果除数为0，报错
                            return "Error!";
                        tmp.push(Div(a, b)); break;
                    case "*": tmp.push(Mul(a, b)); break;
                    case "-": tmp.push(Sub(a, b)); break;
                    case "+": tmp.push(Add(a, b)); break;
                }
            }
        }
        return tmp.pop().toString();
    }
    public double Div(double a, double b) {
        BigDecimal a1 = new BigDecimal(a);
        BigDecimal b1 = new BigDecimal(b);
        return a1.divide(b1, 5, BigDecimal.ROUND_HALF_UP).doubleValue();
    }
    public double Mul(double a, double b) {
        return a * b;
    }
    public double Sub(double a, double b) {
        return a - b;
    }
    public double Add(double a, double b) {
        return a + b;
    }
    public static void main(String[] args) {
        System.out.print("输入算式：");
        Scanner scanner = new Scanner(System.in);
        String express = scanner.nextLine();
        Calculator calculator = new Calculator(express);
        System.out.println(calculator.Calculated());
    }
}