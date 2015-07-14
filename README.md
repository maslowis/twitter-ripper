# Twitter-Ripper
Console client for twitter account.

Allows to perform **CRUD** (create, read, update, delete) operations on friends (user objects for every user the specified user is following) and tweets. The application supports [PIN-based authorization for Twitter] (https://dev.twitter.com/oauth/pin-based).

Run the application by using the console command `java -jar twitter-ripper.jar`. After it displays a message with the authorization URL. You should enter this URL in the browser to get the PIN and enter this PIN in the console. After successful authorization it displays a welcome message. The application is ready to use. 

## List of available commands

    user-search(us)      Run a search for users similar to the Find People button on Twitter.com
      Usage: user-search(us) [options]
        Options:
          -page, -p
             The page of results to retrieve, number of statuses per page is
             fixed to 20
             Default: 1
        * -query, -q
             The query to run against people search

    tweet-show(ts)      Returns the status specified by the required ID parameter
      Usage: tweet-show(ts) [options]
        Options:
        * -id, -i
             The ID of the status you're trying to retrieve
             Default: 0

    tweet-delete(td)      Destroys the status specified by the required ID parameter
      Usage: tweet-delete(td) [options]
        Options:
        * -id, -i
             The ID of the status to destroy
             Default: 0

    tweet-delete-all(tda)      Destroys all statuses of the authenticating user
      Usage: tweet-delete-all(tda) [options]

    tweet-add(ta)      Updates the authenticating user's text
      Usage: tweet-add(ta) [options] The text of your text update

    timeline-get-id(tgi)      Returns the recent statuses posted from the user specified in the ID parameter
      Usage: timeline-get-id(tgi) [options]
        Options:
          -count, -c
             The number of statuses to return per page
             Default: 20
        * -id, -i
             The ID of the user for whom to return the timeline
             Default: 0
          -page, -p
             The requesting page
             Default: 1

    timeline-get-name(tgn)      Returns the recent statuses posted from the user specified in the name parameter
      Usage: timeline-get-name(tgn) [options]
        Options:
          -count, -c
             The number of statuses to return per page
             Default: 20
        * -name, -n
             The screen name of the user for whom to return the timeline
          -page, -p
             The requesting page
             Default: 1

    friend-update-id(fui)      Allows the authenticating user to enable or disable retweets and device notifications from the specified user in the ID parameter
      Usage: friend-update-id(fui) [options]
        Options:
          -device, -d
             Enable or disable device notifications
             Default: false
        * -id, -i
             The user id to update
             Default: 0
          -retweets, -r
             Enable or disable device retweets
             Default: false

    friend-update-name(fun)      Allows the authenticating user to enable or disable retweets and device notifications from the specified user in the name parameter
      Usage: friend-update-name(fun) [options]
        Options:
          -device, -d
             Enable or disable device notifications
             Default: false
        * -name, -n
             The screen name of user to update
          -retweets, -r
             Enable or disable device retweets
             Default: false

    friend-get-id(fgi)      Returns a list of user objects for every user the specified user is following
      Usage: friend-get-id(fgi) [options]
        Options:
          -count, -c
             The number of users to return per page, up to a maximum of 200
             Default: 20
        * -id, -i
             The ID of the user for whom to return results for
             Default: 0
          -pointer, -p
             The cursor that you should send to the endpoint to receive the next
             batch of responses
             Default: -1

    friend-get-name(fgn)      Returns a list of user objects for every user the specified user is following
      Usage: friend-get-name(fgn) [options]
        Options:
          -count, -c
             The number of users to return per page, up to a maximum of 200
             Default: 20
        * -name, -n
             The screen name of the user for whom to return results for
          -pointer, -p
             The cursor that you should send to the endpoint to receive the next
             batch of responses
             Default: -1

    friend-delete-id(fdi)      Allows the authenticating user to unfollow the user specified in the ID parameter
      Usage: friend-delete-id(fdi) [options]
        Options:
        * -id, -i
             The ID of the user from which to unfollow
             Default: 0

    friend-delete-name(fdn)      Allows the authenticating user to unfollow the user specified in the name parameter
      Usage: friend-delete-name(fdn) [options]
        Options:
          -name, -n
             The screen name of the user from which to unfollow

    friend-delete-all(fda)      Allows the authenticating user unfollow from all user
      Usage: friend-delete-all(fda) [options]

    friend-add-id(fai)      Allows the authenticating user to follow the user specified in the ID parameter
      Usage: friend-add-id(fai) [options]
        Options:
          -follow, -f
             Enable notifications for the target user in addition to becoming
             friends
             Default: false
        * -id, -i
             The ID of the user to be befriended
             Default: 0

    friend-add-name(fan)      Allows the authenticating user to follow the user specified in the name parameter
      Usage: friend-add-name(fan) [options]
        Options:
          -follow, -f
             Enable notifications for the target user in addition to becoming
             friends
             Default: false
        * -name, -n
             The screen name of the user to be befriended

    help(usage,h)      Print help for available commands
      Usage: help(usage,h) [options] The command names

    clear(c)      Clear the screen
      Usage: clear(c) [options]