package nxt.rbt.communication;

import java.io.DataOutputStream;
import java.io.IOException;

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
    	btConn = Bluetooth.waitForConnection();
		outputStream = btConn.openDataOutputStream();
		inizialized = true;		
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
