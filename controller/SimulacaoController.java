/* ***************************************************************
* Autor............: Fernando Nardes Ferreira Neto
* Matricula........: 202410403
* Inicio...........: 14/03/2025
* Ultima alteracao.: 23/03/2025
* Nome.............: SimulacaoController.java
* Funcao...........: Controlador da tela de simulacao, realizando o controle domovimento, posicionamento e interacoes do Villager no cenario.
*************************************************************** */
package controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Slider;
import javafx.scene.image.ImageView;
import model.Villager;
import util.MovimentoController;

public class SimulacaoController extends BaseController implements Initializable{
  private MovimentoController movimentoController = new MovimentoController(); // Controlador responsavel pelo movimento dos Villagers

  @FXML
  private ImageView imagemVillagerDireita, imagemVillagerEsquerda; // Imagens dos villagers na simulacao

  private Villager villagerDireita, villagerEsquerda; // Objetos Villager que representam os personagens na tela

  @FXML
  private Slider sliderDireita, sliderEsquerda; // Sliders que controlam a velocidade

  /* ***************************************************************
  * Animacao: timerVillagerDireita
  * Funcao: Controla a animacao do movimento do Villager da direita
  *************************************************************** */
  AnimationTimer timerVillagerDireita = new AnimationTimer() {

    /* ***************************************************************
    * Metodo: handle
    * Funcao: Metodo chamado a cada frame da animacao, executando tudo que esta contido nele, movmentando o Villager na direita
    * Parametros: long now -> O parametro now de tipo long representando o tempo atual do sistema em nanossegundos
    * Retorno: void
    *************************************************************** */
    @Override
    public void handle(long now) {
      Platform.runLater(() ->{
        villagerDireita.setVelocidade(sliderDireita.getValue());
        movimentoController.movimentarVillagers(villagerDireita);
      });
    }// Fim do metodo handle
  };// Fim do timerVillagerDireita

  /* ***************************************************************
  * Animacao: timerVillagerEsquerda
  * Funcao: Controla a animacao do movimento do Villager da esquerda
  *************************************************************** */
  AnimationTimer timerVillagerEsquerda = new AnimationTimer() {

    /* ***************************************************************
    * Metodo: handle
    * Funcao: Metodo chamado a cada frame da animacao, executando tudo que esta contido nele, movmentando o Villager na esquerda
    * Parametros: long now -> O parametro now de tipo long representando o tempo atual do sistema em nanossegundos
    * Retorno: void
    *************************************************************** */
    @Override
    public void handle(long now) {
      Platform.runLater(() ->{
        villagerEsquerda.setVelocidade(sliderEsquerda.getValue());
        movimentoController.movimentarVillagers(villagerEsquerda);
      });
    }// Fim do metodo handle
  };// Fim do timerVillagerEsquerda

  /* ***************************************************************
  * Metodo: subirVillagerDireita
  * Funcao: Move o Villager da direita para a posicao superior
  * Parametros: event -> Acao do botao
  * Retorno: void
  *************************************************************** */
  @FXML
  private void subirVillagerDireita(ActionEvent event){
    posicionarVillagers(2, villagerEsquerda.getPosicao());
  }// Fim do metodo subirVillagerDireita

  /* ***************************************************************
  * Metodo: descerVillagerDireita
  * Funcao: Move o Villager da direita para a posicao inferior
  * Parametros: event -> Acao do botao
  * Retorno: void
  *************************************************************** */
  @FXML
  private void descerVillagerDireita(ActionEvent event){
    posicionarVillagers(1, villagerEsquerda.getPosicao());
  }// Fim do metodo descerVillagerDireita

  /* ***************************************************************
  * Metodo: subirVillagerEsquerda
  * Funcao: Move o Villager da esquerda para a posicao superior
  * Parametros: event -> Acao do botao
  * Retorno:void
  *************************************************************** */
  @FXML
  private void subirVillagerEsquerda(ActionEvent event){
    posicionarVillagers(villagerDireita.getPosicao(), 4);
  }// Fim do metodo subirVillagerEsquerda

  /* ***************************************************************
  * Metodo: descerVillagerEsquerda
  * Funcao: Move o Villager da esquerda para a posicao inferior
  * Parametros: event -> Acao do botao
  * Retorno: void
  *************************************************************** */
  @FXML
  private void descerVillagerEsquerda(ActionEvent event){
    posicionarVillagers(villagerDireita.getPosicao(), 3);
  }// FIm do metodo descerVillagerEsquerda

  /* ***************************************************************
  * Metodo: posicionarVillagers
  * Funcao: Instancia e posiciona os Villagers na tela com base nas posicoes fornecidas
  * Parametros: posicaoDireita -> Inteiro representando a posicao do Villager da direita
  *             posicaoEsquerda -> Inteiro representando a posicao do Villager da esquerda
  * Retorno: void
  *************************************************************** */
  private void posicionarVillagers(int posicaoDireita, int posicaoEsquerda){
    villagerDireita = new Villager(imagemVillagerDireita, posicaoDireita);
    villagerEsquerda = new Villager(imagemVillagerEsquerda, posicaoEsquerda);
  }// Fim do metodo posicionarVillagers

  /* ***************************************************************
  * Metodo: resetarPosicao
  * Funcao: Reseta a posicao dos villagers e trazem eles para a posicao incial
  * Parametros: event -> Evento acionado pelo usuario
  * Retorno: void
  *************************************************************** */
  @FXML
  private void resetarPosicao(){
    villagerDireita = new Villager(imagemVillagerDireita, villagerDireita.getPosicao());
    villagerEsquerda = new Villager(imagemVillagerEsquerda, villagerEsquerda.getPosicao());
  }// Fim do metodo resetarPosicao

  /* ***************************************************************
  * Metodo: initialize
  * Funcao: Inicializa os elementos da simulacao, definindo posicoes iniciais e iniciando animacoes
  * Parametros: location -> URL do arquivo FXML
  *             resources -> Recursos utilizados na interface
  * Retorno: void
  *************************************************************** */
  @Override
  public void initialize(URL location, ResourceBundle resources){
    posicionarVillagers(1, 3); // Posiciona os mobs nas posicoes 1 e 3, para eles comecarem em baixo.
    sliderDireita.setValue(2); // Define a velocidade inicial do villager direito em 2
    sliderEsquerda.setValue(2); // Define a velocidade inicial do villager esquerdo em 2
    timerVillagerDireita.start(); // Inicia a animacao do Villager da direita
    timerVillagerEsquerda.start(); // Inicia a animacao do Villager da esquerda
  }// Fim do metodo initialize
}// Fim da classe SimulacaoController