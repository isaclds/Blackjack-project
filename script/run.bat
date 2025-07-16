@echo off
chcp 65001 > nul
title Blackjack em Java
:: Limpa a pasta bin
rmdir /s /q ..\bin
mkdir ..\bin

echo Compilando...

: Compila os arquivos .java da pasta src e salva os .class em bin
javac -encoding UTF-8 -d ..\bin ..\src\*.java

: Executa os arquivos 
java -Dfile.encoding=UTF-8 -cp ..\bin Project

pause