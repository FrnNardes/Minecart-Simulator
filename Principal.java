/* ***************************************************************
* Autor............: Fernando Nardes Ferreira Neto
* Matricula........: 202410403
* Inicio...........: 14/03/2025
* Ultima alteracao.: 23/03/2025
* Nome.............: Principal.java
* Funcao...........: Classe principal que inicializa a aplicacao com as bibliotecas
 necessaria para a execucao do JavaFX, abrindo a prima tela carregando o fxml do menu.
*************************************************************** */

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import controller.BaseController;
import controller.MenuController;
import controller.SimulacaoController;
import controller.TutorialController;

@SuppressWarnings("unused")

public class Principal extends Application{

  /* ***************************************************************
  * Metodo: start
  * Funcao: Incializar o stage primario do programa, aonde sera exibido nossas cenas, e exibindo a priori o menu
  * Parametros: primaryStage, o stage principal para as cenas
  * Retorno: void
  *************************************************************** */
  @Override
  public void start(Stage primaryStage) throws Exception{
    FXMLLoader loader = new FXMLLoader(getClass().getResource("view/menu.fxml"));// Carrega o fxml do menu
    Parent root = loader.load();// Define o root com o fxml e carrega o controller do menu atraves do metodo load
    Scene scene = new Scene(root);// Define a cena com o root

    primaryStage.setScene(scene);// Define o Stage com a cena
    primaryStage.setTitle("MineCart");// Define o titulo do Stage
    primaryStage.initStyle(StageStyle.UNDECORATED);// Estiliza o Stage,.deixando ele sem bordas
    primaryStage.resizableProperty().setValue(false);;// Define o Stage como nao redimensionavel
    primaryStage.show();// Mostra o Stage para o usuario
  }// Fim do metodo start

  /* ***************************************************************
  * Metodo: main
  * Funcao: Principal funcao do programa, aonde sera inicializado a aplicacao
  * Parametros: Um array de Strings que guarda os argumentos
  * Retorno: void
  *************************************************************** */
  public static void main(String[] args) {
    launch(args);
  }
}