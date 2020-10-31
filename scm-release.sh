#!/bin/bash

mvn release:clean -P 'oss-release,aliyun-repo' && \
mvn release:prepare -P 'oss-release,aliyun-repo' && \
mvn release:perform -P 'oss-release,aliyun-repo'