/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package whiteboardproject;


import java.beans.XMLEncoder;
import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.*;
import java.util.Iterator;

public class Server extends Thread
{
    private int port;
    private Whiteboard whiteboard;
    private Canvas canvas;
  
    public Server(Whiteboard whiteboard, Canvas canvas, int port)
    {
        this.whiteboard = whiteboard;
        this.canvas = canvas;
        this.port = port;
    } //End Server constructor
    
    public void run()
    {
        try
        {
            ServerSocket serverSocket = new ServerSocket(port);
            while(true)
            {
                Socket toClient = null;
                toClient = serverSocket.accept();
                final ObjectOutputStream out = new ObjectOutputStream(toClient.getOutputStream());
                
                if(!whiteboard.getOutputs().contains(out))
                {
                    Thread worker = new Thread(new Runnable(){
                       public void run()
                       {
                           //For each shape each canvas, add it to an XML
                           for(DShape shape : canvas.getShapes())
                           {
                               try
                               {
                                   //Obtain and add shape to XML
                                   out.writeObject(getXMLStringMessage(new MessageNotification(MessageNotification.ADD, shape.getModel())));
                                   out.flush();
                               }
                               catch (Exception e)
                               {
                                   
                               }
                           }
                       }
                    });
                    
                    worker.start();
                } //End if
                
                //Add to whiteboard the current output stream
                whiteboard.getOutputs().add(out);
                
            } //End while
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    } //End run
    
    public String getXMLStringMessage(MessageNotification message)
    {
        OutputStream messageStream = new ByteArrayOutputStream();
        XMLEncoder encoder = new XMLEncoder(messageStream);
        encoder.writeObject(message);
        encoder.close();
        
        return messageStream.toString();
    } //End getXMLStringMessage
    
    //Server is responsible for sending the message
    public void sendMessage(int command, DShapeModel model)
    {
        MessageNotification message = new MessageNotification();
        message.setCommand(command);
        message.setModel(model);
        
        String xmlString = getXMLStringMessage(message);
        Iterator<ObjectOutputStream> it = whiteboard.getOutputs().iterator();
        
        while(it.hasNext())
        {
            ObjectOutputStream out = it.next();
            try
            {
                out.writeObject(xmlString);
                out.flush();
            }
            catch(Exception e)
            {
                e.printStackTrace();
                it.remove();
            }
        }
    } //End sendMessage
    
} //End Server Class
