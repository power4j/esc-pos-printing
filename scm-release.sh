#!/bin/bash

./mvnw  release:clean -P 'oss-release,aliyun-repo' && \
./mvnw  release:prepare -P 'oss-release,aliyun-repo' && \
./mvnw  release:perform -P 'oss-release,aliyun-repo'