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

package info.maslowis.twitterripper.util;

import twitter4j.Status;
import twitter4j.User;

/**
 * Helper class
 * <p/>
 * <p><em>This class should not be instancing.</em> It include only a helper static methods.</p>
 *
 * @author Ivan Maslov
 */
public final class Util {

    private Util() {
    }

    /**
     * Returns a representation the user as string
     *
     * @return a representation the user as string in format <em>User{id=, name='', screenName='', location='', description=''}</em>
     */
    public static String toString(final User user) {
        return "User{id=" + user.getId() + ", name='" + user.getName() + "', screenName='" + user.getScreenName() + "', location='" + user.getLocation() + "', description='" + user.getDescription() + "'}";
    }

    /**
     * Returns a representation the status (tweet) as string
     *
     * @return a representation the status as string in format <em>Status{id=, text='', lang='', createdAt=, geoLocation=, isFavorited=, isRetweeted=, retweetCount=, isTruncated=}</em>
     */
    public static String toString(final Status status) {
        return "Status{id=" + status.getId() + ", text='" + status.getText() + "', lang='" + status.getLang() + "', createdAt=" + status.getCreatedAt() + ", geoLocation=" + status.getGeoLocation() + ", isFavorited=" + status.isFavorited() + ", isRetweeted=" + status.isRetweeted() + ", retweetCount=" + status.getRetweetCount() + ", isTruncated=" + status.isTruncated() + "}";
    }

}
