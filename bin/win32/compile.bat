@echo off

cd %~dp0/../../src/cmdver/singleplayer
javac -d ../../../out/production/SeaBattle SeaBattle.java

echo The SeaBattle game was compiled successfully! You may find it in the out/production/SeaBattle folder. 