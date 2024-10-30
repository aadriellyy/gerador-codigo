# Contatos

## Previsão de Tempo

Para ter uma previsão de tempo, é preciso criar um  _token_  de acesso na  _url https://advisor.climatempo.com.br/_ . Apenas faça login usando sua conta do Google ou uma das alternativas oferecidas,
crie o token com o plano gratuito e, por fim, coloque o valor na variável  **TOKEN_CLIMA_TEMPO** na classe OlaRestControlle.java.

## Iniciando a aplicação

Execute a classe ContatosApplication como uma *aplicação Java*. Essa operação vai subir um banco em memória, criar as estruturas descritas no arquivo **resources/schema-h2.sql** e executar as 
operações que estão arquivo **AppMain.java**; com isso vc deverá ter uma saída similar ao que stá abaixo:

```
1
(540) 618-9264
```

## URL's de teste
 - http://localhost:8581/ativo

## URL's de busca de previsão do tempo

Caso queiram algo um pouco mais interativo, criem um token de acesso no climatempo

 - http://localhost:8581/codigos
 - http://localhost:8581/previsao?code=8183
