# ---------------------------------------------------------------------------
!if !$d(BCB)
BCB = $(MAKEDIR)\..
!endif

# ---------------------------------------------------------------------------
# IDE SECTION
# ---------------------------------------------------------------------------
# The following section of the project makefile is managed by the BCB IDE.
# It is recommended to use the IDE to change any of the values in this
# section.
# ---------------------------------------------------------------------------

VERSION = BCB.04.04
# ---------------------------------------------------------------------------
PROJECT = matriz1_usa_vetor.exe
OBJFILES = matriz1_usa_vetor.obj
RESFILES =
RESDEPEN = $(RESFILES)
LIBFILES =
IDLFILES =
IDLGENFILES =
LIBRARIES = vcl40.lib
SPARELIBS = vcl40.lib
PACKAGES =
DEFFILE =
# ---------------------------------------------------------------------------
PATHCPP = .;
PATHASM = .;
PATHPAS = .;
PATHRC = .;
DEBUGLIBPATH = $(BCB)\lib\debug
RELEASELIBPATH = $(BCB)\lib\release
SYSDEFINES = 
USERDEFINES =
# ---------------------------------------------------------------------------
CFLAG1 = -I$(BCB)\include;$(BCB)\include\vcl -Od -Hc -H=$(BCB)\lib\vcl40.csm -w -Ve \
  -Tkh30000 -r- -a4 -k -y -v -vi- -c -tWM -b- -w-par -w-inl -Vx -tWC \
  -D$(SYSDEFINES);$(USERDEFINES)
IDLCFLAGS = -I$(BCB)\include -I$(BCB)\include\vcl -src_suffixcpp
PFLAGS = -U$(BCB)\lib\obj;$(BCB)\lib;$(DEBUGLIBPATH) -I$(BCB)\include;$(BCB)\include\vcl \
  -$YD -$W -$O- -v -JPHN -M
RFLAGS = -i$(BCB)\include;$(BCB)\include\vcl
AFLAGS = /i$(BCB)\include /i$(BCB)\include\vcl /mx /w2 /zd
LFLAGS = -L$(BCB)\lib\obj;$(BCB)\lib;$(DEBUGLIBPATH) -ap -Tpe -x -Gn -v
# ---------------------------------------------------------------------------
ALLOBJ = c0x32.obj $(OBJFILES)
ALLRES = $(RESFILES)
ALLLIB = $(LIBFILES) $(LIBRARIES) import32.lib cp32mt.lib
# ---------------------------------------------------------------------------
!ifdef IDEOPTIONS

[Version Info]
IncludeVerInfo=0
AutoIncBuild=0
MajorVer=1
MinorVer=0
Release=0
Build=0
Debug=0
PreRelease=0
Special=0
Private=0
DLL=0
Locale=1046
CodePage=1252

[Version Info Keys]
CompanyName=
FileDescription=Executable (Console)
FileVersion=1.0.0.0
InternalName=
LegalCopyright=
LegalTrademarks=
OriginalFilename=
ProductName=
ProductVersion=1.0.0.0
Comments=

[Debugging]
DebugSourceDirs=$(BCB)\source\vcl

[Parameters]
RunParams=
HostApplication=
RemoteHost=
RemotePath=
RemoteDebug=0

[Compiler]
InMemoryExe=0
ShowInfoMsgs=0

[CORBA]
AddServerUnit=1
AddClientUnit=1
PrecompiledHeaders=1

!endif

# ---------------------------------------------------------------------------
# MAKE SECTION
# ---------------------------------------------------------------------------
# This section of the project file is not used by the BCB IDE.  It is for
# the benefit of building from the command-line using the MAKE utility.
# ---------------------------------------------------------------------------

.autodepend
# ---------------------------------------------------------------------------
!if !$d(BCC32)
BCC32 = bcc32
!endif

!if !$d(DCC32)
DCC32 = dcc32
!endif

!if !$d(TASM32)
TASM32 = tasm32
!endif

!if !$d(LINKER)
LINKER = ilink32
!endif

!if !$d(BRCC32)
BRCC32 = brcc32
!endif

!if !$d(IDL2CPP)
IDL2CPP = idl2cpp
!endif

# ---------------------------------------------------------------------------
!if $d(PATHCPP)
.PATH.CPP = $(PATHCPP)
.PATH.C   = $(PATHCPP)
!endif

!if $d(PATHPAS)
.PATH.PAS = $(PATHPAS)
!endif

!if $d(PATHASM)
.PATH.ASM = $(PATHASM)
!endif

!if $d(PATHRC)
.PATH.RC  = $(PATHRC)
!endif
# ---------------------------------------------------------------------------
$(PROJECT): $(IDLGENFILES) $(OBJFILES) $(RESDEPEN) $(DEFFILE)
    $(BCB)\BIN\$(LINKER) @&&!
    $(LFLAGS) +
    $(ALLOBJ), +
    $(PROJECT),, +
    $(ALLLIB), +
    $(DEFFILE), +
    $(ALLRES)
!
# ---------------------------------------------------------------------------
.pas.hpp:
    $(BCB)\BIN\$(DCC32) $(PFLAGS) {$< }

.pas.obj:
    $(BCB)\BIN\$(DCC32) $(PFLAGS) {$< }

.cpp.obj:
    $(BCB)\BIN\$(BCC32) $(CFLAG1) -n$(@D) {$< }

.c.obj:
    $(BCB)\BIN\$(BCC32) $(CFLAG1) -n$(@D) {$< }

.asm.obj:
    $(BCB)\BIN\$(TASM32) $(AFLAGS) $<, $@

.rc.res:
    $(BCB)\BIN\$(BRCC32) $(RFLAGS) -fo$@ $<
# ---------------------------------------------------------------------------
