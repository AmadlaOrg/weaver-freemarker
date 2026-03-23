package org.amadla.weaver.freemarker;

import org.springframework.boot.ExitCodeGenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import picocli.CommandLine;
import picocli.CommandLine.IFactory;

@SpringBootApplication
public class WeaverFreemarkerApplication implements ExitCodeGenerator {

    private final WeaverFreemarkerCommand command;
    private final IFactory factory;
    private int exitCode;

    public WeaverFreemarkerApplication(WeaverFreemarkerCommand command, IFactory factory) {
        this.command = command;
        this.factory = factory;
    }

    @Override
    public int getExitCode() {
        return exitCode;
    }

    public void run(String... args) {
        exitCode = new CommandLine(command, factory).execute(args);
    }

    public static void main(String[] args) {
        System.setProperty("logback.statusListenerClass", "ch.qos.logback.core.status.NopStatusListener");
        var ctx = SpringApplication.run(WeaverFreemarkerApplication.class, args);
        var app = ctx.getBean(WeaverFreemarkerApplication.class);
        app.run(args);
        System.exit(app.getExitCode());
    }
}
