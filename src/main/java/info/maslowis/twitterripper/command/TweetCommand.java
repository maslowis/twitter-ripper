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

package info.maslowis.twitterripper.command;

import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;

/**
 * Commands for work with a tweets
 *
 * @author Ivan Maslov
 */
@Parameters(commandNames = "tweet", commandDescription = "Operations with user's tweets")
public class TweetCommand implements Command {
    @Parameter(names = "-p", description = "Post a tweet")
    private String post;
    @Parameter(names = "-f", description = "Search for tweets")
    private String find;
    @Parameter(names = "-g", description = "Get a list of latest tweets from user's home timeline")
    private String get;
    @Parameter(names = "-d", description = "Delete a tweet by Id")
    private String delete;

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public String getFind() {
        return find;
    }

    public void setFind(String find) {
        this.find = find;
    }

    public String getGet() {
        return get;
    }

    public void setGet(String get) {
        this.get = get;
    }

    public String getDelete() {
        return delete;
    }

    public void setDelete(String delete) {
        this.delete = delete;
    }
}
