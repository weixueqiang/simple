package log.test;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class LogTest {

    public   static   void  main(String[] args)  {
        PropertyConfigurator.configure( "C:\\code\\log4j2.properties" );
        Logger logger  =  Logger.getLogger(LogTest. class );
        logger.debug( " debug " );
        logger.error( " error " );
    } 
	
}
