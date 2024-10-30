# <u>TELOSYS-MANUAL</u>

## Comandos iniciais:

- tt - inicializa o Telosys pelo prompt de comando;

- env - lista as configurações iniciais (padrões) do Telosys;

- h - define o diretório em que iremos alocar o código gerado. Exemplo de uso: h C:\caminho\do\gerador que enviará o código para esse diretório; ou então h . usado para indicar que o local atual já é o de local final do código;

- init - inicia o software de geração de código. Só é aceito quando o home directory está definido;

- cfg - lista a configuração atual do ambiente de geração de código;

- edb - abre o arquivo "databases.yaml" de ediçao das conexões com bancos de dados;

- ldb - imprime o arquivo "databases.yaml" no terminal;

- cdb - checa o database atual e inicia uma conexão, se existir;

- nm - cria um novo modelo no projeto. Exemplo de uso: nm myModel;

- ne - cria novas entidades em um modelo já existente. Exemplo de uso: ne myEntity; obs: não pode ser usado caso não exista algum modelo;

- ib - (install bundle): instala templates no nosso projeto. Inicialmente está conectado a base de templates do próprio telosys, localizados em um repositório GitHub, mas podemos alterar o local de origem dos templates. Exemplo de uso: ib java (instala todos os templates do repositório GitHub do telosys que contém o nome java);

- gh - nos mostra qual fonte estamos usando para buscar os templates. Se usado acompanhado de algum outro nome altera o local de busca dos templates;

- b - (bundle): usado para definir o template da geração de código, caso o template esteja instalado;

- gen - comando usado para gerar o código, última etapa do processo;

## Templates:

Para criar um template com base em uma aplicação REST, começamos estudando a documentação oficial do Telosys, disponível no site Telosys Documentation. Em seguida, realizamos nosso primeiro experimento utilizando um template já existente no repositório oficial do Telosys no GitHub, chamado <b>java-rest-springboot-jpa-basic</b>.

Com base nesse template existente, analisamos o processo de geração de código para entender seu funcionamento. Após essa etapa de aprendizado, iniciamos a criação de nosso próprio template personalizado.

### Diretório main-java:

Esse diretório contém todo o arquétipo do código a ser gerado, escrito em <b>Velocity</b>. Cada arquivo presente na subpasta representa um arquivo que pode ser gerado uma ou múltiplas vezes, para todas ou somente uma entidade. No nosso caso, mapeamos todos os arquivos da aplicação base e os reescrevemos utilizando a linguagem <b>Velocity</b>. Esses arquivos são gerados uma vez para cada entidade existente no modelo, exceto os arquivos <b>XxxValidOnCreation</b> e <b>XxxValidOnUpdate</b> que são gerados apenas uma vez independente das entidades.

### Diretório main-resources:

Este diretório contém apenas um arquivo chamado <b>application_yml</b>, escrito também na linguagem velocity. O código é um arquivo de configuração <b>YALM</b> típico de uma aplicação <b>Spring Boot</b>, que define as configurações da aplicação, do banco de dados e do sistema de logging. Entretanto, o próprio escopo do código é comentado com o intuito de indicar ao usuário para definir sua própria configuração. Esse arquivo é gerado apenas uma vez e se localiza na subpasta resources do código gerado.

### Diretório include:

Este diretório contém a configuração de variáveis que serão usadas na construção do template e pelo gerador de código. Toda variável usada em arquivos velocity no template devem estar mapeadas no arquivo <b>init_var_entity.vm</b>.

Fora desse diretório, temos um arquivo <b>pom.xml</b> que inclui as dependências usadas no projeto. Esse arquivo é gerado apenas uma vez para todo o código. Também temos o arquivo <b>templates.cfg</b>, essencial para a geração do código com o telosys pois mapeia os arquivos que serão gerados, define seu nome, local de destino e quantas vezes serão gerados. 

### Para a execução do template:

Com o template pronto, o adicionamos na pasta <b>TelosysTools/templates</b>. Após isso, usamos o comando <b>b test-java</b> (nome dado ao template) para indicar a ferramenta que esse será nosso template escolhido. Após isso, basta definir o modelo e as entidades.

Para definir o modelo é necessário que se use o apoio de um banco de dados. Dessa forma, editamos, no arquivo databases.yaml, a configuração de conexão com o Postgres (SGBD escolhido). Além disso, foi necessário adicionar o driver do Postgres na pasta TelosysTools\lib.

Após esses passos de conexão com o banco, podemos usar o comando “<b>nm nome_do_modelo id_banco</b>”. Esse comando nos retornará um modelo com entidades criadas a partir das tabelas presentes no esquema definido. 

Por fim, usamos o comando “<b>gen * * -r -y</b>”. O trecho <b>gen</b> indica ao software que queremos gerar o código; o <b>primeiro asterisco</b> indica que queremos gerar todas as entidades;o <b>segundo asterisco</b> indica que queremos gerar com todos os recursos do template; o <b>-r</b> indica que queremos copiar os recursos estáticos fornecidos pelo pacote; o <b>-y</b> usamos para forçar a resposta “sim”.  



