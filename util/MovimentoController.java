/* ***************************************************************
* Autor............: Fernando Nardes Ferreira Neto
* Matricula........: 202410403
* Inicio...........: 14/03/2025
* Ultima alteracao.: 20/03/2025
* Nome.............: MovimentoController.java
* Funcao...........: Classe responsavel pelo controle do movimento dos Villagers na simulacao, fazendo curvas e acelerando ele através de verificacoes de posicoes
*************************************************************** */
package util;

import javafx.scene.image.ImageView;
import model.Villager;

public class MovimentoController {
  
  /* ***************************************************************
  * Construtor: MovimentoController
  * Funcao: Construtor padrao da classe
  * Parametros: nenhum
  * Retorno: Objeto do tipo MovimentoController
  *************************************************************** */
  public MovimentoController(){
  }

  /* ***************************************************************
  * Metodo: movimentarVillagers
  * Funcao: Movimenta o Villager chamando a funcao adequada de acordo com sua posicao atual
  * Parametros: villager -> Objeto Villager a ser movimentado
  * Retorno: void
  *************************************************************** */
  public void movimentarVillagers(Villager villager){
    switch (villager.getPosicao()) { //Muda a movimentacao de acordo com a posicao inicial setada no atributo posicao do villager
      case 1:
        movimentarVillagerDireitaBaixo(villager);
        break;
      case 2:
        movimentarVillagerDireitaCima(villager);
        break;
      case 3:
        movimentarVillagerEsquerdaBaixo(villager);
        break;
      case 4:
        movimentarVillagerEsquerdaCima(villager);
        break;
      default:
        break;
    }// Fim do switch
  }// Fim do metodo movimentarVillagers

  /* ***************************************************************
  * Metodo: movimentarVillagerDireitaBaixo
  * Funcao: Movimenta o Villager para baixo no trilho da direita
  * Parametros: villager -> Objeto Villager a ser movimentado
  * Retorno: void
  *************************************************************** */
  public void movimentarVillagerDireitaBaixo(Villager villager){
    ImageView image = villager.getVillagerImage();
    
    if(image.getLayoutY() > 560){ // Verifica a posicao do villager ate o limite da curva
      image.setLayoutY(image.getLayoutY() - villager.getVelocidade()); // Movimenta o villager no eixo Y
    } else if(image.getLayoutY() <= 560 && image.getLayoutY() > 540){ // Verifica a posicao do villager do inicio ate o fim da curva
      image.setLayoutX(image.getLayoutX() - villager.getVelocidade()); // Modifica o eixo X, realizando a curva
      image.setLayoutY(image.getLayoutY() - villager.getVelocidade()); // Modifica o eixo Y, realizando a curva
      image.setRotate(-60); // Seta a rotacao para ficar condizente com o trilho
    } else if(image.getLayoutY() <= 540 && image.getLayoutY() > 410){
      image.setLayoutY(image.getLayoutY() - villager.getVelocidade());
      image.setRotate(0);
      image.setLayoutX(485);
    } else if(image.getLayoutY() <= 410 && image.getLayoutY() > 390){
      image.setLayoutX(image.getLayoutX() + villager.getVelocidade());
      image.setLayoutY(image.getLayoutY() - villager.getVelocidade());
      image.setRotate(60);
    } else if(image.getLayoutY() <= 390 && image.getLayoutY() > 260){
      image.setLayoutY(image.getLayoutY() - villager.getVelocidade());
      image.setRotate(0);
      image.setLayoutX(508);
    } else if(image.getLayoutY() <= 260 && image.getLayoutY() > 240){
      image.setLayoutX(image.getLayoutX() - villager.getVelocidade());
      image.setLayoutY(image.getLayoutY() - villager.getVelocidade());
      image.setRotate(-60);
    } else if(image.getLayoutY() <= 240 && image.getLayoutY() > 110){
      image.setLayoutY(image.getLayoutY() - villager.getVelocidade());
      image.setRotate(0);
      image.setLayoutX(485);
    } else if(image.getLayoutY() <= 110 && image.getLayoutY() > 90){
      image.setLayoutX(image.getLayoutX() + villager.getVelocidade());
      image.setLayoutY(image.getLayoutY() - villager.getVelocidade());
      image.setRotate(60);
    } else if(image.getLayoutY() <= 90){
      image.setLayoutY(image.getLayoutY() - villager.getVelocidade());
      image.setRotate(0);
      image.setLayoutX(508);
    }// Fim das verificacoes de movimentacao
    
    // If que verifica o limiar das bordas da aplicacao, resetando a posicao incial do villager quando ele ultrapassa-la
    if(image.getLayoutY() < -60){
      villager.setPosicaoIncial(villager.getPosicao());
    }// Fim do if
  }// Fim do metodo movimentarVillagerDiretaBaixo

