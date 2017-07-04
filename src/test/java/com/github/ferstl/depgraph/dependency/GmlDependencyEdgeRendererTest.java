/*
 * Copyright (c) 2014 - 2017 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.github.ferstl.depgraph.dependency;

import org.junit.Test;

import static com.github.ferstl.depgraph.dependency.DependencyNodeUtil.createDependencyNode;
import static com.github.ferstl.depgraph.dependency.DependencyNodeUtil.createDependencyNodeWithConflict;
import static org.junit.Assert.assertEquals;

public class GmlDependencyEdgeRendererTest {

  @Test
  public void renderWithoutVersion() {
    // arrange
    GmlDependencyEdgeRenderer renderer = new GmlDependencyEdgeRenderer(false);
    DependencyNode from = createDependencyNode("group1", "artifact1", "version1");
    DependencyNode to = createDependencyNode("group2", "artifact2", "version2");

    // act
    String result = renderer.render(from, to);

    // assert
    assertEquals("", result);
  }

  @Test
  public void renderWithNonConflictingVersion() {
    // arrange
    GmlDependencyEdgeRenderer renderer = new GmlDependencyEdgeRenderer(true);
    DependencyNode from = createDependencyNode("group1", "artifact1", "version1");
    DependencyNode to = createDependencyNode("group2", "artifact2", "version2");

    // act
    String result = renderer.render(from, to);

    // assert
    assertEquals("", result);
  }

  @Test
  public void renderWithConflictingVersion() {
    // arrange
    GmlDependencyEdgeRenderer renderer = new GmlDependencyEdgeRenderer(true);
    DependencyNode from = createDependencyNode("group1", "artifact1", "version1");
    DependencyNode to = createDependencyNodeWithConflict("group2", "artifact2", "version2");

    // act
    String result = renderer.render(from, to);

    // assert
    assertEquals("label \"version2\"\n"
        + "graphics\n"
        + "[\n"
        + "style \"dashed\"\n"
        + "targetArrow \"standard\"\n"
        + "fill \"#FF0000\"\n"
        + "]\n"
        + "LabelGraphics\n"
        + "[\n"
        + "color \"#FF0000\"\n"
        + "]", result);
  }
}
