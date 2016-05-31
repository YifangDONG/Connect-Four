package application;


import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Connection {
	private String ip = "localhost";
	private int port = 22227;
	private Socket socket;
	private ServerSocket serverSocket;
	private DataOutputStream dos;
	private DataInputStream dis;
	private boolean accepted = false;
	private boolean player = true;
	private boolean yourturn = false;
	
	public boolean isAccepted() {
		return accepted;
	}
	public Socket getSocket() {
		return socket;
	}
	public DataOutputStream getDos() {
		return dos;
	}
	public DataInputStream getDis() {
		return dis;
	}
	public boolean getPlayer() {
		return player;
	}
	public void setPlayer(boolean player) {
		this.player = player;
	}
	public boolean getYourturn() {
		return yourturn;
	}
	public void setYourturn(boolean yourturn) {
		this.yourturn = yourturn;
	}
	public boolean connect() {
		try {
			socket = new Socket(ip, port);
			dos = new DataOutputStream(socket.getOutputStream());
			dis = new DataInputStream(socket.getInputStream());
			accepted = true;
		} catch (IOException e) {
			System.out.println("Unable to connect to the address: " + ip + ":" + port + " | Starting a server");
			return false;
		}
		System.out.println("Successfully connected to the server.");
		return true;
	}
	
	public void initializeServer() {
		try {
			serverSocket = new ServerSocket(port, 8, InetAddress.getByName(ip));
		} catch (Exception e) {
			e.printStackTrace();
		}
		player = false;
		yourturn = true;
	}
	public void listenForServerRequest() {
		try {
			socket = serverSocket.accept();
			dos = new DataOutputStream(socket.getOutputStream());
			dis = new DataInputStream(socket.getInputStream());
			accepted = true;
			System.out.println("CLIENT HAS REQUESTED TO JOIN, AND WE HAVE ACCEPTED");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
