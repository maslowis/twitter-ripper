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

import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;
import info.maslowis.twitterripper.command.CommandName;
import info.maslowis.twitterripper.command.ExecuteCmdException;
import info.maslowis.twitterripper.command.TwitterCommand;
import twitter4j.Relationship;
import twitter4j.Twitter;
import twitter4j.TwitterException;

import static java.lang.System.out;

/**
 * Enable or disable notifications from a user by him ID
 *
 * @author Ivan Maslov
 */
@CommandName(name = "friend-update-id", aliases = "fui")
@Parameters(commandDescription = "Allows the authenticating user to enable or disable retweets and device notifications from the specified user in the ID parameter")
public class FriendUpdateId extends TwitterCommand {

    @Parameter(names = {"-id", "-i"}, description = "The user id to update", required = true)
    protected long id;

    @Parameter(names = {"-device", "-d"}, description = "Enable or disable device notifications")
    protected boolean device;

    @Parameter(names = {"-retweets", "-r"}, description = "Enable or disable device retweets")
    protected boolean retweets;

    public FriendUpdateId(Twitter twitter) {
        super(twitter);
    }

    @Override
    public void execute() throws ExecuteCmdException {
        try {
            Relationship relationship = twitter.updateFriendship(id, device, retweets);
            String output = String.format("Changed the notification settings from User{id=%1s, screenName='%2s'}", relationship.getTargetUserId(), relationship.getTargetUserScreenName());
            out.println(output);
        } catch (TwitterException e) {
            throw new ExecuteCmdException(e);
        }
    }
}
