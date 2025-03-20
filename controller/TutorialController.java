/* ***************************************************************
* Autor............: Fernando Nardes Ferreira Neto
* Matricula........: 202410403
* Inicio...........: 14/03/2025
* Ultima alteracao.: 20/03/2025
* Nome.............: TutorialController.java
* Funcao...........: Controlador da tela de tutorial, realizando o posicionamento inicial dos villagers, indicando o funcionamento de botoes, etc;
*************************************************************** */
package controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class TutorialController extends BaseController{
  protected static int posicaoInicialDireita = 1; // Posicao incial em baixo 
  protected static int posicaoInicialEsquerda = 3; // Posicao incial em baixo 

  @FXML
  private ImageView botaoProsseguir; // Imagem do botao para pegar a cena atual

  @FXML
  private ImageView setaDireitaCima, setaDireitaBaixo, setaEsquerdaCima, setaEsquerdaBaixo; // Imagens das setas dos botoes

  @FXML
  private ImageView imagemVillagerDireita, imagemVillagerEsquerda; // Imagens dos villagers na simulacao

  /* ***************************************************************
  * Metodo: subirVillagerDireita
  * Funcao: Move o Villager da direita para a posicao superior ou inferior
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
      setaDireitaBaixo.setOpacity(1);
      setaDireitaCima.setOpacity(0.2);
    } else{
      imagemVillagerDireita.setLayoutY(635); // Define a coordenada Y
      imagemVillagerDireita.setLayoutX(508); // Define a coordenada X
      imagemVillagerDireita.setRotate(0); // Define a rotacao do villager
      posicaoInicialDireita = 1; // Altera a posicao incial 
      setaDireitaBaixo.setOpacity(0.2);
      setaDireitaCima.setOpacity(1);
    }
    
  }// Fim do metodo botaoDireita

  /* ***************************************************************
  * Metodo: botaoEsquerda
  * Funcao: Move o Villager da esquerda para a posicao superior ou inferior
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
      setaEsquerdaBaixo.setOpacity(1);
      setaEsquerdaCima.setOpacity(0.2);
    } else {
      imagemVillagerEsquerda.setLayoutY(635); // Define a coordenada Y
      imagemVillagerEsquerda.setLayoutX(460); // Define a coordenada X
      imagemVillagerEsquerda.setRotate(0); // Define a rotacao do villager
      posicaoInicialEsquerda = 3; // Altera a posicao incial
      setaEsquerdaBaixo.setOpacity(0.2);
      setaEsquerdaCima.setOpacity(1);
    }
    
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
}// Fim da classe TutorialController
