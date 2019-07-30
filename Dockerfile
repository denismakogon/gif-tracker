FROM openjdk:13 as maven-stage

RUN curl -L http://repos.fedorapeople.org/repos/dchen/apache-maven/epel-apache-maven.repo -o /etc/yum.repos.d/epel-apache-maven.repo && \
    yum update -y && yum install -y apache-maven

ADD . /project
WORKDIR /project
RUN mvn clean && ./build_dependencies.sh && mvn package

FROM oraclelinux:7-slim

RUN curl -L https://raw.githubusercontent.com/denismakogon/oraclelinux-opencv/master/apply_binaries.sh | /bin/bash
RUN yum update -y && yum install -y gtk2-devel
ADD original.gif /original.gif

COPY --from=manve-stage /project/target/*.jar /target/
COPY --from=maven-stage /project/main.app/target/maven-jlink /jdk

ADD entrypoint.sh /entrypoint.sh

ENTRYPOINT ["/bin/bash", "entrypoint.sh"]
