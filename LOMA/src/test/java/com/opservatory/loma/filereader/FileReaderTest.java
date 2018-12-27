package com.opservatory.loma.filereader;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.opservatory.loma.testfilewriter.TestFileSimpleWriter;

public class FileReaderTest {
	
	@Test
	public void basic() throws IOException, InterruptedException, FileReadException {
		String filePath = "tempTestFile.txt";
		List<String> writtenLines = new ArrayList<String>();
		List<String> readLines = new ArrayList<String>();
		
		TestFileSimpleWriter writer = new TestFileSimpleWriter(filePath);
		try {
			FileReader reader = new FileReader();
			
			// empty file
			readLines = reader.getNewLines();
			assertEquals(writtenLines, readLines);
			
			// 1 line added
			writtenLines.add("line 1");
			writer.writeLines(writtenLines);
			readLines = reader.getNewLines();
			assertEquals(writtenLines, readLines);
			
			// no lines added
			writtenLines.clear();
			readLines = reader.getNewLines();
			assertEquals(writtenLines, readLines);
			
			// 2 lines added
			writtenLines.add("line 2");
			writtenLines.add("line 3");
			writer.writeLines(writtenLines);
			readLines = reader.getNewLines();
			assertEquals(writtenLines, readLines);
		} finally {
			writer.discardFile();
		}
		
		
		
	}
}
