package com.github.eirslett.maven.plugins.frontend.lib;

import java.util.Arrays;
import java.util.Map;

public interface KarmaRunner {
    public void execute(String args, Map<String, String> environment) throws TaskRunnerException;
}

final class DefaultKarmaRunner extends NodeTaskExecutor implements KarmaRunner {

    static final String TASK_LOCATION = "node_modules/karma/bin/karma";

    DefaultKarmaRunner(NodeExecutorConfig config) {
        super(config, TASK_LOCATION, Arrays.asList("--no-colors"));
    }
}
