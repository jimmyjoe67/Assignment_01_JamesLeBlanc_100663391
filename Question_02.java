/* James LeBlanc : 100663391
 * Question number two of assignment one for CSCI-2020U
 * 
 * This program takes displays a pane and allow the user
 * to enter in three values; investment amount, years,
 * and annual interest rate, and then this program will then
 * calculate and display the Future Value
 */

package assignment_01;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Question_02 extends Application{

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		//setting up panes for the display
		BorderPane pane = new BorderPane();
		pane.setPadding(new Insets(10,10,10,10));
		GridPane gp = new GridPane();
		gp.setPadding(new Insets(0,0,10,0));
		gp.setHgap(5);
		
		//setting up different labels for later use
		Label[] labels = new Label[4];
		labels[0] = new Label("Investment Amount");
		labels[1] = new Label("Years");
		labels[2] = new Label("Annual Intrest Rate");
		labels[3] = new Label("Future Value");
		gp.addColumn(0, labels);
		
		//sets up all of the different text fields for the
		//user to use
		TextField[] text = new TextField[4];
		for(int x = 0; x < 4; x++) {
			text[x] = new TextField("");
			text[x].setAlignment(Pos.CENTER_RIGHT);
		}
		text[3].setEditable(false);
		gp.addColumn(1, text);
		
		Button btn = new Button("Calculate");
		btn.setDefaultButton(true);
		
		//main functionality:
		//when pressed this takes all of the different 
		//numbers entered by the user and calculates the future value
		btn.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {
				double IA = Double.parseDouble(text[0].getText());
				double years = Double.parseDouble(text[1].getText());
				double MIR = Double.parseDouble(text[2].getText()) / (12.0*100);
				
				text[3].setText(Double.toString(IA * Math.pow(1+MIR,years*12.0)));
			}
			
		});
		
		pane.setTop(gp);
		pane.setRight(btn);
		
		
	    Scene scene = new Scene(pane,280,150);
	    primaryStage.setTitle("Question 02"); // Set the stage title
	    primaryStage.setScene(scene); // Place the scene in the stage
	    primaryStage.show(); // Display the stage
		
	}

}
