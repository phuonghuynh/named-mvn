package com.github.phuonghuynh;

import org.apache.commons.lang.reflect.FieldUtils;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.junit.Before;
import org.junit.Test;

public class NamedMvnTest {

  private NamedMvn namedMvn = new NamedMvn();

  @Before
  public void before() throws IllegalAccessException, NoSuchFieldException, SecurityException {
    FieldUtils.writeField(FieldUtils.getField(NamedMvn.class, "workingDirectory", true), namedMvn,
      "src/test/projects", true);
    FieldUtils.writeField(FieldUtils.getField(NamedMvn.class, "name", true), namedMvn,
      "test", true);
  }

  @Test
  public void testNamedMvn() throws MojoExecutionException, MojoFailureException {
    namedMvn.execute();
  }
}
