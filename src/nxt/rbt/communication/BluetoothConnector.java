package nxt.rbt.communication;

import java.io.DataOutputStream;
import java.io.IOException;

import lejos.nxt.LCD;
import lejos.nxt.comm.BTConnection;
import lejos.nxt.comm.Bluetooth;

public class BluetoothConnector {
	
	private static final BluetoothConnector instance= new BluetoothConnector();
	
	BTConnection btConn;
	DataOutputStream outputStream;
	
	boolean inizialized = false;
	 
    private BluetoothConnector() {}
 
    public static BluetoothConnector getInstance() {
        return instance;
    }
    
    public void initialize() {
    	LCD.drawString("Try to connect...", 0, 0);		
    	btConn = Bluetooth.waitForConnection();
		outputStream = btConn.openDataOutputStream();
		inizialized = true;
		LCD.drawString("", 0, 0);
		LCD.drawString("connected", 0, 0);
    }
    
    public void sendMessage(String message) {
    	if (inizialized) {
    		try {
    			outputStream.write(message.getBytes());
    			outputStream.flush();
    		} catch (IOException e) {
    			e.printStackTrace();
    		}
		}
    }
}
