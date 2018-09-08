#!/bin/bash
#rmiregistry &
/home/jefferson/java/jdk1.6.0/bin/java -classpath '/home/jefferson/projetos/Forro/jar/rmi.jar:.' -Djava.rmi.server.codebase=file:/home/jefferson/projetos/Forro/class/ -Djava.security.policy=file:/home/jefferson/projetos/Forro/permission.policy forrocore.ForroDriver
#java -classpath '/shared/home/heron/projetos/Forro/jar/rmi.jar:.' -Djava.rmi.server.codebase=file:/shared/home/heron/projetos/Forro/class/ -Djava.security.policy=file:/shared/home/heron/projetos/Forro/permission.policy forrocore.ForroDriver