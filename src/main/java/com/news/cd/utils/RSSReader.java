package com.news.cd.utils;

import java.io.IOException;
import java.io.Serializable;
import java.net.URL;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.springframework.stereotype.Component;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import com.news.cd.entities.RSSChannel;
import com.news.cd.exception.RSSErrorHandler;
import com.news.cd.helper.RSSHelper;

@SuppressWarnings("serial")
@Component("rssReader")
public class RSSReader implements Serializable {
	private RSSChannel rssChannel;
	private String rssUrl;

	public RSSReader() {
		this.rssChannel = new RSSChannel();
	}

	public RSSReader read() {
		rssChannel = new RSSChannel();
		rssChannel.setLink(rssUrl);
		SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
		try {
			SAXParser saxParser = saxParserFactory.newSAXParser();
			XMLReader xmlReader = saxParser.getXMLReader();
			xmlReader.setContentHandler(new RSSHelper(rssChannel));
			xmlReader.setErrorHandler(new RSSErrorHandler());
			URL url = new URL(rssUrl);
			InputSource inputs = new InputSource(url.openStream());
			xmlReader.parse(inputs);
		} catch (ParserConfigurationException | SAXException | IOException e) {
			return null; // If url not readable then return null
		}
		return this;
	}

	public String getRssUrl() {
		return rssUrl;
	}

	public RSSReader setRssUrl(String rssUrl) {
		this.rssUrl = rssUrl;
		return this;
	}

	public RSSChannel getRssChannel() {
	    if(null == rssChannel.getGenerator()) {
	        String generator = rssUrl.substring(7, rssUrl.length());
	        generator = generator.substring(0, generator.indexOf("/"));
	        rssChannel.setGenerator(generator);
	    }
		return rssChannel;
	}

	public void setRssChannel(RSSChannel rssChannel) {
		this.rssChannel = rssChannel;
	}

}
