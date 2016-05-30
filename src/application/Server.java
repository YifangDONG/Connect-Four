package application;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Server extends Application {

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
		final int PORT = 22222;
		try {
			ServerSocket server = new ServerSocket(PORT);
			System.out.println("waiting for client");			
			Socket socket = server.accept();
			InputStream is = socket.getInputStream();
			InputStreamReader isw = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(isw);
			if(br.readLine()!=null) {
				socket.shutdownInput();
				OutputStream os = socket.getOutputStream();
				PrintWriter pw = new PrintWriter(os);
				pw.write("ok");
				pw.flush();
				socket.shutdownOutput();
				launch(args);
				pw.close();
				os.close();
			}
			br.close();
			isw.close();
			is.close();
			socket.close();
			server.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
