package com.github.eirslett.maven.plugins.frontend.lib;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface NpmRunner {
    public void execute(String args, Map<String, String> environment) throws TaskRunnerException;
}

final class DefaultNpmRunner extends NodeTaskExecutor implements NpmRunner {
    static final String TASK_NAME = "npm";

    public DefaultNpmRunner(NodeExecutorConfig config, ProxyConfig proxy) {
        super(config, TASK_NAME, config.getNpmPath().getAbsolutePath(), buildArguments(proxy));
    }

    private static List<String> buildArguments(ProxyConfig proxy) {
        List<String> arguments = new ArrayList<String>();
        arguments.add("--color=false");
        if (proxy != null) {
            if(proxy.isSecure()){
                arguments.add("--https-proxy=" + proxy.getUri().toString());
            } else {
                arguments.add("--proxy=" + proxy.getUri().toString());
            }
        }
        return arguments;
    }
}
