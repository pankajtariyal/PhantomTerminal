package com.phantomterminal.commands.tokenizer;

import javax.xml.stream.events.Characters;
import java.util.ArrayList;
import java.util.List;

public class Tokenize {

    public static List<String> getTokenizer(String expression){

        char[] charArray = expression.toCharArray();
        StringBuilder number = new StringBuilder();
        List<String> tokens = new ArrayList<>();
        for(char ch: charArray){

            if(Character.isDigit(ch) || ch == '.'){
                number.append(ch);
            }else{
                if(number.length()>0){
                    tokens.add(number.toString());
                    number.setLength(0);
                }
                tokens.add(String.valueOf(ch));
            }
        }

        if(number.length()>0){
            tokens.add(number.toString());
        }
        return tokens;
    }
}
