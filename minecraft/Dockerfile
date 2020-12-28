FROM openjdk:13-alpine
VOLUME "/world"


ENV JAVA_ARGS="-XX:+UseG1GC -XX:+UnlockExperimentalVMOptions -XX:MaxGCPauseMillis=100 -XX:+DisableExplicitGC -XX:TargetSurvivorRatio=90 -XX:G1NewSizePercent=50 -XX:G1MaxNewSizePercent=80 -XX:G1MixedGCLiveThresholdPercent=35 -XX:+AlwaysPreTouch -XX:+ParallelRefProcEnabled -Dusing.aikars.flags=mcflags.emc.g"
EXPOSE 25565

# install the overlay fs tools
# RUN git clone https://github.com/kmxz/overlayfs-tools.git
# RUN make ./overlayfs-tools

RUN apk add curl
# install paper mc + pre patch it.
RUN curl -s -o paper.jar https://papermc.io/api/v1/paper/1.16.4/latest/download && java -jar paper.jar --help

#FROM install as run
ADD config ./

ADD https://ci.dmulloy2.net/job/ProtocolLib/lastStableBuild/artifact/target/ProtocolLib.jar ./plugins/ProtocolLib.jar
ADD https://ci.md-5.net/job/LibsDisguises/lastSuccessfulBuild/artifact/target/LibsDisguises.jar ./plugins/LibsDisguises.jar

#RUN cd plugins && xargs -a urls.txt -n 1 curl -Os && cd ..

CMD java ${JAVA_ARGS} -Dcom.mojang.eula.agree=true -jar paper.jar --nojline --nogui -W /worlds -p 25565