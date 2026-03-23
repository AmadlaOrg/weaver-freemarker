package org.amadla.weaver.freemarker;

import org.springframework.stereotype.Component;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;

@Component
@Command(name = "render", description = "Render a FreeMarker template")
public class RenderCommand implements Runnable {

    @Option(names = {"-t", "--template"}, description = "Path to the FreeMarker template file", required = true)
    String templatePath;

    @Option(names = {"-f", "--file"}, description = "Input data file path (- for stdin)", defaultValue = "-")
    String filePath;

    @Option(names = {"-o", "--output"}, description = "Output file path (default: stdout)")
    String outputPath;

    private final InputLoader inputLoader;
    private final TemplateRenderer renderer;

    public RenderCommand(InputLoader inputLoader, TemplateRenderer renderer) {
        this.inputLoader = inputLoader;
        this.renderer = renderer;
    }

    @Override
    public void run() {
        try {
            Map<String, Object> data = inputLoader.load(filePath);
            String result = renderer.render(templatePath, data);

            if (outputPath != null) {
                Files.writeString(Path.of(outputPath), result);
            } else {
                System.out.print(result);
            }
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
            System.exit(1);
        }
    }
}
