package l0cal.r1ddle.application;

import l0cal.r1ddle.smtp.SmtpConnectionException;
import l0cal.r1ddle.smtp.SmtpOptionsException;

@FunctionalInterface
public interface Command {
    void exec() throws SmtpOptionsException, SmtpConnectionException;
}
