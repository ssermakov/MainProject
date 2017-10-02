package ru.ssermakov.connectToMailBox;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.UIDFolder;
import javax.mail.search.AndTerm;
import javax.mail.search.ComparisonTerm;
import javax.mail.search.ReceivedDateTerm;
import javax.mail.search.SearchTerm;

import com.sun.mail.imap.IMAPStore;

public class Connect {

	public static void main(String[] args) throws MessagingException, IOException, ParseException {
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
		
		UIDFolder uf = (UIDFolder)folder;
		
		String d1 = "03/10/17";
		String d2 = "01/10/17";
		SimpleDateFormat df1 = new SimpleDateFormat("dd/MM/yy"); 
		SimpleDateFormat df2 = new SimpleDateFormat("dd/MM/yy");
		Date someFutureDate = df1.parse(d1);
		Date somePastDate = df2.parse(d2);
		  
		SearchTerm olderThan = new ReceivedDateTerm(ComparisonTerm.LT, someFutureDate);
		SearchTerm newerThan = new ReceivedDateTerm(ComparisonTerm.GT, somePastDate);
		SearchTerm andTerm = new AndTerm(olderThan, newerThan);
		Message[] msgs = folder.search(andTerm);
		
//		Message message = msgs[msgs.length-1];
//		System.out.println("Subject: " + message.getSubject());
//		System.out.println("Date: " + message.getReceivedDate());
		
		for (int i = msgs.length, n = 0; i > n; i--) {
			Message message = msgs[i-1];
			System.out.println("---------------------------------");
			System.out.println("Email Number " + (i));
			System.out.println("Subject: " + message.getSubject());
			Long messageId = uf.getUID(msgs[i-1]);
			
			System.out.println("Date: " + message.getReceivedDate());
			System.out.println("ID: " + messageId);
			System.out.println("From: " + message.getFrom()[0]);
			System.out.println("From: " + message.getDescription());
			}
		

	}

}
