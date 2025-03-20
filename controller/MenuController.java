/* ***************************************************************
* Autor............: Fernando Nardes Ferreira Neto
* Matricula........: 202410403
* Inicio...........: 14/03/2025
* Ultima alteracao.: 20/03/2025
* Nome.............: MenuController.java
* Funcao...........: Classe controladora da tela de menu, gerenciando os botoes de iniciar e sair do programa.
*************************************************************** */
package controller;

// Importando as bibliotecas necessarias
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class MenuController extends BaseController implements Initializable{

  @FXML
  private ImageView botaoIniciar, botaoSair; // Instanciando os botoes
  
  /* ***************************************************************
  * Metodo: iniciarTutorial
  * Funcao: Troca a tela atual para a tela de tutorial
  * Parametros: nenhum
  * Retorno: void
  *************************************************************** */
  @FXML
  private void iniciarTutorial() throws IOException{
    Stage stage = (Stage) botaoIniciar.getScene().getWindow();
    trocarTela(stage, "/view/tutorial.fxml");
  }// Fim do metodo iniciarTutorial

  /* ***************************************************************
  * Metodo: initialize
  * Funcao: Inicializa a animacao dos botoes
  * Parametros: location -> URL do arquivo FXML
  *             resources -> Recursos utilizados na interface
  * Retorno: void
  *************************************************************** */
  @Override
  public void initialize(URL location, ResourceBundle resources){
    aplicarAnimacaoBotao(botaoIniciar, botaoSair); // Aplica a animacao nos botoes
  } // Fim do metodo initialize
}// Fim da classe MenuController
