/* James LeBlanc : 100663391
 * Question number three of assignment one for CSCI-2020U
 * 
 * This program allows the user to play around with three
 * Separate points, rotating around a triangle
 * The user can move any of the points, and the 
 * triangle will update in real time, as well as displaying
 * each angle of the triangle
 */

package assignment_01;

import java.util.Random;
import java.lang.Math;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Question_03 extends Application{

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
	    
		Pane pane = new Pane();
		Random rand = new Random();
		
		//The circle used as the structure for the triangle
		//is static throughout the entire runtime
		Circle circ = new Circle(200,200,150);
		circ.setFill(null);
		circ.setStroke(Color.BLACK);
		
		//the points on the circle
		Circle[] points = new Circle[3];
		//this displays each angle of the triangle
		Text[] texts = new Text[3];
		
		//This sets up the original image that the user sees
		//when they start the program
		for(int x = 0; x < 3; x++) {
			double angle = Math.toRadians(rand.nextInt(361));

			if(angle < 90 && angle > 270)
				texts[x] = new Text(200 + 180*Math.cos(angle), 200 + 170*Math.sin(angle),"");
			else
				texts[x] = new Text(200 + 160*Math.cos(angle), 200 + 170*Math.sin(angle),"");
			
			points[x] = new Circle(200 + 150*Math.cos(angle), 200 + 150*Math.sin(angle), 5);
			points[x].setFill(Color.RED);
			points[x].setStroke(Color.BLACK);
		}
		
		double[] angles = new double[3];
		Line[] lines = new Line[3];
		
		calculate(points, angles, texts, lines);
		
		//each of these control each point of the triangle
		//and is what allows the triangle to change 
		points[0].setOnMouseDragged(e -> {
			double X = e.getX()-200.0;
			double Y = e.getY()-200.0;
			double angle = Math.atan(Y/X);
			movePoint(0,points,texts,angle,X);
			calculate(points, angles, texts, lines);
		});
		points[1].setOnMouseDragged(e -> {
			double X = e.getX()-200.0;
			double Y = e.getY()-200.0;
			double angle = Math.atan(Y/X);
			movePoint(1,points,texts,angle,X);
			calculate(points, angles, texts, lines);
		});
		points[2].setOnMouseDragged(e -> {
			double X = e.getX()-200.0;
			double Y = e.getY()-200.0;
			double angle = Math.atan(Y/X);
			movePoint(2,points,texts,angle,X);
			calculate(points, angles, texts, lines);
		});
		
		
		pane.getChildren().add(circ);
		pane.getChildren().addAll(lines);
		pane.getChildren().addAll(points);
		pane.getChildren().addAll(texts);
		
		Scene scene = new Scene(pane,400,400);
	    primaryStage.setTitle("Question 03"); // Set the stage title
	    primaryStage.setScene(scene); // Place the scene in the stage
	    primaryStage.show(); // Display the stage
	}
	
	//This function handles the movement of the point passed, and allows the 
	//point to move smoothly around the circle
	public void movePoint(int point, Circle[] points, Text[] texts, double angle, double X) {
		if(X >= 0) {
			points[point].setCenterX(200 + 150*Math.cos(angle));
			points[point].setCenterY(200 + 150*Math.sin(angle));
			texts[point].setX(200 + 160*Math.cos(angle));
			texts[point].setY(200 + 170*Math.sin(angle));
		}else {
			points[point].setCenterX(200 - 150*Math.cos(angle));
			points[point].setCenterY(200 - 150*Math.sin(angle));
			texts[point].setX(200 - 180*Math.cos(angle));
			texts[point].setY(200 - 170*Math.sin(angle));
		}
	}
	
	//This function does the mathematical calculations for the program; things like the
	//lines and angles of the Triangle.
	//after calculation, it then updates the arrays given with the new values
	public void calculate(Circle[] points, double[] angles, Text[] texts, Line[] lines) {
		//made an array of all of the different positions of the points, so you don't have
		//to look them up every time
		//1st number is the point you wish to look up
		//2nd number is if you want x or y, 0 = x, and 1 = y
		double xyPos[][] = {{points[0].getCenterX(), points[0].getCenterY()},
							{points[1].getCenterX(), points[1].getCenterY()},
							{points[2].getCenterX(), points[2].getCenterY()}};
		
		//this creates the lines of the triangle if they are not already made
		//and if they are this simply updates them
		if(lines[0] == null) {
			lines[0] = new Line(xyPos[0][0], xyPos[0][1], xyPos[1][0], xyPos[1][1]);
			lines[1] = new Line(xyPos[1][0], xyPos[1][1], xyPos[2][0], xyPos[2][1]);
			lines[2] = new Line(xyPos[2][0], xyPos[2][1], xyPos[0][0], xyPos[0][1]);
		}else {
			lines[0].setStartX(xyPos[0][0]); lines[0].setStartY(xyPos[0][1]);
			lines[0].setEndX(xyPos[1][0]); lines[0].setEndY(xyPos[1][1]);
			lines[1].setStartX(xyPos[1][0]); lines[1].setStartY(xyPos[1][1]);
			lines[1].setEndX(xyPos[2][0]); lines[1].setEndY(xyPos[2][1]);
			lines[2].setStartX(xyPos[2][0]); lines[2].setStartY(xyPos[2][1]);
			lines[2].setEndX(xyPos[0][0]); lines[2].setEndY(xyPos[0][1]);
		}
		
		//by using pythagorean theorem, the program calculates the length of each line
		double a = Math.sqrt(Math.pow(Math.abs(xyPos[1][0] - xyPos[2][0]), 2) + 
				   Math.pow(Math.abs(xyPos[1][1] - xyPos[2][1]), 2));
		double b = Math.sqrt(Math.pow(Math.abs(xyPos[2][0] - xyPos[0][0]), 2) + 
				   Math.pow(Math.abs(xyPos[2][1] - xyPos[0][1]), 2));
		double c = Math.sqrt(Math.pow(Math.abs(xyPos[0][0] - xyPos[1][0]), 2) + 
				   Math.pow(Math.abs(xyPos[0][1] - xyPos[1][1]), 2));

		//uses cosine law to calculate each of the angles for the triangle
		angles[0] = Math.acos((a*a - b*b - c*c) / (-2 * b * c));
		angles[1] = Math.acos((b*b - a*a - c*c) / (-2 * a * c));
		angles[2] = Math.acos((c*c - a*a - b*b) / (-2 * a * b));

		//updates the text to show the new angles of the triangle
		for(int x = 0; x < 3; x++)
			texts[x].setText(Double.toString(Math.round(Math.toDegrees(angles[x])*100.0)/100.0));
	}

}
