package l0cal.r1ddle.application;


import l0cal.r1ddle.application.stuff.Option;

public class Configuration {

    private static Configuration context;

    @Option
    public static String ip = "";
    @Option
    public static int port = 25;
    @Option
    public static String emailFrom = "";
    @Option
    public static String emailTo = "";
    @Option
    public static String realEmailFrom = "";
    @Option
    public static String realEmailTo = "";
    @Option
    public static boolean auth = false;
    @Option
    private static String login = "";
    @Option
    private static String password = "";
    // @Option UNDER CONSTRUCTION
    public static boolean spam = false;

    private static final boolean verbose = false;
    private static String emailsPath = "";

    private Configuration(){

    }

    public static Configuration getConfig(){
        if(context == null){
            context = new Configuration();
        }
        return context;
    }

    public  boolean isVerbose() {
        return verbose;
    }

    public  int getPort() {
        return port;
    }

    public  void setPort(int port) {
        Configuration.port = port;
    }

    public String getIp() {
        return ip;
    }

    public  void setIp(String ip) {
        Configuration.ip = ip;
    }

    public  String getRealEmailTo() {
        return realEmailTo;
    }

    public  void setRealEmailTo(String realEmailTo) {
        Configuration.realEmailTo = realEmailTo;
    }

    public  String getRealEmailFrom() {
        return realEmailFrom;
    }

    public  void setRealEmailFrom(String realEmailFrom) {
        Configuration.realEmailFrom = realEmailFrom;
    }

    public  String getEmailTo() {
        return emailTo;
    }

    public  void setEmailTo(String emailTo) {
        Configuration.emailTo = emailTo;
    }

    public String getEmailFrom() {
        return emailFrom;
    }

    public  void setEmailFrom(String emailFrom) {
        Configuration.emailFrom = emailFrom;
    }

    public boolean isAuth() {
        return auth;
    }

    public void setAuth(boolean rAuth) {
        Configuration.auth = rAuth;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        Configuration.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        Configuration.password = password;
    }

    public boolean isSpam() {
        return spam;
    }

    public void setSpam(boolean spam) {
        Configuration.spam = spam;
    }

    public String getEmailsPath() {
        return emailsPath;
    }

    public void setEmailsPath(String emailsPath) {
        Configuration.emailsPath = emailsPath;
    }
}
