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

import com.acmutv.ontoqa.core.semantics.dudes.Dudes;
import com.acmutv.ontoqa.core.semantics.sltag.ElementarySLTAG;
import com.acmutv.ontoqa.core.semantics.sltag.SLTAG;
import com.acmutv.ontoqa.core.semantics.sltag.SimpleElementarySLTAG;
import com.acmutv.ontoqa.core.semantics.sltag.SimpleSLTAG;
import com.acmutv.ontoqa.core.syntax.ltag.Ltag;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;

/**
 * The JSON deserializer for {@link ElementarySLTAG}.
 * @author Antonella Botte {@literal <abotte@acm.org>}
 * @author Giacomo Marciani {@literal <gmarciani@acm.org>}
 * @author Debora Partigianoni {@literal <dpartigianoni@acm.org>}
 * @since 1.0
 * @see ElementarySLTAG
 * @see ElementarySLTAGSerializer
 */
public class ElementarySLTAGDeserializer extends StdDeserializer<ElementarySLTAG> {

  /**
   * The singleton of {@link ElementarySLTAGDeserializer}.
   */
  private static ElementarySLTAGDeserializer instance;

  /**
   * Returns the singleton of {@link ElementarySLTAGDeserializer}.
   * @return the singleton.
   */
  public static ElementarySLTAGDeserializer getInstance() {
    if (instance == null) {
      instance = new ElementarySLTAGDeserializer();
    }
    return instance;
  }

  /**
   * Initializes the singleton of {@link ElementarySLTAGDeserializer}.
   */
  private ElementarySLTAGDeserializer() {
    super((Class<ElementarySLTAG>) null);
  }


  @Override
  public ElementarySLTAG deserialize(JsonParser parser, DeserializationContext ctx) throws IOException {
    JsonNode node = parser.getCodec().readTree(parser);

    if (!node.hasNonNull("word") ||
        !node.hasNonNull("syntax") ||
        !node.hasNonNull("interpretation")) {
      throw new IOException("Cannot read [word,syntax,interpretation].");
    }

    final String word = node.get("word").asText();

    final Ltag ltag = ctx.readValue(node.get("syntax").traverse(parser.getCodec()), Ltag.class);

    final Dudes dudes = ctx.readValue(node.get("interpretation").traverse(parser.getCodec()), Dudes.class);

    return new SimpleElementarySLTAG(word, ltag, dudes);
  }
}
