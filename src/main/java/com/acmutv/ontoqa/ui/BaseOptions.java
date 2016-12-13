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

package com.acmutv.ontoqa.ui;

import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;

/**
 * This class realizes the command line interface options of the whole application.
 * The class is implemented as a singleton.
 * @author Giacomo Marciani {@literal <gmarciani@acm.org>}
 * @since 1.0
 * @see Option
 */
public class BaseOptions extends Options {

  private static final long serialVersionUID = 1L;

  private static final String OPTION_DESCRIPTION_CONFIG = "Custom configuration.";
  private static final String OPTION_DESCRIPTION_SILENT = "Activate silent mode.";
  private static final String OPTION_DESCRIPTION_TRACE = "Activate trace mode.";
  private static final String OPTION_DESCRIPTION_HELP = "Show app helper.";
  private static final String OPTION_DESCRIPTION_VERSION = "Show app version.";

  private static BaseOptions instance;

  /**
   * Initializes the singleton instance of the class.
   * @return the singleton instance of the class.
   */
  public static BaseOptions getInstance() {
    if (instance == null) {
      instance = new BaseOptions();
    }
    return instance;
  }

  /**
   * Constructor for the singleton of the class.
   */
  private BaseOptions() {
    Option version = this.optVersion();
    Option help = this.optHelp();
    Option silent = this.optSilent();
    Option trace = this.optTrace();
    Option config = this.optConfig();

    super.addOption(version);
    super.addOption(help);
    super.addOption(silent);
    super.addOption(trace);
    super.addOption(config);
  }

  /**
   * Builds the option `version`.
   * @return the option.
   */
  private Option optVersion() {
    return Option.builder("v")
        .longOpt("version")
        .desc(OPTION_DESCRIPTION_VERSION)
        .required(false)
        .hasArg(false)
        .build();
  }

  /**
   * Builds the option `help`.
   * @return the option.
   */
  private Option optHelp() {
    return Option.builder("h")
        .longOpt("help")
        .desc(OPTION_DESCRIPTION_HELP)
        .required(false)
        .hasArg(false)
        .build();
  }

  /**
   * Builds the option `silent`.
   * @return the option.
   */
  private Option optSilent() {
    return Option.builder("s")
        .longOpt("silent")
        .desc(OPTION_DESCRIPTION_SILENT)
        .required(false)
        .hasArg(false)
        .build();
  }

  /**
   * Builds the option `trace`.
   * @return the option.
   */
  private Option optTrace() {
    return Option.builder("t")
        .longOpt("trace")
        .desc(OPTION_DESCRIPTION_TRACE)
        .required(false)
        .hasArg(false)
        .build();
  }

  /**
   * Builds the option `config`.
   * @return the option.
   */
  private Option optConfig() {
    return Option.builder("c")
        .longOpt("config")
        .desc(OPTION_DESCRIPTION_CONFIG)
        .required(false)
        .hasArg(true)
        .numberOfArgs(1)
        .argName("YAML-FILE")
        .build();
  }

}
