/* ***************************************************************
* Autor............: Fernando Nardes Ferreira Neto
* Matricula........: 202410403
* Inicio...........: 14/03/2025
* Ultima alteracao.: 20/03/2025
* Nome.............: MenuController.java
* Funcao...........: Classe controladora da tela de menu, gerenciando os botoes de iniciar e sair do programa.
*************************************************************** */
package controller;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class MenuController extends BaseController{

  @FXML
  private ImageView botaoIniciar; // Instanciando o botao para pegar a cena atual
  
  /* ***************************************************************
  * Metodo: iniciarSimulacao
  * Funcao: Troca a tela atual para a tela de tutorial
  * Parametros: nenhum
  * Retorno: void
  *************************************************************** */
  @FXML
  private void iniciarTutorial() throws IOException{
    Stage stage = (Stage) botaoIniciar.getScene().getWindow();
    trocarTela(stage, "/view/tutorial.fxml");
  }// Fim do metodo iniciarTutorial
}// Fim da classe MenuController
