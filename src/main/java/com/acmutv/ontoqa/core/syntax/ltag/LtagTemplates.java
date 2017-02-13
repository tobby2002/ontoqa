/*
  The MIT License (MIT)

  Copyright (c) 2017 Giacomo Marciani

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
package com.acmutv.ontoqa.core.syntax.ltag;

import com.acmutv.ontoqa.core.syntax.POS;

/**
 * A collection of common LTAG templates generators
 * @author Antonella Botte {@literal <abotte@acm.org>}
 * @author Giacomo Marciani {@literal <gmarciani@acm.org>}
 * @author Debora Partigianoni {@literal <dpartigianoni@acm.org>}
 * @since 1.0
 */
public class LtagTemplates {

  /**
   * Generates a LTAG representing a proper noun.
   * @param noun the proper noun.
   * @return the LTAG representing the specified proper noun.
   */
  public static Ltag properNoun(String noun) {
    LtagNode dp = new PosNode("DP1", POS.DP);
    LtagNode lex = new LexicalNode("LEX:"+noun, noun);

    Ltag template = new BaseLtag(dp);
    template.addProduction(dp, lex);

    return template;
  }

  /**
   * Generates a LTAG representing a class noun.
   * @param noun the class noun.
   * @param generic whether or not the class noun must be generic.
   * @return the LTAG representing the specified class noun.
   */
  public static Ltag classNoun(String noun, boolean generic) {
    LtagNode np = new PosNode("NP1", POS.NP);
    LtagNode lex = new LexicalNode("LEX:"+noun, noun);

    Ltag template;

    if (generic) {
      LtagNode dp = new PosNode("DP1", POS.DP);
      template = new BaseLtag(dp);
      template.addProduction(dp, np);
    } else {
      template = new BaseLtag(np);
    }

    template.addProduction(np, lex);

    return template;
  }

  public static Ltag classNounPrepositional(String noun,
                                            String preposition, String prepositionAnchor,
                                            boolean generic) {
    //TODO
    return null;
  }

  public static Ltag cause(String cause, String causeAnchor) {
    //TODO
    return null;
  }

  /**
   * Generates a LTAG representing a relational prepositional noun.
   * @param noun the class noun.
   * @param preposition the relational preposition.
   * @param anchor the relational anchor.
   * @param generic whether or not the class noun must be generic.
   * @return the LTAG representing the specified prepositional noun.
   */
  public static Ltag relationalPrepositionalNoun(String noun, String preposition, String anchor, boolean generic) {
    LtagNode np = new PosNode("NP1", POS.NP);
    LtagNode n = new PosNode("N1", POS.N);
    LtagNode pp = new PosNode("PP1", POS.PP);
    LtagNode p = new PosNode("P1", POS.P);
    LtagNode dp2 = new PosNode(anchor, POS.DP, LtagNode.Marker.SUB);
    LtagNode lex = new LexicalNode("LEX:"+noun, noun);
    LtagNode lexOf = new LexicalNode("LEX:"+preposition, preposition);

    Ltag template;

    if (generic) {
      LtagNode dp1 = new PosNode("DP1", POS.DP);
      template = new BaseLtag(dp1);
      template.addProduction(dp1, np);
    } else {
      template = new BaseLtag(np);
    }

    template.addProduction(np, n);
    template.addProduction(np, pp);
    template.addProduction(n, lex);
    template.addProduction(pp, p);
    template.addProduction(pp, dp2);
    template.addProduction(p, lexOf);

    return template;
  }

