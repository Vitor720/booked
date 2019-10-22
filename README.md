# booked

Desafio proposto pela empresa Lemobs, aceito e concludio.

Aplicativo utiliza arquitetura MVVM, injeção de dependencias com Kotlin Koin e consumo de API com a nova abordagem do Retrofit 2.6

Abaixo especificações do desafio




Biblioteca Virtual

O desafio consiste em criar um aplicativo android de uma blibloteca de livros virtuais.

O candidato deve enviar o código do desafio por email para: rh.lemobs@gmail.com

O código deve ser feito na ferramenta Android Studio. Pode ser em Java ou em Kotlin.
Introdução

O aplicativo têm duas telas. A lista de livros do usuário e a lista de livros à venda.

O aplicativo começa na lista de livros do usuário. Ao iniciar o aplicativo pela primeira vez essa lista está vazia.

O usuário começa com R$ 100,00. A informação de dinheiro do usuário deve ser exibida em todas as telas.

O usuário pode ir para a lista de livros à venda ao apertar o botão "Comprar Livros".
Tela "Meus Livros"

O aplicativo deve mostrar a lista de livros do usuário.

Um livro tem as seguintes informações:

    Nome [title]
    Escritor [writer]
    Foto do item [thumbnailHd]

Tela "Livros à Venda"

O aplicativo deve mostrar a lista de livros à venda. Para obter os livros da loja, sua aplicação deverá realizar uma chamada GET na URL https://raw.githubusercontent.com/Felcks/desafio-mobile-lemobs/master/products.json

Um livro à venda tem as seguintes informações:

    Nome [title]
    Preço [price]
    Escritor [writer]
    Foto do item [thumbnailHd]

Cada item possui um botão de comprar. O aplicativo só aprova a compra se o usuário tiver dinheiro suficiente. Se confirmada a compra, o valor deve ser descontado do dinheiro do usuário. Livros já comprados não aparecem na lista à venda ou aparecem como bloqueados.
Resumo do Aplicativo

    Meus livros
    Livros à venda

Bônus

    Conseguir dar Zoom nos livros.
    Criatividade na criação das telas do aplicativo.
    Uso de padrões arquiteturais.
    Ter uma opção de favoritar um livro na lista de livros à venda. Livros favoritados aparecem no topo da lista.
    Ter uma opção de atualizar a lista de livros à venda como na toolbar do aplicativo.
    Ter a opção de busca pelo nome na lista de livros à venda.
    Usar Kotlin.
    Uso de Mvp ou MVVM.
    Uso de SharedPreferences para salvar o dinheiro.
