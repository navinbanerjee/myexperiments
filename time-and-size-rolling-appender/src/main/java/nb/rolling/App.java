package nb.rolling;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.RollingFileAppender;
import org.apache.log4j.SimpleLayout;
import org.apache.log4j.filter.LevelMatchFilter;
import org.apache.log4j.spi.LoggerFactory;
import org.xml.sax.SAXException;

public class App 
{
	
	Map<Integer, Logger> loggersBySubscriptions = new HashMap<Integer, Logger>();
	
	public App() throws SAXException, IOException{
		
	}
	
    private Logger createNewLogger(int folderId) throws IOException {
    	SimpleLayout layout = new SimpleLayout();
    	String folderBySubscription = "Rolling" + folderId;
    	File file = new File("/Users/navinbanerjee/work/git/myExperiments/time-and-size-rolling-appender/logs/" + folderBySubscription);
    	if(!file.exists()){
    		file.mkdir();
    	}
    	File logFile = new File(file, "app.log");
    	RollingFileAppender rollingFileAppender = new RollingFileAppender(layout, logFile.getAbsolutePath(), true);
    	rollingFileAppender.setMaxFileSize("1KB");
    	rollingFileAppender.setEncoding("utf-8");
    	rollingFileAppender.setThreshold(Level.INFO);
    	
    	LevelMatchFilter levelMatchFilter = new LevelMatchFilter();
    	levelMatchFilter.setLevelToMatch(Level.INFO.toString());
    	levelMatchFilter.setAcceptOnMatch(true);

    	rollingFileAppender.addFilter(levelMatchFilter);
    	rollingFileAppender.activateOptions();
    	
    	LoggerFactory factory = new LoggerFactory() {
			
			public Logger makeNewLoggerInstance(String name) {
				Logger logger = Logger.getLogger(name);
				return logger;
			}
		};
    	
    	Logger logger = factory.makeNewLoggerInstance("Rolling" + folderId);
    	logger.removeAllAppenders();
    	logger.addAppender(rollingFileAppender);
    	
    	return logger;
	}

	public static void main( String[] args ) throws SAXException, IOException
    {
    	App app = new App();
    	for(int i = 0; i < 1; i ++){
    		int folderId = i;
    		for(int j = 0; j < 50 ; j++){
    			app.testSizeRoller(folderId);
    		}
    	}
    }


	private void testSizeRoller(int folderId) throws IOException {
		Logger logger = loggersBySubscriptions.get(folderId);
		if(logger == null){
			logger = createNewLogger(folderId);
			loggersBySubscriptions.put(folderId, logger);
		}
		logger.info("iska matlab samjhe daya ...");
	}
}
