#############################################################################
#!
#! This is a tmake template for creating a makefile that invokes make in
#! sub directories - for Win32.
#!
#${
    StdInit();
    $m = "";
    foreach ( split(/\s+/,$project{"SUBDIRS"}) ) {
	$m = $m . "\tcd $_\n\tDOMAKE\n\t\@cd ..\n";
    }
    $project{"SUBMAKE"} = $m;
    Project('MAKEFILE') || Project('MAKEFILE = Makefile');
    Project('TMAKE') || Project('TMAKE = tmake');
#$}
#!
# Makefile for building targets in sub directories.
# Generated by tmake;
#     Project: #$ Expand("PROJECT");
#    Template: #$ Expand("TEMPLATE");
#############################################################################

MAKEFILE=	#$ Expand("MAKEFILE");
TMAKE	=	#$ Expand("TMAKE");

SUBDIRS =	#$ ExpandList("SUBDIRS");

all: $(SUBDIRS)

#${
    foreach ( split(/\s+/,$project{"SUBDIRS"}) ) {
	if ( Project("TMAKE_NOFORCE") ) {
	    $text = $text . $_ . ":\n\t" .
		"cd $_\n\t\$(MAKE\)\n\t\@cd ..\n\n";
	} else {
	    $text = $text . $_ . ": FORCE\n\t" .
		"cd $_\n\t\$(MAKE\)\n\t\@cd ..\n\n";
	}
    }
#$}
#$ TmakeSelf();

tmake_all:
#${
    foreach ( split(/\s+/,$project{"SUBDIRS"}) ) {
	$text .= "\tcd $_\n\t\$(TMAKE\) $_.pro -o \$(MAKEFILE)\n\t\@cd ..\n";
    }
#$}

clean:
#$ $text = $project{"SUBMAKE"}; $text =~ s/DOMAKE/\$(MAKE\) clean/g;
#$ Project("TMAKE_NOFORCE") && DisableOutput();
FORCE:
#$ Project("TMAKE_NOFORCE") && EnableOutput();
