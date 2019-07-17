#!/usr/bin/env bash

set -ex

OPENCV_VERSION="4.1.0"

mkdir /tmp/opencv && \
    cd /tmp/opencv && \
    wget -O opencv.zip https://github.com/opencv/opencv/archive/${OPENCV_VERSION}.zip && \
    unzip opencv.zip && \
    wget -O opencv_contrib.zip https://github.com/opencv/opencv_contrib/archive/${OPENCV_VERSION}.zip && \
    unzip opencv_contrib.zip && \
    mkdir /tmp/opencv/opencv-${OPENCV_VERSION}/build && cd /tmp/opencv/opencv-${OPENCV_VERSION}/build && \
    cmake \
      -D OPENCV_EXTRA_MODULES_PATH=/tmp/opencv/opencv_contrib-${OPENCV_VERSION}/modules \
      -D CMAKE_INSTALL_PREFIX=/usr/local \
      -D BUILD_TIFF=ON \
      -D BUILD_opencv_java=ON \
      -D WITH_CUDA=OFF \
      -D ENABLE_AVX=ON \
      -D WITH_OPENGL=ON \
      -D WITH_OPENCL=ON \
      -D WITH_IPP=ON \
      -D WITH_TBB=ON \
      -D WITH_EIGEN=ON \
      -D WITH_V4L=ON \
      -D BUILD_TESTS=OFF \
      -D BUILD_PERF_TESTS=OFF \
      -D CMAKE_BUILD_TYPE=RELEASE \
      -D BUILD_opencv_python=NO \
      -D BUILD_opencv_python2=NO \
      -D BUILD_opencv_python3=NO \
      -D INSTALL_C_EXAMPLES=NO \
      -D INSTALL_PYTHON_EXAMPLES=NO \
      -D BUILD_ANDROID_EXAMPLES=NO \
      -D BUILD_DOCS=NO \
      -D BUILD_TESTS=NO \
      -D BUILD_PERF_TESTS=NO \
      -D BUILD_EXAMPLES=NO \
      -D WITH_WIN32UI=OFF \
      -D WITH_QT=OFF \
      -D OPENCV_GENERATE_PKGCONFIG=YES \
      -D WITH_GTK=OFF .. && \
    make -j4 && \
    make install
