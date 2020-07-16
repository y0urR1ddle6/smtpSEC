package l0cal.r1ddle.socialEng;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

public class MailSchemas implements Serializable{
    public static ArrayList<MailSchema> mailSchemasList = new ArrayList<>();

    public static class MailSchema implements Serializable {
        private final String body;
        private final String subject;

        public MailSchema(String subject, String body) {
            this.body = body;
            this.subject = subject;
        }

        public String getBody() {
            return body;
        }

        public String getSubject() {
            return subject;
        }
    }

    public void createMailSchema(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter email subject: ");
        String subject = scanner.nextLine();
        System.out.println("Enter email body. You can use html (type '[end]' to send mail)");
        StringBuilder bodyBuilder = new StringBuilder();
        String append;
        while (true){
            append = scanner.nextLine();
            if(append.toLowerCase().equals("[end]"))
                break;
            bodyBuilder.append(append);
        }
        String body = bodyBuilder.toString();
        mailSchemasList.add(new MailSchema(subject,body));
    }

    public static void addMailSchema(String sbj, String body){
        mailSchemasList.add(new MailSchema(sbj,body));
    }
    public ArrayList<MailSchema> getMailSchemasList() {
        return mailSchemasList;
    }

    public static MailSchemas restoreSchemas(){
        MailSchemas mailSchemas = new MailSchemas();
        try {
            FileReader fileReader = new FileReader("schemas.dat");
            Scanner scanner = new Scanner(fileReader);

            while(scanner.hasNextLine()){
               //     System.out.println();
                    String obj = scanner.nextLine();
                    String title = obj.substring(0,obj.indexOf("[:]"));
                    String body = obj.substring(obj.indexOf("[:]") + 3 ,obj.length() - 3);
                    addMailSchema(title,body);
                    //System.out.println();
            }
            fileReader.close();
        } catch (IOException  ioException) {
            ioException.printStackTrace();
        }
        return mailSchemas;
    }
    public static void saveSchemas(){
        try {
            FileWriter sw = new FileWriter("schemas.dat");
            for(MailSchema mailSchema : mailSchemasList){
                System.out.println(mailSchema.subject + "[:]" + mailSchema.body + "[^]");
                sw.write(mailSchema.subject + "[:]" + mailSchema.body + "[^]\n");
            }
            sw.flush();
            sw.close();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }
}
