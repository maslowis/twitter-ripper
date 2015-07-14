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
import twitter4j.TwitterException;
import twitter4j.User;

import static java.lang.System.out;

/**
 * Delete a user by ID from the friend list of the authenticating user
 *
 * @author Ivan Maslov
 */
@CommandName(name = "friend-delete-id", aliases = "fdi")
@Parameters(commandDescription = "Allows the authenticating user to unfollow the user specified in the ID parameter")
public class FriendDeleteId extends AbstractTwitterCommand {

    @Parameter(names = {"-id", "-i"}, description = "The ID of the user from which to unfollow", required = true)
    protected long id;

    @Override
    public void execute() throws ExecuteCmdException {
        try {
            User user = twitter.friendsFollowers().destroyFriendship(id);
            out.println("You unfollow from " + Util.toString(user));
        } catch (TwitterException e) {
            throw new ExecuteCmdException(e);
        }
    }
}
