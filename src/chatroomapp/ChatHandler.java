/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatroomapp;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author hagar
 */
public class ChatHandler extends Thread{
    
    DataInputStream dis;
    PrintStream ps;
    static Vector<ChatHandler> client_vector = new Vector<ChatHandler>();
    
    public ChatHandler(Socket cs)
    {
        try 
        {
            dis = new DataInputStream(cs.getInputStream());
            ps = new PrintStream(cs.getOutputStream());
            client_vector.add(this);
            this.start();
        } 
        catch (IOException ex) 
        {
            ex.printStackTrace();
        }
    }
    
    public void run()
    {
        while(true)
        {
            try 
            {
                String str = dis.readLine();
                sendMessageToAll(str);
            } 
            catch (IOException ex) 
            {
                ex.printStackTrace();
            }
            
        }
    }
    
    public void sendMessageToAll(String msg)
    {
        for(ChatHandler ch: client_vector)
        {

            ch.ps.println(msg);
        }
        System.out.print(msg);
    }
}
