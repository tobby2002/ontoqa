/*
  The MIT License (MIT)

  Copyright (c) 2017 Antonella Botte, Giacomo Marciani and Debora Partigianoni

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

package com.acmutv.ontoqa.core.grammar;

import com.acmutv.ontoqa.core.semantics.sltag.ElementarySLTAG;

import java.util.*;

/**
 * A simple grammar.
 * @author Antonella Botte {@literal <abotte@acm.org>}
 * @author Giacomo Marciani {@literal <gmarciani@acm.org>}
 * @author Debora Partigianoni {@literal <dpartigianoni@acm.org>}
 * @since 1.0
 */
public class SimpleGrammar implements Grammar {

  /**
   * The map of elementary SLTAGs.
   */
  private Map<String, Set<ElementarySLTAG>> elementarySLTAG = new HashMap<>();

  /**
   * Returns the set of all elementary SLTAGs.
   * @return the set of all elementary SLTAGs.
   */
  @Override
  public Set<ElementarySLTAG> getAllElementarySLTAG() {
    Set<ElementarySLTAG> all = new HashSet<>();
    this.elementarySLTAG.values().forEach(all::addAll);
    return all;
  }

  /**
   * Adds {@code sltag} to the grammar, as a new elementary SLTAG for {@code word}.
   * @param sltag the SLTAG to add.
   * @return true if {@code sltag} has been added; false otherwise.
   */
  @Override
  public boolean addElementarySLTAG(ElementarySLTAG sltag) {
    String word = sltag.getReference();
    this.elementarySLTAG.putIfAbsent(word, new HashSet<>());
    return this.elementarySLTAG.get(word).add(sltag);
  }

  /**
   * Returns the set of elementary SLTAG for {@code word}.
   * @param word the word.
   * @return the set of elementary SLTAG for {@code word}.
   */
  @Override
  public Set<ElementarySLTAG> getAllElementarySLTAG(String word) {
    return this.elementarySLTAG.get(word);
  }
}
