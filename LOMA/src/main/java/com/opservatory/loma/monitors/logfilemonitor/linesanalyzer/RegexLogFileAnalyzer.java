package com.opservatory.loma.monitors.logfilemonitor.linesanalyzer;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.opservatory.loma.monitors.logfilemonitor.LogEvent;

@Component
@Scope("prototype")
public class RegexLogFileAnalyzer implements ILogLinesAnalyzer {

	@Override
	public List<LogEvent> analyzeLogLines(List<String> logLines) {
		// TODO Auto-generated method stub
		return null;
	}

}
