package l0cal.r1ddle.application;

import l0cal.r1ddle.Main;
import l0cal.r1ddle.application.stuff.Option;
import l0cal.r1ddle.smtp.SmtpConnection;
import l0cal.r1ddle.smtp.SmtpConnectionException;
import l0cal.r1ddle.smtp.SmtpOptionsException;
import l0cal.r1ddle.socialEng.MailStuff;

import javax.mail.Session;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Field;
import java.util.Scanner;

public enum Commandlet {

    help(() -> {
        for(Commandlet commandlet : Commandlet.values()){
            System.out.println(commandlet.name() + ": "+ commandlet.description);
        }
    },"Show this message"),

    clear(() -> {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }, "Clear terminal screen"),

    set(()-> {
        Configuration configuration =  Configuration.getConfig();
        boolean found = false;

        Scanner scanner = new Scanner(System.in);
        String key,value;

        System.out.print("Enter key: ");
        key = scanner.nextLine().toLowerCase();

        for(Field field : configuration.getClass().getFields()){
            if(field.isAnnotationPresent(Option.class) && field.getName().toLowerCase().equals(key)){
                found = true;
                break;
            }
        }
        if(!found){
            System.out.println("key not found... ");
            return;
        }

        System.out.print("Enter value: ");
        value = scanner.nextLine();

        // STUPID WAY: I forgot about dict
        switch (key){
            case "ip": configuration.setIp(value); break;
            case "emailto": configuration.setEmailTo(value); break;
            case "emailfrom": configuration.setEmailFrom(value); break;
            case "port": configuration.setPort(Integer.parseInt(value)); break;
            case "auth":
                configuration.setAuth(Boolean.parseBoolean(value));
                System.out.print("Enter username: ");
                configuration.setLogin(scanner.nextLine());
                System.out.print("Enter password: ");
                configuration.setPassword(scanner.nextLine());
                break;
            case "spam":
                configuration.setSpam(Boolean.parseBoolean(value));
                System.out.print("Enter emails file path: ");
                configuration.setEmailsPath(scanner.nextLine());
                System.out.print("Enter schema number: ");
                configuration.setSchemaNmb(scanner.nextInt());
        }



    }, "Set some values"),
    run(()->{
        Configuration configuration =  Configuration.getConfig();
        SmtpConnection smtpConnection = new SmtpConnection(configuration);
        Session mailSession = smtpConnection.bind();
        MailStuff mailStuff = new MailStuff(configuration);
        if(configuration.isSpam()){
            Scanner scanner = new Scanner(new FileReader(configuration.getEmailsPath()));
            while (scanner.hasNextLine()){
                String email = scanner.nextLine();
                System.out.println("[+] sending mail to " + email);
                mailStuff.sendMail(mailSession, email);
            }
        }else {
            mailStuff.sendMail(mailSession,configuration.getEmailTo());
        }
    },"Start attack"),
    show(()->{
        Configuration configuration = Configuration.getConfig();
        System.out.println("ip : " + configuration.getIp());
        System.out.println("port: " + configuration.getPort());
        System.out.println("emailFrom: " + configuration.getEmailFrom());
        System.out.println("emailTo: " + configuration.getEmailTo());

    },"Show options"),
    create(Main::createSchema,"Create mail schema"),
    save(Main::saveSchemas,"Save mail schemas"),
    restore(Main::restoreSchemas,"Restore schemas"),
    schemas(Main::showSchemas,"Show schemas");


    public Command command;
    public String description;

    Commandlet(Command command, String description){
        this.command = command;
        this.description = description;
    }

    public void exec() throws SmtpOptionsException, SmtpConnectionException, FileNotFoundException {
        command.exec();
    }


}
