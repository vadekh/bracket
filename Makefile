JXX = javac
J = java
OUTDIR = bin/
JARGS = -d $(OUTDIR)
SRC = src/application/
SOURCES = $(SRC)Main.java $(SRC)TeamLoader.java $(SRC)Competitor.java $(SRC)Matchup.java $(SRC)Round.java

all: clean setup build run

setup:
	mkdir -p $(OUTDIR)

build:
	$(JXX) $(JARGS) $(SOURCES)

run:
	cd bin/; $(J) application.Main teams.txt

clean:
	rm -f $(OUTDIR)**/*