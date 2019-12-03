/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package msm.java.core.framework.helpers;

/**
 *
 * @author Murugan Sivananantha Perumal
 */

import java.io.*;
import java.util.*;
import java.util.logging.*;

public class SystemLogger
{
    
    /* Private Members */
    private Logger logger;
    private FileHandler filehandler;
    private Exception internalexception;
    private SimpleFormatter fileformatter;
    
    /* Private Methods */
    private void Initialize()
    {
        
        try 
        {
            
            fileformatter = new SimpleFormatter();
            filehandler = new FileHandler("synclog.log", true);            
            logger = Logger.getLogger(this.getClass().getName());
            
            filehandler.setFormatter(fileformatter);
            logger.addHandler(filehandler);
            
        }
        catch(Exception ex)
        {
            internalexception = ex;
        }
        
    }
    
    private String GetMethodName(StackTraceElement[] stacktrace)
    {
        
        String methodname = "";
        StackTraceElement tracelement;
        
        try 
        {
            
            if(stacktrace != null)
            {
                
                tracelement = stacktrace[2];
                
                methodname = tracelement.getMethodName();
                
            }
            
        }
        catch(Exception ex)
        {
            
        }
     
        return methodname;
        
    }
    
    /* Constructors */
    public SystemLogger()
    {
        Initialize();
    }
    
    /* Public Properties */
    
    /* Public Methods */
    public void WriteLog(String message)
    {
        
        String methodname;
        StackTraceElement[] stacktrace;
        
        if(logger != null)
        {
            
            if(logger.isLoggable(Level.INFO))
            {
                
                stacktrace = Thread.currentThread().getStackTrace();
                
                methodname = GetMethodName(stacktrace);

                methodname = String.format("method: %s, error: %s", methodname, message);
                
                logger.info(methodname);
                
            }
            
        }
        
    }
    
    public void WriteLog(Exception exception)
    {
        
        String methodname;
        StackTraceElement[] stacktrace;
        
        if(logger != null && exception != null)
        {
            
            if(logger.isLoggable(Level.INFO))
            {
                
                stacktrace = Thread.currentThread().getStackTrace();

                methodname = GetMethodName(stacktrace);
                
                methodname = String.format("method: %s, error: %s", methodname, exception.getMessage());
                
                logger.info(methodname);
                
            }
            
        }
        
    }    
    
}
