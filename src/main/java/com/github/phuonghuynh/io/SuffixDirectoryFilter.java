package com.github.phuonghuynh.io;

import org.apache.commons.io.IOCase;
import org.apache.commons.io.filefilter.SuffixFileFilter;

import java.io.File;
import java.util.List;

/**
 * Created by phuonghqh on 11/21/15.
 */
public class SuffixDirectoryFilter extends SuffixFileFilter {

  public SuffixDirectoryFilter(String suffix) {
    super(suffix);
  }

  public SuffixDirectoryFilter(String suffix, IOCase caseSensitivity) {
    super(suffix, caseSensitivity);
  }

  public SuffixDirectoryFilter(String[] suffixes) {
    super(suffixes);
  }

  public SuffixDirectoryFilter(String[] suffixes, IOCase caseSensitivity) {
    super(suffixes, caseSensitivity);
  }

  public SuffixDirectoryFilter(List<String> suffixes) {
    super(suffixes);
  }

  public SuffixDirectoryFilter(List<String> suffixes, IOCase caseSensitivity) {
    super(suffixes, caseSensitivity);
  }

  public boolean accept(File file) {
    if (!file.isDirectory()) return false;
    return super.accept(file);
  }

  public boolean accept(File file, String name) {
    if (!file.isDirectory()) return false;
    return super.accept(file, name);
  }
}
