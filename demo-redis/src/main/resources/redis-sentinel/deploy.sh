#!/bin/bash
exec redis-server ./redis-master-6379.conf
exec redis-server ./redis-slave-6380.conf
exec redis-server ./redis-slave-6381.conf
exec redis-server ./sentinel-26379.conf
exec redis-server ./sentinel-26380.conf
