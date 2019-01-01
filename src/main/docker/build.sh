#!/usr/bin/env bash
echo "开始构建--------->"
docker build --tag latest -t upload .
echo "构建完成--------->"
