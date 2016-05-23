package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class Connect4Controller implements Initializable {

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
}
