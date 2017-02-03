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
package com.acmutv.ontoqa.core.lemon.impl;

import com.acmutv.ontoqa.core.lemon.LinguisticOntology;
import  com.acmutv.ontoqa.core.lemon.impl.io.ReaderAccepter;
import com.acmutv.ontoqa.core.lemon.model.Constituent;
import java.net.URI;

/**
 * Instantiated via {@link LemonFactoryImpl}
 * @author John McCrae
 */
public class ConstituentImpl extends LemonElementImpl implements Constituent {
    private static final long serialVersionUID = -7919650982044360527L;

    ConstituentImpl(URI uri, LemonModelImpl model) {
        super(uri, "Constituent",model);
    }

    ConstituentImpl(String id, LemonModelImpl model) {
        super(id, "Constituent",model);
    }

    @Override
    public ReaderAccepter accept(URI pred, URI value, LinguisticOntology lingOnto, AccepterFactory factory) {
        return defaultAccept(pred, value,lingOnto);
    }

    @Override
    public ReaderAccepter accept(URI pred, String bNode, LinguisticOntology lingOnto, AccepterFactory factory) {
        return defaultAccept(pred, bNode);
    }

    @Override
    public void accept(URI pred, String value, String lang, LinguisticOntology lingOnto, AccepterFactory factory) {
        defaultAccept(pred, value, lang);
    }
    
    @Override
    public void merge(ReaderAccepter accepter, LinguisticOntology lingOnto, AccepterFactory factory) {
        defaultMerge(accepter, lingOnto, factory);
    }
}