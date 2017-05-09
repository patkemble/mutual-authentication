#!/bin/bash

# Create the server keystore
keytool -genkeypair -alias serverkey -keyalg RSA -dname "CN=Server,OU=Physican Experience,O=DaVita,L=Casa Nuevo,S=CO,C=US" -keypass password -keystore server.jks -storepass password

# Export the public Server cert
keytool -exportcert -alias serverkey -file server-public.cer -keystore server.jks -storepass password

# Create the client keystore
keytool -genkeypair -alias clientkey -keyalg RSA -dname "CN=Client,OU=Physician Experience,O=DaVita,L=Casa Nuevo,S=CO,C=US" -keypass password -keystore client.jks -storepass password

# Export the public client cert
keytool -exportcert -alias clientkey -file client-public.cer -keystore client.jks -storepass password


# Import server public cert into client keystore
keytool -importcert -keystore client.jks -alias servercert -file server-public.cer -storepass password -noprompt

# Import client public cert into server keystore
keytool -importcert -keystore server.jks -alias clientcert -file client-public.cer -storepass password -noprompt

# Copy keystores into the respective project folders src/main/resources
cp server.jks server/src/main/resources

cp client.jks client/src/main/resources