# time-and-size-rolling-appender
Way to use TimeAndSizeRollingAppender to have separate folders based on an identifier.

This project helps us use TimeAndSizeRollingAppender by creating log files in separate folders based on an identifier. In this project it is simply a string 'Rolling' appended by an integer. 

To make sure that the log files are generated please change the param File in log4j.xml for our appender.

    
       <param name="File" value="/Users/navinbanerjee/work/git/myExperiments/time-and-size-rolling-appender/logs/app.log"/>
    
To compile run: mvn clean compile

To run the main class: mvn exec:java -Dexec.mainClass="nb.rolling.App"
