#!/bin/bash
echo "Blackjack em Java"
echo "Compilando..."

# Limpa a pasta bin e cria outra pasta bin
rm -rf ../bin
mkdir -p ../bin

# Compila os arquivos .java da pasta src e salva os .class em bin
javac -encoding UTF-8 -d ../bin ../src/*.java

# Executa o programa principal a partir da pasta bin
java -cp ../bin Project

read -p "Pressione Enter para sair..."