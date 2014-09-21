package apiintegrationtest

import org.example.SecUser
import twitter4j.Twitter
import twitter4j.TwitterFactory


class TwitterController {

    //def index() {}

    def springSecurityService

   String key = 'n37SvZq0AtNR3N39BI442I0vt'   //Your twitter app key
    String secret = 'nquOQFOfzRWhZpzj4r6UZVMjibyM8dw4ZObiA1PUmUmgIxkCgW' //Your twitter app secret
    String callbackUrl = "http://127.0.0.1:8080/APIIntegrationTest/twitter/twitterRequest"
    String requestToken
    Twitter twitter

    def requestLogin() {

        //def twitter = new twitter4j.Twitter()

      // def twitter =new TwitterFactory().
        twitter = TwitterFactory.getSingleton();
        //def callbackUrl = g.createLink(action: 'processLogin', absolute:true).toString()

       /* def consumerKey = ConfigurationHolder.config.twitter.oauth.consumer_key

        def consumerSecret = ConfigurationHolder.config.twitter.oauth.consumer_secret*/

        twitter.setOAuthConsumer(key, secret)

        def requestToken = twitter.getOAuthRequestToken(callbackUrl)

        println("***************Request token is************************"+requestToken)

        //session.twitter = twitter

        //session.requestToken = requestToken

        redirect(url:requestToken.getAuthorizationURL())

    }

    def twitterRequest() {

        println("1111111111111111111111111111111111111111111111111111111111111111111")
        if (!requestToken) {
            println("2222222222222222222222222222222222222222222222222222222222222222")
            redirect(action: 'requestLogin')

        } else {
            println("33333333333333333333333333333333333333333333333333333333333333333")
            def accessToken = twitter.getOAuthAccessToken(requestToken, params.oauth_verifier)
            println("444444444444444444444444444444444444444444444444444444444444444444")
            def twitterUser = twitter.verifyCredentials()
            println("55555555555555555555555555555555555555555555555555555555555555555555555")
            session.twitterUser = twitterUser

            println("******************************** Inside Last method***********************"+accessToken)
            println("***************Twitter Credentials********"+twitterUser.getId()+"*******************"+twitterUser.getScreenName())

            SecUser user = SecUser.findByUsername(twitterUser.getScreenName())
            if (user) {
                // If user found with this facebook id, authenticate him
                springSecurityService.reauthenticate(twitterUser.getScreenName())

                println('*************************User found in database**********************')
            } else {
                SecUser secUser=new SecUser();
                secUser.setUsername(twitterUser.getScreenName())
                secUser.setPassword(twitterUser.getId())
                secUser.save flush:true
                println('*************************User not found in database**********************')
                springSecurityService.reauthenticate(twitterUser.getScreenName())

            }
            redirect(controller: 'facebook', action: 'fMessage')


        }

    }

}


