/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sumaresta;

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
        
        
        Text txt1 = new Text("Numero 1");
        grid.add(txt1, 0, 0);
        
        TextField text1 = new TextField();
        grid.add(text1, 1, 0);
        
        Text txt2 = new Text("Numero 2");
        grid.add(txt2, 0, 1);
        
        TextField text2 = new TextField();
        grid.add(text2, 1, 1);
        
        Text txt3 = new Text("");
        grid.add(txt3, 0, 4);
        
        Button btn = new Button();
        btn.setText("Suma Infija");
        btn.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                
                int suma = Integer.parseInt(text1.getText()) + Integer.parseInt(text2.getText());
                txt3.setText("Suma Infija\n el resultado es: " +suma);
                System.out.println("El resultado es: " +suma);
            }
        });
        grid.add(btn, 0, 2);
        
        
        
        Button btn1 = new Button();
        btn1.setText("Suma Prefija");
        btn1.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                
                int suma = Integer.parseInt(text1.getText()) + Integer.parseInt(text2.getText());
                txt3.setText("Suma Prefija, el resultado es: " +suma);
                System.out.println("El resultado es: " +suma);
            }
        });
        grid.add(btn1, 1, 2);
        
        
        
        
        
        Button btn2 = new Button();
        btn2.setText("Suma Sufija");
        btn2.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                
                int suma = Integer.parseInt(text1.getText()) + Integer.parseInt(text2.getText());
                txt3.setText("Suma Sufija, el resultado es: " +suma);
                System.out.println("El resultado es: " +suma);
            }
        });
        grid.add(btn2, 0, 3);
        
        
        
        
        
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
    
}
