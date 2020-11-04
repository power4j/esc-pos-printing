#!/bin/bash

mvn clean deploy -Dgpg.passphrase=${GPG_PWD} -DskipTests=true -P 'oss-release,aliyun-repo'