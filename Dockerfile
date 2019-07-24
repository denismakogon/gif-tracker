FROM openjdk:13

RUN curl -L https://raw.githubusercontent.com/denismakogon/java-opencv/master/apply_binaries.sh | /bin/bash

# required by javacv
RUN yum update -y && yum install -y gtk2-devel
ADD original.gif /original.gif
ADD target/*.jar /target/
ADD entrypoint.sh /entrypoint.sh

ENTRYPOINT ["/bin/bash", "entrypoint.sh"]
