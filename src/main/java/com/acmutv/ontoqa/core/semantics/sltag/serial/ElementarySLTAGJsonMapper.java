/*
  The MIT License (MIT)

  Copyright (c) 2016 Giacomo Marciani and Michele Porretta

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

package com.acmutv.ontoqa.core.semantics.sltag.serial;

import com.acmutv.ontoqa.core.semantics.drs.Drs;
import com.acmutv.ontoqa.core.semantics.drs.serial.DrsDeserializer;
import com.acmutv.ontoqa.core.semantics.drs.serial.DrsSerializer;
import com.acmutv.ontoqa.core.semantics.dudes.Dudes;
import com.acmutv.ontoqa.core.semantics.dudes.serial.DudesDeserializer;
import com.acmutv.ontoqa.core.semantics.dudes.serial.DudesSerializer;
import com.acmutv.ontoqa.core.semantics.sltag.ElementarySLTAG;
import com.acmutv.ontoqa.core.semantics.sltag.SLTAG;
import com.acmutv.ontoqa.core.syntax.ltag.Ltag;
import com.acmutv.ontoqa.core.syntax.ltag.serial.LtagDeserializer;
import com.acmutv.ontoqa.core.syntax.ltag.serial.LtagSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;
import lombok.EqualsAndHashCode;

/**
 * The JSON constructor for {@link ElementarySLTAG}.
 * @author Antonella Botte {@literal <abotte@acm.org>}
 * @author Giacomo Marciani {@literal <gmarciani@acm.org>}
 * @author Debora Partigianoni {@literal <dpartigianoni@acm.org>}
 * @since 1.0
 * @see ElementarySLTAG
 * @see SLTAGSerializer
 * @see SLTAGDeserializer
 */
@EqualsAndHashCode(callSuper = true)
public class ElementarySLTAGJsonMapper extends ObjectMapper {

  /**
   * Initializes the JSON constructor.
   */
  public ElementarySLTAGJsonMapper() {
    super();
    SimpleModule module = new SimpleModule();
    module.addSerializer(ElementarySLTAG.class, ElementarySLTAGSerializer.getInstance());
    module.addSerializer(Ltag.class, LtagSerializer.getInstance());
    module.addSerializer(Dudes.class, DudesSerializer.getInstance());
    module.addSerializer(Drs.class, DrsSerializer.getInstance());
    module.addDeserializer(ElementarySLTAG.class, ElementarySLTAGDeserializer.getInstance());
    module.addDeserializer(Ltag.class, LtagDeserializer.getInstance());
    module.addDeserializer(Dudes.class, DudesDeserializer.getInstance());
    module.addDeserializer(Drs.class, DrsDeserializer.getInstance());
    super.registerModule(module);
    super.enable(SerializationFeature.INDENT_OUTPUT);
  }
}
