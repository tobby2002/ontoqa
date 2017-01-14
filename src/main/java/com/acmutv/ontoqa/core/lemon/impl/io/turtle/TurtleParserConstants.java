/* Generated By:JavaCC: Do not edit this line. TurtleParserConstants.java */
/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

/**********************************************************************************
 * Copyright (c) 2011, Monnet Project
 * All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *     * Redistributions of source code must retain the above copyright
 *       notice, this list of conditions and the following disclaimer.
 *     * Redistributions in binary form must reproduce the above copyright
 *       notice, this list of conditions and the following disclaimer in the
 *       documentation and/or other materials provided with the distribution.
 *     * Neither the name of the Monnet Project nor the
 *       names of its contributors may be used to endorse or promote products
 *       derived from this software without specific prior written permission.
 * 
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE MONNET PROJECT BE LIABLE FOR ANY
 * DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *********************************************************************************/


package com.acmutv.ontoqa.core.lemon.impl.io.turtle;


/**
 * Token literal values and constants.
 * Generated by org.javacc.parser.OtherFilesGen#start()
 */
public interface TurtleParserConstants {

  /** End of File. */
  int EOF = 0;
  /** RegularExpression Id. */
  int PLING = 1;
  /** RegularExpression Id. */
  int VBAR = 2;
  /** RegularExpression Id. */
  int CARROT = 3;
  /** RegularExpression Id. */
  int FPATH = 4;
  /** RegularExpression Id. */
  int RPATH = 5;
  /** RegularExpression Id. */
  int WS = 11;
  /** RegularExpression Id. */
  int SINGLE_LINE_COMMENT = 12;
  /** RegularExpression Id. */
  int KW_A = 13;
  /** RegularExpression Id. */
  int PREFIX = 14;
  /** RegularExpression Id. */
  int BASE = 15;
  /** RegularExpression Id. */
  int TRUE = 16;
  /** RegularExpression Id. */
  int FALSE = 17;
  /** RegularExpression Id. */
  int INTEGER = 18;
  /** RegularExpression Id. */
  int DECIMAL = 19;
  /** RegularExpression Id. */
  int DOUBLE = 20;
  /** RegularExpression Id. */
  int EXPONENT = 21;
  /** RegularExpression Id. */
  int QUOTE_3D = 22;
  /** RegularExpression Id. */
  int QUOTE_3S = 23;
  /** RegularExpression Id. */
  int ECHAR = 24;
  /** RegularExpression Id. */
  int STRING_LITERAL1 = 25;
  /** RegularExpression Id. */
  int STRING_LITERAL2 = 26;
  /** RegularExpression Id. */
  int STRING_LITERAL_LONG1 = 27;
  /** RegularExpression Id. */
  int STRING_LITERAL_LONG2 = 28;
  /** RegularExpression Id. */
  int DIGITS = 29;
  /** RegularExpression Id. */
  int IRIref = 30;
  /** RegularExpression Id. */
  int PNAME_NS = 31;
  /** RegularExpression Id. */
  int PNAME_LN = 32;
  /** RegularExpression Id. */
  int BLANK_NODE_LABEL = 33;
  /** RegularExpression Id. */
  int VAR = 34;
  /** RegularExpression Id. */
  int LANGTAG = 35;
  /** RegularExpression Id. */
  int A2Z = 36;
  /** RegularExpression Id. */
  int A2ZN = 37;
  /** RegularExpression Id. */
  int LPAREN = 38;
  /** RegularExpression Id. */
  int RPAREN = 39;
  /** RegularExpression Id. */
  int NIL = 40;
  /** RegularExpression Id. */
  int LBRACE = 41;
  /** RegularExpression Id. */
  int RBRACE = 42;
  /** RegularExpression Id. */
  int LBRACKET = 43;
  /** RegularExpression Id. */
  int RBRACKET = 44;
  /** RegularExpression Id. */
  int ANON = 45;
  /** RegularExpression Id. */
  int SEMICOLON = 46;
  /** RegularExpression Id. */
  int COMMA = 47;
  /** RegularExpression Id. */
  int DOT = 48;
  /** RegularExpression Id. */
  int EQ = 49;
  /** RegularExpression Id. */
  int ARROW = 50;
  /** RegularExpression Id. */
  int DOLLAR = 51;
  /** RegularExpression Id. */
  int QMARK = 52;
  /** RegularExpression Id. */
  int TILDE = 53;
  /** RegularExpression Id. */
  int COLON = 54;
  /** RegularExpression Id. */
  int STAR = 55;
  /** RegularExpression Id. */
  int SLASH = 56;
  /** RegularExpression Id. */
  int RSLASH = 57;
  /** RegularExpression Id. */
  int BOM = 58;
  /** RegularExpression Id. */
  int DATATYPE = 59;
  /** RegularExpression Id. */
  int AT = 60;
  /** RegularExpression Id. */
  int PN_CHARS_BASE = 61;
  /** RegularExpression Id. */
  int PN_CHARS_U = 62;
  /** RegularExpression Id. */
  int PN_CHARS = 63;
  /** RegularExpression Id. */
  int PN_PREFIX = 64;
  /** RegularExpression Id. */
  int PN_LOCAL = 65;
  /** RegularExpression Id. */
  int VARNAME = 66;
  /** RegularExpression Id. */
  int UNKNOWN = 67;

  /** Lexical state. */
  int DEFAULT = 0;

  /** Literal token values. */
  String[] tokenImage = {
    "<EOF>",
    "\"!\"",
    "\"|\"",
    "\"^\"",
    "\"->\"",
    "\"<-\"",
    "\" \"",
    "\"\\t\"",
    "\"\\n\"",
    "\"\\r\"",
    "\"\\f\"",
    "<WS>",
    "<SINGLE_LINE_COMMENT>",
    "\"a\"",
    "\"@prefix\"",
    "\"@base\"",
    "\"true\"",
    "\"false\"",
    "<INTEGER>",
    "<DECIMAL>",
    "<DOUBLE>",
    "<EXPONENT>",
    "\"\\\"\\\"\\\"\"",
    "\"\\\'\\\'\\\'\"",
    "<ECHAR>",
    "<STRING_LITERAL1>",
    "<STRING_LITERAL2>",
    "<STRING_LITERAL_LONG1>",
    "<STRING_LITERAL_LONG2>",
    "<DIGITS>",
    "<IRIref>",
    "<PNAME_NS>",
    "<PNAME_LN>",
    "<BLANK_NODE_LABEL>",
    "<VAR>",
    "<LANGTAG>",
    "<A2Z>",
    "<A2ZN>",
    "\"(\"",
    "\")\"",
    "<NIL>",
    "\"{\"",
    "\"}\"",
    "\"[\"",
    "\"]\"",
    "<ANON>",
    "\";\"",
    "\",\"",
    "\".\"",
    "\"=\"",
    "\"=>\"",
    "\"$\"",
    "\"?\"",
    "\"~\"",
    "\":\"",
    "\"*\"",
    "\"/\"",
    "\"\\\\\"",
    "\"\\ufeff\"",
    "\"^^\"",
    "\"@\"",
    "<PN_CHARS_BASE>",
    "<PN_CHARS_U>",
    "<PN_CHARS>",
    "<PN_PREFIX>",
    "<PN_LOCAL>",
    "<VARNAME>",
    "<UNKNOWN>",
  };

}
