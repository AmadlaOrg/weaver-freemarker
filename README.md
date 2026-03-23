# weaver-freemarker

Amadla Weaver plugin for the Apache FreeMarker template engine, built with Spring Boot 4 and compiled as a native binary with GraalVM.

## Build

```bash
# JVM jar
make build

# Native binary (requires GraalVM with native-image)
make build-native

# Run tests
make test
```

## Usage

```bash
# Show plugin info
weaver-freemarker info

# Render a template with JSON input from stdin
echo '{"name": "nginx"}' | weaver-freemarker render -t config.ftl

# Render with YAML file input
weaver-freemarker render -t config.ftl -f data.yaml

# Render to output file
weaver-freemarker render -t config.ftl -f data.yaml -o output.conf
```

## FreeMarker Syntax

```
${name}                               - Variable
${server.host}                        - Nested access
<#list items as item>...</#list>      - Loop
<#if enabled>...<#else>...</#if>      - Conditional
```

See [FreeMarker Manual](https://freemarker.apache.org/docs/) for full syntax.

## License

Copyright (c) Amadla. All rights reserved.
