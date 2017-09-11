package nb.rolling;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import org.apache.log4j.xml.Log4jEntityResolver;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.sun.org.apache.xerces.internal.parsers.DOMParser;

import uk.org.simonsite.log4j.appender.TimeAndSizeRollingAppender;

public class App 
{
	
	Logger logger = Logger.getLogger(this.getClass());
	
	public App() throws SAXException, IOException{
		initLogger();
	}
	
    private void initLogger() throws SAXException, IOException {
		InputStream is = Logger.class.getClassLoader().getResourceAsStream("log4j.xml");
        DOMParser parser = new DOMParser();
        parser.setEntityResolver(new Log4jEntityResolver());
        parser.parse(new InputSource(is));
        DOMConfigurator.configure(parser.getDocument().getDocumentElement());
	}
    
    public void testTimeAndSizeRoller(int folderId){
    	TimeAndSizeRollingAppender appender = (TimeAndSizeRollingAppender)Logger.getRootLogger().getAppender("ROLL");
    	String folderBySubscription = "Rolling" + folderId;
    	File file = new File("/Users/navinbanerjee/work/git/myExperiments/log4j-rolling-appender-test/logs/" + folderBySubscription);
    	if(!file.exists()){
    		file.mkdir();
    	}
    	File logFile = new File(file, "app.log");
    	appender.setFile(logFile.getAbsolutePath());
    	appender.activateOptions();
    	logger.info("wow this is magic");
    }

	public static void main( String[] args ) throws SAXException, IOException
    {
    	App app = new App();
    	for(int i = 0; i < 10; i ++){
    		int folderId = i;
    		for(int j = 0; j < 50 ; j++){
    			app.testTimeAndSizeRoller(folderId);
    		}
    	}
    }
}
