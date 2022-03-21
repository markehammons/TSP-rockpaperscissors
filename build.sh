#!/usr/bin/env bash

if hash scala-cli 2>/dev/null; then
   scala-cli package rps.scala --js --js-emit-source-maps --dependency org.scala-js::scalajs-dom::2.1.0 -f
else
   echo "Scala-CLI is not installed. Please go to https://scala-cli.virtuslab.org/ and install it"
fi