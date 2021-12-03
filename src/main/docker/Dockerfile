#
# A Dockerfile for a servlet-based Jetty Web service.
#

ARG ALPINE_VERSION

FROM alpine:${ALPINE_VERSION}

# The name of the Jar we're going to use for our Web service
ARG SERVICE_WAR

# Define the Jetty environmental variables
ENV JETTY_HOME="/usr/local/jetty"
ENV JETTY_WAR="${JETTY_HOME}/${SERVICE_WAR}"

# Copy project resources into the Web service's Docker image
WORKDIR "${JETTY_HOME}"
COPY "maven/target/${SERVICE_WAR}" .
COPY "maven/src/main/docker/scripts/start-service" "/usr/bin/start-service"

# Install Jetty (which also pulls in OpenJDK as a dependency)
RUN apk add jetty-runner

# Expose the port at which our Jetty server will run
EXPOSE 8080

# Start our service when the Docker container runs
ENTRYPOINT sh -c "start-service ${JETTY_WAR}"
