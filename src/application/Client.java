package application;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Client extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("Connect4.fxml"));
		Scene scene = new Scene(root);
		Stage stage = new Stage();
		stage.setScene(scene);
		stage.setTitle("Connect 4");
		stage.show();
	}
	
	public static void main(String[] args) {
	    String IP = "localhost";   
	    int PORT = 22222;
	    try {
			Socket socket = new Socket(IP,PORT);
			OutputStream os = socket.getOutputStream();
			PrintWriter pw = new PrintWriter(os);
			pw.write("connect");
			pw.flush();
			socket.shutdownOutput();
			InputStream is = socket.getInputStream();
			InputStreamReader isw = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(isw);
			if (br.readLine()!=null) {
				launch(args);
			}
			pw.close();
			os.close();
			br.close();
			isw.close();
			is.close();
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}