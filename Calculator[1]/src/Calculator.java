import java.math.BigDecimal;
import java.util.Scanner;

public class Calculator {
    private String expression;
    public static void main(String[] args) {
        System.out.print("输入算式：");
        Scanner scanner = new Scanner(System.in);
        String express = scanner.nextLine();
        Calculator calculator = new Calculator(express);
        System.out.println(calculator.Calculated());
    }
    public Calculator(String expression) {
        this.expression = expression;
    }
    public String Calculated() {
        if (expression == null || expression.trim().equals("")) { //如果算式为空，返回"0"
            return "0";
        }
        int a1 = expression.indexOf("+"); //求出求一个"+"的位置
        int a2 = expression.indexOf("-"); //求出求一个"-"的位置
        int a3 = expression.indexOf("*"); //求出求一个"*"的位置
        int a4 = expression.indexOf("/"); //求出求一个"/"的位置
        int a5 = expression.indexOf("("); //求出求一个"("的位置
        int a6 = expression.lastIndexOf(")"); //求出最后一个")"的位置
        //如果都不存在
        if (a1 == -1 && a2 == -1 && a3 == -1 && a4 == -1 && a5 == -1 && a6 == -1) {
            if (expression.trim() == null || expression.trim().equals("")) { //如果为空，返回Error!
                return "Error";
            }
            return expression.trim(); //直接返回
        }
        if (a5 != -1 && a6 > a5) { //存在配对的括号
            //计算出括号里面的内容
            String str = new Calculator(expression.substring(a5 + 1, a6).trim()).Calculated();
            if (str.equals("Error!")) {//如果返回"Error!"，有可能是()()这种形式
                a6 = expression.indexOf(")"); //得到第一个")"位置
                if (a6 > a5) {//如果右括号在左括号的右边
                    //计算出括号里面的内容
                    str = new Calculator(expression.substring(a5 + 1, a6).trim()).Calculated();
                    if (str.equals("Error!")) //如果得到"Error!"
                        return str; //直接返回
                }
                else return "Error!";
            }
            expression = expression.replace(expression.substring(a5, a6 + 1), str);
            return new Calculator(expression).Calculated();
        }
        else if (a5 != -1 || a6 != -1) //括号不匹配
            return "Error!";
        if (a1 != -1) { //有加号
            String str1 = new Calculator(expression.substring(0, a1)).Calculated();
            String str2 = new Calculator(expression.substring(a1 + 1, expression.length())).Calculated();
            //如果其中一个返回"Error!"，直接返回"Error!"
            if (str1.equals("Error!") || str2.equals("Error!"))
                return "Error!";
            //计算
            return String.valueOf(Double.parseDouble(str1) + Double.parseDouble(str2));
        }
        if (a2 != -1) { //有减号
            String str1 = new Calculator(expression.substring(0, a2)).Calculated();
            String str2 = new Calculator(expression.substring(a2 + 1, expression.length())).Calculated();
            //如果其中一个返回"Error!"，直接返回"Error!"
            if (str1.equals("Error!") || str2.equals("Error!"))
                return "Error!";
            //计算
            return String.valueOf(Double.parseDouble(str1) - Double.parseDouble(str2));
        }
        if (a3 != -1) { //有乘号
            String str1 = new Calculator(expression.substring(0, a3)).Calculated();
            String str2 = new Calculator(expression.substring(a3 + 1, expression.length())).Calculated();
            //如果其中一个返回"Error!"，直接返回"Error!"
            if (str1.equals("Error!") || str2.equals("Error!"))
                return "Error!";
            //计算
            return String.valueOf(Double.parseDouble(str1) * Double.parseDouble(str2));
        }
        if(a4 != -1) { //有除号
            String str1 = new Calculator(expression.substring(0, a4)).Calculated();
            String str2 = new Calculator(expression.substring(a4 + 1, expression.length())).Calculated();
            //如果其中一个返回"Error!"或除数为0，直接返回"Error!"
            if (str1.equals("Error!") || str2.equals("Error!") || Double.parseDouble(str2) == 0.0)
                return "Error!";
            //舍入计算
            BigDecimal a = new BigDecimal(Double.parseDouble(str1));
            BigDecimal b = new BigDecimal(Double.parseDouble(str2));
            return a.divide(b, 5, BigDecimal.ROUND_HALF_UP).toString();
        }
        return expression.trim();
    }
}