/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sumaresta;

import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

/**
 *
 * @author gunarortiz
 */
public class SumaResta extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        
        GridPane grid = new GridPane();
        grid.setMinSize(500,500);
        grid.setVgap(10);
        grid.setHgap(5);
        grid.setPadding(new Insets(10, 10, 10, 10));
        
        
        Text txt1 = new Text("Ingrese la operacion, Ej: 1+2+3");
        grid.add(txt1, 0, 0);
        
        Text txt2 = new Text("Numero");
        grid.add(txt2, 0, 1);
        
        TextField text1 = new TextField();
        grid.add(text1, 1, 1);
        
        Text txt3 = new Text("");
        grid.add(txt3, 0, 4);
        
        Button btn = new Button();
        btn.setText("Suma Infija");
        btn.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                String numero1= text1.getText();
                
                ScriptEngineManager mgr = new ScriptEngineManager();
                ScriptEngine engine = mgr.getEngineByName("JavaScript");
                String foo = numero1;
                
                try {
                    txt3.setText("El resultado es:\nSuma Infija: " + numero1 + " = " + engine.eval(foo)+"\nSuma Prefija: "+ infixToPrefix(numero1) + " = " + engine.eval(foo) + "\nSuma Postfija: "+infixToPostfix(numero1) + " = " + engine.eval(foo));
                } catch (ScriptException ex) {
                    Logger.getLogger(SumaResta.class.getName()).log(Level.SEVERE, null, ex);
                }
              
            }
        });
        grid.add(btn, 0, 2);
        
        Scene scene = new Scene(grid, 300, 250);
        
        primaryStage.setTitle("Calculadora");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
    
    
    //prefix
    
    static boolean isOperator(char c) 
{ 
    return (!(c >= 'a' && c <= 'z') &&  
            !(c >= '0' && c <= '9') &&  
            !(c >= 'A' && c <= 'Z')); 
} 
  
static int getPriority(char C) 
{ 
    if (C == '-' || C == '+') 
        return 1; 
    else if (C == '*' || C == '/') 
        return 2; 
    else if (C == '^') 
        return 3; 
    return 0; 
} 


static String infixToPrefix(String infix) 
{ 
    Stack<Character> operators = new Stack<Character>(); 
  
    Stack<String> operands = new Stack<String>(); 
  
    for (int i = 0; i < infix.length(); i++)  
    { 
  
        if (infix.charAt(i) == '(')  
        { 
            operators.push(infix.charAt(i)); 
        } 
  
        else if (infix.charAt(i) == ')')  
        { 
            while (!operators.empty() &&  
                operators.peek() != '(')  
                { 
  
                String op1 = operands.peek(); 
                operands.pop(); 

                String op2 = operands.peek(); 
                operands.pop(); 
  
                char op = operators.peek(); 
                operators.pop(); 
   
                String tmp = op + op2 + op1; 
                operands.push(tmp); 
            } 
  
            operators.pop(); 
        } 
  
        else if (!isOperator(infix.charAt(i)))  
        { 
            operands.push(infix.charAt(i) + ""); 
        } 
        else 
        { 
            while (!operators.empty() &&  
                getPriority(infix.charAt(i)) <=  
                    getPriority(operators.peek()))  
                { 
  
                String op1 = operands.peek(); 
                operands.pop(); 
  
                String op2 = operands.peek(); 
                operands.pop(); 
  
                char op = operators.peek(); 
                operators.pop(); 
  
                String tmp = op + op2 + op1; 
                operands.push(tmp); 
            } 
  
            operators.push(infix.charAt(i)); 
        } 
    } 
  
    while (!operators.empty())  
    { 
        String op1 = operands.peek(); 
        operands.pop(); 
  
        String op2 = operands.peek(); 
        operands.pop(); 
  
        char op = operators.peek(); 
        operators.pop(); 
  
        String tmp = op + op2 + op1; 
        operands.push(tmp); 
    } 
  
    return operands.peek(); 
}


//postfix

static int Prec(char ch) 
    { 
        switch (ch) 
        { 
        case '+': 
        case '-': 
            return 1; 
       
        case '*': 
        case '/': 
            return 2; 
       
        case '^': 
            return 3; 
        } 
        return -1; 
    } 
       
    // The main method that converts given infix expression 
    // to postfix expression.  
    static String infixToPostfix(String exp) 
    { 
        // initializing empty String for result 
        String result = new String(""); 
          
        // initializing empty stack 
        Stack<Character> stack = new Stack<>(); 
          
        for (int i = 0; i<exp.length(); ++i) 
        { 
            char c = exp.charAt(i); 
              
             // If the scanned character is an operand, add it to output. 
            if (Character.isLetterOrDigit(c)) 
                result += c; 
               
            // If the scanned character is an '(', push it to the stack. 
            else if (c == '(') 
                stack.push(c); 
              
            //  If the scanned character is an ')', pop and output from the stack  
            // until an '(' is encountered. 
            else if (c == ')') 
            { 
                while (!stack.isEmpty() && stack.peek() != '(') 
                    result += stack.pop(); 
                  
                if (!stack.isEmpty() && stack.peek() != '(') 
                    return "Invalid Expression"; // invalid expression                 
                else
                    stack.pop(); 
            } 
            else // an operator is encountered 
            { 
                while (!stack.isEmpty() && Prec(c) <= Prec(stack.peek())){ 
                    if(stack.peek() == '(') 
                        return "Invalid Expression"; 
                    result += stack.pop(); 
             } 
                stack.push(c); 
            } 
       
        } 
       
        // pop all the operators from the stack 
        while (!stack.isEmpty()){ 
            if(stack.peek() == '(') 
                return "Invalid Expression"; 
            result += stack.pop(); 
         } 
        return result; 
    } 
    

}
