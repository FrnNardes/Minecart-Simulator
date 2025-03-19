package controller;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class TutorialController extends BaseController{
  @FXML
  private ImageView botaoProsseguir;
  
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
  * Metodo: iniciarSimulacao
  * Funcao: Troca a tela atual para a tela de menu
  * Parametros: nenhum
  * Retorno: void
  *************************************************************** */
  @FXML
  public void iniciarMenu() throws IOException{
    Stage stage = (Stage) botaoProsseguir.getScene().getWindow();
    trocarTela(stage,"/view/menu.fxml");
  }// Fim do metodo iniciarMenu
}// Fim da classe TutorialController
