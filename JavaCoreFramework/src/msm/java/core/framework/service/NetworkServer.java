/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package msm.java.core.framework.service;

/**
 *
 * @author Administrator
 */

import java.io.*;
import java.net.*;
import msm.java.core.framework.SystemUtility;
import msm.java.core.framework.interfaces.*;

public class NetworkServer 
        extends Thread 
        implements IServiceInitalizer
{
    
    /**
     * Private Members 
     */
    private int socketport;
    private String clientdata;
        private String clientaddress;
    private static boolean StopService;
    
    private Socket server;
    private ServerSocket serversocket;

    /**
     * Constructors  
     */
    public NetworkServer()
    {
        
    }
    
    public NetworkServer(int port)
    {
        
    }
    
    /**
     * Public Methods 
     */
    
    public String GetClientAddress()
    {
        return clientaddress;
    }

    public String GetClientData()
    {
        return clientdata;
    }
    
    public void WriteResponse(String message)
    {
        
        DataOutputStream outputstream;
        
        try 
        {
            
            if(server != null)
            {
                
                outputstream = new DataOutputStream(server.getOutputStream());
                
                if(outputstream != null)
                {
                    outputstream.writeUTF(message);
                }
                
            }
            
        }
        catch(Exception ex)
        {
            SystemUtility.WriteLog(ex);
        }
        
    }
    
    public void Initialize()
    {
      
        try 
        {
        
            NetworkServer.StopService = false;
            
            this.serversocket = new ServerSocket(this.socketport);
            this.serversocket.setSoTimeout(10000);
            
        }
        catch(Exception ex)
        {
            SystemUtility.WriteLog(ex);
        }
        
    }
    
    public void run()
    {
        
        DataInputStream inputstream;
        
        try 
        {
            
            SystemUtility.WriteLog("Server started.");
                                
            while(true)
            {
                
                server = serversocket.accept();
                
                if(server != null)
                {
                    
                    clientaddress = server.getRemoteSocketAddress().toString();
                    
                    inputstream = new DataInputStream(server.getInputStream());
                    
                    clientdata = inputstream.readUTF();
                    
                }

                if(NetworkServer.StopService)
                {
                    
                    SystemUtility.WriteLog("Server stopped.");
                    
                    break;
                    
                }
                
            }
            
        }
        catch(Exception ex)
        {
            SystemUtility.WriteLog(ex);
        }        
        
    }
    
    public void StartService()
    {
        
        NetworkServer.StopService = false;
        
        this.run();
        
    }
    
    public void StopService()
    {
        NetworkServer.StopService = true;
    }
    
}
