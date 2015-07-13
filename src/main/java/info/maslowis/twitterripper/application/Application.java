/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2015 Ivan Maslov
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package info.maslowis.twitterripper.application;

import com.beust.jcommander.JCommander;
import info.maslowis.twitterripper.twitter.OAuthTwitter;
import info.maslowis.twitterripper.jcommander.JCommanderFactory;
import jline.console.ConsoleReader;
import jline.console.completer.ArgumentCompleter;
import jline.console.completer.Completer;
import jline.console.completer.StringsCompleter;
import org.apache.log4j.Logger;
import org.fusesource.jansi.AnsiConsole;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static java.lang.System.exit;

/**
 * Console application
 *
 * @author Ivan Maslov
 */
public final class Application {
    private final static Logger logger = Logger.getLogger(Application.class);

    public final static Application INSTANCE = new Application();

    private final ConsoleReader reader;
    private final Thread listenerThread;
    private final OAuthTwitter oAuth;
    private boolean isStarted;

    private Application() {
        reader = initConsoleReader();
        ConsoleListener listener = new ConsoleListener(reader);
        listenerThread = new Thread(listener);
        oAuth = new OAuthTwitter(reader);
    }

    private ConsoleReader initConsoleReader() {
        ConsoleReader reader = null;
        try {
            reader = new ConsoleReader();
            JCommander jCommander = JCommanderFactory.getInstance();
            Set<String> commands = jCommander.getCommands().keySet();
            List<Completer> completers = new ArrayList<>();
            completers.add(new StringsCompleter(commands.toArray(new String[commands.size()])));
            reader.addCompleter(new ArgumentCompleter(completers));
        } catch (IOException e) {
            logger.error("Initialization application failed...", e);
            exit(-1);
        }
        return reader;
    }

    public ConsoleReader getReader() {
        return reader;
    }

    /**
     * Start console application
     */
    public void start() {
        if (isStarted) throw new IllegalStateException("Application already started.");
        try {
            logger.info("Starting application...");
            AnsiConsole.systemInstall();
            /*PIN-based twitter authorization*/
            oAuth.authorization();
            /*Run the listener for console*/
            listenerThread.start();
            isStarted = true;
            logger.info("Application started!");
        } catch (IOException e) {
            logger.error("Start application failed...", e);
            exit(-1);
        }
    }

    /**
     * Stop console application
     */
    public void stop() {
        if (!isStarted) throw new IllegalStateException("Application has not yet started.");
        logger.info("Stopping application...");
        if (listenerThread.isAlive()) listenerThread.interrupt();
        AnsiConsole.systemUninstall();
        logger.info("Stop done!");
    }

}
