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

package com.acmutv.ontoqa.core.syntax;

import com.acmutv.ontoqa.core.syntax.ltag.*;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * JUnit tests for {@link SimpleLtag} query.
 * @author Antonella Botte {@literal <abotte@acm.org>}
 * @author Giacomo Marciani {@literal <gmarciani@acm.org>}
 * @author Debora Partigianoni {@literal <dpartigianoni@acm.org>}
 * @since 1.0
 * @see Ltag
 * @see SimpleLtag
 */
public class LtagQueryTest {

  private static final Logger LOGGER = LoggerFactory.getLogger(LtagQueryTest.class);

  /**
   * Tests the first node matching starting at some given lexical point.
   */
  @Test
  public void test_firstMatch() {
    LtagNode nodeS = new NonTerminalNode(SyntaxCategory.S);
    LtagNode nodeDP1 = new NonTerminalNode(1, SyntaxCategory.DP, LtagNodeMarker.SUB, "myDP1");
    LtagNode nodeVP = new NonTerminalNode(SyntaxCategory.VP);
    LtagNode nodeV = new NonTerminalNode(SyntaxCategory.V);
    LtagNode nodeDP2 = new NonTerminalNode(2, SyntaxCategory.DP, LtagNodeMarker.SUB, "myDP2");
    LtagNode nodeWins = new TerminalNode("wins");

    Ltag tree = new SimpleLtag(nodeS);
    tree.addEdge(nodeS, nodeDP1);
    tree.addEdge(nodeS, nodeVP);
    tree.addEdge(nodeVP, nodeV);
    tree.addEdge(nodeVP, nodeDP2);
    tree.addEdge(nodeV, nodeWins);

    LtagNode actual1 = tree.firstMatch(SyntaxCategory.DP, "wins", null);

    Assert.assertEquals(nodeDP2, actual1);

    LtagNode actual2 = tree.firstMatch(SyntaxCategory.VP, "wins", null);

    Assert.assertEquals(nodeVP, actual2);

    LtagNode actual3 = tree.firstMatch(SyntaxCategory.ADJ, "wins", null);

    Assert.assertNull(actual3);
  }

  /**
   * Tests the labeled node retrieval.
   */
  @Test
  public void test_getNodeByLabel() {
    LtagNode nodeS = new NonTerminalNode(SyntaxCategory.S);
    LtagNode nodeDP1 = new NonTerminalNode(1, SyntaxCategory.DP, LtagNodeMarker.SUB, "myDP1");
    LtagNode nodeVP = new NonTerminalNode(SyntaxCategory.VP);
    LtagNode nodeV = new NonTerminalNode(SyntaxCategory.V);
    LtagNode nodeDP2 = new NonTerminalNode(2, SyntaxCategory.DP, LtagNodeMarker.SUB, "myDP2");
    LtagNode nodeWins = new TerminalNode("wins");

    Ltag tree = new SimpleLtag(nodeS);
    tree.addEdge(nodeS, nodeDP1);
    tree.addEdge(nodeS, nodeVP);
    tree.addEdge(nodeVP, nodeV);
    tree.addEdge(nodeVP, nodeDP2);
    tree.addEdge(nodeV, nodeWins);

    Assert.assertEquals(nodeDP1, tree.getNode("myDP1"));
    Assert.assertEquals(nodeDP2, tree.getNode("myDP2"));
    Assert.assertEquals(null, tree.getNode("myDP3"));
  }

  /**
   * Tests the RHS retrieval.
   */
  @Test
  public void test_getRhs() {
    LtagNode root = new NonTerminalNode(SyntaxCategory.S);
    Ltag tree = new SimpleLtag(root);
    List<LtagNode> expectedRhs = new ArrayList<>();
    for (int i = 0; i < 1000; i++) {
      LtagNode child = new NonTerminalNode(i, SyntaxCategory.NP);
      tree.addEdge(root, child);
      expectedRhs.add(child);
    }

    List<LtagNode> actualRhs = tree.getRhs(root);

    for (int i = 0; i < expectedRhs.size(); i++) {
      Assert.assertEquals("iteration " + i, expectedRhs.get(i), actualRhs.get(i));
    }
  }

