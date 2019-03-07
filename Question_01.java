/*James LeBlanc : 100663391
 * Question number one of assignment one for CSCI-2020U
 * 
 * This program takes 3 random pictures of cards from the
 * filename given and adds them to the screen
 */

package assignment_01;

import java.util.Random;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Question_01 extends Application{

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		Random rand = new Random();
		//the FOLDER where all the different card png's are
		//change the string to whatever directory you have the folder
		String fileName = "file:///C:/Users/User/Desktop/Assignment/Cards";
		
		//the number of cards to be printed to the screen
		//this allows the program to be modifiable
		int numCards = 3;
		if(numCards > 54) numCards = 54;
		int[] cards = new int[numCards];
		int check = 0;
		
		//loops until three separate random cards are
		//chosen from the 54 available
		while(cards[numCards-1] == 0) {
			int num = rand.nextInt(54) + 1;
			boolean ok = true;
			
			for(int y = 0; y < check; y++) {
				if(num == cards[y]) {
					ok = false;
					break;
				}
			}
			if(ok) {
				cards[check] = num;
				check++;
			}
		}
		
		GridPane pane = new GridPane();
		
		//takes the cards chosen and adds them to the gridpane
		for(int x = 0; x < numCards; x++) {
			Label card = new Label("",new ImageView(fileName + "/" + cards[x] + ".png"));
			pane.add(card,x,0);
		}
		
	    Scene scene = new Scene(pane,numCards*72,96);
	    primaryStage.setTitle("Question 01"); // Set the stage title
	    primaryStage.setScene(scene); // Place the scene in the stage
	    primaryStage.show(); // Display the stage
		
	}

}
