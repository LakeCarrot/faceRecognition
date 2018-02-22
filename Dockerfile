FROM ubuntu:16.04

RUN apt-get update
RUN apt-get install -y software-properties-common python-software-properties
RUN add-apt-repository ppa:cwchien/gradle
RUN apt-get update
RUN apt-get install -y gradle
RUN apt-get install -y default-jdk
RUN apt-get install -y git wget build-essential
RUN gradle -version
RUN java -version
RUN git clone https://github.com/LakeCarrot/faceRecognition.git

WORKDIR "faceRecognition"

EXPOSE 50052

RUN git pull
RUN gradle build 
RUN gradle installDist

RUN apt-get install -y libdc1394-22 libavcodec-ffmpeg-extra56
RUN apt-get install -y libjasper-dev 
RUN apt-get install -y libavformat-ffmpeg56
RUN apt-get install -y libswscale-ffmpeg3 ffmpeg

RUN wget https://github.com/git-lfs/git-lfs/releases/download/v2.3.4/git-lfs-linux-386-2.3.4.tar.gz
RUN tar -xvf git-lfs-linux-386-2.3.4.tar.gz && cd git-lfs-2.3.4/ && ./install.sh && git lfs install
RUN git checkout .

CMD git pull && gradle installDist && ./build/install/faceRecognition/bin/faceRecognition 

