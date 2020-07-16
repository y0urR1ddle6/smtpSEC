package l0cal.r1ddle.smtp;

import l0cal.r1ddle.application.Configuration;

import javax.mail.Session;
import java.io.IOException;
import java.net.Socket;
import java.util.Properties;


public class SmtpConnection {
    private final Configuration configuration;

    public SmtpConnection(Configuration configuration){
        this.configuration = configuration;

    }

    public Session bind() throws SmtpOptionsException, SmtpConnectionException {
        if(!checkOptions(configuration))
            throw new SmtpOptionsException("MISCONFIGURATION");
        if(!pingHost(configuration))
            throw new SmtpConnectionException("HOST UNREACHABLE");

        Properties props = System.getProperties();

        props.put("mail.smtp.host", configuration.getIp());
        props.put("mail.smtp.port", configuration.getPort());
        if(configuration.isAuth()){
            props.put("mail.smtp.user", configuration.getLogin());
            props.put("mail.smtp.password", configuration.getPassword());
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");
        }
        return Session.getDefaultInstance(props);
    }

    private boolean checkOptions(Configuration configuration){
        return !configuration.getIp().equals("") && !configuration.getEmailFrom().equals("");
    }

    private boolean pingHost(Configuration configuration){
        boolean result = true;
        try {
            Socket socket = new Socket(configuration.getIp(),configuration.getPort());
            System.out.println("Connected -> " + socket.isConnected());
        } catch (IOException ioException) {
            ioException.getMessage();
            result = false;
        }

        return result;
    }
}
