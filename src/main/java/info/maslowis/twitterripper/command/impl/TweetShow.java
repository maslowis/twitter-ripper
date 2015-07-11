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
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;

import static java.lang.System.out;

/**
 * Show a specific tweet by ID
 *
 * @author Ivan Maslov
 */
@CommandName(name = "tweet-show", aliases = "ts")
@Parameters(commandDescription = "Returns the status specified by the required ID parameter")
public class TweetShow extends TwitterCommand {

    @Parameter(description = "The ID of the status you're trying to retrieve", required = true)
    protected long id;

    public TweetShow(Twitter twitter) {
        super(twitter);
    }

    @Override
    public void execute() throws ExecuteCmdException {
        try {
            Status status = twitter.showStatus(id);
            out.println(Util.toString(status));
        } catch (TwitterException e) {
            throw new ExecuteCmdException(e);
        }
    }
}
