package com.github.eirslett.maven.plugins.frontend.lib;

import java.util.Map;

public interface EmberRunner {
    public void execute(String args, Map<String, String> environment) throws TaskRunnerException;
}

final class DefaultEmberRunner extends NodeTaskExecutor implements EmberRunner {

    private static final String TASK_LOCATION = "node_modules/ember-cli/bin/ember";

    DefaultEmberRunner(NodeExecutorConfig config) {
        super(config, TASK_LOCATION);
    }
}
