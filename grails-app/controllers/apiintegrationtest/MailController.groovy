package apiintegrationtest

class MailController {

    def mailService

    def index() {}

    def mailPage(){

    }

    def contact(){

    }

    def sendMyMail(){



        String tomailId=params.tomailId

        String message = params.message

        String mailSubject=params.subject

        String errorMessage=''


        if(!tomailId.contains('@')|| tomailId.size()==0){
            errorMessage=errorMessage+'Provide correct mail Id. '
        }

        if(mailSubject.size()==0){
            errorMessage=errorMessage+' Mail subject cannot be empty. '
        }

        if(message.size()==0){
            errorMessage=errorMessage+' Message cannot be empty. '
        }

        if(!errorMessage.equals('')){
            flash.message=errorMessage
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

    def contactUsMail(){

        String emailID=params.mailId

        String tomailId=params.tomailId

        String message = params.message

        String fullMessage=message + '  ' + emailID

        String mailSubject=params.subject

        String errorMessage=''

        if(!emailID.contains('@')|| emailID.size()==0){
           errorMessage=errorMessage+'Provide correct mail Id. '
        }

        if(mailSubject.size()==0){
            errorMessage=errorMessage+' Mail subject cannot be empty. '
        }

        if(message.size()==0){
            errorMessage=errorMessage+' Message cannot be empty. '
        }

        if(!errorMessage.equals('')){
            flash.message=errorMessage
            redirect(controller: 'mail',action: 'contact')
        }
        else {

            mailService.sendMail {
                async true
                to tomailId
                subject mailSubject
                body fullMessage
            }

            println('***************************** Mail Send*******************************')

            redirect(controller: 'API', action: 'index')
        }

    }
}