  /* ***************************************************************
  * Metodo: movimentarVillagerDireitaCima
  * Funcao: Movimenta o Villager para cima no trilho da direita
  * Parametros: villager -> Objeto Villager a ser movimentado
  * Retorno: void
  *************************************************************** */
  public void movimentarVillagerDireitaCima(Villager villager){
    ImageView image = villager.getVillagerImage();
    
    if(image.getLayoutY() < 90){
      image.setLayoutY(image.getLayoutY() + villager.getVelocidade());
    } else if(image.getLayoutY() >= 90 && image.getLayoutY() < 110){
      image.setLayoutX(image.getLayoutX() - villager.getVelocidade());
      image.setLayoutY(image.getLayoutY() + villager.getVelocidade());
      image.setRotate(240);
    } else if(image.getLayoutY() >= 110 && image.getLayoutY() < 240){
      image.setLayoutY(image.getLayoutY() + villager.getVelocidade());
      image.setRotate(180);
      image.setLayoutX(485);
    } else if(image.getLayoutY() >= 240 && image.getLayoutY() < 260){
      image.setLayoutX(image.getLayoutX() + villager.getVelocidade());
      image.setLayoutY(image.getLayoutY() + villager.getVelocidade());
      image.setRotate(120);
    } else if(image.getLayoutY() >= 260 && image.getLayoutY() < 390){
      image.setLayoutY(image.getLayoutY() + villager.getVelocidade());
      image.setRotate(180);
      image.setLayoutX(508);
    } else if(image.getLayoutY() >= 390 && image.getLayoutY() < 410){
      image.setLayoutX(image.getLayoutX() - villager.getVelocidade());
      image.setLayoutY(image.getLayoutY() + villager.getVelocidade());
      image.setRotate(240);
    } else if(image.getLayoutY() >= 410 && image.getLayoutY() < 540){
      image.setLayoutY(image.getLayoutY() + villager.getVelocidade());
      image.setRotate(180);
      image.setLayoutX(485);
    } else if(image.getLayoutY() >= 540 && image.getLayoutY() < 560){
      image.setLayoutX(image.getLayoutX() + villager.getVelocidade());
      image.setLayoutY(image.getLayoutY() + villager.getVelocidade());
      image.setRotate(120);
    } else if(image.getLayoutY() >= 560){
      image.setLayoutY(image.getLayoutY() + villager.getVelocidade());
      image.setRotate(180);
      image.setLayoutX(508);
    }// Fim das verificacoes de movimentacao

    if(image.getLayoutY() > 700){
      villager.setPosicaoIncial(villager.getPosicao());
    }// Fim do if
  }// Fim do metodo movimentarVillagerDiretaCima

