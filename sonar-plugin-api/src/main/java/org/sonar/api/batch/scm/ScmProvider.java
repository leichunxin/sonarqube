/*
 * SonarQube, open source software quality management tool.
 * Copyright (C) 2008-2014 SonarSource
 * mailto:contact AT sonarsource DOT com
 *
 * SonarQube is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 3 of the License, or (at your option) any later version.
 *
 * SonarQube is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 */
package org.sonar.api.batch.scm;

import org.sonar.api.BatchExtension;
import org.sonar.api.batch.InstantiationStrategy;
import org.sonar.api.batch.fs.FileSystem;
import org.sonar.api.batch.fs.InputFile;

import java.io.File;
import java.util.List;

/**
 * @since 5.0
 */
@InstantiationStrategy(InstantiationStrategy.PER_BATCH)
public interface ScmProvider extends BatchExtension {

  /**
   * Unique identifier of the provider. Can be used in SCM URL to define the provider to use.
   */
  String key();

  /**
   * Does this provider able to manage files located in this directory.
   * Used by autodetection.
   */
  boolean supports(File baseDir);

  /**
   * Compute blame of the provided files. Computation can be done in parallel.
   * If there is an error that prevent to blame a file then an exception should be raised.
   */
  void blame(FileSystem fs, Iterable<InputFile> files, BlameResult result);

  /**
   * Callback for the provider to save results of blame per file.
   */
  public static interface BlameResult {

    void add(InputFile file, List<BlameLine> lines);

  }

}