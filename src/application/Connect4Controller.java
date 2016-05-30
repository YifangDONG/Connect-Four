package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.text.Text;

public class Connect4Controller implements Initializable {

	@FXML
	Text text;
	@FXML
	Pane pane;
	private Connect4Board board = new Connect4Board();
	private boolean player = true; //'x'
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
	}
	
	@FXML
	public void selectColumn(Event e) {
		if(e.getEventType().getName().equals("MOUSE_ENTERED")) {
			((Rectangle) e.getSource()).setFill(Color.rgb(255,255,255,0.3));
		}
		else if(e.getEventType().getName().equals("MOUSE_EXITED")) {
			((Rectangle) e.getSource()).setFill(Color.TRANSPARENT);
		}			
	}
	@FXML
	public void play(Event e) {
		int column = (int)((Rectangle)e.getSource()).getLayoutX()/100;
		if(!board.isWin('x')&&!board.isWin('o')) {
			if(player) {
				int row = 5 - board.play(column, 'x');
				player = false;
				Circle circle = new Circle(50);
				circle.setCenterX(50);
				circle.setCenterY(50);
				circle.setLayoutX(column*100);
				circle.setLayoutY(row*100);
				circle.setFill(Color.RED);
				pane.getChildren().add(circle);
				if(board.isWin('x')) {
					text.setText("The red wins");
				}
			}
			else {
				int row = 5 - board.play(column, 'o');
				player = true;
				Circle circle = new Circle(50);
				circle.setCenterX(50);
				circle.setCenterY(50);
				circle.setLayoutX(column*100);
				circle.setLayoutY(row*100);
				circle.setFill(Color.BLUE);
				pane.getChildren().add(circle);
				if(board.isWin('o')) {
					text.setText("The blue wins");
				}
			}
		}
	}
}
