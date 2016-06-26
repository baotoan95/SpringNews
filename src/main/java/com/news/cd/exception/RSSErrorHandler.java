package com.news.cd.exception;

import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

public class RSSErrorHandler implements ErrorHandler {

	@Override
	public void error(SAXParseException spe) throws SAXException {
		showError("Error", spe);
	}

	@Override
	public void fatalError(SAXParseException spe) throws SAXException {
		showError("FatalError", spe);
	}

	@Override
	public void warning(SAXParseException spe) throws SAXException {
		showError("Warning", spe);
	}
	
	private void showError(String errorType, SAXParseException se) {
		System.out.println(errorType + ": " + se.getException());
		System.out.println("Line number: " + se.getLineNumber() + " column: " + se.getColumnNumber());
		System.out.println();
	}

}
