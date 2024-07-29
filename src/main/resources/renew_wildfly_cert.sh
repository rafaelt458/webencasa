#!/bin/bash

# Variables
DOMAIN="tutorialrafa.crabdance.com"
WILDFLY_HOME="/opt/wildfly"
LE_CERT_DIR="/etc/letsencrypt/live/$DOMAIN"
KEYSTORE_PASSWORD="labpass"
KEYSTORE="$WILDFLY_HOME/standalone/configuration/labrafakeystore.jks"
WILDFLY_SERVICE="wildfly"

# Logging function
log_message() {
    echo "$1" >> /var/log/wildfly_cert_renewal.log
}

# Log the start of the process
log_message "Starting certificate renewal process for $DOMAIN at $(date)"

# Remove the old keystore
rm -f $KEYSTORE

# Convert Let's Encrypt certificates to PKCS12
openssl pkcs12 -export \
    -in $LE_CERT_DIR/fullchain.pem \
    -inkey $LE_CERT_DIR/privkey.pem \
    -out /tmp/$DOMAIN.p12 \
    -name $DOMAIN \
    -CAfile $LE_CERT_DIR/chain.pem \
    -caname root \
    -passout pass:$KEYSTORE_PASSWORD 2>/dev/null

# Import the PKCS12 file into a Java Keystore
keytool -importkeystore \
    -deststorepass $KEYSTORE_PASSWORD \
    -destkeypass $KEYSTORE_PASSWORD \
    -destkeystore $KEYSTORE \
    -srckeystore /tmp/$DOMAIN.p12 \
    -srcstoretype PKCS12 \
    -srcstorepass $KEYSTORE_PASSWORD \
    -alias $DOMAIN 2>/dev/null

# Clean up temporary PKCS12 file
rm /tmp/$DOMAIN.p12

# Restart WildFly service
sudo systemctl restart $WILDFLY_SERVICE

# Log the completion of the process
log_message "Renewal process for $DOMAIN completed and WildFly restarted at $(date)"

# Output to stdout to confirm script execution
echo "Renewal process for $DOMAIN completed and WildFly restarted at $(date)"
