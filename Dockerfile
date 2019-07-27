# todo: build loom branch here
FROM openjdk:13

RUN curl -L https://raw.githubusercontent.com/denismakogon/oraclelinux-opencv/master/apply_binaries.sh | /bin/bash

# required by javacv
RUN yum update -y && yum install -y gtk2-devel
ADD original.gif /original.gif
ADD target/*linux-x86_64.jar /target/
ADD target/gif-processor-1.0.jar /target/gif-processor-1.0.jar
ADD entrypoint.sh /entrypoint.sh

ENTRYPOINT ["/bin/bash", "entrypoint.sh"]
