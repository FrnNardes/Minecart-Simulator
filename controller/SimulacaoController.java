/* ***************************************************************
* Autor............: Fernando Nardes Ferreira Neto
* Matricula........: 202410403
* Inicio...........: 14/03/2025
* Ultima alteracao.: 05/04/2025
* Nome.............: SimulacaoController.java
* Funcao...........: Controlador da tela de simulacao, realizando o controle domovimento, posicionamento e interacoes do Villager no cenario.
*************************************************************** */
package controller;

import java.io.IOException;
// Importando as bibliotecas necessarias
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.Villager;

public class SimulacaoController extends BaseController implements Initializable{

  private Villager villagerDireita, villagerEsquerda; // Objetos Villager que representam os personagens na tela

  @FXML
  private ImageView imagemVillagerDireita, imagemVillagerEsquerda; // Imagens dos villagers na simulacao

  Image botaoVerde = new Image(getClass().getResourceAsStream("/assets/menuButtonGreen.png")); // Pega a imagem do botao verde
  Image botaoCinza = new Image(getClass().getResourceAsStream("/assets/menuButton.png")); // Pega a imagem do botao cinza

  @FXML
  private ImageView botaoReset, botaoSair, botaoVoltar, botaoTecnica, botaoComColisao, botaoVariavelDeTravamento, botaoEstritaAlternancia, botaoPeterson, setaDireitaCima, setaDireitaBaixo, setaEsquerdaCima, setaEsquerdaBaixo; // Instanciando imagens dos botoes e das setas dos botoes
  
  @FXML
  private ImageView ccText, vtText, eaText, spText, ccText1, vtText1, eaText1, spText1; // Instanciando os textos dos botoes

  @FXML
  private Slider sliderDireita, sliderEsquerda; // Sliders que controlam a velocidade

