# SeaBattle

This is a simple Java cmd game written by me. I had plans to make it something a bit more serious (like a GUI game, for example), but hasn't done anything alike yet. 
So for now, it's just a very simple 300+ lines-of-code game. 
All of it's rules are being told at the beginning, but if you do not understand something - welcome into the source code. 

## How to play

In order to play it, you need to run the out/production/SeaBattle/cmdver/singleplayer/SeaBattle.class file via the JVM.
It is done like so: 

    $ java -classpath out/production/SeaBattle  cmdver.singleplayer.SeaBattle

After it, the game starts. 

## Compile from source

It is not very difficult, considering the whole game consists of just one file. 
It is done like so: 

    $ cd src/cmdver/singleplayer/ && javac -d ../../../out/production/SeaBattle/SeaBattle.java

## The 'bin' directory. 

If you get tired of typing the same thing while running or compiling the game, then afraid be not for recently I've written small scripts for automazing the processes of running and compiling it. 

You may find them in the bin folder. 
For now only Windows (Batchfiles) is supported, but soon Linux is to arrive as well. 