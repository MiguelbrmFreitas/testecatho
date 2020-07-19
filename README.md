# Teste da Catho
Implementação em Java do desafio técnico do processo seletivo da Catho.  
Demonstração em vídeo: https://bit.ly/3eH5Voa

## Descrição
Tela de home de um usuário com foto de perfil, slider com sugestões de vagas (com timer de mudança de página incluso) e card com dicas dos recrutadores.  
  
É possível clicar em enviar currículo para cada sugestão (mudando o texto do botão) e clicar no ícone de visualizar para abrir outra tela para ver mais detalhes sobre uma vaga.  
Existe uma funcionalidade para avaliar dicas como úteis ou não. Quando uma dica é avaliada, a próxima é mostrada.  

Foram feitas requisições GET e POST para a API definida aqui: https://github.com/catho/teste-apps.  
  
Algumas das funcionalidades apresentadas são extras em relação ao que foi pedido.  

## Features implementadas
- [X] Visualizar a foto cadastrada. 
- [X] Animação de loading (extra). 
- [X] Timer para o slider mudar automaticamente em um intervalo de tempo (extra).
- [X] Arrastar para os lados entre as sugestões de vagas.
- [X] Aplicar-se a uma vaga.
- [X] Visualizar informações detalhadas de uma vaga (extra).
- [X] Visualizar dica de recrutadores.
- [X] Votar se uma dica é útil ou não.
- [X] Ir para a próxima dica (extra).

## Outras features que poderiam ter sido interessantes de implementar
- Efeito de clique nos botões;
- Transições entre mudanças de dicas;

## Diagrama de Casos de Uso
![Diagrama de Casos de Uso](/img/DiagramaTesteCatho.png)

## Observação
Para facilitar os testes dispensando configurações adicionais para rodar o celular e a API local na mesma rede, foi feito o deploy da API para o Heroku na seguinte URL: https://teste-catho-api-v2.herokuapp.com

## Como rodar
Abrir a pasta **testecathoapp** da raiz no Android Studio como um projeto. Uma vez carregado, ir em **Build -> Make Project** e depois no ícone de **Run**, escolhendo o dispositivo virtual ou físico em que o app será executado.  
Como foi dito na observação, não é necessário rodar um servidor local para a API.
