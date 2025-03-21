# 🚆 Simulador de Trens em Java

## 📌 Sobre o Projeto
O **Simulador de Trens** é um projeto desenvolvido em Java que tem como objetivo simular o funcionamento de um sistema ferroviário, incluindo trilhos, estações, composições ferroviárias e o gerenciamento do fluxo de trens. Este projeto pode ser utilizado para estudos de logística, simulações acadêmicas ou simplesmente para entretenimento.

## 🎯 Funcionalidades
- 🔄 **Simulação de Trens**: Movimentação dos trens em diferentes trilhos.
- 🛤️ **Gestão de Trilhos**: Definição de rotas, cruzamentos e bifurcações.
- 🚉 **Estações**: Paradas programadas e embarque/desembarque de passageiros.
- ⏱️ **Controle de Tempo**: Simulação de intervalos entre viagens.
- 🔄 **Gerenciamento Dinâmico**: Controle em tempo real dos trens e suas trajetórias.
- 🖥️ **Interface Gráfica (Opcional)**: Representação visual do sistema ferroviário.

## 🛠️ Tecnologias Utilizadas
- **Java 11+**
- **Java Swing/JavaFX** (para interface gráfica, opcional)
- **Threads e Concurrency** (para simulação realista de múltiplos trens)
- **Arquivos JSON/XML** (para configuração do ambiente)

## 🚀 Como Executar
1. **Clone o repositório:**
   ```sh
   git clone https://github.com/seu-usuario/simulador-trens.git
   ```
2. **Acesse o diretório do projeto:**
   ```sh
   cd simulador-trens
   ```
3. **Compile o projeto:**
   ```sh
   javac -d bin src/*.java
   ```
4. **Execute a simulação:**
   ```sh
   java -cp bin Main
   ```

## 🖥️ Interface Gráfica (Opcional)
Se desejar utilizar a interface gráfica, certifique-se de instalar o JavaFX e execute:
```sh
java --module-path "path/to/javafx/lib" --add-modules javafx.controls,javafx.fxml -cp bin Main
```

## 📂 Estrutura do Projeto
```
📂 simulador-trens
 ├── 📂 src
 │   ├── Main.java
 │   ├── Trem.java
 │   ├── Trilho.java
 │   ├── Estacao.java
 │   ├── Simulador.java
 │   └── ...
 ├── 📂 bin
 ├── 📂 assets
 │   ├── trilhos.json
 │   ├── estacoes.json
 │   └── trens.json
 ├── README.md
 └── .gitignore
```

## 🤝 Contribuição
Contribuições são bem-vindas! Siga estes passos:
1. **Fork** o repositório
2. Crie uma **branch** com sua feature (`git checkout -b minha-feature`)
3. Commit suas alterações (`git commit -m 'Adicionando nova feature'`)
4. Faça o push para a branch (`git push origin minha-feature`)
5. Abra um **Pull Request**

## 📄 Licença
Este projeto está sob a **MIT License**. Sinta-se livre para utilizá-lo e modificá-lo.

---
Criado com ❤️ por [Seu Nome](https://github.com/seu-usuario)

