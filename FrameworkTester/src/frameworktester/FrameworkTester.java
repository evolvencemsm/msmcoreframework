/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package frameworktester;

/**
 *
 * @author Administrator
 */

import java.io.*;
import msm.java.core.framework.*;
import msm.java.core.framework.service.*;

public class FrameworkTester {
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
        
        // TODO code application logic here
        
        try 
        {
        
        NetworkServer server = new NetworkServer(9090);
        
        server.start();
        
        System.in.read();
        
        }
        catch(Exception ex)
        {
            SystemUtility.WriteLog(ex);
        }
        
    }
    
}
