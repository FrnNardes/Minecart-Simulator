/* ***************************************************************
* Autor............: Fernando Nardes Ferreira Neto
* Matricula........: 202410403
* Inicio...........: 14/03/2025
* Ultima alteracao.: 20/03/2025
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
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class TutorialController extends BaseController implements Initializable{
  protected static int posicaoInicialDireita = 1; // Setando a posicao incial da direita em baixo 
  protected static int posicaoInicialEsquerda = 3; // Setando a posicao incial da esquerda em baixo 

  @FXML
  private ImageView botaoProsseguir, botaoVoltar; // Instanciando os botoes

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
    posicaoInicialDireita = 1;
    posicaoInicialEsquerda = 3;
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
    aplicarAnimacaoBotao(botaoProsseguir, botaoVoltar); // Aplicando a animacao aos botoes
  }// Fim do metodo initialize
}// Fim da classe TutorialController
