package com.opservatory.loma.testfilewriter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class TestFileWriterTimerTask extends TimerTask{

	private final Timer timer;
	private final int numOfSteps;
	private TestFileSimpleWriter simpleWriter;
	private int linesStepIncrement;
	private int currLineIndex;
	private int currStepIndex;
	private int currStepSize;
	
	public TestFileWriterTimerTask(Timer myTimer, int numOfSteps) throws IOException
	{
		this(myTimer, 1, numOfSteps);
	}
	
	public TestFileWriterTimerTask(Timer myTimer, int linesStepIncrement, int numOfSteps) throws IOException {
		this.timer = myTimer;
		this.numOfSteps = numOfSteps;
		this.simpleWriter = new TestFileSimpleWriter("realTimeTestFile.txt");
		this.linesStepIncrement = linesStepIncrement;
		this.currStepSize = 1;
		this.currLineIndex = 0;
		this.currStepIndex = 1;
		
	}

	@Override
	public void run() {
		if (currStepIndex > numOfSteps) {
			cancel();
			return;
		}
		List<String> lines = new ArrayList<String>();
		for (int i = 0; i < currStepSize; i++) {
			currLineIndex++;
			lines.add("line " + currLineIndex);
		}
		try {
			simpleWriter.writeLines(lines);
			System.out.println("TestFileWriterTimerTask> written " + lines.size() + " lines");
		} catch (IOException e) {
			System.out.println("error writing to file");
		}
		currStepIndex++;
		currStepSize += linesStepIncrement;
	}
	
	@Override
	public boolean cancel() {
		try {
			simpleWriter.discardFile();
		} catch (IOException e) {
			System.out.println("TestFileWriterTimerTask> error while discarding file! - " + e.getMessage());
		} 
		timer.cancel();
		return super.cancel();
	}
	
	
}
