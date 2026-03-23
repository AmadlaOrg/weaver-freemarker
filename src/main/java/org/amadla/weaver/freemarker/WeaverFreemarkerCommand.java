package org.amadla.weaver.freemarker;

import org.springframework.stereotype.Component;
import picocli.CommandLine.Command;

@Component
@Command(
        name = "weaver-freemarker",
        description = "FreeMarker template engine plugin for Amadla Weaver",
        mixinStandardHelpOptions = true,
        version = "weaver-freemarker 1.0.0",
        subcommands = {
                InfoCommand.class,
                RenderCommand.class
        }
)
public class WeaverFreemarkerCommand implements Runnable {

    @Override
    public void run() {
        new picocli.CommandLine(this).usage(System.out);
    }
}
