package utilities;

import java.io.IOException;
import java.net.ServerSocket;

public class ServerRun {
    public static boolean checkServerRunning(int port){
        boolean isServerRunning = false;
        ServerSocket serverSocket;
        try{
            serverSocket = new ServerSocket(port);
            serverSocket.close();
        } catch (IOException e) {
            //if control comes here, it means the port is in use
            isServerRunning = true;
        } finally {
            serverSocket = null;
        }
        return isServerRunning;
    }
}
