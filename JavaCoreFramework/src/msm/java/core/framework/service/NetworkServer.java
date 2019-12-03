/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package msm.java.core.framework.service;

/**
 *
 * @author Murugan Sivananantha Perumal
 */

import java.io.*;
import java.net.*;
import msm.java.core.framework.interfaces.*;
import msm.java.core.framework.SystemUtility;

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
        Initialize();
    }
    
    public NetworkServer(int port)
    {
        
        this.socketport = port;
        
        Initialize();
        
    }
    
    /**
     * Public Methods 
     */
    
    public String ReadRequestorAddress()
    {
        
        DataInputStream inputstream;
        
        try 
        {
            
            if(server != null)
            {

                clientaddress = server.getRemoteSocketAddress().toString();

                inputstream = new DataInputStream(server.getInputStream());

                clientdata = inputstream.readUTF();

            }
            
        }
        catch(Exception ex)
        {
            SystemUtility.WriteLog(ex);
        }
        
        return clientaddress;
        
    }

    public String ReadRequest()
    {
        
        DataInputStream inputstream;
        
        try 
        {
            
                if(server != null)
                {
                    
                    clientaddress = server.getRemoteSocketAddress().toString();
                    
                    inputstream = new DataInputStream(server.getInputStream());
                    
                    clientdata = inputstream.readUTF();
                    
                }
            
        }
        catch(Exception ex)
        {
            SystemUtility.WriteLog(ex);
        }
        
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
        
        try 
        {
            
            SystemUtility.WriteLog("Server started.");
            
            while(true)
            {
                
                server = serversocket.accept();
                
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
    }
    
    public void StopService()
    {
        NetworkServer.StopService = true;
    }
    
}
