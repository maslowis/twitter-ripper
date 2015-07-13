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

import com.beust.jcommander.Parameters;
import info.maslowis.twitterripper.application.Application;
import info.maslowis.twitterripper.command.Command;
import info.maslowis.twitterripper.command.CommandName;
import info.maslowis.twitterripper.command.ExecuteCmdException;
import org.fusesource.jansi.Ansi;

import static java.lang.System.out;
import static org.fusesource.jansi.Ansi.ansi;

/**
 * Exit from the application
 *
 * @author Ivan Maslov
 */
@CommandName(name = "exit", aliases = {"quit", "e", "x", "q"})
@Parameters(commandDescription = "Exit from the application")
public class Exit extends Command {

    @Override
    public void execute() throws ExecuteCmdException {
        out.println(ansi().a(Ansi.Attribute.INTENSITY_BOLD).fg(Ansi.Color.GREEN).a("Good bye!").reset());
        Application.INSTANCE.stop();
        System.exit(0);
    }
}
