/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatroomapp;

import java.io.IOException;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author hagar
 */
public class ChatServer {
    
    ServerSocket serverSocket;
    Socket s;
    public ChatServer()
    {
        try 
        {
            serverSocket = new ServerSocket(5005);
            while (true)
            {
                s = serverSocket.accept();
                new ChatHandler(s);

                //start each couple of sockets in a thread
            }
        } 
        catch (Exception ex) 
        {
            ex.printStackTrace();
        }
        
    }
    public static void main(String args[])
    {
        new ChatServer();
    }
}
