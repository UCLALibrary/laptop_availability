#
# A Dockerfile for a servlet-based Jetty Web service.
#

# The version of Alpine that we use when creating this image
ARG ALPINE_VERSION

# Build our actual image
FROM alpine:${ALPINE_VERSION}

# Build arguments used to build our Docker image
ARG SERVICE_NAME
ARG SERVICE_WAR="${SERVICE_NAME}.war"
ARG SERVICE_ETC="/etc/${SERVICE_NAME}"

# Define the Jetty environmental variables
ENV JETTY_HOME="/usr/local/jetty"
ENV JETTY_WAR="${JETTY_HOME}/${SERVICE_WAR}"

# Copy the logging configuration file into the service's etc directory
WORKDIR "${SERVICE_ETC}"
COPY "maven/src/main/docker/configs/logback.xml" "${SERVICE_ETC}/logback.xml"

# Copy project resources into the Web service's Docker image
WORKDIR "${JETTY_HOME}"
COPY "maven/target/${SERVICE_WAR}" .
COPY "maven/src/main/docker/scripts/start-service" "/usr/bin/start-service"

# Install Jetty (which also pulls in OpenJDK as a dependency)
RUN apk --no-cache add jetty-runner zip

# Run as a generic system user, rather than as root
RUN addgroup -S jetty && adduser --disabled-password --no-create-home -S jetty -G jetty \
    && chown -R jetty:jetty "${JETTY_HOME}"
USER jetty

# Expose the port at which our Jetty server will run
EXPOSE 8080

# Start our service when the Docker container runs
ENTRYPOINT sh -c "start-service ${JETTY_WAR}"