  /**
   * Generates a LTAG representing a relational possessive noun.
   * @param noun the class noun.
   * @param possessive the possessive preposition.
   * @param anchor the relational anchor.
   * @param generic whether or not the class noun must be generic.
   * @return the LTAG representing the specified possessive noun.
   */
  public static Ltag relationalPossessiveNoun(String noun, String possessive, String anchor, boolean generic) {
    LtagNode np = new PosNode("NP1", POS.NP);
    LtagNode dp2 = new PosNode(anchor, POS.DP, LtagNode.Marker.SUB);
    LtagNode poss = new PosNode("POSS1", POS.POSS);
    LtagNode n = new PosNode("N1", POS.N);
    LtagNode lex = new LexicalNode("LEX"+noun, noun);
    LtagNode lexGenitive = new LexicalNode("LEX:"+possessive, possessive);

    Ltag template;

    if (generic) {
      LtagNode dp1 = new PosNode("DP1", POS.DP);
      template = new BaseLtag(dp1);
      template.addProduction(dp1, np);
    } else {
      template = new BaseLtag(np);
    }

    template.addProduction(np, dp2);
    template.addProduction(np, poss);
    template.addProduction(np, n);
    template.addProduction(poss, lexGenitive);
    template.addProduction(n, lex);

    return template;
  }

  /**
   * Generates a LTAG representing an intransitive verb.
   * @param verb the verb.
   * @param anchor the subject anchor.
   * @return the LTAG representing the specified intransitive verb.
   */
  public static Ltag intransitiveVerb(String verb, String anchor) {
    LtagNode s = new PosNode("S1", POS.S);
    LtagNode dp = new PosNode(anchor, POS.DP, LtagNode.Marker.SUB);
    LtagNode vp = new PosNode("VP1", POS.VP);
    LtagNode lex = new LexicalNode("LEX:"+verb, verb);

    Ltag template = new BaseLtag(s);
    template.addProduction(s, dp);
    template.addProduction(s, vp);
    template.addProduction(vp, lex);

    return template;
  }

  /**
   * Generates a LTAG representing a transitive verb (active indicative).
   * @param verb the verb.
   * @param subjectAnchor the subject anchor.
   * @param objectAnchor the object anchor.
   * @return the LTAG representing the specified transitive verb (active indicative).
   */
  public static Ltag transitiveVerbActiveIndicative(String verb, String subjectAnchor, String objectAnchor) {
    LtagNode s = new PosNode("S1", POS.S);
    LtagNode dp1 = new PosNode(subjectAnchor, POS.DP, LtagNode.Marker.SUB);
    LtagNode vp = new PosNode("VP1", POS.VP);
    LtagNode v = new PosNode("V1", POS.V);
    LtagNode dp2 = new PosNode(objectAnchor, POS.DP, LtagNode.Marker.SUB);
    LtagNode lex = new LexicalNode("LEX:"+verb, verb);

    Ltag template = new BaseLtag(s);
    template.addProduction(s, dp1);
    template.addProduction(s, vp);
    template.addProduction(vp, v);
    template.addProduction(vp, dp2);
    template.addProduction(v, lex);

    return template;
  }

  /**
   * Generates a LTAG representing a transitive verb (passive indicative).
   * @param verb the verb.
   * @param copula the copula.
   * @param subjectAnchor the subject anchor.
   * @param objectAnchor the object anchor.
   * @return the LTAG representing the specified transitive verb (passive indicative).
   */
  public static Ltag transitiveVerbPassiveIndicative(String verb, String copula, String preposition,
                                                     String subjectAnchor, String objectAnchor) {
    LtagNode s = new PosNode("S1", POS.S);
    LtagNode dp2 = new PosNode(subjectAnchor, POS.DP, LtagNode.Marker.SUB);
    LtagNode vp = new PosNode("VP1", POS.VP);
    LtagNode v = new PosNode("V1", POS.V);
    LtagNode ap = new PosNode("AP1", POS.AP);
    LtagNode pp = new PosNode("PP1", POS.PP);
    LtagNode p = new PosNode("P1", POS.P);
    LtagNode dp1 = new PosNode(objectAnchor, POS.DP, LtagNode.Marker.SUB);
    LtagNode lexCopula = new LexicalNode("LEX:"+copula, copula);
    LtagNode lexVerb = new LexicalNode("LEX:"+verb, verb);
    LtagNode lexPreposition = new LexicalNode("LEX:"+preposition, preposition);

    Ltag template = new BaseLtag(s);
    template.addProduction(s, dp2);
    template.addProduction(s, vp);
    template.addProduction(vp, v);
    template.addProduction(vp, ap);
    template.addProduction(vp, pp);
    template.addProduction(v, lexCopula);
    template.addProduction(ap, lexVerb);
    template.addProduction(pp, p);
    template.addProduction(pp, dp1);
    template.addProduction(p, lexPreposition);

    return template;
  }

