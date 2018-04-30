JXX = javac
J = java
<<<<<<< HEAD
OUTDIR = FinalProject/bin/
JARGS = -d $(OUTDIR)
SOURCES = FinalProject/src/application/Main.java

all: clean setup build run

setup:
	mkdir -p $(OUTDIR)

build:
	$(JXX) $(JARGS) $(SOURCES)

run:
	cd FinalProject/bin; $(J) application.Main
=======
OUTDIR = bin/
JARGS = -d $(OUTDIR)
SOURCES = src/application/Main.java src/application/TeamLoader.java

all: clean setup build run

setup:
	mkdir -p $(OUTDIR)

build:
	$(JXX) $(JARGS) $(SOURCES)

run:
	cd bin/; $(J) application.Main teams.txt
>>>>>>> branch 'master' of https://github.com/aechan/bracket.git

clean:
	rm -f $(OUTDIR)**/*