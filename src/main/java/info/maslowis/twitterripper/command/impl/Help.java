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

package info.maslowis.twitterripper.command.impl;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.beust.jcommander.Parameters;
import info.maslowis.twitterripper.command.AbstractCommand;
import info.maslowis.twitterripper.command.CommandName;
import info.maslowis.twitterripper.command.ExecuteCmdException;
import info.maslowis.twitterripper.jcommander.JCommanderFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Output the help for all available commands
 *
 * @author Ivan Maslov
 */
@CommandName(name = "help", aliases = {"usage", "h"})
@Parameters(commandDescription = "Print help for available commands")
public class Help extends AbstractCommand {

    @Parameter(description = "The command names")
    protected List<String> commandNames = new ArrayList<>();

    @Override
    public void execute() throws ExecuteCmdException {
        JCommander jCommander = JCommanderFactory.getInstance();
        if (commandNames.isEmpty()) {
            jCommander.usage();
        } else {
            for (String commandName : commandNames) {
                try {
                    jCommander.getCommandDescription(commandName);
                    jCommander.usage(commandName);
                } catch (ParameterException e) {
                    logger.info(commandName + ": no help for. Command unknown!");
                }
            }
        }
    }
}