  /**
   * Generates a LTAG representing a transitive verb (active gerundive).
   * @param verb the verb.
   * @param subjectAnchor the subject anchor.
   * @param objectAnchor the object anchor.
   * @return the LTAG representing the specified transitive verb (active gerundive).
   */
  public static Ltag transitiveVerbActiveGerundive(String verb,
                                                   String subjectAnchor, String objectAnchor) {
    LtagNode np1 = new PosNode("NP1", POS.NP);
    LtagNode np2 = new PosNode(subjectAnchor, POS.NP, LtagNode.Marker.ADJ);
    LtagNode ap = new PosNode("AP1", POS.AP);
    LtagNode a = new PosNode("A1", POS.A);
    LtagNode dp2 = new PosNode(objectAnchor, POS.DP, LtagNode.Marker.SUB);
    LtagNode lex = new LexicalNode("LEX:"+verb, verb);

    Ltag template = new BaseLtag(np1);
    template.addProduction(np1, np2);
    template.addProduction(np1, ap);
    template.addProduction(ap, a);
    template.addProduction(ap, dp2);
    template.addProduction(a, lex);

    return template;
  }

  /**
   * Generates a LTAG representing a transitive verb (passive gerundive).
   * @param verb the verb.
   * @param subjectAnchor the subject anchor.
   * @param objectAnchor the object anchor.
   * @return the LTAG representing the specified transitive verb (passive gerundive).
   */
  public static Ltag transitiveVerbPassiveGerundive(String verb, String preposition,
                                                    String subjectAnchor, String objectAnchor) {
    LtagNode np1 = new PosNode("NP1", POS.NP);
    LtagNode np2 = new PosNode(subjectAnchor, POS.NP, LtagNode.Marker.ADJ);
    LtagNode ap = new PosNode("AP1", POS.AP);
    LtagNode a = new PosNode("A1", POS.A);
    LtagNode pp = new PosNode("PP1", POS.PP);
    LtagNode p = new PosNode("P1", POS.P);
    LtagNode dp1 = new PosNode(objectAnchor, POS.DP, LtagNode.Marker.SUB);
    LtagNode lexVerb = new LexicalNode("LEX:"+verb, verb);
    LtagNode lexPreposition = new LexicalNode("LEX:"+preposition, preposition);

    Ltag template = new BaseLtag(np1);
    template.addProduction(np1, np2);
    template.addProduction(np1, ap);
    template.addProduction(ap, a);
    template.addProduction(ap, pp);
    template.addProduction(a, lexVerb);
    template.addProduction(pp, p);
    template.addProduction(pp, dp1);
    template.addProduction(p, lexPreposition);

    return template;
  }

  /**
   * Generates a LTAG representing a transitive verb (active relative).
   * @param verb the verb.
   * @param pronoun the relational pronoun.
   * @param subjectAnchor the subject anchor.
   * @param objectAnchor the object anchor.
   * @return the LTAG representing the specified transitive verb (active relative).
   */
  public static Ltag transitiveVerbActiveRelative(String verb, String pronoun,
                                                  String subjectAnchor, String objectAnchor) {
    LtagNode np1 = new PosNode("NP1", POS.NP);
    LtagNode np2 = new PosNode(subjectAnchor, POS.NP, LtagNode.Marker.ADJ);
    LtagNode s = new PosNode("S1", POS.S);
    LtagNode rel = new PosNode("REL1", POS.REL);
    LtagNode vp = new PosNode("VP1", POS.VP);
    LtagNode v = new PosNode("V1", POS.V);
    LtagNode dp2 = new PosNode(objectAnchor, POS.DP, LtagNode.Marker.SUB);
    LtagNode lexRelative = new LexicalNode("LEX:"+pronoun, pronoun);
    LtagNode lexVerb = new LexicalNode("LEX:"+verb, verb);

    Ltag template = new BaseLtag(np1);
    template.addProduction(np1, np2);
    template.addProduction(np1, s);
    template.addProduction(s, rel);
    template.addProduction(s, vp);
    template.addProduction(rel, lexRelative);
    template.addProduction(vp, v);
    template.addProduction(vp, dp2);
    template.addProduction(v, lexVerb);

    return template;
  }

