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

import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;
import info.maslowis.twitterripper.command.AbstractTwitterCommand;
import info.maslowis.twitterripper.command.CommandName;
import info.maslowis.twitterripper.command.ExecuteCmdException;
import info.maslowis.twitterripper.util.Util;
import twitter4j.PagableResponseList;
import twitter4j.TwitterException;
import twitter4j.User;

import static java.lang.System.out;

/**
 * Returns a list of friends for a specific user by him name
 *
 * @author Ivan Maslov
 */
@CommandName(name = "friend-get-name", aliases = "fgn")
@Parameters(commandDescription = "Returns a list of user objects for every user the specified user is following")
public class FriendGetName extends AbstractTwitterCommand {

    @Parameter(names = {"-name", "-n"}, description = "The screen name of the user for whom to return results for", required = true)
    protected String name;

    @Parameter(names = {"-pointer", "-p"}, description = "The cursor that you should send to the endpoint to receive the next batch of responses")
    protected long pointer = -1L;

    @Parameter(names = {"-count", "-c"}, description = "The number of users to return per page, up to a maximum of 200")
    protected int count = 20;

    @Override
    public void execute() throws ExecuteCmdException {
        try {
            PagableResponseList<User> users = twitter.getFriendsList(name, pointer, count);
            if (users.isEmpty()) {
              out.println("No users");
            } else {
                for (User user : users) {
                    out.println(Util.toString(user));
                }
            }
        } catch (TwitterException e) {
            throw new ExecuteCmdException(e);
        }
    }


}