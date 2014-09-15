package apiintegrationtest

class MailController {

    def mailService

    def index() {}

    def mailPage(){

    }

    def contact(){

    }

    def sendMyMail(){

        String emailID=params.mailId

        String tomailId=params.tomailId

        String message

        if(emailID!=null) {
            message = params.message + '  ' + emailID
        }else{
            message = params.message
        }
        String mailSubject=params.subject



        if( mailSubject.equals('')){
            flash.message='Subject cannot be blank'
            redirect(controller: 'mail',action: 'mailPage')
        }

        if( message.equals('')){
            flash.message='Message cannot be blank'
            redirect(controller: 'mail',action: 'mailPage')
        }

       if(!tomailId.contains('@')|| tomailId.equals('')){
           flash.message='Provide correct mail Id'
           redirect(controller: 'mail',action: 'mailPage')
       }
        else {

           mailService.sendMail {
               async true
               to tomailId
               subject mailSubject
               body message
           }

           println('***************************** Mail Send*******************************')

           redirect(controller: 'API', action: 'index')
       }

       }
}
