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

package com.acmutv.ontoqa.core.parser;

import com.acmutv.ontoqa.core.parser.conflict.Candidate;
import com.acmutv.ontoqa.core.parser.conflict.ConflictList;
import com.acmutv.ontoqa.core.semantics.base.statement.Statement;
import com.acmutv.ontoqa.core.semantics.base.term.Variable;
import com.acmutv.ontoqa.core.semantics.sltag.Sltag;
import lombok.Data;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.commons.lang3.tuple.Triple;

import java.util.*;

/**
 * The parse stack.
 * @author Antonella Botte {@literal <abotte@acm.org>}
 * @author Giacomo Marciani {@literal <gmarciani@acm.org>}
 * @author Debora Partigianoni {@literal <dpartigianoni@acm.org>}
 * @since 1.0
 */
@Data
public class ParserStateNew {

  /**
   * Current SLTAG.
   */
  Sltag curr;

  /**
   * True if ASK semantics.
   */
  boolean ask = false;

  /**
   * The list of words.
   */
  private List<String> words = new ArrayList<>();

  /**
   * The id of the previous handled lexical entry.
   */
  private Integer idxPrev;

  /**
   * The list of waiting substitutions.
   */
  private List<Candidate> waitSubstitutions = new ArrayList<>();

  /**
   * The list of waiting adjunctions.
   */
  private List<Candidate> waitAdjunction = new ArrayList<>();

  /**
   * The conflicts list.
   */
  private ConflictList conflictList = new ConflictList();

  /**
   * Main variable miss.
   */
  Map<Integer, Triple<Variable,Variable,Set<Statement>>> missedMainVariables = new HashMap<>();

  public ParserStateNew(String sentence) {
    this.words.addAll(Arrays.asList(sentence.split(" ")));
  }

  public void addWaitingSubstitution(Sltag candidate, Integer idxPrev) {
    this.waitSubstitutions.add(new Candidate(candidate, idxPrev));
  }

  public void addWaitingAdjunction(Sltag candidate, Integer idxPrev) {
    this.waitAdjunction.add(new Candidate(candidate, idxPrev));
  }
}
