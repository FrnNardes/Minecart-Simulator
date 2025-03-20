/* ***************************************************************
* Autor............: Fernando Nardes Ferreira Neto
* Matricula........: 202410403
* Inicio...........: 14/03/2025
* Ultima alteracao.: 20/03/2025
* Nome.............: Villager.java
* Funcao...........: Classe modelo do objeto Villager, no qual sera a representacao do trem na aplicacao
*************************************************************** */
package model;

import javafx.scene.image.ImageView;

public class Villager{
  private ImageView villagerImagem; // Representa a imagem do villager na interface grafica
  private double velocidade; // Velocidade padrao do villager
  private int posicao; // Posicao inicial do villager

  /* ***************************************************************
  * Metodo: Villager (Construtor)
  * Funcao: Inicializa um objeto Villager com a imagem e a posicao especificada
  * Parametros: imageVillager -> Objeto ImageView representando a imagem do villager
  *             posicao -> Inteiro indicando a posicao inicial do villager
  * Retorno: Instancia de um objeto Villager
  *************************************************************** */
  public Villager(ImageView imageVillager, int posicao){
    this.villagerImagem = imageVillager;
    this.posicao = posicao;
    setPosicaoIncial(posicao); // Chama metodo para definir a posicao inicial
  }// Fim do construtor Villager

  /* ***************************************************************
  * Metodo: setarPosicao
  * Funcao: Define a posicao inicial do villager na tela baseado no numero informado
  * Parametros: posicao -> Inteiro que define a posicao do villager na tela
  * Retorno: void
  *************************************************************** */
  public void setPosicaoIncial(int posicao){
    switch (posicao){
      case 1:
        villagerImagem.setLayoutY(700); // Define a coordenada Y
        villagerImagem.setLayoutX(508); // Define a coordenada X
        villagerImagem.setRotate(0); // Define a rotacao do villager
        break;
      case 2:
        villagerImagem.setLayoutY(-60);
        villagerImagem.setLayoutX(508);
        villagerImagem.setRotate(180);
        break;
      case 3:
        villagerImagem.setLayoutY(700);
        villagerImagem.setLayoutX(460);
        villagerImagem.setRotate(0);
        break;
      case 4:
        villagerImagem.setLayoutY(-60);
        villagerImagem.setLayoutX(460);
        villagerImagem.setRotate(180);
        break;
    }// Fim do switch
  }// Fim do metodo setPosicaoIncial

  /* ***************************************************************
  * Metodo: getVillagerImage
  * Funcao: Retorna o objeto ImageView associado ao villager
  * Parametros: Nenhum
  * Retorno: ImageView -> Representa a imagem do villager
  *************************************************************** */
  public ImageView getVillagerImage(){
    return this.villagerImagem;
  }// Fim do metodo getVillagerImage

  /* ***************************************************************
  * Metodo: getPosicao
  * Funcao: Retorna a posicao atual do villager
  * Parametros: Nenhum
  * Retorno: int -> Posicao atual do villager
  *************************************************************** */
  public int getPosicao(){
    return this.posicao;
  }// Fim do metodo getPosicao

  /* ***************************************************************
  * Metodo: getVelocidade
  * Funcao: Retorna a velocidade do villager
  * Parametros: Nenhum
  * Retorno: double -> Velocidade atual do villager
  *************************************************************** */
  public double getVelocidade(){
    return this.velocidade;
  }// Fim do metodo getVelocidade

  /* ***************************************************************
  * Metodo: getVelocidade
  * Funcao: Seta a velocidade do villager
  * Parametros: Velocidade
  * Retorno: void
  *************************************************************** */
  public void setVelocidade(double velocidade){
    this.velocidade = velocidade;
  }// Fim do metodo setVelocidade
}// Fim da classe Villager