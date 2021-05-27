#!/bin/bash
cd /home/zy/redis-config/redis-sentinel
redis-server ./redis-6379.conf &
redis-server ./redis-6380.conf &
redis-server ./redis-6381.conf &
redis-sentinel ./sentinel-26379.conf &
redis-sentinel ./sentinel-26380.conf &
