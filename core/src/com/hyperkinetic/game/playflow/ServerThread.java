package com.hyperkinetic.game.playflow;

import java.io.*;
import java.net.Socket;

public class ServerThread extends Thread {
    private String playerID;
    private GameRoom room;
    private boolean color;
    private Socket socket;
    private ObjectInputStream in;
    private ObjectOutputStream out;

    public ServerThread(Socket s, String playerID){
        this.playerID = playerID;
        socket = s;
        try
        {
            in = new ObjectInputStream(socket.getInputStream());
            out = new ObjectOutputStream(socket.getOutputStream());
        }
        catch(IOException ioe) {
            ioe.printStackTrace();
        }

    }

    public void sendMessage(GameMessage message)
    {
        try
        {
            out.writeObject(message);
            out.flush();
        }
        catch(IOException ioe)
        {
            System.out.println("ioe in sendMessage() of ServerThread " + playerID);
            ioe.printStackTrace();
        }
    }

    public void enterGame(GameRoom gm)
    {
        if(this.room != null) return;
        this.room = gm;
    }

    public void setColor(boolean color)
    {
        this.color = color;
    }

    public String getPlayerID()
    {
        return playerID;
    }

    @Override
    public void run() {
        while(!room.isOver)
        {
            // querying for GameMessage objects
            try
            {
                GameMessage message = (GameMessage) in.readObject();
                room.readMessage(message);
            }
            catch (ClassNotFoundException cnfe)
            {
                System.out.println("cnfe in run() of ServerThread " + playerID);
                cnfe.printStackTrace();
            }
            catch(IOException ioe)
            {
                System.out.println("ioe in run() of ServerThread " + playerID);
            }
        }

        room = null;
    }
}
