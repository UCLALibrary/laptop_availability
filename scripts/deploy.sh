#!/bin/sh

# Set variables to make this easier to adapt to other projects.
SFTP_SERVER=travisci-sftp.library.ucla.edu
SFTP_USER=travisci
SFTP_TARGET_DIR=build/laptop_availability
SFTP_FILESPEC='*.war'

# Decrypt our private key, using travis encryption.
# -K and -iv apparently set by travis.
openssl aes-256-cbc -K $encrypted_7270e623501c_key -iv $encrypted_7270e623501c_iv \
  -in id_rsa_travisci.enc -out id_rsa_travisci -d

# Set required permissions for decrypted private key
# and move to required location.
chmod 600 id_rsa_travisci
mv id_rsa_travisci ~/.ssh/id_rsa

# Add sftp site to known_hosts to avoid permanent hang on first sftp connection
ssh-keyscan travisci-sftp.library.ucla.edu >> ~/.ssh/known_hosts

# Upload file(s) to sftp site in project-specific directory

(
  echo "cd ${SFTP_TARGET_DIR}"
  echo "rm ${SFTP_FILESPEC}"
  echo "put target/${SFTP_FILESPEC}"
  echo "ls -l"
) | sftp ${SFTP_USER}@${SFTP_SERVER}

