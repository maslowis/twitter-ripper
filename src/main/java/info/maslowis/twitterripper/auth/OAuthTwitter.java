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

package info.maslowis.twitterripper.auth;

import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * The PIN-based OAuth
 *
 * @author Iavn Maslov
 */
public class OAuthTwitter {
    private final BufferedReader consoleReader;
    private final String consumerKey = "BGLOSuhvkQP9AZpsMhFmL6CKW";
    private final String consumerKeySecret = "10qVt73golhCM9vQj0z9wqQYPaV94xpaUEAh6T8UqFmFcMWzt6";

    public OAuthTwitter(BufferedReader consoleReader) {
        this.consoleReader = consoleReader;
    }

    /**
     * Displays authorization URL and gets PIN for connection to twitter under authorized user
     *
     * @return {@link twitter4j.Twitter}
     */
    public Twitter getTwitter() {
        Twitter twitter = new TwitterFactory().getInstance();
        twitter.setOAuthConsumer(consumerKey, consumerKeySecret);
        try {
            RequestToken requestToken = twitter.getOAuthRequestToken();
            System.out.println("Authorization URL:\n" + requestToken.getAuthorizationURL());
            AccessToken accessToken = null;
            while (accessToken == null) {
                System.out.print("Input PIN here: ");
                try {
                    String pin = consoleReader.readLine();
                    accessToken = twitter.getOAuthAccessToken(requestToken, pin);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (TwitterException e) {
            e.printStackTrace();
        }
        return twitter;
    }
}
