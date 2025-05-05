/* ***************************************************************
* Autor............: Fernando Nardes Ferreira Neto
* Matricula........: 202410403
* Inicio...........: 14/03/2025
* Ultima alteracao.: 05/04/2025
* Nome.............: TutorialController.java
* Funcao...........: Controlador da tela de tutorial, realizando o posicionamento inicial dos villagers, indicando o funcionamento de botoes, etc;
*************************************************************** */
package controller;

// Importando as bibliotecas necessarias
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class TutorialController extends BaseController implements Initializable{
  protected static int posicaoInicialDireita = 1; // Setando a posicao incial da direita em baixo 
  protected static int posicaoInicialEsquerda = 3; // Setando a posicao incial da esquerda em baixo 
  protected static int sistemaColisao = 1; // Setando o sistema de colisao inicial com colisao

  Image botaoVerde = new Image(getClass().getResourceAsStream("/assets/menuButtonGreen.png")); // Pega a imagem do botao verde
  Image botaoCinza = new Image(getClass().getResourceAsStream("/assets/menuButton.png")); // Pega a imagem do botao cinza

  @FXML
  private ImageView botaoProsseguir, botaoVoltar, botaoComColisao, botaoVariavelDeTravamento, botaoEstritaAlternancia, botaoPeterson; // Instanciando os botoes

  @FXML
  private ImageView setaDireitaCima, setaDireitaBaixo, setaEsquerdaCima, setaEsquerdaBaixo; // Instanciando imagens das setas dos botoes

  @FXML
  private ImageView imagemVillagerDireita, imagemVillagerEsquerda; // Instanciando imagens dos villagers na simulacao

  /* ***************************************************************
  * Metodo: botaoDireita
  * Funcao: Move o Villager da direita para a posicao superior ou inferior
  *         e faz uma animacao nas setas do botao
  * Parametros: event -> Acao do botao
  * Retorno: void
  *************************************************************** */
  @FXML
  private void botaoDireita(ActionEvent event){
    if(posicaoInicialDireita == 1){
      imagemVillagerDireita.setLayoutY(10); // Define a coordenada Y
      imagemVillagerDireita.setLayoutX(508); // Define a coordenada X
      imagemVillagerDireita.setRotate(180); // Define a rotacao do villager
      posicaoInicialDireita = 2; // Altera a posicao incial 
      setaDireitaBaixo.setOpacity(1); // Define a opacidade das setas
      setaDireitaCima.setOpacity(0.2); 
    } else{
      imagemVillagerDireita.setLayoutY(635); 
      imagemVillagerDireita.setLayoutX(508); 
      imagemVillagerDireita.setRotate(0); 
      posicaoInicialDireita = 1;  
      setaDireitaBaixo.setOpacity(0.2); 
      setaDireitaCima.setOpacity(1);
    }// Fim das verificacoes
  }// Fim do metodo botaoDireita

  /* ***************************************************************
  * Metodo: botaoEsquerda
  * Funcao: Move o Villager da esquerda para a posicao superior ou inferior
  *         e faz uma animacao nas setas do botao
  * Parametros: event -> Acao do botao
  * Retorno:void
  *************************************************************** */
  @FXML
  private void botaoEsquerda(ActionEvent event){
    if(posicaoInicialEsquerda == 3){
      imagemVillagerEsquerda.setLayoutY(10); // Define a coordenada Y
      imagemVillagerEsquerda.setLayoutX(460); // Define a coordenada X
      imagemVillagerEsquerda.setRotate(180); // Define a rotacao do villager
      posicaoInicialEsquerda = 4; // Altera a posicao incial
      setaEsquerdaBaixo.setOpacity(1); // Define a opacidade das setas
      setaEsquerdaCima.setOpacity(0.2); 
    } else {
      imagemVillagerEsquerda.setLayoutY(635); 
      imagemVillagerEsquerda.setLayoutX(460); 
      imagemVillagerEsquerda.setRotate(0); 
      posicaoInicialEsquerda = 3; 
      setaEsquerdaBaixo.setOpacity(0.2);
      setaEsquerdaCima.setOpacity(1);
    }// Fim do if
  }// Fim do metodo botaoEsquerda

  /* ***************************************************************
  * Metodo: selecionaSistemaColisao
  * Funcao: Verifica o sistema de colisao selecionado pelo usuario e
            colore o botao selecionado em um tom de verde
  * Parametros: event -> Acao do mouse
  * Retorno:void
  *************************************************************** */
  @FXML
  public void selecionaSistemaColisao(MouseEvent event){
    ImageView clicado = (ImageView) event.getSource();
    String idClicado = clicado.getId();
    
    if(idClicado.equals("botaoComColisao")){
      botaoComColisao.setImage(botaoVerde);
      botaoVariavelDeTravamento.setImage(botaoCinza);
      botaoEstritaAlternancia.setImage(botaoCinza);
      botaoPeterson.setImage(botaoCinza);
      sistemaColisao = 0;
    } else if(idClicado.equals("botaoVariavelDeTravamento")){
      botaoComColisao.setImage(botaoCinza);
      botaoVariavelDeTravamento.setImage(botaoVerde);
      botaoEstritaAlternancia.setImage(botaoCinza);
      botaoPeterson.setImage(botaoCinza);
      sistemaColisao = 1;
    } else if(idClicado.equals("botaoEstritaAlternancia")){
      botaoComColisao.setImage(botaoCinza);
      botaoVariavelDeTravamento.setImage(botaoCinza);
      botaoEstritaAlternancia.setImage(botaoVerde);
      botaoPeterson.setImage(botaoCinza);
      sistemaColisao = 2;
    } else {
      botaoComColisao.setImage(botaoCinza);
      botaoVariavelDeTravamento.setImage(botaoCinza);
      botaoEstritaAlternancia.setImage(botaoCinza);
      botaoPeterson.setImage(botaoVerde);
      sistemaColisao = 3;
    }
  }

  /* ***************************************************************
  * Metodo: verificaSistemaSelecionado
  * Funcao: Verifica o sistema de colisao atual selecinado e o colore
  *         de acordo
  * Parametros: nenhum
  * Retorno:void
  *************************************************************** */
  public void verificaSistemaSelecionado(){
    if(TutorialController.sistemaColisao == 0){
      botaoComColisao.setImage(botaoVerde);
      botaoVariavelDeTravamento.setImage(botaoCinza);
      botaoEstritaAlternancia.setImage(botaoCinza);
      botaoPeterson.setImage(botaoCinza);
      
    } else if(TutorialController.sistemaColisao == 1){
      botaoComColisao.setImage(botaoCinza);
      botaoVariavelDeTravamento.setImage(botaoVerde);
      botaoEstritaAlternancia.setImage(botaoCinza);
      botaoPeterson.setImage(botaoCinza);
    } else if(TutorialController.sistemaColisao == 2){
      botaoComColisao.setImage(botaoCinza);
      botaoVariavelDeTravamento.setImage(botaoCinza);
      botaoEstritaAlternancia.setImage(botaoVerde);
      botaoPeterson.setImage(botaoCinza);
    } else {
      botaoComColisao.setImage(botaoCinza);
      botaoVariavelDeTravamento.setImage(botaoCinza);
      botaoEstritaAlternancia.setImage(botaoCinza);
      botaoPeterson.setImage(botaoVerde);
    }
  }
  
  /* ***************************************************************
  * Metodo: iniciarSimulacao
  * Funcao: Troca a tela atual para a tela de simulacao
  * Parametros: nenhum
  * Retorno: void
  *************************************************************** */
  @FXML
  public void iniciarSimulacao() throws IOException{
    Stage stage = (Stage) botaoProsseguir.getScene().getWindow();
    trocarTela(stage, "/view/simulacao.fxml");
  }// Fim do metodo iniciarSimulacao

  /* ***************************************************************
  * Metodo: iniciarMenu
  * Funcao: Troca a tela atual para a tela de menu
  * Parametros: nenhum
  * Retorno: void
  *************************************************************** */
  @FXML
  public void iniciarMenu() throws IOException{
    Stage stage = (Stage) botaoProsseguir.getScene().getWindow();
    trocarTela(stage,"/view/menu.fxml");
  }// Fim do metodo iniciarMenu

  /* ***************************************************************
  * Metodo: initialize
  * Funcao: Inicializa a animacao dos botoes
  * Parametros: location -> URL do arquivo FXML
  *             resources -> Recursos utilizados na interface
  * Retorno: void
  *************************************************************** */
  @Override
  public void initialize(URL location, ResourceBundle resources){
    posicaoInicialDireita = 1;
    posicaoInicialEsquerda = 3;
    aplicarAnimacaoBotao(botaoProsseguir, botaoVoltar, botaoComColisao, botaoEstritaAlternancia, botaoPeterson, botaoVariavelDeTravamento); // Aplicando a animacao aos botoes
    verificaSistemaSelecionado(); // Pinta de verde o botao do sistema selecionado
  }// Fim do metodo initialize
}// Fim da classe TutorialController
