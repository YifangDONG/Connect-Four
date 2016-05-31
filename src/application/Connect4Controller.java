package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.sun.javafx.tk.Toolkit;

import javafx.application.Platform;
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
	private Connection con = new Connection();
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		if(!con.connect()) con.initializeServer();
		Chat chat = new Chat();
		new Thread(chat).start();
	}
	private class Chat implements Runnable {

		@Override
		public void run() {
			if(!con.isAccepted()&&!con.getPlayer()) {
				con.listenForServerRequest();
			}			
			while(true) {
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				sync();
			}
		}
	}
	private void draw(int column, char piece, Color color) {
		int row = 5 - board.play(column, piece);
		Circle circle = new Circle(50);
		circle.setCenterX(50);
		circle.setCenterY(50);
		circle.setLayoutX(column*100);
		circle.setLayoutY(row*100);
		circle.setFill(color);
		Platform.runLater(new Runnable() {
			@Override
			public void run() {	
				pane.getChildren().add(circle);
			}
		});
	}
	private void sync() {
		
		if(!con.getYourturn()) {
			try {
				int column = con.getDis().readInt();
//				con.getSocket().shutdownInput();
				if(!board.isWin('x')&&!board.isWin('o')) {
					if(con.getPlayer()) {
						draw(column,'x',Color.RED);
						if(board.isWin('x')) {
							text.setText("The red wins");
						}
					}
					else {
						draw(column,'o',Color.BLUE);
						if(board.isWin('o')) {
							text.setText("The blue wins");
						}
					}
				}
				con.setYourturn(true);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
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
		if(con.isAccepted()&&con.getYourturn()) {
			if(!board.isWin('x')&&!board.isWin('o')) {
				if(con.getPlayer()) {
					draw(column,'o',Color.BLUE);
				}
				else {
					draw(column,'x',Color.RED);
				}
				con.setYourturn(false);
				if(board.isWin('x')) {
					text.setText("The red wins");
				}
				else if(board.isWin('o')) {
					text.setText("The blue wins");
				}
				else if(!board.free_space_in_board()) {
					text.setText("Non one wins");
				}
				
				try {
					con.getDos().writeInt(column);
					con.getDos().flush();
//					con.getSocket().shutdownOutput();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
			
		}
	}
}
