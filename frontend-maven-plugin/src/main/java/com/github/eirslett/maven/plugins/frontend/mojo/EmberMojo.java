package com.github.eirslett.maven.plugins.frontend.mojo;

import com.github.eirslett.maven.plugins.frontend.lib.FrontendPluginFactory;
import com.github.eirslett.maven.plugins.frontend.lib.TaskRunnerException;
import org.apache.maven.plugins.annotations.Component;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.sonatype.plexus.build.incremental.BuildContext;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Mojo(name="ember", defaultPhase = LifecyclePhase.GENERATE_RESOURCES)
public final class EmberMojo extends AbstractFrontendMojo {

    /**
     * Grunt arguments. Default is empty (runs just the "grunt" command).
     */
    @Parameter(property = "frontend.ember.arguments")
    private String arguments;

    /**
     * Files that should be checked for changes, in addition to the srcdir files.
     * {@link #workingDirectory}.
     */
    @Parameter(property = "triggerfiles")
    private List<File> triggerfiles;

    /**
     * The directory containing front end files that will be processed by grunt.
     * If this is set then files in the directory will be checked for
     * modifications before running grunt.
     */
    @Parameter(property = "srcdir")
    private File srcdir;

    /**
     * The directory where front end files will be output by grunt. If this is
     * set then they will be refreshed so they correctly show as modified in
     * Eclipse.
     */
    @Parameter(property = "outputdir")
    private File outputdir;

    /**
     * Additional environment variables to pass to the build.
     */
    @Parameter
    private Map<String, String> environmentVariables;

    /**
     * Skips execution of this mojo.
     */
    @Parameter(property = "skip.ember", defaultValue = "false")
    private Boolean skip;

    @Component
    private BuildContext buildContext;

    @Override
    protected boolean isSkipped() {
        return this.skip;
    }

    @Override
    public void execute(FrontendPluginFactory factory) throws TaskRunnerException {
        if (shouldExecute()) {
            factory.getEmberRunner().execute(arguments, environmentVariables);

            if (outputdir != null) {
                getLog().info("Refreshing files after ember: " + outputdir);
                buildContext.refresh(outputdir);
            }
        } else {
            getLog().info("Skipping ember as no modified files in " + srcdir);
        }
    }

    private boolean shouldExecute() {

        if (triggerfiles == null || triggerfiles.isEmpty()) {
            triggerfiles = Arrays.asList(new File(workingDirectory, "Gruntfile.js"));
        }

        return MojoUtils.shouldExecute(buildContext, triggerfiles, srcdir);
    }

}