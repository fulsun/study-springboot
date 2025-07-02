#!/bin/bash

# 构建镜像
docker build -t pdfviews-lazy:latest .

# 运行容器
docker run -p 8080:8080 -v /home/sun/app:/data pdfviews-lazy