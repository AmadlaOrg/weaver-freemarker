package org.amadla.weaver.freemarker;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;
import picocli.CommandLine.Command;

import java.util.List;
import java.util.Map;

@Component
@Command(name = "info", description = "Show plugin metadata")
public class InfoCommand implements Runnable {

    private static final ObjectMapper MAPPER = new ObjectMapper();

    @Override
    public void run() {
        try {
            Map<String, Object> metadata = Map.of(
                    "name", "weaver-freemarker",
                    "version", "1.0.0",
                    "engine", "freemarker",
                    "supports", List.of("amadla.org/entity/template@^v1.0.0"),
                    "file_extensions", List.of(".ftl"),
                    "description", "FreeMarker template engine plugin for Weaver"
            );
            String json = MAPPER.writerWithDefaultPrettyPrinter().writeValueAsString(metadata);
            System.out.println(json);
        } catch (Exception e) {
            System.err.println("Error encoding metadata: " + e.getMessage());
            System.exit(1);
        }
    }
}
