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

import com.acmutv.ontoqa.core.CoreController;
import com.acmutv.ontoqa.config.AppConfigurationService;
import com.acmutv.ontoqa.core.knowledge.Answer;
import com.acmutv.ontoqa.tool.RuntimeManager;
import com.acmutv.ontoqa.tool.task.ShutdownHook;
import com.acmutv.ontoqa.ui.CliService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.List;

/**
 * This class realizes the app entry-point.
 * @author Antonella Botte {@literal <abotte@acm.org>}
 * @author Giacomo Marciani {@literal <gmarciani@acm.org>}
 * @author Debora Partigianoni {@literal <dpartigianoni@acm.org>}
 * @since 1.0
 * @see AppConfigurationService
 * @see RuntimeManager
 */
class AppMain {

  private static final Logger LOGGER = LogManager.getLogger(AppMain.class);

  /**
   * The app main method, executed when the program is launched.
   * @param args the command line arguments.
   */
  public static void main(String[] args) {

    List<String> arguments = CliService.handleArguments(args);

    RuntimeManager.registerShutdownHooks(new ShutdownHook());

    final String question = arguments.get(0);
    try {
      final Answer answer = CoreController.process(question);
      System.out.println(answer.toPrettyString());
    } catch (IOException e) {
      LOGGER.error(e.getMessage());
      LOGGER.traceExit(-1);
      System.exit(-1);
    }

    LOGGER.traceExit(0);

    System.exit(0);
  }
}
