FROM gradle:6.7.1-jdk11 as build
ADD ./mod/ ./
RUN gradle jar

FROM openjdk
VOLUME "/worlds"


ENV JAVA_ARGS="-XX:+UseG1GC -XX:+UnlockExperimentalVMOptions -XX:MaxGCPauseMillis=100 -XX:+DisableExplicitGC -XX:TargetSurvivorRatio=90 -XX:G1NewSizePercent=50 -XX:G1MaxNewSizePercent=80 -XX:G1MixedGCLiveThresholdPercent=35 -XX:+AlwaysPreTouch -XX:+ParallelRefProcEnabled -Dusing.aikars.flags=mcflags.emc.g"
EXPOSE 25565

# install the overlay fs tools
# RUN git clone https://github.com/kmxz/overlayfs-tools.git
# RUN make ./overlayfs-tools

# install paper mc + pre patch it.
ADD https://papermc.io/api/v1/paper/1.16.4/latest/download ./paper.jar
RUN java -jar paper.jar --help

ADD https://ci.dmulloy2.net/job/ProtocolLib/lastStableBuild/artifact/target/ProtocolLib.jar ./plugins/ProtocolLib.jar
ADD https://ci.md-5.net/job/LibsDisguises/lastSuccessfulBuild/artifact/target/LibsDisguises.jar ./plugins/LibsDisguises.jar

COPY --from=build /home/gradle/build/libs/*.jar /plugins/

#ADD ./mod/build/libs/*.jar /plugins/

ADD config ./

CMD java ${JAVA_ARGS} -Dcom.mojang.eula.agree=true -jar paper.jar --nojline --nogui -W /worlds -p 25565
