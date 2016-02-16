#!/usr/bin/env bash
ps aux | grep java | grep -v grep  
ps aux | grep java | grep -v grep | awk '{print $2}' | xargs -t -I '{}' kill '{}'; echo done
