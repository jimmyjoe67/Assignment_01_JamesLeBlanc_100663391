/* James LeBlanc : 100663391
 * Question number four of assignment one for CSCI-2020U
 * 
 * This program displays a histogram of all of the different
 * letters in a file given by the user 
 */

package assignment_01;

import java.io.*;
import java.util.Scanner;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class Question_04 extends Application{

	private final String[] Alphabet = {"A","B","C","D","E","F","G","H","I","J","K","L","M",
			   						   "N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		//setting up initial panes
		BorderPane pane = new BorderPane();
		pane.setPadding(new Insets(10,10,10,10));
		HBox box = new HBox();
		Pane pn = new Pane();
		
		//width and height that will be used for the bars of the graph
		int width = 10; int height = 200;
		
		//alpha count = amount of each letter
		int[] alphaCount = new int[26];
		//letters = A,B,C...., used for display on histogram
		Text[] letters = new Text[26];
		for(int i = 0; i < 26; i++) {
			letters[i] = new Text((width+5)*i,height+10, Alphabet[i]);
			alphaCount[i] = 0;
		}
		
		//bars = bars of the histogram
		Rectangle[] bars = makeBars(alphaCount, height, width);

		//setting up the pieces need for the user input
		TextField fileSelect = new TextField();
		fileSelect.setPrefColumnCount(25);
		Button sel = new Button("select");
		Button load = new Button("Load");
		load.setDefaultButton(true);
		box.getChildren().addAll(fileSelect, sel, load);
		
		//when select is pressed, the program will bring up a file select screen,
		//allowing the user to navigate through their files and select one of their 
		//choice
		//The file path is then placed into the text field, allowing the user
		//to load that file chosen
		sel.setOnAction(new EventHandler<ActionEvent>(){

			public void handle(ActionEvent arg0) {
				 FileChooser fileChooser = new FileChooser();
				 fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
				 FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
	             fileChooser.getExtensionFilters().add(extFilter);
	             File file = fileChooser.showOpenDialog(primaryStage);
	             if(file != null)
	            	 fileSelect.setText(file.getAbsolutePath());
			}
			
		});
		
		//When load is pressed, the text in the text field is tested to see
		//if what is given is actually a path to a text file or not
		//if it is, then methods will be called, allowing the histogram
		//to update, displaying the letter count for the file
		load.setOnAction(new EventHandler<ActionEvent>() {

			public void handle(ActionEvent arg0) {
				File file = new File(fileSelect.getText());
				if(file.exists()) {
					try {
						Rectangle[] temp = makeBars(countLetters(file), height, width);
						for(int i = 0; i < 26; i++) {
							bars[i].setX(temp[i].getX());
							bars[i].setY(temp[i].getY());
							bars[i].setWidth(temp[i].getWidth());
							bars[i].setHeight(temp[i].getHeight());
						}
						
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					}
				}
			}
			
		});
		
		pn.getChildren().addAll(bars);
		pn.getChildren().addAll(letters);
		pn.getChildren().add(new Line(0,height,(width+5)*26,height));
		
		pane.setTop(pn);
		pane.setRight(box);
		
		Scene scene = new Scene(pane,(width+5)*26 + 20, height+60);
	    primaryStage.setTitle("Question 04"); // Set the stage title
	    primaryStage.setScene(scene); // Place the scene in the stage
	    primaryStage.show(); // Display the stage
	}

	//This method reads in the file given line by line
	//and counts the different letters inside it, it then return
	//the final tally
	private int[] countLetters(File file) throws FileNotFoundException {
		Scanner input = new Scanner(file);
		int[] letters = new int[26];
		
		while(input.hasNext()) {
			String line = input.nextLine();
			for(int x = 0; x < line.length(); x++) {
				char letter = line.charAt(x);
				for(int y = 0; y < 26; y++) {
					if((int)letter == 65+y || (int)letter == 97+y) {
						letters[y]++;
						break;
					}
				}
			}
		}
		input.close();
		
		return letters;
	}
	
	//This function makes the bars of the histogram and returns the bars 
	//created
	private Rectangle[] makeBars(int[] alphaCount, int height, int width) {
		Rectangle[] bars = new Rectangle[26];
		
		double max = 0;
		for(int i:alphaCount) {
			if(i > max) max = i;
		}
		
		for(int i = 0; i < 26; i++) {
			double indHeight = (height-10)*((double)alphaCount[i]/max);
			bars[i] = new Rectangle(15*i,height-indHeight,width,indHeight);
			bars[i].setFill(Color.WHITE);
			bars[i].setStroke(Color.BLACK);
		}
		
		return bars;
	}
	
}
