package l0cal.r1ddle.socialEng;

import l0cal.r1ddle.Main;
import l0cal.r1ddle.application.Configuration;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Scanner;

public class MailStuff {
    private final Configuration configuration;

    public MailStuff(Configuration configuration) {
        this.configuration = configuration;
    }

    public void sendMail(Session session,String recipient){
        // BODY & HEAD
        MailSchemas.MailSchema mailSchema;
        int n;
        if(!configuration.isSpam()){
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter email schema number: ");
            n = scanner.nextInt();
        }else {
           n = configuration.getSchemaNmb();
        }
        try{
            mailSchema = Main.getMailSchemas().getMailSchemasList().get(n);
        }catch (Exception e){
            System.out.println("Schema not found");
            return;
        }

        String[] to = { recipient }; // list of recipient email addresses
        //LOGIC
        MimeMessage message = new MimeMessage(session);
        try {
            message.setFrom(new InternetAddress(configuration.getEmailFrom()));
            InternetAddress[] toAddress = new InternetAddress[to.length];
            for( int i = 0; i < to.length; i++ ) {
                toAddress[i] = new InternetAddress(to[i]);
            }
            for (InternetAddress address : toAddress) {
                message.addRecipient(Message.RecipientType.TO, address);
            }

            message.setSubject(mailSchema.getSubject());
            message.setText(mailSchema.getBody());
            Transport transport = session.getTransport("smtp");
            transport.connect(configuration.getIp(), configuration.getLogin(),configuration.getPassword());
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();

        } catch (MessagingException ae) {
            ae.printStackTrace();
        }

    }

}