  /**
   * Generates a LTAG representing a transitive verb (passive relative).
   * @param verb the verb.
   * @param copula the copula.
   * @param pronoun the relational pronoun.
   * @param preposition the relative preposition.
   * @param subjectAnchor the subject anchor.
   * @param objectAnchor the object anchor.
   * @return the LTAG representing the specified transitive verb (passive relative).
   */
  public static Ltag transitiveVerbPassiveRelative(String verb, String copula, String pronoun, String preposition,
                                                   String subjectAnchor, String objectAnchor) {
    LtagNode np1 = new PosNode("NP1", POS.NP);
    LtagNode np2 = new PosNode(subjectAnchor, POS.NP, LtagNode.Marker.ADJ);
    LtagNode s = new PosNode("S1", POS.S);
    LtagNode rel = new PosNode("REL1", POS.REL);
    LtagNode vp = new PosNode("VP1", POS.VP);
    LtagNode v = new PosNode("V1", POS.V);
    LtagNode ap = new PosNode("AP1", POS.AP);
    LtagNode a = new PosNode("A1", POS.A);
    LtagNode pp = new PosNode("PP1", POS.PP);
    LtagNode p = new PosNode("P1", POS.P);
    LtagNode dp1 = new PosNode(objectAnchor, POS.DP, LtagNode.Marker.SUB);
    LtagNode lexRelative = new LexicalNode("LEX:"+pronoun, pronoun);
    LtagNode lexCopula = new LexicalNode("LEX:"+copula, copula);
    LtagNode lexVerb = new LexicalNode("LEX:"+verb, verb);
    LtagNode lexPreposition = new LexicalNode("LEX:"+preposition, preposition);

    Ltag template = new BaseLtag(np1);
    template.addProduction(np1, np2);
    template.addProduction(np1, s);
    template.addProduction(s, rel);
    template.addProduction(s, vp);
    template.addProduction(rel, lexRelative);
    template.addProduction(vp, v);
    template.addProduction(vp, ap);
    template.addProduction(v, lexCopula);
    template.addProduction(ap, a);
    template.addProduction(ap, pp);
    template.addProduction(a, lexVerb);
    template.addProduction(pp, p);
    template.addProduction(pp, dp1);
    template.addProduction(p, lexPreposition);

    return template;
  }

  public static Ltag transitiveVerbPrepositional(String verb, String... prepositions) {
    LtagNode s = new PosNode("S:1", POS.S);
    LtagNode dp1 = new PosNode("DP:1", POS.DP, LtagNode.Marker.SUB);
    LtagNode vp = new PosNode("VP:1", POS.VP);
    LtagNode v = new PosNode("V:1", POS.V);
    LtagNode dp2 = new PosNode("DP:2", POS.DP);
    LtagNode lexVerb = new LexicalNode("LEX:verb", "verb");

    Ltag template = new BaseLtag(s);
    template.addProduction(s, dp1);
    template.addProduction(s, vp);
    template.addProduction(vp, v);
    template.addProduction(vp, dp2);
    template.addProduction(v, lexVerb);

    for (String preposition : prepositions) {
      LtagNode pp = new PosNode("PP:"+preposition, POS.PP);
      LtagNode p = new PosNode("P:"+preposition, POS.P);
      LtagNode dp3 = new PosNode("DP:"+preposition, POS.DP, LtagNode.Marker.SUB);
      LtagNode lexPreposition = new LexicalNode("LEX:"+preposition, preposition);

      template.addProduction(vp, pp);
      template.addProduction(pp, p);
      template.addProduction(pp, dp3);
      template.addProduction(p, lexPreposition);
    }

    return template;
  }

