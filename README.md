# Projet_6_Site_Communautaire

Commencez par clonez le code de l'application en tapant en ligne de commande :

	git clone https://github.com/RomainDemellier/Projet_6_Site_Communautaire.git

Vous aurez besoin de Maven pour faire fonctionner cette application.
Vous pouvez télécharger Maven à cette adresse : https://maven.apache.org/download.cgi.

Après avoir téléchargé Maven tapez mvn --version en ligne de commande pour s'assurer que Maven a bien été téléchargé.
Si vous voyez quelquechose comme suit c'est bon : 

	Apache Maven 3.6.3 (cecedd343002696d0abb50b32b541b8a6ba2883f)
	Maven home: D:\apache-maven-3.6.3\apache-maven\bin\..
	Java version: 1.8.0_232, vendor: AdoptOpenJDK, runtime: C:\Program Files\AdoptOpenJDK\jdk-8.0.232.09-hotspot\jre
	Default locale: en_US, platform encoding: Cp1250
	OS name: "windows 10", version: "10.0", arch: "amd64", family: "windows" 

Pour builder l'application mettez vous sous la racine de l'application (cd Projet_6_Site_Communautaire/).
Ensuite tapez la commande suivante dans votre terminal :

	mvn package

Il vous faut maintenant récupérer le fichier projet6-1.0.war. Sous la racine de l'application il y a un dossier nommé projet6. Dans ce dossier se trouve un dossier nommé target dans lequel se trouve le fichier projet6-1.0.war. Il vous faut copier ce fichier et le collez dans un dossier nommé webapps. Ce dossier se trouve dans le dossier nommé apache-tomcat. Le nom de ce dossier peut varier selon les versions.
Il ne reste plus qu'à lancer tomcat. Pour ce faire dans votre terminal placez vous sous le dossier apache-tomcat (cd apache-tomcat/) puis sous le dossier bin (cd bin/). Il y a dans ce dossier un fichier nommé startup.bat et un autre nommé startup.sh. Pour lancez tomcat il vous suffit maintenant de tapez dans votre terminal la commande suivante :

	Sous Windows :
	startup.bat

	Sous Linux :
	startup.sh 

Une fois lancé ouvrez votre navigateur et tapez dans la zone url :

	http://localhost:8080/projet6-1.0/

Toutefois pour que l'application fonctionne il vous faudra créer une base de données.
Pour importer la base de données que  j'ai créée, commencez par créer une base de données et appelez la : projet_6_site_com_esc.
Ensuite cliquez sur import et sélectionnez le fichier nommé script-structure-et-donnees.sql qui se trouve dans le dossier script-bdd à la racine du projet.
