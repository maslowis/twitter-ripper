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
import com.beust.jcommander.MissingCommandException;
import com.beust.jcommander.ParameterException;
import info.maslowis.twitterripper.command.Command;
import info.maslowis.twitterripper.command.ExecuteCmdException;
import info.maslowis.twitterripper.jcommander.JCommanderFactory;
import jline.console.ConsoleReader;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.List;

import static java.lang.System.exit;
import static java.lang.System.out;
import static org.fusesource.jansi.Ansi.*;

/**
 * Console listener
 * <p/>
 * <p>Listen console input and run a command in separate task</p>
 *
 * @author Ivan Maslov
 */
class ConsoleListener implements Runnable {

    private final static Logger logger = Logger.getLogger(ConsoleListener.class);

    private final ConsoleReader reader;

    ConsoleListener(ConsoleReader reader) {
        this.reader = reader;
    }

    @Override
    public void run() {
        printWelcome();
        while (true) {
            try {
                String line = reader.readLine();
                if (line == null) return;
                if (line.isEmpty()) {
                    printEnter();
                    continue;
                }
                go(line);
            } catch (IOException e) {
                logger.error("Error reading input", e);
                exit(-1);
            }
        }

    }

    private void go(String line) {
        String inputCommand = "";
        try {
            final String[] input = line.split(" ");
            if (input.length > 0) {
                inputCommand = input[0];
                final JCommander jCommander = JCommanderFactory.getInstance();
                jCommander.parse(input);
                final String parsedCommand = jCommander.getParsedCommand();
                final List<Object> commands = jCommander.getCommands().get(parsedCommand).getObjects();
                for (final Object object : commands) {
                    if (object instanceof Command) {
                        Command command = (Command) object;
                        try {
                            command.execute();
                        } catch (ExecuteCmdException e) {
                            logger.error("Error", e);
                        } finally {
                            printEnter();
                        }
                    }
                }
            } else {
                logger.info("Command not found!");
            }
        } catch (MissingCommandException e) {
            logger.info(inputCommand + ": command not found!");
            printEnter();
        } catch (ParameterException e) {
            logger.info(inputCommand + ": invalid parameters passed " + e.getMessage());
            printEnter();
        } catch (Exception e) {
            logger.error("Error", e);
        }
    }

    private void printWelcome() {
        out.println(ansi().eraseScreen().a(Attribute.INTENSITY_BOLD).fg(Color.GREEN).a("Welcome to Twitter-Ripper application!").newline().reset());
        JCommanderFactory.getInstance().usage();
        printEnter();
    }

    private void printEnter() {
        logger.info(System.lineSeparator());
        out.println(ansi().a(Attribute.INTENSITY_BOLD).fg(Color.GREEN).a("Enter the command:").reset());
    }
}
