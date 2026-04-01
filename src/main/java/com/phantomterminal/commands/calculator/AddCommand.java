package com.phantomterminal.commands.calculator;

import com.phantomterminal.common.CommonVariable;
import com.phantomterminal.common.calculatorUtil.CalculatorUtil;
import com.phantomterminal.common.calculatorUtil.CalculatorUtilImp;

import java.math.BigDecimal;
import java.util.List;
import java.util.Stack;

public class AddCommand implements CalculatorCommand {
    private final CalculatorUtil calculatorUtil;
    public AddCommand(){
        super();
        this.calculatorUtil = CalculatorUtilImp.getInstance();
    }
    @Override
    public void execute(List<String> args){
        if(args.size()<3){
            CommonVariable.outputAreaCommon.appendText("error: add missing operand\n");
            return;
        }
        List<String> tokens = args.subList(1,args.size());
        System.out.println(tokens);
        Stack<BigDecimal> numbers = new Stack<>();
        for(int i=0; i<tokens.size(); i++){
            String token = tokens.get(i);
            if(calculatorUtil.isNumber(token)){
                System.out.println(token);
                numbers.add(new BigDecimal(token));
            }else{
                CommonVariable.outputAreaCommon.appendText("warning: div: only number allowed with spaces");
            }
        }
        while (!numbers.isEmpty() && numbers.size()!=1){
            calculatorUtil.performOperation(numbers,"+");
        }
        BigDecimal value = numbers.pop();
        CommonVariable.outputAreaCommon.appendText(value+"\n");
    }
}
