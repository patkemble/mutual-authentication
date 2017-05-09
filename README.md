# Service Authentication Example
This project is intended to show how an SSL certificate
can be used to authenticate between two spring boot applications

## SSL Certs
Each spring boot runtime needs to have it's own certificate and keystore to reference and use for calling from the "client" app to the "server" app

There are keystores already present in each sub-project under the src/main/resources that can be used.

You can also use the keyGen.sh script to generate local certificates, export the public keys and import those public keys into the respective keystores which are copied to the appropriate folders too

Steps to certificate preparedness:

1. Create the keystore for the server
2. Create the keystore for the client
3. Export the public key from the server keystore
4. Export the public key from the client keystore
5. Import the client public certificate key into the server keystore
6. Import the server pulic certificate key into the client keystore.

## Server
The server application represents the endpoint that would be called in the transaction and requires SSL and a certificate to be presented in
order to authenticate and allow the transaction to go ahead.

The keystore, password, etc. are all configured within the application.yml file within the src/main/resources folder.
The client-auth: need property requires a client certificate to be presented in order to allow access to the application over https

If a valid certificate isn't presented the caller receives a 401 http status code in response


## Client
The client app is setup to run on a randomly generated port (server.port: 0)

Upon startup the client loads the client keystore which includes the
public key from the server's keystore.
This is used as part of the handshake between the client
and the server.

The client takes one parameter which is the URL of the server that should be called after startup (https://localhost:8080/hello) and the
response will be printed out in the logs of the client application

<code>
2017-05-09 12:50:35.986  INFO 24220 --- [           main] c.davita.physician.security.RestClient   : hello world
</code>

You can see the error received if you attempt to access the server URL from the browser or from curl on the command line