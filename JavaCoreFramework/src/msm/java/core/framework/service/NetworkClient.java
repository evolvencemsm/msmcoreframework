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
import msm.java.core.framework.SystemUtility;

public class NetworkClient 
{
    
    int serverport;    
    String servername = "";
    
    Socket client;
    
    private void Initialize()
    {
        
        try 
        {
            
            if(Validate())
            {
                client = new Socket(servername, serverport);                
            }
            
        }
        catch(Exception ex)
        {
            SystemUtility.WriteLog(ex);
        }
        
    }
    
    private boolean Validate()
    {
        
        boolean returnvalue = false;
        
        if(servername != null )
            if(!servername.equals(""))
                if(serverport > 0)
                    returnvalue = true;
        
        return returnvalue;
        
    }
    
    public NetworkClient(String name, int port)
    {

        this.servername = name;
        this.serverport = port;
        
        Initialize();
        
    }
    
    public boolean SendToServer(String message)
    {
        
        Boolean returnvalue = false;
        
        OutputStream outputstream;
        DataOutputStream dataoutstream;
        
        try 
        {
            
            if(client != null)
            {
                
                outputstream = client.getOutputStream();
                
                if(outputstream != null)
                {
                    
                    dataoutstream = new DataOutputStream(outputstream);
                    
                    if(dataoutstream != null)
                    {
                        
                        dataoutstream.writeUTF(message);
                        
                        returnvalue = true;
                        
                    }
                    
                }
                
            }
            
        }
        catch(Exception ex)
        {
            SystemUtility.WriteLog(ex);
        }
        
        return returnvalue;
        
    }
    
    public String ReadFromServer()
    {
        
        String returnvalue = "";
        
        InputStream inputstream;
        DataInputStream datainstream;
        
        try 
        {
            
            if(client != null)
            {
                
                inputstream = client.getInputStream();
                
                if(inputstream != null)
                {
                    
                    datainstream = new DataInputStream(inputstream);
                    
                    if(datainstream != null)
                    {
                        returnvalue = datainstream.readUTF();
                    }
                    
                }
                
            }
            
        }
        catch(Exception ex)
        {
            SystemUtility.WriteLog(ex);
        }
        
        return returnvalue;
        
    }
    
}
