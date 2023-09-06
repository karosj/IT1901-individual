package karoshm.calc;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BinaryOperator;
import java.util.function.UnaryOperator;


public class Calc {

    private final List<Double> operandStack;

    public Calc(double... operands) {
        operandStack = new ArrayList<>(operands.length + 2);
        for (var d : operands) {
            operandStack.add(d);
        }
    }

    public int getOperandCount() {
        return operandStack.size();
    }

    public void pushOperand(double d) {
        operandStack.add(d);
    }

    public double peekOperand(int n) {
        if (n >= getOperandCount()) {
            throw new IllegalArgumentException(
                    "Cannot peek at position " + n + " when the operand count is " + getOperandCount());
        }
        return operandStack.get(getOperandCount() - n - 1);
    }

    public double peekOperand() {
        return peekOperand(0);
    }

    
    public double popOperand() {
        if (getOperandCount() == 0) {
            throw new IllegalStateException("Cannot pop from an empty stack");
        }
        return operandStack.remove(operandStack.size() - 1);
    }

    
    public double performOperation(UnaryOperator<Double> op) throws IllegalStateException {

        if (operandStack.isEmpty())
            throw new IllegalStateException("The operand stack is empty.");

        double op1 = popOperand();
        double result = op.apply(op1);
        pushOperand(result);

        return op.apply(op1);
    }

    public double performOperation(BinaryOperator<Double> op) throws IllegalStateException {
        if (getOperandCount() < 2) {
            throw new IllegalStateException("Too few operands (" + getOperandCount() + ") on the stack");
        }
        var op2 = popOperand();
        var op1 = popOperand();
        var result = op.apply(op1, op2);
        pushOperand(result);
        return result;
    }

    
    public void swap() {

        if (getOperandCount() < 2)
            throw new IllegalStateException("The operand stack is not big enough");

        double op1 = popOperand();
        double op2 = popOperand();

        pushOperand(op1);
        pushOperand(op2);
    }

    
    public void dup() {
        if (operandStack.isEmpty())
            throw new IllegalStateException("The operand stack is empty.");

        pushOperand(operandStack.get(0));
    }

    
}