  /* ***************************************************************
  * Metodo: movimentarVillagerEsquerdaBaixo
  * Funcao: Movimenta o Villager para baixo no trilho da esquerda
  * Parametros: villager -> Objeto Villager a ser movimentado
  * Retorno: void
  *************************************************************** */
  public void movimentarVillagerEsquerdaBaixo(Villager villager){
    ImageView image = villager.getVillagerImage();
    
    if(image.getLayoutY() > 560){
      image.setLayoutY(image.getLayoutY() - villager.getVelocidade());
    } else if(image.getLayoutY() <= 560 && image.getLayoutY() > 540){ 
      image.setLayoutX(image.getLayoutX() + villager.getVelocidade());
      image.setLayoutY(image.getLayoutY() - villager.getVelocidade());
      image.setRotate(60);
    } else if(image.getLayoutY() <= 540 && image.getLayoutY() > 410){
      image.setLayoutY(image.getLayoutY() - villager.getVelocidade());
      image.setRotate(0);
      image.setLayoutX(485);
    } else if(image.getLayoutY() <= 410 && image.getLayoutY() > 390){
      image.setLayoutX(image.getLayoutX() - villager.getVelocidade());
      image.setLayoutY(image.getLayoutY() - villager.getVelocidade());
      image.setRotate(-60);
    } else if(image.getLayoutY() <= 390 && image.getLayoutY() > 260){
      image.setLayoutY(image.getLayoutY() - villager.getVelocidade());
      image.setRotate(0);
      image.setLayoutX(460);
    } else if(image.getLayoutY() <= 260 && image.getLayoutY() > 240){
      image.setLayoutX(image.getLayoutX() + villager.getVelocidade());
      image.setLayoutY(image.getLayoutY() - villager.getVelocidade());
      image.setRotate(60);
    } else if(image.getLayoutY() <= 240 && image.getLayoutY() > 110){
      image.setLayoutY(image.getLayoutY() - villager.getVelocidade());
      image.setRotate(0);
      image.setLayoutX(485);
    } else if(image.getLayoutY() <= 110 && image.getLayoutY() > 90){
      image.setLayoutX(image.getLayoutX() - villager.getVelocidade());
      image.setLayoutY(image.getLayoutY() - villager.getVelocidade());
      image.setRotate(-60);
    } else if(image.getLayoutY() <= 90){
      image.setLayoutY(image.getLayoutY() - villager.getVelocidade());
      image.setRotate(0);
      image.setLayoutX(460);
    }// Fim das verificacoes de movimentacao

    if(image.getLayoutY() < -60){
      villager.setPosicaoIncial(villager.getPosicao());
    }// Fim do if
  }// Fim do metodo movimentarVillagerEsquerdaBaixo

  /* ***************************************************************
  * Metodo: movimentarVillagerEsquerdaCima
  * Funcao: Movimenta o Villager para cima no trilho da esquerda
  * Parametros: villager -> Objeto Villager a ser movimentado
  * Retorno: void
  *************************************************************** */
  public void movimentarVillagerEsquerdaCima(Villager villager){
    ImageView image = villager.getVillagerImage();
    
    if(image.getLayoutY() < 90){
      image.setLayoutY(image.getLayoutY() + villager.getVelocidade());
    } else if(image.getLayoutY() >= 90 && image.getLayoutY() < 110){
      image.setLayoutX(image.getLayoutX() + villager.getVelocidade());
      image.setLayoutY(image.getLayoutY() + villager.getVelocidade());
      image.setRotate(120);
    } else if(image.getLayoutY() >= 110 && image.getLayoutY() < 240){
      image.setLayoutY(image.getLayoutY() + villager.getVelocidade());
      image.setRotate(180);
      image.setLayoutX(485);
    } else if(image.getLayoutY() >= 240 && image.getLayoutY() < 260){
      image.setLayoutX(image.getLayoutX() - villager.getVelocidade());
      image.setLayoutY(image.getLayoutY() + villager.getVelocidade());
      image.setRotate(240);
    } else if(image.getLayoutY() >= 260 && image.getLayoutY() < 390){
      image.setLayoutY(image.getLayoutY() + villager.getVelocidade());
      image.setRotate(180);
      image.setLayoutX(460);
    } else if(image.getLayoutY() >= 390 && image.getLayoutY() < 410){
      image.setLayoutX(image.getLayoutX() + villager.getVelocidade());
      image.setLayoutY(image.getLayoutY() + villager.getVelocidade());
      image.setRotate(120);
    } else if(image.getLayoutY() >= 410 && image.getLayoutY() < 540){
      image.setLayoutY(image.getLayoutY() + villager.getVelocidade());
      image.setRotate(180);
      image.setLayoutX(485);
    } else if(image.getLayoutY() >= 540 && image.getLayoutY() < 560){
      image.setLayoutX(image.getLayoutX() - villager.getVelocidade());
      image.setLayoutY(image.getLayoutY() + villager.getVelocidade());
      image.setRotate(240);
    } else if(image.getLayoutY() >= 560){
      image.setLayoutY(image.getLayoutY() + villager.getVelocidade());
      image.setRotate(180);
      image.setLayoutX(460);
    }// Fim das verificacoes de movimentacao

    if(image.getLayoutY() > 700){
      villager.setPosicaoIncial(villager.getPosicao());
    }// Fim do if
  }// Fim do metodo movimentarVillagerEsquerdaCima
}// Fim da classe MovimentoController