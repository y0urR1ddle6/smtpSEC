package l0cal.r1ddle.application;

import l0cal.r1ddle.smtp.SmtpConnectionException;
import l0cal.r1ddle.smtp.SmtpOptionsException;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class Console {

    @SuppressWarnings("InfiniteLoopStatement")
    public void run(){
        Scanner scanner = new Scanner(System.in);
        String cmd;

        while (true){
            System.out.print("$ ");
            cmd = scanner.nextLine();
            parseCommand(cmd);
        }
    }

    private void parseCommand(String cmd){
        boolean found = false;
        for(Commandlet commandlet : Commandlet.values()){
            if(cmd.toLowerCase().equals(commandlet.name())){
               found = true;
                try {
                    commandlet.exec();
                } catch (SmtpOptionsException | SmtpConnectionException | FileNotFoundException e) {
                    System.out.println(e.getMessage());
                }
                break;
            }
        }
        if(!found){
            System.out.println("Command not found...");
        }
    }
}
