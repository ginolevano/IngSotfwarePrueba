#!/bin/bash
cd /Users/ginolevano/Documents/GitHub/IngSotfwarePrueba
java -jar target/demo-1.0-SNAPSHOT-jar-with-dependencies.jar &
sleep 2
kill %1 2>/dev/null || true
