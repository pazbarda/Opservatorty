package com.opservatory.loma.LOMA;

import java.io.IOException;
import java.util.List;
import java.util.Timer;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.opservatory.loma.monitors.IMonitor;
import com.opservatory.loma.monitors.logfilemonitor.LogEvent;
import com.opservatory.loma.notificationclients.LocalConsoleNotificationClient;
import com.opservatory.loma.testfilewriter.TestFileWriterTimerTask;

public class App 
{
    public static void main( String[] args ) throws IOException, InterruptedException
    {
    	// set up and launch the test log file writer
		Timer timer = new Timer(false);
		TestFileWriterTimerTask writerTimeTask = new TestFileWriterTimerTask(timer, 10);
		timer.schedule(writerTimeTask, 0, 1000);
		
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(LOMASpringConfig.class);
		
		// set up and run the log file monitor
		@SuppressWarnings("unchecked")
		IMonitor<List<LogEvent>> monitor = context.getBean("logFileMonitor", IMonitor.class); //new LogFileMonitor(new FileReader("realTimeTestFile.txt"), new StandardLogLinesAnalyzer());
		monitor.addListener(new LocalConsoleNotificationClient());
		monitor.start();
		Thread.sleep(11000);
		// close context
		context.close();
    }
}
