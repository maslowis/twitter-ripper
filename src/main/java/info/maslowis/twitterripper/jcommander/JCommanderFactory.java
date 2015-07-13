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

package info.maslowis.twitterripper.jcommander;

import com.beust.jcommander.JCommander;
import info.maslowis.twitterripper.command.Command;
import info.maslowis.twitterripper.command.impl.Clear;
import info.maslowis.twitterripper.command.impl.Exit;
import info.maslowis.twitterripper.command.impl.Help;
import info.maslowis.twitterripper.command.impl.twitter.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Factory for {@link com.beust.jcommander.JCommander}
 * <p/>
 * <p><em>This class should not be instancing.</em> It provide a static factory method.</p>
 *
 * @author Ivan Maslov
 */
public final class JCommanderFactory {

    private JCommanderFactory() {
    }

    /**
     * Returns new instance {@link com.beust.jcommander.JCommander} with available commandNames for application
     *
     * @return {@link com.beust.jcommander.JCommander}
     */
    public static JCommander getInstance() {
        JCommander jCommander = new JCommander();
        for (Command command : getCommands()) {
            jCommander.addCommand(command.getName(), command, command.getAliases());
        }
        return jCommander;
    }

    /*Initialize available commands*/
    private static List<Command> getCommands() {
        List<Command> commands = new ArrayList<>();
        /*Twitter commands*/
        commands.add(new UserSearch());
        commands.add(new TweetShow());
        commands.add(new TweetDelete());
        commands.add(new TweetDeleteAll());
        commands.add(new TweetAdd());
        commands.add(new TimelineGetId());
        commands.add(new TimelineGetName());
        commands.add(new FriendUpdateId());
        commands.add(new FriendUpdateName());
        commands.add(new FriendUpdateName());
        commands.add(new FriendGetId());
        commands.add(new FriendGetName());
        commands.add(new FriendDeleteId());
        commands.add(new FriendDeleteName());
        commands.add(new FriendDeleteAll());
        commands.add(new FriendAddId());
        commands.add(new FriendAddName());
        /*Common commands*/
        commands.add(new Help());
        commands.add(new Clear());
        commands.add(new Exit());
        return commands;
    }
}
