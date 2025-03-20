/* ***************************************************************
* Autor............: Fernando Nardes Ferreira Neto
* Matricula........: 202410403
* Inicio...........: 14/03/2025
* Ultima alteracao.: 20/03/2025
* Nome.............: SimulacaoController.java
* Funcao...........: Controlador da tela de simulacao, realizando o controle domovimento, posicionamento e interacoes do Villager no cenario.
*************************************************************** */
package controller;

// Importando as bibliotecas necessarias
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

  private Villager villagerDireita, villagerEsquerda; // Objetos Villager que representam os personagens na tela

  @FXML
  private ImageView imagemVillagerDireita, imagemVillagerEsquerda; // Imagens dos villagers na simulacao

  @FXML
  private ImageView botaoReset, botaoSair, setaDireitaCima, setaDireitaBaixo, setaEsquerdaCima, setaEsquerdaBaixo; // Instanciando imagens dos botoes e das setas dos botoes

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
  * Metodo: botaoDireita
  * Funcao: Move o Villager da direita para a posicao superior ou inferior
  *         e faz uma animacao nas setas do botao
  * Parametros: event -> Acao do botao
  * Retorno: void
  *************************************************************** */
  @FXML
  private void botaoDireita(ActionEvent event){
    if(villagerDireita.getPosicao() == 1){ // Verifica a posicao atual do villager
      posicionarVillagers(2, villagerEsquerda.getPosicao());
      setaDireitaCima.setOpacity(0.2); // Define a opacidade das setas
      setaDireitaBaixo.setOpacity(1);
    } else{
      posicionarVillagers(1, villagerEsquerda.getPosicao());
      setaDireitaCima.setOpacity(1); // Define a opacidade das setas
      setaDireitaBaixo.setOpacity(0.2);
    }// Fim das verificacoes
  }// Fim do metodo botaoDireita

  /* ***************************************************************
  * Metodo: botaoEsquerda
  * Funcao: Move o Villager da esquerda para a posicao superior ou inferior
  *         e faz uma animacao nas setas do botao
  * Parametros: event -> Acao do botao
  * Retorno: void
  *************************************************************** */
  @FXML
  private void botaoEsquerda(ActionEvent event){
    if(villagerEsquerda.getPosicao() == 3){ // Verifica a posicao atual do villager
      posicionarVillagers(villagerDireita.getPosicao(), 4);
      setaEsquerdaCima.setOpacity(0.2); // Define a opacidade das setas
      setaEsquerdaBaixo.setOpacity(1);
    } else{
      posicionarVillagers(villagerDireita.getPosicao(), 3);
      setaEsquerdaCima.setOpacity(1); // Define a opacidade das setas
      setaEsquerdaBaixo.setOpacity(0.2);
    }// Fim das verificacoes
  }// Fim do metodo botaoEsquerda

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
  * Parametros: Nenhum
  * Retorno: void
  *************************************************************** */
  @FXML
  private void resetarPosicao(){
    posicionarVillagers(villagerDireita.getPosicao(), villagerEsquerda.getPosicao());
    sliderDireita.setValue(2);
    sliderEsquerda.setValue(2);
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
    posicionarVillagers(TutorialController.posicaoInicialDireita, TutorialController.posicaoInicialEsquerda); // Posiciona os mobs nas posicoes indicadas na tela de tutorial
    aplicarAnimacaoBotao(botaoReset, botaoSair); // Aplicando a animacao aos botoes

    if(TutorialController.posicaoInicialDireita == 2){ // Verifica a posicao que o villager começa e define a opacidade das setas de acordo
      setaDireitaCima.setOpacity(0.2);
      setaDireitaBaixo.setOpacity(1);
    } else {
      setaDireitaCima.setOpacity(1);
      setaDireitaBaixo.setOpacity(0.2);
    }// Fim das verificacoes

    if(TutorialController.posicaoInicialEsquerda == 4){ // Verifica a posicao que o villager começa e define a opacidade das setas de acordo
      setaEsquerdaCima.setOpacity(0.2);
      setaEsquerdaBaixo.setOpacity(1);
    } else {
      setaEsquerdaCima.setOpacity(1);
      setaEsquerdaBaixo.setOpacity(0.2);
    }// Fim das verificacoes

    sliderDireita.setValue(2); // Define a velocidade inicial do villager direito em 2
    sliderEsquerda.setValue(2); // Define a velocidade inicial do villager esquerdo em 2
    timerVillagerDireita.start(); // Inicia a animacao do Villager da direita
    timerVillagerEsquerda.start(); // Inicia a animacao do Villager da esquerda
  }// Fim do metodo initialize
}// Fim da classe SimulacaoController