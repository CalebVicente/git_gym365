# git_gym365
lo primero que hay que hacer es situarse en el directorio donde queremos tener la carpeta git y hacer un git clone "la url que aparece en github"

para situarnos en la rama que queremos git checkout nombre_rama

para añadir los cambio realizados a nuestra rama git add *, git commit -m "mensaje que se desee añadir", git push

(ESTO HACERLO SIEMPRE AVISANDO POR EL GRUPO DE WHASTAPP)para añadir los cambios de una rama concreta a la master, situado en una rama concreta: 
	- git merge -s ours master
	- git add, git commit
	- git checkout master
	- git merge nombre_rama
