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

package info.maslowis.twitterripper.command.impl.twitter;

import com.beust.jcommander.Parameters;
import info.maslowis.twitterripper.application.Application;
import info.maslowis.twitterripper.command.CommandName;
import info.maslowis.twitterripper.command.ExecuteCmdException;
import info.maslowis.twitterripper.command.TwitterCommand;
import info.maslowis.twitterripper.util.Util;
import org.fusesource.jansi.Ansi;
import twitter4j.PagableResponseList;
import twitter4j.TwitterException;
import twitter4j.User;

import java.io.IOException;

import static java.lang.System.exit;
import static java.lang.System.out;

/**
 * Delete all user from the friend list of the authenticating user
 *
 * @author Ivan Maslov
 */
@CommandName(name = "friend-delete-all", aliases = "fda")
@Parameters(commandDescription = "Allows the authenticating user unfollow from all user")
public class FriendDeleteAll extends TwitterCommand {

    @Override
    public void execute() throws ExecuteCmdException {
        out.println(Ansi.ansi().a(Ansi.Attribute.INTENSITY_BOLD).fg(Ansi.Color.RED).a("This command delete all user from your friend list! You want to continue? [yes/no]").reset());
        try {
            String input = Application.INSTANCE.getReader().readLine().trim();
            if (input.equalsIgnoreCase("yes")) {
                try {
                    long nextCursor = -1L;
                    final int countEntry = 200;
                    while (nextCursor != 0) {
                        PagableResponseList<User> users = twitter.getFriendsList(twitter.getId(), nextCursor, countEntry);
                        for (User user : users) {
                            twitter.friendsFollowers().destroyFriendship(user.getId());
                            out.println("You unfollow from " + Util.toString(user));
                        }
                        nextCursor = users.getNextCursor();
                    }
                } catch (TwitterException e) {
                    throw new ExecuteCmdException(e);
                }
            } else {
                return;
            }
        } catch (IOException e) {
            logger.error("Error reading input", e);
            exit(-1);
        }
    }
}
