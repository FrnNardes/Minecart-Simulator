/* ***************************************************************
* Autor............: Fernando Nardes Ferreira Neto
* Matricula........: 202410403
* Inicio...........: 14/03/2025
* Ultima alteracao.: 20/03/2025
* Nome.............: BaseController.java
* Funcao...........: Classe base para os controladores, fornecendo funcionalidade para troca de telas na aplicacao.
*************************************************************** */
package controller;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public abstract class BaseController {
  
  /* ***************************************************************
  * Metodo: trocarTela
  * Funcao: Realiza a troca de tela na aplicacao carregando um novo arquivo FXML
  * Parametros: event -> Evento acionado pelo usuario
  *             fxml -> Caminho do arquivo FXML a ser carregado
  * Retorno: void
  *************************************************************** */
  protected void trocarTela(Stage stage, String fxml) throws IOException{
    try{
      Parent root = FXMLLoader.load(getClass().getResource(fxml)); // Carrega o novo FXML
      
      stage.setScene(new Scene(root)); // Define a nova cena
      stage.centerOnScreen(); // Centraliza a janela
      stage.show(); // Exibe a nova tela
    } catch (IOException e){
      e.printStackTrace(); // Exibe erros caso ocorra falha no carregamento do FXML
    }// Fim do try-catch
  }// Fim do metodo trocarTela

  /* ***************************************************************
  * Metodo: fecharPrograma
  * Funcao: Finaliza o programa ao receber a acao de um clique de mouse
  * Parametros: event -> Evento acionado pelo usuario
  * Retorno: void
  *************************************************************** */
  @FXML
  public void fecharPrograma(MouseEvent event) {
    System.exit(0);
  }// Fim do metodo fecharPrograma
}// Fim da classe BaseController
