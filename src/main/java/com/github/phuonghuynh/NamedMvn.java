package com.github.phuonghuynh;

import com.github.phuonghuynh.io.SuffixDirectoryFilter;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugin.logging.Log;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.shared.invoker.*;
import org.codehaus.plexus.util.StringUtils;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

/**
 * Build maven projects by name
 *
 * @author phuonghqh
 */
@Mojo(name = "named-mvn")
public class NamedMvn extends AbstractMojo {

  public static final String SUFFIX = "suffix";
  public static final String PREFIX = "prefix";

  private final Log log;

  /**
   * Working directory, default is current directory
   */
  @Parameter(property = "workingDirectory", required = false)
  private String workingDirectory;

  /**
   * Name of maven projects
   */
  @Parameter(property = "name", required = true)
  private String name;

  /**
   * Type of name, value should be "prefix", "suffix" (default value)
   */
  @Parameter(property = "type", required = false)
  private String type;

  public NamedMvn() {
    log = getLog();
  }

  /**
   * Main processing
   *
   * @return
   * @throws {@link MojoExecutionException}
   * @throws {@link MojoFailureException}
   */
  public void execute() throws MojoExecutionException, MojoFailureException {
    try {

      if (StringUtils.isBlank(workingDirectory)) {
        workingDirectory = ".";
      }

      if (!PREFIX.equalsIgnoreCase(type)) {
        type = SUFFIX;
      }

      File workingDir = new File(workingDirectory);
      String workingDirPath = workingDir.getCanonicalPath();
      String[] projectDirs = SUFFIX.equalsIgnoreCase(type) ? workingDir.list(new SuffixDirectoryFilter(name)) : new String[]{};

      for (int i = 0; i < projectDirs.length; i++) {
        try {
          log.debug("Execute project " + projectDirs[i]);
          InvocationRequest request = new DefaultInvocationRequest();
          request.setPomFile(new File(String.format("%s/%s/pom.xml", workingDirPath, projectDirs[i])));
          request.setGoals(Arrays.asList("clean", "install"));

          Invoker invoker = new DefaultInvoker();
          InvocationResult invocationResult = invoker.execute(request);
          if (invocationResult.getExitCode() != 0) {
            throw new MavenInvocationException("Build failed.", invocationResult.getExecutionException());
          }
        }
        catch (MavenInvocationException e) {
          log.error("Failed execute project " + projectDirs[i], e);
          throw new IllegalStateException("Failed execute project " + projectDirs[i], e);
        }
      }
    }
    catch (IOException e) {
      log.error("Invalid working directory", e);
    }
  }
}
