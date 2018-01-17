##################
TUTORIAL PORTUGUÊS
##################
 
Pré requisitos para utilização do projeto.
	1- Acesso a internet
	2- Java 1.8 instalado na máquina
		obs: É necessário que a JDK esteja setada na variável JAVA_HOME e que esteja sendo setada primeiro que o caminho "C:\ProgramData\Oracle\Java\javapath"
		no PATH do sistema, pois esse caminho aponta para uma JRE, causando problema na utilização do serviço.

Para iniciar, siga os passos abaixo:

1° - Acesse o caminho do projeto tweet-finder/build/output
2° - Se você estiver utilizando windows, execute o arquivo start.bat, caso esteja utilizando linux, execute o arquivo start.sh
3° - Após o start acesse o serviço através da url: http://localhost:8080/tweet-finder
4° - Insira a palavra a ser procurada na base do twitter


O projeto possui um arquivo de configuração com relação as chaves de acesso para o uso da api do twitter tanto quanto
as configurações de latitude, longitude e radius da área aonde serão procurados os tweets da palavra específica.
A configuração está setada por padrão para a área do Brasil, para pesquisar por outra área, basta inserir a latitude, longitude e radius 
no arquivo tweet-finder\src\main\resources\application.properties.


################
ENGLISH TUTORIAL
################


Prerequisites for using the project.
	1- Internet access
	2- Java 1.8 installed on the machine
       Note: It is necessary that the JDK is set to the variable JAVA_HOME and that it is being set first to the path "C:\ProgramData\Oracle\Java\javapath"
       in the system PATH because this path points to a JRE, causing a problem in using the service.

To start, follow the steps below:

1° - Access the root of the tweet-finder/build/output
2° - If you are using windows, run the file start.bat, if you are using linux, run the file start.sh
3° - After the start access the service through the url: http://localhost:8080/tweet-finder
4° - Enter the word to be searched on the twitter base


The project has a configuration file regarding the access keys for the use of twitter api as much as
the latitude, longitude and radius settings of the area where specific word tweets will be searched.
The configuration is set by default for the area of ​​Brazil, to search for another area, simply enter the latitude, longitude and radius
in the tweet-finder file \src\main\resources\application.properties.