JXX = javac
J = java
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

clean:
	rm -f $(OUTDIR)**/*