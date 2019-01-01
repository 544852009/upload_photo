#!/usr/bin/env bash
echo "start sh1--------->"
docker build -t upload /root/.jenkins/workspace/upload_photo/src/main/docker
echo "end sh1--------->"