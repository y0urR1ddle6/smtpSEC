package l0cal.r1ddle;

import l0cal.r1ddle.application.Configuration;
import l0cal.r1ddle.application.Console;
import l0cal.r1ddle.socialEng.MailSchemas;

import java.util.ArrayList;


public class Main {

    private static final Configuration conf = Configuration.getConfig();
    private static MailSchemas mailSchemas = new MailSchemas();

    public static void main(String[] args) {
        showBanner();
        startInteractiveSession();
    }

    private static void startInteractiveSession(){
        new Console().run();
    }

    private static void showBanner(){
        System.out.println("███████╗███╗   ██╗███╗   ███╗██████╗ ███████╗███████╗██╗  ██╗\n" +
                "██╔════╝████╗  ██║████╗ ████║██╔══██╗██╔════╝██╔════╝██║ ██╔╝\n" +
                "███████╗██╔██╗ ██║██╔████╔██║██████╔╝███████╗█████╗  █████╔╝ \n" +
                "╚════██║██║╚██╗██║██║╚██╔╝██║██╔═══╝ ╚════██║██╔══╝  ██╔═██╗ \n" +
                "███████║██║ ╚████║██║ ╚═╝ ██║██║     ███████║███████╗██║  ██╗\n" +
                "╚══════╝╚═╝  ╚═══╝╚═╝     ╚═╝╚═╝     ╚══════╝╚══════╝╚═╝  ╚═╝\n" +
                "                                                             ");
        System.out.println("Starting interactive session...");
    }


    public static void saveSchemas(){
        System.out.println("saved");
        MailSchemas.saveSchemas();
    }

    public static void restoreSchemas(){
        mailSchemas = MailSchemas.restoreSchemas();

    }
    public static void createSchema(){
        mailSchemas.createMailSchema();
    }
    public static void showSchemas(){
        ArrayList<MailSchemas.MailSchema> mailSchemaArrayList = mailSchemas.getMailSchemasList();
        for(MailSchemas.MailSchema mailSchema : mailSchemaArrayList){
            System.out.println("Schema #" + mailSchemaArrayList.indexOf(mailSchema));
            System.out.println("-".repeat(70));
            System.out.println("Subject: " + mailSchema.getSubject());
            System.out.println(mailSchema.getBody());
            System.out.println("-".repeat(70));
        }
    }

    public static MailSchemas getMailSchemas() {
        return mailSchemas;
    }
}
