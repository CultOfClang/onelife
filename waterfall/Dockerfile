FROM openjdk
ADD config ./
ADD https://papermc.io/api/v1/waterfall/1.16/latest/download waterfall.jar 
CMD java ${JAVA_ARGS} -jar waterfall.jar --nojline nogui