http-client-tester
==================

An HTTP based client app to API services.  This project is used to 
run a set of pre-created models.  It will submit the models to a 
running instance of the server via the HTTP interrface and XML payloads.  
The models are assumed to come from a model that was previous created and 
will trigger a unit of work.    


The execution will then be done from the command line as shown below:

java -jar wciTest.jar --user admin -passwd admin -modeldir /tmp/bob

where 
    user - will be the user to log into KAS as
    passwd - will be the password for that user
    modeldir - will be the directory where the model files are stored
