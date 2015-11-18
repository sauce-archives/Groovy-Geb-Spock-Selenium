#!/usr/bin/env bash
while read config
do
    echo "Running config $config"
    mvn -Dgeb.saucelabs.capabilities="$config" clean test > "$config.log" 2>&1 &
done < geb.saucelabs.capabilities.json

