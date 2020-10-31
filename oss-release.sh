#!/bin/bash

mvn clean deploy -DskipTests=true -P 'oss-release,aliyun-repo'