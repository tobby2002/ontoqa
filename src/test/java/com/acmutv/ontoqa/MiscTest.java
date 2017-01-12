/*
  The MIT License (MIT)

  Copyright (c) 2016 Antonella Botte, Giacomo Marciani and Debora Partigianoni

  Permission is hereby granted, free of charge, to any person obtaining a copy
  of this software and associated documentation files (the "Software"), to deal
  in the Software without restriction, including without limitation the rights
  to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
  copies of the Software, and to permit persons to whom the Software is
  furnished to do so, subject to the following conditions:


  The above copyright notice and this permission notice shall be included in
  all copies or substantial portions of the Software.


  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
  IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
  FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.  IN NO EVENT SHALL THE
  AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
  LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
  OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
  THE SOFTWARE.
 */

package com.acmutv.ontoqa;

import com.acmutv.ontoqa.core.lexicon.Lexicon;
import com.acmutv.ontoqa.core.lexicon.LexiconFormat;
import com.acmutv.ontoqa.core.lexicon.LexiconManager;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jgrapht.experimental.dag.DirectedAcyclicGraph;
import org.jgrapht.graph.SimpleDirectedGraph;
import org.jgrapht.traverse.AbstractGraphIterator;
import org.jgrapht.traverse.DepthFirstIterator;
import org.junit.Assert;
import org.junit.Test;

import java.io.*;
import java.util.*;

/**
 * JUnit tests (for personal use only)
 * @author Antonella Botte {@literal <abotte@acm.org>}
 * @author Giacomo Marciani {@literal <gmarciani@acm.org>}
 * @author Debora Partigianoni {@literal <dpartigianoni@acm.org>}
 * @since 1.0
 */
public class MiscTest {

  private static final Logger LOGGER = LogManager.getLogger(MiscTest.class);

  @Test
  public void test() throws IOException {
    LOGGER.info("message");
    LOGGER.warn("message");
    LOGGER.error("message");
    LOGGER.fatal("message");
    LOGGER.trace("message");
  }

  @Test
  public void test1() throws IOException {
    List<Integer> l = new ArrayList<>();
    l.add(1);
    l.add(3);
    System.out.println(l);

    l.add(1,2);
    System.out.println(l);
  }
}
