/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package msm.java.core.framework;

/**
 *
 * @author Murugan Sivananantha Perumal
 */

import java.io.*;
import msm.java.core.framework.helpers.*;

public class SystemUtility 
{
    
    public SystemUtility()
    {
        
    }
    
    public static void WriteLog(String message)
    {
        
        SystemLogger logger = new SystemLogger();
        
        logger.WriteLog(message);
        
    }

    public static void WriteLog(Exception exception)
    {
        
        SystemLogger logger = new SystemLogger();
        
        logger.WriteLog(exception);
        
    }
    
}
