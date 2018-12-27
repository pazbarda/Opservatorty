package com.opservatory.loma.filereader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

// TODO - doc
@Component
@Scope("prototype")
public class FileReader {
//	@Value("${testrun.filePath}")
	private String filePath = "realTimeTestFile.txt";
	private int currentLine = 0;
	// TODO - make configurable
	private final int READ_ATTEMPT_INTERVAL_MILLISECS = 1000;
	// TODO - make configurable
	private static final int READ_TIMEOUT_MILISECS = 60000; 

	public FileReader() {
	}

	public String getFilePath() {
		return filePath;
	}

	public int getCurrentLine() {
		return currentLine;
	}
	
	public List<String> getNewLines() throws InterruptedException, FileReadException {
		List<String> ret = null;
		long startTimeMilisecs = (new Date()).getTime();
		while (null == ret) {
			checkTimeout(startTimeMilisecs);
			try (Stream<String> stream = Files.lines(Paths.get(filePath))) {
				ret = new ArrayList<String>();
				Iterator<String> iterator = stream.iterator();
				int lineIndex = 1;
				while (iterator.hasNext() && lineIndex <= currentLine) {
					iterator.next();
					lineIndex++;
				}
				while(iterator.hasNext()) {
					ret.add(iterator.next());
					currentLine++;
				}
			} catch (IOException e) {
				Thread.sleep(READ_ATTEMPT_INTERVAL_MILLISECS);
				// NOP - we throw an exception on time out
			}
		}
		return ret;
	}
	
	private static void checkTimeout(long startTimeMilisecs) throws FileReadException {
		long timePassed = (new Date()).getTime() - startTimeMilisecs;
		if (timePassed > READ_TIMEOUT_MILISECS) {
			throw new FileReadException();
		}
	}
}
