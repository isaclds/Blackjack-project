@echo off
chcp 65001 > nul
title Blackjack em Java
:: Limpa a pasta bin
rmdir /s /q ..\bin
mkdir ..\bin

echo Compilando...

: Compila os arquivos .java da pasta src e salva os .class em bin
javac -encoding UTF-8 -d ..\bin ..\src\logic\*.java
if %errorlevel% neq 0 (
    echo Erro na compilaçÆo! Verifique os arquivos fonte.
    pause
    exit /b 1
)
: Executa os arquivos 
java -Dfile.encoding=UTF-8 -cp ..\bin logic.Project
if %errorlevel% neq 0 (
    echo Erro na execuçÆo do jogo!
)
pause