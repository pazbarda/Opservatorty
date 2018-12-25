package com.opservatory.loma.LOMA;

import java.io.IOException;
import java.util.Timer;

import com.opservatory.loma.filereader.FileReader;
import com.opservatory.loma.monitors.logfilemonitor.LogFileMonitor;
import com.opservatory.loma.monitors.logfilemonitor.linesanalyzer.StandardLogLinesAnalyzer;
import com.opservatory.loma.notificationclients.LocalConsoleNotificationClient;
import com.opservatory.loma.testfilewriter.TestFileWriterTimerTask;

public class App 
{
    public static void main( String[] args ) throws IOException
    {
    	// set up and launch the test log file writer
		Timer timer = new Timer(false);
		TestFileWriterTimerTask writerTimeTask = new TestFileWriterTimerTask(timer, 10);
		timer.schedule(writerTimeTask, 0, 1000);
		
		// set up and run the log file monitor
		LogFileMonitor monitor = new LogFileMonitor(new FileReader("realTimeTestFile.txt"), new StandardLogLinesAnalyzer());
		monitor.addListener(new LocalConsoleNotificationClient());
		monitor.start();
    }
}