  /**
   * Tests the retrieval of all the adjunction nodes.
   */
  @Test
  public void test_getAdjunctionNodes() {
    LtagNode nodeVP1 = new NonTerminalNode(1, SyntaxCategory.VP);
    LtagNode nodeVP2 = new NonTerminalNode(2, SyntaxCategory.VP, LtagNodeMarker.ADJ, "myVP2");
    LtagNode nodeADV = new NonTerminalNode(SyntaxCategory.ADV);
    LtagNode nodeEasily = new TerminalNode("easily");

    Ltag tree = new SimpleLtag(nodeVP1);
    tree.addEdge(nodeVP1, nodeVP2);
    tree.addEdge(nodeVP1, nodeADV);
    tree.addEdge(nodeADV, nodeEasily);

    List<LtagNode> actual = tree.getNodes(LtagNodeMarker.ADJ);

    List<LtagNode> expected = new ArrayList<>();
    expected.add(nodeVP2);

    Assert.assertEquals(expected, actual);
  }

  /**
   * Tests the retrieval of all the substitution nodes.
   */
  @Test
  public void test_getSubstitutionNodes() {
    LtagNode nodeS = new NonTerminalNode(SyntaxCategory.S);
    LtagNode nodeDP1 = new NonTerminalNode(1, SyntaxCategory.DP, LtagNodeMarker.SUB, "myDP1");
    LtagNode nodeVP = new NonTerminalNode(SyntaxCategory.VP);
    LtagNode nodeV = new NonTerminalNode(SyntaxCategory.V);
    LtagNode nodeDP2 = new NonTerminalNode(2, SyntaxCategory.DP, LtagNodeMarker.SUB, "myDP2");
    LtagNode nodeWins = new TerminalNode("wins");

    Ltag tree = new SimpleLtag(nodeS);
    tree.addEdge(nodeS, nodeDP1);
    tree.addEdge(nodeS, nodeVP);
    tree.addEdge(nodeVP, nodeV);
    tree.addEdge(nodeVP, nodeDP2);
    tree.addEdge(nodeV, nodeWins);

    List<LtagNode> actual = tree.getNodes(LtagNodeMarker.SUB);

    List<LtagNode> expected = new ArrayList<>();
    expected.add(nodeDP1);
    expected.add(nodeDP2);

    Assert.assertTrue(expected.containsAll(actual) && actual.containsAll(expected));
  }

  @Test
  public void test_isLeftAdj() {
    LtagNode tree1_nodeS = new NonTerminalNode(SyntaxCategory.S);
    LtagNode tree1_nodeDP1 = new NonTerminalNode(1, SyntaxCategory.DP, LtagNodeMarker.SUB, "myDP1");
    LtagNode tree1_nodeVP = new NonTerminalNode(SyntaxCategory.VP);
    LtagNode tree1_nodeV = new NonTerminalNode(SyntaxCategory.V);
    LtagNode tree1_nodeDP2 = new NonTerminalNode(2, SyntaxCategory.DP, LtagNodeMarker.SUB, "myDP2");
    LtagNode tree1_nodeWins = new TerminalNode("wins");

    Ltag tree1 = new SimpleLtag(tree1_nodeS);
    tree1.addEdge(tree1_nodeS, tree1_nodeDP1);
    tree1.addEdge(tree1_nodeS, tree1_nodeVP);
    tree1.addEdge(tree1_nodeVP, tree1_nodeV);
    tree1.addEdge(tree1_nodeVP, tree1_nodeDP2);
    tree1.addEdge(tree1_nodeV, tree1_nodeWins);

    Assert.assertFalse(tree1.isLeftAdj());

    LtagNode tree2_nodeVP1 = new NonTerminalNode(1, SyntaxCategory.VP);
    LtagNode tree2_nodeVP2 = new NonTerminalNode(2, SyntaxCategory.VP, LtagNodeMarker.ADJ, "myVP2");
    LtagNode tree2_nodeADV = new NonTerminalNode(SyntaxCategory.ADV);
    LtagNode tree2_nodeEasily = new TerminalNode("easily");

    Ltag tree2 = new SimpleLtag(tree2_nodeVP1);
    tree2.addEdge(tree2_nodeVP1, tree2_nodeADV);
    tree2.addEdge(tree2_nodeVP1, tree2_nodeVP2);
    tree2.addEdge(tree2_nodeADV, tree2_nodeEasily);

    Assert.assertFalse(tree2.isLeftAdj());

    LtagNode tree3_nodeVP1 = new NonTerminalNode(1, SyntaxCategory.VP);
    LtagNode tree3_nodeVP2 = new NonTerminalNode(2, SyntaxCategory.VP, LtagNodeMarker.ADJ, "myVP2");
    LtagNode tree3_nodeADV = new NonTerminalNode(SyntaxCategory.ADV);
    LtagNode tree3_nodeEasily = new TerminalNode("easily");

    Ltag tree3 = new SimpleLtag(tree3_nodeVP1);
    tree3.addEdge(tree3_nodeVP1, tree3_nodeVP2);
    tree3.addEdge(tree3_nodeVP1, tree3_nodeADV);
    tree3.addEdge(tree3_nodeADV, tree3_nodeEasily);

    Assert.assertTrue(tree3.isLeftAdj());
  }

