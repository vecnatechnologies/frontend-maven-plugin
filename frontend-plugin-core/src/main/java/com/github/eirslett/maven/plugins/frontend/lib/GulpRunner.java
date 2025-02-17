package com.github.eirslett.maven.plugins.frontend.lib;

import java.util.Arrays;
import java.util.Map;

public interface GulpRunner {
    public void execute(String args, Map<String, String> environment) throws TaskRunnerException;
}

final class DefaultGulpRunner extends NodeTaskExecutor implements GulpRunner {
    private static final String TASK_LOCATION = "node_modules/gulp/bin/gulp.js";

    DefaultGulpRunner(NodeExecutorConfig config) {
        super(config, TASK_LOCATION, Arrays.asList("--no-color"));
    }
}
