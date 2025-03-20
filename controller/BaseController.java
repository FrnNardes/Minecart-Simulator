/* ***************************************************************
* Autor............: Fernando Nardes Ferreira Neto
* Matricula........: 202410403
* Inicio...........: 14/03/2025
* Ultima alteracao.: 20/03/2025
* Nome.............: BaseController.java
* Funcao...........: Classe base para os controladores, fornecendo 
*                    funcionalidade para troca de telas na aplicacao.
*************************************************************** */
package controller;

// Importando as bibliotecas necessarias
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public abstract class BaseController {
  
  /* ***************************************************************
  * Metodo: trocarTela
  * Funcao: Realiza a troca de tela na aplicacao, carregando um novo 
  *         arquivo FXML e substituindo a cena atual.
  * Parametros: stage - o stage atual da aplicacao onde a nova tela sera exibida.
  *             fxml  - o caminho do arquivo FXML que sera carregado.
  * Retorno: void
  *************************************************************** */
  protected void trocarTela(Stage stage, String fxml) throws IOException {
    try {
      Parent root = FXMLLoader.load(getClass().getResource(fxml)); // Carrega o novo FXML
      
      stage.setScene(new Scene(root)); // Define a nova cena no stage
      stage.centerOnScreen(); // Centraliza a janela na tela
      stage.show(); // Exibe a nova tela
    } catch (IOException e) {
      e.printStackTrace(); // Exibe erro caso ocorra falha no carregamento do FXML
    } // Fim do try-catch
  } // Fim do metodo trocarTela

  /* ***************************************************************
  * Metodo: fecharPrograma
  * Funcao: Finaliza a aplicacao quando acionado um evento de clique 
  *         do mouse pelo usuario.
  * Parametros: event - evento de clique do mouse.
  * Retorno: void
  *************************************************************** */
  @FXML
  public void fecharPrograma(MouseEvent event) {
    System.exit(0);
  } // Fim do metodo fecharPrograma


/* ***************************************************************
  * Metodo: aplicarAnimacaoBotao
  * Funcao: Adiciona interatividade aos botoes da interface, permitindo
  *         que as classes que herdam BaseController aplique essa simples
  *         animacao aos botoes de usa interface.
  * Parametros: Lista de ImageView - Objetos ImageView da imagem de cada botao
  * Retorno: void
  *************************************************************** */
  public void aplicarAnimacaoBotao(ImageView... ImageViews) {
    ColorAdjust efeitoHover = new ColorAdjust();
    ColorAdjust efeitoPressionado = new ColorAdjust();

    efeitoHover.setBrightness(0.2); // Clareia a imagem ao passar o mouse
    efeitoPressionado.setBrightness(-0.2); // Escurece a imagem ao pressionar

    for(ImageView imageView : ImageViews){
      imageView.setOnMouseEntered(e -> imageView.setEffect(efeitoHover)); // Evento de hover (mouse entra na imagem
      imageView.setOnMouseExited(e -> imageView.setEffect(null)); // Evento de saída do hover (mouse sai da imagem)
      imageView.setOnMousePressed(e -> imageView.setEffect(efeitoPressionado)); // Evento de clique pressionado
      imageView.setOnMouseReleased(e -> imageView.setEffect(efeitoHover)); // Evento de clique liberado
    }// Fim do For
  }// Fim do metodo aplicarAnimacaoBotao
} // Fim da classe BaseController