  private boolean menuAberto = false; // Variavel de alternancia do menu de opcoes de tecnicas
  
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
      villagerDireita.parar(); // Encerra a thread
      villagerEsquerda.parar();
      posicionarVillagers(2, villagerEsquerda.getPosicao());
      setaDireitaCima.setOpacity(0.2); // Define a opacidade das setas
      setaDireitaBaixo.setOpacity(1);
    } else{
      villagerDireita.parar(); // Encerra a thread
      villagerEsquerda.parar();
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
      villagerDireita.parar();
      villagerEsquerda.parar();
      posicionarVillagers(villagerDireita.getPosicao(), 4);
      setaEsquerdaCima.setOpacity(0.2); // Define a opacidade das setas
      setaEsquerdaBaixo.setOpacity(1);
    } else{
      villagerDireita.parar();
      villagerEsquerda.parar();
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
    villagerDireita = new Villager(imagemVillagerDireita, posicaoDireita, sliderDireita, TutorialController.sistemaColisao);
    villagerEsquerda = new Villager(imagemVillagerEsquerda, posicaoEsquerda, sliderEsquerda, TutorialController.sistemaColisao);
    villagerDireita.start(); // Inicia a animacao do Villager da direita
    villagerEsquerda.start(); // Inicia a animacao do Villager da esquerda
  }// Fim do metodo posicionarVillagers

  /* ***************************************************************
  * Metodo: resetarPosicao
  * Funcao: Para a thread dos villagers, reseta a sua posicao e trazem
  *         eles para a posicao incial
  * Parametros: Nenhum
  * Retorno: void
  *************************************************************** */
  @FXML
  private void resetarPosicao(){
    villagerDireita.parar();
    villagerEsquerda.parar();
    posicionarVillagers(villagerDireita.getPosicao(), villagerEsquerda.getPosicao());
    sliderDireita.setValue(2);
    sliderEsquerda.setValue(2);
  }// Fim do metodo resetarPosicao

  /* ***************************************************************
  * Metodo: selecionaSistemaColisao
  * Funcao: Verifica o sistema de colisao selecionado pelo usuario e
            colore o botao selecionado em um tom de verde
  * Parametros: event -> Acao do mouse
  * Retorno:void
  *************************************************************** */
  @FXML
  public void selecionaSistemaColisao(MouseEvent event){
    ImageView clicado = (ImageView) event.getSource(); // Pega o botao que foi clicada
    String idClicado = clicado.getId(); // Pega o id do botao
    
    // Altera a cor do botao selecionado e o texto do titulo
    if(idClicado.equals("botaoComColisao")){
      botaoComColisao.setImage(botaoVerde);
      botaoVariavelDeTravamento.setImage(botaoCinza);
      botaoEstritaAlternancia.setImage(botaoCinza);
      botaoPeterson.setImage(botaoCinza);
      ccText1.setVisible(true);
      vtText1.setVisible(false);
      eaText1.setVisible(false);
      spText1.setVisible(false);
      TutorialController.sistemaColisao = 0;
    } else if(idClicado.equals("botaoVariavelDeTravamento")){
      botaoComColisao.setImage(botaoCinza);
      botaoVariavelDeTravamento.setImage(botaoVerde);
      botaoEstritaAlternancia.setImage(botaoCinza);
      botaoPeterson.setImage(botaoCinza);
      ccText1.setVisible(false);
      vtText1.setVisible(true);
      eaText1.setVisible(false);
      spText1.setVisible(false);
      TutorialController.sistemaColisao = 1;
    } else if(idClicado.equals("botaoEstritaAlternancia")){
      botaoComColisao.setImage(botaoCinza);
      botaoVariavelDeTravamento.setImage(botaoCinza);
      botaoEstritaAlternancia.setImage(botaoVerde);
      botaoPeterson.setImage(botaoCinza);
      ccText1.setVisible(false);
      vtText1.setVisible(false);
      eaText1.setVisible(true);
      spText1.setVisible(false);
      TutorialController.sistemaColisao = 2;
    } else {
      botaoComColisao.setImage(botaoCinza);
      botaoVariavelDeTravamento.setImage(botaoCinza);
      botaoEstritaAlternancia.setImage(botaoCinza);
      botaoPeterson.setImage(botaoVerde);
      ccText1.setVisible(false);
      vtText1.setVisible(false);
      eaText1.setVisible(false);
      spText1.setVisible(true);
      TutorialController.sistemaColisao = 3;
    }
    alterarTecnica(event);
    resetarPosicao();
  }

  /* ***************************************************************
  * Metodo: verificaSistemaSelecionado
  * Funcao: Verifica o sistema de colisao atual selecinado e o seleciona
  *         de acordo
  * Parametros: nenhum
  * Retorno:void
  *************************************************************** */
  public void verificaSistemaSelecionado(){
    // Altera a cor do botao selecionado e o texto do titulo
    if(TutorialController.sistemaColisao == 0){
      botaoComColisao.setImage(botaoVerde);
      botaoVariavelDeTravamento.setImage(botaoCinza);
      botaoEstritaAlternancia.setImage(botaoCinza);
      botaoPeterson.setImage(botaoCinza);
      ccText1.setVisible(true);
      vtText1.setVisible(false);
      eaText1.setVisible(false);
      spText1.setVisible(false);
    } else if(TutorialController.sistemaColisao == 1){
      botaoComColisao.setImage(botaoCinza);
      botaoVariavelDeTravamento.setImage(botaoVerde);
      botaoEstritaAlternancia.setImage(botaoCinza);
      botaoPeterson.setImage(botaoCinza);
      ccText1.setVisible(false);
      vtText1.setVisible(true);
      eaText1.setVisible(false);
      spText1.setVisible(false);
    } else if(TutorialController.sistemaColisao == 2){
      botaoComColisao.setImage(botaoCinza);
      botaoVariavelDeTravamento.setImage(botaoCinza);
      botaoEstritaAlternancia.setImage(botaoVerde);
      botaoPeterson.setImage(botaoCinza);
      ccText1.setVisible(false);
      vtText1.setVisible(false);
      eaText1.setVisible(true);
      spText1.setVisible(false);
    } else {
      botaoComColisao.setImage(botaoCinza);
      botaoVariavelDeTravamento.setImage(botaoCinza);
      botaoEstritaAlternancia.setImage(botaoCinza);
      botaoPeterson.setImage(botaoVerde);
      ccText1.setVisible(false);
      vtText1.setVisible(false);
      eaText1.setVisible(false);
      spText1.setVisible(true);
    }
  }

  /* ***************************************************************
  * Metodo: alterarTecnica
  * Funcao: Expande ou contrai o menu de opcoes de tecnica
  * Parametros: nenhum
  * Retorno:void
  *************************************************************** */
  @FXML
  public void alterarTecnica(MouseEvent event){
    if(menuAberto == false){ // Verifica se está contraido ou expandido
      menuAberto = true;
    } else {
      menuAberto = false;
    }
    // Expande caso esteja contraido e vice-versa
    botaoComColisao.setVisible(menuAberto);
    botaoVariavelDeTravamento.setVisible(menuAberto);
    botaoEstritaAlternancia.setVisible(menuAberto);
    botaoPeterson.setVisible(menuAberto);
    ccText.setVisible(menuAberto);
    vtText.setVisible(menuAberto);
    eaText.setVisible(menuAberto);
    spText.setVisible(menuAberto);
  }

  /* ***************************************************************
  * Metodo: iniciarTutorial
  * Funcao: Troca a tela atual para a tela de tutorial
  * Parametros: nenhum
  * Retorno: void
  *************************************************************** */
  @FXML
  private void iniciarTutorial() throws IOException{
    resetarPosicao(); 
    villagerDireita.setPosicao(1);
    villagerEsquerda.setPosicao(3);
    villagerDireita.parar(); // Encerra a thread
    villagerEsquerda.parar();
    Stage stage = (Stage) botaoSair.getScene().getWindow();
    trocarTela(stage, "/view/tutorial.fxml");
  }// Fim do metodo iniciarTutorial

  /* ***************************************************************
  * Metodo: initialize
  * Funcao: Inicializa os elementos da simulacao, definindo posicoes iniciais e iniciando animacoes
  * Parametros: location -> URL do arquivo FXML
  *             resources -> Recursos utilizados na interface
  * Retorno: void
  *************************************************************** */
  @Override
  public void initialize(URL location, ResourceBundle resources){
    sliderDireita.setValue(2); // Define a velocidade inicial do villager direito em 2
    sliderEsquerda.setValue(2); // Define a velocidade inicial do villager esquerdo em 2
    posicionarVillagers(TutorialController.posicaoInicialDireita, TutorialController.posicaoInicialEsquerda); // Posiciona os mobs nas posicoes indicadas na tela de tutorial
    aplicarAnimacaoBotao(botaoReset, botaoSair, botaoComColisao, botaoEstritaAlternancia, botaoPeterson, botaoVariavelDeTravamento, botaoVoltar, botaoTecnica); // Aplicando a animacao aos botoes
    verificaSistemaSelecionado(); // Verifica qual sistema foi selecionado na tela de tutorial

    // Deixa os botoes invisiveis
    botaoComColisao.setVisible(false); 
    botaoVariavelDeTravamento.setVisible(false);
    botaoEstritaAlternancia.setVisible(false);
    botaoPeterson.setVisible(false);
    ccText.setVisible(false);
    vtText.setVisible(false);
    eaText.setVisible(false);
    spText.setVisible(false);

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
  }// Fim do metodo initialize
}// Fim da classe SimulacaoController