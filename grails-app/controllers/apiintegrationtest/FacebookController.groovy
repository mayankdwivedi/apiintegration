package apiintegrationtest

import com.sun.xml.internal.ws.encoding.HeaderTokenizer
import grails.converters.JSON
import org.codehaus.groovy.classgen.Verifier
import org.example.SecUser

class FacebookController {

     String appId='292305014282618'
    String appSecret='a1f526e954c040d5a0ca9e7565602da9'
    String accessToken

    def springSecurityService

    def mailService

    def index() {

        render view: 'index.gsp'

    }


    def facebookCall() {

        //String appId = "${grailsApplication.config.grails.facebookAppId}"
        String callBack = "http://localhost:8080/APIIntegrationTest/facebook/facebookRequest"
        println("******************** CALL BACK URL****************************"+callBack)

        String permission = 'email,publish_stream'
        String facebookAuthUrl = "http://graph.facebook.com/oauth/authorize?client_id=${appId}&redirect_uri=${callBack}&scope=${permission}"
        redirect(url: facebookAuthUrl)


    }

    def facebookRequest() {

        String callBack = "http://localhost:8080/APIIntegrationTest/facebook/facebookRequest"
        String authCode = params.code
        println("&&&&&&&&&&&&&&&&&&&&&&&&& Auth Code is &&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&")

        println("&&&&&&&&&&&&&&&&&&&&&&&&Auth Code Value"+authCode)

        println("&&&&&&&&&&&&&&&&&&&&&&&&& Auth Code End &&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&")


        String facebookUrl = "https://graph.facebook.com/oauth/access_token?client_id=${appId}&client_secret=${appSecret}&code=${authCode}&redirect_uri=${callBack}"
        URL url = new URL(facebookUrl)

        println("************************ URL is"+url)
        String response = url.text
        println("############After URL.Text#######################################")
      accessToken = response.substring((response.indexOf('=') + 1), response.indexOf('&'))

        println('############################ ACCESS TOKEN #############################################'+accessToken)

        redirect(action: 'facebookResponse')
    }

    def facebookResponse() {

        URL profileURL = new URL("https://graph.facebook.com/me?fields=first_name,last_name,email&access_token=${accessToken}")
        String profileJson = profileURL.text
        def profileData = JSON.parse(profileJson)


            println('#########First Name###########'+profileData.first_name)

            /*socialProfile.lastName = profileData.last_name
            socialProfile.email = profileData.email
            socialProfile.socialProfileId = profileData.id
            socialProfile.accessToken = accessToken
            socialProfile.user = User.findByUsername(User.findById(userFB).username)*/

        println("########################Profile Data is#################################"+profileData.firstN)
        /*[id:770713062966998, first_name:Mayank, email:mayanksep19@gmail.com, last_name:Dwivedi*/

        SecUser user = SecUser.findByUsername(profileData.email)
        if (user) {
            // If user found with this facebook id, authenticate him
            springSecurityService.reauthenticate(profileData.email)

            println('*************************User found in database**********************')
        } else {
            SecUser secUser=new SecUser();
            secUser.setUsername(profileData.email)
            secUser.setPassword(profileData.id)
            secUser.save flush:true
            println('*************************User not found in database**********************')
            springSecurityService.reauthenticate(profileData.email)

        }
            redirect(controller: 'facebook', action: 'fMessage')

    }

    def sendMyMail(){

        mailService.sendMail {
            async true
            to 'rkriteshkumar.23@gmail.com','mayanksep19@gmail.com'
            subject 'Grails Mail'
            body 'This is grails mail in your inbox.'
        }

        println('***************************** Mail Send*******************************')
    }

    def fMessage(){

      render view: 'fMessage.gsp'
    }
}
