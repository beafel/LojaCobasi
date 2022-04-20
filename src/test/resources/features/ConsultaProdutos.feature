#language: pt
#enconding: utf8

  Funcionalidade: Consultar Produto
    Como um cliente eventual, gostaria de consultar a disponibilidade
    e preço de alguns produtos que tenho interesse em adquirir

    Esquema do Cenario: Consulta Produtos no Site da Cobasi
      Dado que acesso o site da Cobasi
      Quando procuro por <produto> e clico na Lupa
      Entao exibe as opcoes relacionadas ao <produto>
      Quando seleciono a <descricaoProduto> da lista
      Entao verifico a <marca> a <opcao> com o <preco>

      Exemplos:
        |      produto     |                 descricaoProduto                |  marca   |  opcao  |   preco    |
        |  "Ração Cibau"   |    "Ração Cibau Cães Adulto Raças Pequenas"     | "Cibau"  |  "1KG"  | "R$ 52,90" |
        | "Petisco Keldog" | "Bifinho Keldog Carne e Cereais Raças Pequenas" | "Keldog" |  "55 G" | "R$ 4,10"  |