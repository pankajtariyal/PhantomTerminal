package com.phantomterminal.commands.calculator;

import com.phantomterminal.commands.tokenizer.Tokenize;
import com.phantomterminal.common.CommonVariable;
import com.phantomterminal.common.calculatorUtil.CalculatorUtil;
import com.phantomterminal.common.calculatorUtil.CalculatorUtilImp;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Stack;

public class AllCalculationOperation implements CalculatorCommand{
    private final CalculatorUtil calculatorUtil;

    public AllCalculationOperation(){
        calculatorUtil = CalculatorUtilImp.getInstance();
    }

    @Override
    public void execute(List<String> args) throws IOException {
        if(args.size()<2){
            CommonVariable.outputAreaCommon.appendText("cal missing operand");
            return;
        }

        String expression = String.join("",args.subList(1,args.size()));
        System.out.println(expression);
        List<String> tokenizer = Tokenize.getTokenizer(expression);
        System.out.println(tokenizer);

        Stack<BigDecimal> number = new Stack<>();
        Stack<String> operator = new Stack<>();
        for(int i=0;i<tokenizer.size();i++){
            String token = tokenizer.get(i);

            if(calculatorUtil.isNumber(token)){
                number.add(new BigDecimal(token));
            }
            else if (token.equalsIgnoreCase("(")){
                operator.add(token);
            }else if (token.equals(")")){
                while (!operator.peek().equals("(")){
                    calculatorUtil.performOperation(number,operator.pop());
                }
                if(!operator.isEmpty()){
                    operator.pop();
                }
            }else if(calculatorUtil.isOperator(token)){
                while (!operator.isEmpty() &&
                        calculatorUtil.operatorPrecedence(operator.peek()) >= calculatorUtil.operatorPrecedence(token)){
                    calculatorUtil.performOperation(number,operator.pop());
                }
                operator.push(token);
            }
        }

        while (!operator.isEmpty()){
            calculatorUtil.performOperation(number,operator.pop());
        }
        BigDecimal result = number.pop();
        CommonVariable.outputAreaCommon.appendText(result.toString()+"\n");
    }

//    private boolean isOperator(String token) {
//        return token.equals("+") || token.equals("-") || token.equals("*") || token.equals("/");
//    }
//
//    private boolean inNumber(String val) {
//        try{
//            new BigDecimal(val);
//            return true;
//        }catch (Exception e){
//            return false;
//        }
//    }

//    private int precedence(String operator){
//        switch (operator){
//            case "+":
//            case "-": return 1;
//
//            case "*":
//            case "/": return 2;
//        }
//        return 0;
//    }

    private void performOperation(Stack<BigDecimal> numbers,String exp){
        if(numbers.isEmpty())return;

        BigDecimal num2 = numbers.pop();
        BigDecimal num1 = numbers.pop();
        BigDecimal result = null;
        switch (exp){
            case "+": result =  num1.add(num2);
            break;
            case "-":result = num1.subtract(num2);
            break;
            case "*":result = num1.multiply(num2);
            break;
            case "/":result = num1.divide(num2);
            break;
        }
        numbers.push(result);
    }
}
