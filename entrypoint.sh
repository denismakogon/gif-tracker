#!/usr/bin/env bash

set -xe

java -cp $(echo /target/*.jar | tr ' ' ':') com.giphy.app.App /original.gif
