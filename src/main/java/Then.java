package main.java;

import javafx.util.Pair;

/**
 * Created by arsen on 09.09.2016.
 */
public class Then {
    protected Triple variable1;
    protected Triple variable2;
    protected String operator;

    public Then(Triple variable1, String operator, Triple variable2) {
        this.variable1 = variable1;
        this.variable2 = variable2;
        this.operator = operator;
    }


    public Then(String variable1, String operator, String variable2) {

        if(Variables.instantiate().IsConstWords(variable1)){

            this.variable1 = new Triple(variable1, variable1, Variables.categories.constant);
        }

        if (this.variable1 == null) {
            this.variable1 = Variables.instantiate().GetFirst(variable1);
        }

        this.variable2 = Variables.instantiate().GetFirst(variable2);
        if (this.variable1 == null) {
            System.out.println("Error createing then obj. Variable \"" + variable1 + "\" not found");

        }
        if (this.variable2 == null) {
            this.variable2 = new Triple(variable2, variable2, Variables.categories.constant);
        }
        this.operator = operator;
    }


    public String getOperator() {
        return operator;
    }

    public Triple getVariable1() {
        return variable1;
    }

    public Triple getVariable2() {
        return variable2;
    }

}
