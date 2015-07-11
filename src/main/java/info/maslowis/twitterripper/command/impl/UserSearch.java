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
import info.maslowis.twitterripper.util.Util;
import twitter4j.ResponseList;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.User;

import static java.lang.System.out;

/**
 * Perform search for a users of the twitter
 *
 * @author Ivan Maslov
 */
@CommandName(name = "user-search", aliases = "us")
@Parameters(commandDescription = "Run a search for users similar to the Find People button on Twitter.com")
public class UserSearch extends TwitterCommand {

    @Parameter(names = {"-query", "-q"}, description = "The query to run against people search", required = true)
    protected String query;

    @Parameter(names = {"-page", "-p"}, description = "The page of results to retrieve, number of statuses per page is fixed to 20")
    protected int page = 1;

    public UserSearch(Twitter twitter) {
        super(twitter);
    }

    @Override
    public void execute() throws ExecuteCmdException {
        try {
            ResponseList<User> users = twitter.users().searchUsers(query, page);
            for (User user : users) {
                out.println(Util.toString(user));
            }
        } catch (TwitterException e) {
            throw new ExecuteCmdException(e);
        }
    }
}
