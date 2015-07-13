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

package info.maslowis.twitterripper.twitter;

import jline.console.ConsoleReader;
import org.apache.log4j.Logger;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.RequestToken;

import java.io.IOException;

import static java.lang.System.exit;
import static java.lang.System.out;
import static org.fusesource.jansi.Ansi.*;

/**
 * The PIN-based OAuth
 *
 * @author Iavn Maslov
 */
public final class OAuthTwitter {
    private final static Logger logger = Logger.getLogger(OAuthTwitter.class);
    private final String consumerKey = "BGLOSuhvkQP9AZpsMhFmL6CKW";
    private final String consumerKeySecret = "10qVt73golhCM9vQj0z9wqQYPaV94xpaUEAh6T8UqFmFcMWzt6";
    private final ConsoleReader reader;

    public OAuthTwitter(ConsoleReader reader) {
        this.reader = reader;
    }

    /**
     * Displays authorization URL and gets PIN for connection to twitter under authorized user
     */
    public void authorization() throws IOException {
        try {
            Twitter twitter = TwitterFactory.getSingleton();
            twitter.setOAuthConsumer(consumerKey, consumerKeySecret);
            RequestToken requestToken = twitter.getOAuthRequestToken();
            out.println(ansi().a(Attribute.INTENSITY_BOLD).fg(Color.GREEN).a("Authorization URL:").reset());
            out.println(ansi().a(Attribute.INTENSITY_BOLD).fg(Color.CYAN).a(requestToken.getAuthorizationURL()));
            out.println(ansi().a(Attribute.INTENSITY_BOLD).fg(Color.RED).a("Enter PIN please:").reset());
            String pin = reader.readLine();
            twitter.getOAuthAccessToken(requestToken, pin);
        } catch (TwitterException e) {
            logger.error("Authorization fail! Please try to run application again and enter PIN.");
            exit(1);
        }
    }
}
