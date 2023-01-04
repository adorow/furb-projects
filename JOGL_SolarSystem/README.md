JOGL - Solar system
===================

A project made with some colleagues for a Computer Graphics class ind mid 2010 at the university ([FURB](https://www.furb.br/)) - The last e-mail I found regarding this is from the 22nd of June 2010. Not all code could be recovered, so I had to recreate a few things based on the existing code (like recreating a `Planets` enum).

I'm not sure exactly how we were running or packaging this back in the day, but we're probably running it mostly straight out of Eclipse.
So I setup some build system to ease maintaining and running this. 
I also changed the original `src/{packages}` folder structure to `src/main/java/{packages}` to aid the build system.


## Running

### Windows

DLLs are included, so hopefully that should work (haven't tested it yet).

### Linux

Haven't tried.

### MacOS

Libraries are missing, so currently doesn't work.
