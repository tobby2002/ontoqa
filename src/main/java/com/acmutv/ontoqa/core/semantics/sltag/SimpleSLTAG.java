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

package com.acmutv.ontoqa.core.semantics.sltag;

import com.acmutv.ontoqa.core.exception.LTAGException;
import com.acmutv.ontoqa.core.semantics.dudes.SimpleDudes;
import com.acmutv.ontoqa.core.semantics.dudes.Dudes;
import com.acmutv.ontoqa.core.syntax.ltag.Ltag;
import com.acmutv.ontoqa.core.syntax.ltag.LtagNode;
import com.acmutv.ontoqa.core.syntax.ltag.SimpleLtag;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NonNull;

/**
 * A simple Semantic Ltag.
 * @author Antonella Botte {@literal <abotte@acm.org>}
 * @author Giacomo Marciani {@literal <gmarciani@acm.org>}
 * @author Debora Partigianoni {@literal <dpartigianoni@acm.org>}
 * @since 1.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SimpleSLTAG extends SimpleLtag implements SLTAG {

  @NonNull
  protected Dudes interpretation = new SimpleDudes();

  public SimpleSLTAG(Ltag ltag, Dudes interpretation) {
    super(ltag);
    this.interpretation = interpretation;
  }

  @Override
  public void substitution(LtagNode target, SLTAG other) throws LTAGException {
    super.substitution(target, other);
    this.interpretation.merge(other.getInterpretation(), target.getLabel()+target.getId());
  }

  @Override
  public String toPrettyString() {
    return String.format("%s\n\n%s", super.toPrettyString(), this.interpretation.toPrettyString());
  }
}