  public static Ltag adjectiveAttributive(String adjective) {
    LtagNode n1 = new PosNode("N:1", POS.N);
    LtagNode adj = new PosNode("ADJ:1", POS.ADJ);
    LtagNode n2 = new PosNode("N:2", POS.N, LtagNode.Marker.ADJ);
    LtagNode lex = new LexicalNode("LEX:adjective", adjective);

    Ltag template = new BaseLtag(n1);
    template.addProduction(n1, adj);
    template.addProduction(n1, n2);
    template.addProduction(adj, lex);

    return template;
  }

  public static Ltag adjectivePredicative(String adjective) {
    LtagNode s = new PosNode("S:1", POS.S);
    LtagNode dp1 = new PosNode("DP:1", POS.DP, LtagNode.Marker.SUB);
    LtagNode vp = new PosNode("VP:1", POS.VP);
    LtagNode v = new PosNode("V:1", POS.V);
    LtagNode adj = new PosNode("ADJ:1", POS.ADJ);
    LtagNode lexCopula = new LexicalNode("LEX:copula", "is");
    LtagNode lexAdjective = new LexicalNode("LEX:adjective", adjective);

    Ltag template = new BaseLtag(s);
    template.addProduction(s, dp1);
    template.addProduction(s, vp);
    template.addProduction(vp, v);
    template.addProduction(vp, adj);
    template.addProduction(v, lexCopula);
    template.addProduction(adj, lexAdjective);

    return template;
  }

  public static Ltag adjectiveComparative(String adjective) {
    LtagNode s = new PosNode("S:1", POS.S);
    LtagNode dp1 = new PosNode("DP:1", POS.DP, LtagNode.Marker.SUB);
    LtagNode vp = new PosNode("VP:1", POS.VP);
    LtagNode v = new PosNode("V:1", POS.V);
    LtagNode ap = new PosNode("AP:1", POS.AP);
    LtagNode adj = new PosNode("ADJ:1", POS.ADJ);
    LtagNode pp = new PosNode("PP:1", POS.PP);
    LtagNode p = new PosNode("P:1", POS.P);
    LtagNode dp2 = new PosNode("DP:2", POS.DP, LtagNode.Marker.SUB);
    LtagNode lexCopula = new LexicalNode("LEX:copula", "is");
    LtagNode lexAdjective = new LexicalNode("LEX:adjective", adjective);
    LtagNode lexComparation = new LexicalNode("LEX:comparation", "than");

    Ltag template = new BaseLtag(s);
    template.addProduction(s, dp1);
    template.addProduction(s, vp);
    template.addProduction(vp, v);
    template.addProduction(vp, ap);
    template.addProduction(v, lexCopula);
    template.addProduction(ap, adj);
    template.addProduction(ap, pp);
    template.addProduction(adj, lexAdjective);
    template.addProduction(pp, p);
    template.addProduction(pp, dp2);
    template.addProduction(p, lexComparation);

    return template;
  }

  public static Ltag adjectiveSuperlative(String adjective) {
    LtagNode dp = new PosNode("DP:1", POS.DP);
    LtagNode det = new PosNode("DET:1", POS.DET);
    LtagNode adj = new PosNode("ADJ:1", POS.ADJ);
    LtagNode np = new PosNode("NP:1", POS.NP, LtagNode.Marker.SUB);
    LtagNode lexDeterminer = new LexicalNode("LEX:determiner", "the");
    LtagNode lexAdjective = new LexicalNode("LEX:adjective", adjective);

    Ltag template = new BaseLtag(dp);
    template.addProduction(dp, det);
    template.addProduction(dp, adj);
    template.addProduction(dp, np);
    template.addProduction(det, lexDeterminer);
    template.addProduction(adj, lexAdjective);

    return template;
  }

  public static Ltag num(String numeral) {
    LtagNode num = new PosNode("NUM:1", POS.NUM);
    LtagNode lexNumeral = new LexicalNode("LEX:numeral", numeral);

    Ltag template = new BaseLtag(num);
    template.addProduction(num, lexNumeral);

    return template;
  }
}
