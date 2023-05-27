#!/bin/bash

# Agrega todos los archivos a git
git add .

# Commit con el argumento de linea de comando
git commit -m "$1"

# Ejecutar el comando "git push" con el usuario y la contraseña
git push -u origin master
