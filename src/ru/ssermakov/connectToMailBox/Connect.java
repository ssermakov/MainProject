package ru.ssermakov.connectToMailBox;

import java.io.IOException;
import java.util.Properties;

import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Store;

import com.sun.mail.imap.IMAPStore;

public class Connect {

	public static void main(String[] args) throws MessagingException, IOException {
		Properties properties = new Properties();
		properties.put("mail.store.protocol", "imaps");
		properties.put("mail.imaps.port", "993");
		properties.put("mail.imaps.starttls.enable", "true");
		Session emailSession = Session.getDefaultInstance(properties);
		// emailSession.setDebug(true);
		// create the IMAP3 store object and connect with the pop server
		Store store = emailSession.getStore("imaps");
		//change the user and password accordingly
		store.connect("imap.gmail.com", "btbwild.ermakov@gmail.com", "btb_wildKserksErmakov77");
		IMAPStore imapStore = (IMAPStore) store;
		System.out.println("imapStore---" + imapStore);
		
		Folder folder = imapStore.getFolder("INBOX");
		folder.open(Folder.READ_ONLY);
		
		Message[] msgs = folder.getMessages();
		System.out.println("messages.length---" + msgs.length);
		
		Message message = msgs[msgs.length-1];
		System.out.println("Subject: " + message.getSubject());
		
//		for (int i = msgs.length, n = msgs.length - 3; i > n; i--) {
//			Message message = msgs[i-1];
//			System.out.println("---------------------------------");
//			System.out.println("Email Number " + (i + 1));
//			System.out.println("Subject: " + message.getSubject());
//			System.out.println("From: " + message.getFrom()[0]);
//			System.out.println("From: " + message.getDescription());
//			}
		

	}

}
