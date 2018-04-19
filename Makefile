JXX = javac
J = java
OUTDIR = bin/
JARGS = -d $(OUTDIR)
SOURCES = src/Main.java

all: clean setup build run

setup:
	mkdir -p $(OUTDIR)

build:
	$(JXX) $(JARGS) $(SOURCES)

run:
	cd bin; $(J) Main

clean:
	rm -f $(OUTDIR)*