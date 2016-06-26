package com.news.cd.constants;

public class ContactStatus implements StatusType {
	public static final int SEEN_AND_NO_ANSWER = 1;
	public static final int SEEN_AND_ANSWER = 2;
	public static final int WAIT_TO_READ = 3;
	public static final int TRASH_UNREAD = 10;
	public static final int TRASH_READ = 11;
	public static final int SENT = 12;
	public static final int DRAFT = 13;
	
	public static final String REPO_INBOX= "inbox";
	public static final String REPO_SENT= "sent";
	public static final String REPO_DRAFT= "draft";
	public static final String REPO_TRASH= "trash";
	
}