  @Test
  public void test_isLeftSub() {
    LtagNode tree1_nodeS = new NonTerminalNode(SyntaxCategory.S);
    LtagNode tree1_nodeDP1 = new NonTerminalNode(1, SyntaxCategory.DP, LtagNodeMarker.SUB, "myDP1");
    LtagNode tree1_nodeVP = new NonTerminalNode(SyntaxCategory.VP);
    LtagNode tree1_nodeV = new NonTerminalNode(SyntaxCategory.V);
    LtagNode tree1_nodeDP2 = new NonTerminalNode(2, SyntaxCategory.DP, LtagNodeMarker.SUB, "myDP2");
    LtagNode tree1_nodeWins = new TerminalNode("wins");

    Ltag tree1 = new SimpleLtag(tree1_nodeS);
    tree1.addEdge(tree1_nodeS, tree1_nodeDP1);
    tree1.addEdge(tree1_nodeS, tree1_nodeVP);
    tree1.addEdge(tree1_nodeVP, tree1_nodeV);
    tree1.addEdge(tree1_nodeVP, tree1_nodeDP2);
    tree1.addEdge(tree1_nodeV, tree1_nodeWins);

    Assert.assertTrue(tree1.isLeftSub());

    LtagNode tree2_nodeVP1 = new NonTerminalNode(1, SyntaxCategory.VP);
    LtagNode tree2_nodeVP2 = new NonTerminalNode(2, SyntaxCategory.VP, LtagNodeMarker.ADJ, "myVP2");
    LtagNode tree2_nodeADV = new NonTerminalNode(SyntaxCategory.ADV);
    LtagNode tree2_nodeEasily = new TerminalNode("easily");

    Ltag tree2 = new SimpleLtag(tree2_nodeVP1);
    tree2.addEdge(tree2_nodeVP1, tree2_nodeADV);
    tree2.addEdge(tree2_nodeVP1, tree2_nodeVP2);
    tree2.addEdge(tree2_nodeADV, tree2_nodeEasily);

    Assert.assertFalse(tree2.isLeftSub());

    LtagNode tree3_nodeVP1 = new NonTerminalNode(1, SyntaxCategory.VP);
    LtagNode tree3_nodeVP2 = new NonTerminalNode(2, SyntaxCategory.VP, LtagNodeMarker.ADJ, "myVP2");
    LtagNode tree3_nodeADV = new NonTerminalNode(SyntaxCategory.ADV);
    LtagNode tree3_nodeEasily = new TerminalNode("easily");

    Ltag tree3 = new SimpleLtag(tree3_nodeVP1);
    tree3.addEdge(tree3_nodeVP1, tree3_nodeVP2);
    tree3.addEdge(tree3_nodeVP1, tree3_nodeADV);
    tree3.addEdge(tree3_nodeADV, tree3_nodeEasily);

    Assert.assertFalse(tree3.isLeftSub());
  }

