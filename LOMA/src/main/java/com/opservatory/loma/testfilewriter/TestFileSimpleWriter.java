package com.opservatory.loma.testfilewriter;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class TestFileSimpleWriter {
	
	private final String filePath;

	public TestFileSimpleWriter(String filePath) throws IOException {
		this.filePath = filePath;
		// create the file if it does not exist
		BufferedWriter bufWriter = getWriter();
		bufWriter.close();
	}
	
	public void writeLines(List<String> lines) throws IOException {
		BufferedWriter bufWriter = getWriter();
		try {
			for (String line : lines) {
				bufWriter.write(line + "\n");
			}
		} finally {
			bufWriter.close();
		} 
	}
	
	public void discardFile() throws IOException {		
		Files.deleteIfExists(Paths.get(filePath));
	}
	
	private BufferedWriter getWriter() throws IOException {
		File file = new File(this.filePath);
		FileWriter writer = new FileWriter(file, true);
		BufferedWriter bufWriter = new BufferedWriter(writer);
		return bufWriter;
	}
}
