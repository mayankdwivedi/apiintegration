package apiintegrationtest

import org.example.SecUser

class SpringSecurityController {

    def springSecurityService

    def index() {

    }

    def springPage(){

    }

    def welcome(){

    }

    def testPage(){

    }

    def validate(){
        def username=params.username
        def password=params.password

        def encodedPassword=springSecurityService.encodePassword(password)

        SecUser user=SecUser.findByUsernameAndPassword(username,encodedPassword)

        if(user){

          springSecurityService.reauthenticate(username)
            redirect(controller:'springSecurity', action:'testPage')
        }
        else{
            flash.message='Invalid credentials'
            redirect(controller:'springSecurity', action:'springPage')

        }
    }
}
