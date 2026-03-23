package org.amadla.weaver.freemarker;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.StringWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;

@Component
public class TemplateRenderer {

    public String render(String templatePath, Map<String, Object> data) throws IOException {
        String templateContent = Files.readString(Path.of(templatePath));

        Configuration cfg = new Configuration(Configuration.VERSION_2_3_34);
        cfg.setDefaultEncoding("UTF-8");
        cfg.setNumberFormat("computer");

        Template template = new Template("template", templateContent, cfg);

        StringWriter writer = new StringWriter();
        try {
            template.process(data, writer);
        } catch (TemplateException e) {
            throw new IOException("Template processing failed: " + e.getMessage(), e);
        }

        return writer.toString();
    }
}
