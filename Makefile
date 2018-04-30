JXX = javac
J = java
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

clean:
	rm -f $(OUTDIR)**/*