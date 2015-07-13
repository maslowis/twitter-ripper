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
import twitter4j.Paging;
import twitter4j.ResponseList;
import twitter4j.Status;
import twitter4j.TwitterException;

import java.io.IOException;

import static java.lang.System.exit;
import static java.lang.System.out;

/**
 * Delete all tweets of the authenticating user
 *
 * @author Ivan Maslov
 */
@CommandName(name = "tweet-delete-all", aliases = "tda")
@Parameters(commandDescription = "Destroys all statuses of the authenticating user")
public class TweetDeleteAll extends TwitterCommand {

    @Override
    public void execute() throws ExecuteCmdException {
        out.println(Ansi.ansi().a(Ansi.Attribute.INTENSITY_BOLD).fg(Ansi.Color.RED).a("This command delete all yours statuses! You want to continue? [yes/no]").reset());
        try {
            String input = Application.INSTANCE.getReader().readLine().trim();
            if (input.equalsIgnoreCase("yes")) {
                try {
                    int page = 1;
                    final int count = 200;
                    Paging paging = new Paging(page, count);
                    ResponseList<Status> statuses = twitter.getUserTimeline(twitter.getId(), paging);
                    while (!statuses.isEmpty()) {
                        for (Status status : statuses) {
                            twitter.destroyStatus(status.getId());
                            out.println("You now deleted " + Util.toString(status));
                        }
                        page++;
                        paging = new Paging(page, count);
                        statuses = twitter.getUserTimeline(twitter.getId(), paging);
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