  @Test
  public void test_isRightAdj() {
    LtagNode tree1_nodeS = new NonTerminalNode(SyntaxCategory.S);
    LtagNode tree1_nodeDP1 = new NonTerminalNode(1, SyntaxCategory.DP, LtagNodeMarker.SUB, "myDP1");
    LtagNode tree1_nodeVP = new NonTerminalNode(SyntaxCategory.VP);
    LtagNode tree1_nodeV = new NonTerminalNode(SyntaxCategory.V);
    LtagNode tree1_nodeDP2 = new NonTerminalNode(2, SyntaxCategory.DP, LtagNodeMarker.SUB, "myDP2");
    LtagNode tree1_nodeWins = new TerminalNode("wins");

    Ltag tree1 = new SimpleLtag(tree1_nodeS);
    tree1.addEdge(tree1_nodeS, tree1_nodeDP1);
    tree1.addEdge(tree1_nodeS, tree1_nodeVP);
    tree1.addEdge(tree1_nodeVP, tree1_nodeV);
    tree1.addEdge(tree1_nodeVP, tree1_nodeDP2);
    tree1.addEdge(tree1_nodeV, tree1_nodeWins);

    Assert.assertFalse(tree1.isRightAdj());

    LtagNode tree2_nodeVP1 = new NonTerminalNode(1, SyntaxCategory.VP);
    LtagNode tree2_nodeVP2 = new NonTerminalNode(2, SyntaxCategory.VP, LtagNodeMarker.ADJ, "myVP2");
    LtagNode tree2_nodeADV = new NonTerminalNode(SyntaxCategory.ADV);
    LtagNode tree2_nodeEasily = new TerminalNode("easily");

    Ltag tree2 = new SimpleLtag(tree2_nodeVP1);
    tree2.addEdge(tree2_nodeVP1, tree2_nodeADV);
    tree2.addEdge(tree2_nodeVP1, tree2_nodeVP2);
    tree2.addEdge(tree2_nodeADV, tree2_nodeEasily);

    Assert.assertTrue(tree2.isRightAdj());

    LtagNode tree3_nodeVP1 = new NonTerminalNode(1, SyntaxCategory.VP);
    LtagNode tree3_nodeVP2 = new NonTerminalNode(2, SyntaxCategory.VP, LtagNodeMarker.ADJ, "myVP2");
    LtagNode tree3_nodeADV = new NonTerminalNode(SyntaxCategory.ADV);
    LtagNode tree3_nodeEasily = new TerminalNode("easily");

    Ltag tree3 = new SimpleLtag(tree3_nodeVP1);
    tree3.addEdge(tree3_nodeVP1, tree3_nodeVP2);
    tree3.addEdge(tree3_nodeVP1, tree3_nodeADV);
    tree3.addEdge(tree3_nodeADV, tree3_nodeEasily);

    Assert.assertFalse(tree3.isRightAdj());
  }

  @Test
  public void test_isRightSub() {
    LtagNode tree1_nodeS = new NonTerminalNode(SyntaxCategory.S);
    LtagNode tree1_nodeDP1 = new NonTerminalNode(1, SyntaxCategory.DP, LtagNodeMarker.SUB, "myDP1");
    LtagNode tree1_nodeVP = new NonTerminalNode(SyntaxCategory.VP);
    LtagNode tree1_nodeV = new NonTerminalNode(SyntaxCategory.V);
    LtagNode tree1_nodeDP2 = new NonTerminalNode(2, SyntaxCategory.DP, LtagNodeMarker.SUB, "myDP2");
    LtagNode tree1_nodeWins = new TerminalNode("wins");

    Ltag tree1 = new SimpleLtag(tree1_nodeS);
    tree1.addEdge(tree1_nodeS, tree1_nodeDP1);
    tree1.addEdge(tree1_nodeS, tree1_nodeVP);
    tree1.addEdge(tree1_nodeVP, tree1_nodeV);
    tree1.addEdge(tree1_nodeVP, tree1_nodeDP2);
    tree1.addEdge(tree1_nodeV, tree1_nodeWins);

    Assert.assertTrue(tree1.isRightSub());

    LtagNode tree2_nodeVP1 = new NonTerminalNode(1, SyntaxCategory.VP);
    LtagNode tree2_nodeVP2 = new NonTerminalNode(2, SyntaxCategory.VP, LtagNodeMarker.ADJ, "myVP2");
    LtagNode tree2_nodeADV = new NonTerminalNode(SyntaxCategory.ADV);
    LtagNode tree2_nodeEasily = new TerminalNode("easily");

    Ltag tree2 = new SimpleLtag(tree2_nodeVP1);
    tree2.addEdge(tree2_nodeVP1, tree2_nodeADV);
    tree2.addEdge(tree2_nodeVP1, tree2_nodeVP2);
    tree2.addEdge(tree2_nodeADV, tree2_nodeEasily);

    Assert.assertFalse(tree2.isRightSub());

    LtagNode tree3_nodeVP1 = new NonTerminalNode(1, SyntaxCategory.VP);
    LtagNode tree3_nodeVP2 = new NonTerminalNode(2, SyntaxCategory.VP, LtagNodeMarker.ADJ, "myVP2");
    LtagNode tree3_nodeADV = new NonTerminalNode(SyntaxCategory.ADV);
    LtagNode tree3_nodeEasily = new TerminalNode("easily");

    Ltag tree3 = new SimpleLtag(tree3_nodeVP1);
    tree3.addEdge(tree3_nodeVP1, tree3_nodeVP2);
    tree3.addEdge(tree3_nodeVP1, tree3_nodeADV);
    tree3.addEdge(tree3_nodeADV, tree3_nodeEasily);

    Assert.assertFalse(tree3.isRightSub());
  }

}
