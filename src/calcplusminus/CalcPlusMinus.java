package calcplusminus;

import linkedlistdiscussion.LinkedList;

public class CalcPlusMinus {
    LinkedList<Integer> values;
    LinkedList<String> operands;
    Boolean dirty;
    int cachedTotal = 0;
    
    public CalcPlusMinus() {
        values = new LinkedList<>();
        operands = new LinkedList<>();
        dirty = false;
        cachedTotal = 0;
    }

    private Boolean isValidExpression () {
        return (values.size() >= 2 && values.size() == (operands.size()+1));
    }
    
    public void parseExpression(String expression) throws Exception {
        
        String[] expressionElements = expression.split(" ");

        LinkedList<Integer> tempValues = new LinkedList<>();
        LinkedList<String> tempOperands = new LinkedList<>();
        
        for (String value : expressionElements) {
            if(value.matches("^[0-9]+$")) {
                tempValues.addTail(Integer.parseInt(value));
            } else if(value.matches("^[+-]{1}$")) {
                tempOperands.addTail(value);
            }
        }
        
        values = tempValues;
        operands = tempOperands;
        dirty = true;
    }
    
    public int evaluate() throws Exception {

        if(!isValidExpression()) {
            throw(new Exception("values and operands mismatch"));
        }
        
        int accumulator = 0;
        String operand;
        int valueIndex = 0;
        for(int i = 0; i < operands.size(); i++) {
            operand = operands.get(i);
            if( valueIndex == 0) {
                if(operand.equals("+")) {
                    accumulator = values.get(valueIndex) + values.get(valueIndex + 1);
                } else if(operand.equals("-")) {
                    accumulator = values.get(valueIndex) - values.get(valueIndex + 1);
                }
                valueIndex += 2;
            } else {
                if(operand.equals("+")) {
                    accumulator = accumulator + values.get(valueIndex);
                } else if(operand.equals("-")) {
                    accumulator = accumulator - values.get(valueIndex);
                }
                valueIndex += 1;
            }
        }
        dirty = false;
        cachedTotal = accumulator;
        return accumulator;
    }
    
    public Boolean isDirty() {
        return dirty;
    }
    
    @Override
    public String toString() {
        StringBuilder expression = new StringBuilder();
        
        if(!dirty) {
            expression.append(cachedTotal);
        } else if(dirty && cachedTotal != 0) {
            expression.append(cachedTotal).append("?");
        } else {
            expression.append("x");
        }
        
        expression.append(expressionToString());
        
        return expression.toString();
    }
    
    private String expressionToString() {
        
        if(!isValidExpression()) {
            return "";
        }
        
        String expression = " = ";
        int i;
        for(i = 0; i < operands.size(); i++) {
            expression += values.get(i).toString() + " " + operands.get(i) + " ";
        }
        expression += values.get(i+1);
        
        return expression;
    }
}
