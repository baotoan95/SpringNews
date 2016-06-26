package com.news.cd.helper;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;

import com.news.cd.entities.Post;
import com.news.cd.entities.RSSChannel;
import com.news.cd.utils.StringUtils;

public class RSSHelper implements ContentHandler {
	private RSSChannel rssChannel;
	private Post rssItem;
	private boolean foundItem;
	private StringBuilder value;
	private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEEE, dd MMM yyyy HH:mm:ss");
	
	public RSSHelper(RSSChannel rssChannel) {
		this.rssChannel = rssChannel;
	}
	
	@Override
	public void startDocument() throws SAXException {
		// Nothing
	}
	
	@Override
	public void characters(char[] chars, int start, int length) throws SAXException {
		this.value.append(chars, start, length);
	}
	
	@Override
	public void startElement(String uri, String localName, String qName, Attributes attrs) throws SAXException {
		value = new StringBuilder();
		if(qName.equalsIgnoreCase("item")) {
			this.rssItem = new Post();
			this.foundItem = true;
		}
		if(qName.equalsIgnoreCase("media:content")) {
		    for (int i = 0; i < attrs.getLength(); i++) {
                if(attrs.getValue(i).contains("http")) {
                    this.rssItem.setAvatarUrl(attrs.getValue(i));
                }
            }
		}
	}
	
	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		switch (qName.toLowerCase()) {
		case "title":
			String title = StringUtils.removeHtmlTags(this.value.toString());
			if (foundItem) {
				this.rssItem.setTitle(title);
			} else {
				this.rssChannel.setTitle(title);
			}
			break;
		case "link":
			if (foundItem) {
				this.rssItem.setlink(this.value.toString());
			}
			break;
		case "description":
			try {
				if (foundItem) {
					this.rssItem.setDesc(StringUtils.removeHtmlTags(this.value.toString().substring(
							(value.indexOf("</br>") > 0) ? value.indexOf("</br>") + 5 : 0, value.length())));
					this.rssItem.setAvatarUrl(getImageUrl(this.value.toString()));
				} else {
					this.rssChannel.setDesc(StringUtils.removeHtmlTags(this.value.toString()));
				}
			} catch (Exception e) {
				// Nothing
			}
			break;
		case "pubdate":
		case "pubDate":
			if (foundItem)
				try {
					this.rssItem.setPublishedDate(simpleDateFormat.parse(this.value.toString()));
				} catch (ParseException e) {
					e.printStackTrace();
				}
			else
				try {
					this.rssChannel.setPubDate(simpleDateFormat.parse(this.value.toString()));
				} catch (ParseException e) {
					e.printStackTrace();
				}
			break;
		case "item":
			this.foundItem = false;
			this.rssChannel.getItems().add(rssItem);
			break;
		case "generator":
			this.rssChannel.setGenerator(this.value.toString());
			break;
		}
	}
	
	private String getImageUrl(String str) {
		if(str.contains("<img")) {
			str = str.substring(str.indexOf("<img"));
			str = str.substring(str.indexOf("src=\"") + 5);
			str = str.substring(0, str.indexOf("\""));
			return str;
		}
		// Nếu media để ngoài description tag
		if(str.contains("image")) {
		    str = str.substring(str.indexOf("src=", str.length()));
		    str = str.substring(0, str.indexOf(" ") - 1);
		    System.out.println(str);
		}
		return "";
	}

	@Override
	public void endDocument() throws SAXException {
		
	}

	@Override
	public void endPrefixMapping(String arg0) throws SAXException {
		
	}

	@Override
	public void ignorableWhitespace(char[] arg0, int arg1, int arg2)
			throws SAXException {
		
	}

	@Override
	public void processingInstruction(String arg0, String arg1)
			throws SAXException {
		
	}

	@Override
	public void setDocumentLocator(Locator arg0) {
		
	}

	@Override
	public void skippedEntity(String arg0) throws SAXException {
		
	}

	@Override
	public void startPrefixMapping(String arg0, String arg1)
			throws SAXException {
		
	}

}
