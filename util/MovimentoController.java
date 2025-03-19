/* ***************************************************************
* Autor............: Fernando Nardes Ferreira Neto
* Matricula........: 202410403
* Inicio...........: 14/03/2025
* Ultima alteracao.: 23/03/2025
* Nome.............: MovimentoController.java
* Funcao...........: Responsavel pelo controle do movimento dos Villagers na simulacao.
*************************************************************** */
package util;

import javafx.scene.image.ImageView;
import model.Villager;

public class MovimentoController {
  
  /* ***************************************************************
  * Construtor: MovimentoController
  * Funcao: Construtor padrao da classe
  *************************************************************** */
  public MovimentoController(){
  }

  /* ***************************************************************
  * Metodo: movimentarVillagers
  * Funcao: Movimenta o Villager de acordo com sua posicao atual
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
  * Funcao: Movimenta o Villager para baixo na trilha da direita
  * Parametros: villager -> Objeto Villager a ser movimentado
  * Retorno: void
  *************************************************************** */
  public void movimentarVillagerDireitaBaixo(Villager villager){
    ImageView image = villager.getVillagerImage();
    
    if(image.getLayoutY() > 610){ // Verifica a posicao do villager ate o limite da curva
      image.setLayoutY(image.getLayoutY() - villager.getVelocidade()); // Movimenta o villager no eixo Y
    } else if(image.getLayoutY() <= 610 && image.getLayoutY() > 580){ // Verifica a posicao do villager do inicio ate o fim da curva
      image.setLayoutX(image.getLayoutX() - villager.getVelocidade()); // Modifica o eixo X, realizando a curva
      image.setLayoutY(image.getLayoutY() - villager.getVelocidade()); // Modifica o eixo Y, realizando a curva
      image.setRotate(-60); // Seta a rotacao para ficar condizente com o trilho
    } else if(image.getLayoutY() <= 580 && image.getLayoutY() > 450){
      image.setLayoutY(image.getLayoutY() - villager.getVelocidade());
      image.setRotate(0);
      image.setLayoutX(485);
    } else if(image.getLayoutY() <= 450 && image.getLayoutY() > 420){
      image.setLayoutX(image.getLayoutX() + villager.getVelocidade());
      image.setLayoutY(image.getLayoutY() - villager.getVelocidade());
      image.setRotate(60);
    } else if(image.getLayoutY() <= 420 && image.getLayoutY() > 290){
      image.setLayoutY(image.getLayoutY() - villager.getVelocidade());
      image.setRotate(0);
      image.setLayoutX(513);
    } else if(image.getLayoutY() <= 290 && image.getLayoutY() > 260){
      image.setLayoutX(image.getLayoutX() - villager.getVelocidade());
      image.setLayoutY(image.getLayoutY() - villager.getVelocidade());
      image.setRotate(-60);
    } else if(image.getLayoutY() <= 260 && image.getLayoutY() > 130){
      image.setLayoutY(image.getLayoutY() - villager.getVelocidade());
      image.setRotate(0);
      image.setLayoutX(485);
    } else if(image.getLayoutY() <= 130 && image.getLayoutY() > 100){
      image.setLayoutX(image.getLayoutX() + villager.getVelocidade());
      image.setLayoutY(image.getLayoutY() - villager.getVelocidade());
      image.setRotate(60);
    } else if(image.getLayoutY() <= 100){
      image.setLayoutY(image.getLayoutY() - villager.getVelocidade());
      image.setRotate(0);
      image.setLayoutX(513);
    }// Fim das verificacoes de movimentacao
    
    // If que verifica o limiar das bordas da aplicacao, resetando a posicao incial do villager
    if(image.getLayoutY() < -60){
      villager.setPosicaoIncial(villager.getPosicao());
    }
  }// Fim do metodo movimentarVillagerDiretaBaixo

  /* ***************************************************************
  * Metodo: movimentarVillagerDireitaCima
  * Funcao: Movimenta o Villager para cima na trilha da direita
  * Parametros: villager -> Objeto Villager a ser movimentado
  * Retorno: void
  *************************************************************** */
  public void movimentarVillagerDireitaCima(Villager villager){
    ImageView image = villager.getVillagerImage();
    
    if(image.getLayoutY() < 100){
      image.setLayoutY(image.getLayoutY() + villager.getVelocidade());
    } else if(image.getLayoutY() >= 100 && image.getLayoutY() < 130){
      image.setLayoutX(image.getLayoutX() - villager.getVelocidade());
      image.setLayoutY(image.getLayoutY() + villager.getVelocidade());
      image.setRotate(240);
    } else if(image.getLayoutY() >= 130 && image.getLayoutY() < 260){
      image.setLayoutY(image.getLayoutY() + villager.getVelocidade());
      image.setRotate(180);
      image.setLayoutX(485);
    } else if(image.getLayoutY() >= 260 && image.getLayoutY() < 290){
      image.setLayoutX(image.getLayoutX() + villager.getVelocidade());
      image.setLayoutY(image.getLayoutY() + villager.getVelocidade());
      image.setRotate(120);
    } else if(image.getLayoutY() >= 290 && image.getLayoutY() < 420){
      image.setLayoutY(image.getLayoutY() + villager.getVelocidade());
      image.setRotate(180);
      image.setLayoutX(513);
    } else if(image.getLayoutY() >= 420 && image.getLayoutY() < 450){
      image.setLayoutX(image.getLayoutX() - villager.getVelocidade());
      image.setLayoutY(image.getLayoutY() + villager.getVelocidade());
      image.setRotate(240);
    } else if(image.getLayoutY() >= 450 && image.getLayoutY() < 580){
      image.setLayoutY(image.getLayoutY() + villager.getVelocidade());
      image.setRotate(180);
      image.setLayoutX(485);
    } else if(image.getLayoutY() >= 580 && image.getLayoutY() < 610){
      image.setLayoutX(image.getLayoutX() + villager.getVelocidade());
      image.setLayoutY(image.getLayoutY() + villager.getVelocidade());
      image.setRotate(120);
    } else if(image.getLayoutY() >= 610){
      image.setLayoutY(image.getLayoutY() + villager.getVelocidade());
      image.setRotate(180);
      image.setLayoutX(513);
    }// Fim das verificacoes de movimentacao

    if(image.getLayoutY() > 760){
      villager.setPosicaoIncial(villager.getPosicao());
    }
  }// Fim do metodo movimentarVillagerDiretaCima

  /* ***************************************************************
  * Metodo: movimentarVillagerEsquerdaBaixo
  * Funcao: Movimenta o Villager para baixo na trilha da esquerda
  * Parametros: villager -> Objeto Villager a ser movimentado
  * Retorno: void
  *************************************************************** */
  public void movimentarVillagerEsquerdaBaixo(Villager villager){
    ImageView image = villager.getVillagerImage();
    
    if(image.getLayoutY() > 610){
      image.setLayoutY(image.getLayoutY() - villager.getVelocidade());
    } else if(image.getLayoutY() <= 610 && image.getLayoutY() > 580){
      image.setLayoutX(image.getLayoutX() + villager.getVelocidade());
      image.setLayoutY(image.getLayoutY() - villager.getVelocidade());
      image.setRotate(60);
    } else if(image.getLayoutY() <= 580 && image.getLayoutY() > 450){
      image.setLayoutY(image.getLayoutY() - villager.getVelocidade());
      image.setRotate(0);
      image.setLayoutX(485);
    } else if(image.getLayoutY() <= 450 && image.getLayoutY() > 420){
      image.setLayoutX(image.getLayoutX() - villager.getVelocidade());
      image.setLayoutY(image.getLayoutY() - villager.getVelocidade());
      image.setRotate(-60);
    } else if(image.getLayoutY() <= 420 && image.getLayoutY() > 290){
      image.setLayoutY(image.getLayoutY() - villager.getVelocidade());
      image.setRotate(0);
      image.setLayoutX(458);
    } else if(image.getLayoutY() <= 290 && image.getLayoutY() > 260){
      image.setLayoutX(image.getLayoutX() + villager.getVelocidade());
      image.setLayoutY(image.getLayoutY() - villager.getVelocidade());
      image.setRotate(60);
    } else if(image.getLayoutY() <= 260 && image.getLayoutY() > 130){
      image.setLayoutY(image.getLayoutY() - villager.getVelocidade());
      image.setRotate(0);
      image.setLayoutX(485);
    } else if(image.getLayoutY() <= 130 && image.getLayoutY() > 100){
      image.setLayoutX(image.getLayoutX() - villager.getVelocidade());
      image.setLayoutY(image.getLayoutY() - villager.getVelocidade());
      image.setRotate(-60);
    } else if(image.getLayoutY() <= 100){
      image.setLayoutY(image.getLayoutY() - villager.getVelocidade());
      image.setRotate(0);
      image.setLayoutX(458);
    }// Fim das verificacoes de movimentacao

    if(image.getLayoutY() < -60){
      villager.setPosicaoIncial(villager.getPosicao());
    }
  }// Fim do metodo movimentarVillagerEsquerdaBaixo

  /* ***************************************************************
  * Metodo: movimentarVillagerEsquerdaCima
  * Funcao: Movimenta o Villager para cima na trilha da esquerda
  * Parametros: villager -> Objeto Villager a ser movimentado
  * Retorno: void
  *************************************************************** */
  public void movimentarVillagerEsquerdaCima(Villager villager){
    ImageView image = villager.getVillagerImage();
    
    if(image.getLayoutY() < 100){
      image.setLayoutY(image.getLayoutY() + villager.getVelocidade());
    } else if(image.getLayoutY() >= 100 && image.getLayoutY() < 130){
      image.setLayoutX(image.getLayoutX() + villager.getVelocidade());
      image.setLayoutY(image.getLayoutY() + villager.getVelocidade());
      image.setRotate(120);
    } else if(image.getLayoutY() >= 130 && image.getLayoutY() < 260){
      image.setLayoutY(image.getLayoutY() + villager.getVelocidade());
      image.setRotate(180);
      image.setLayoutX(485);
    } else if(image.getLayoutY() >= 260 && image.getLayoutY() < 290){
      image.setLayoutX(image.getLayoutX() - villager.getVelocidade());
      image.setLayoutY(image.getLayoutY() + villager.getVelocidade());
      image.setRotate(240);
    } else if(image.getLayoutY() >= 290 && image.getLayoutY() < 420){
      image.setLayoutY(image.getLayoutY() + villager.getVelocidade());
      image.setRotate(180);
      image.setLayoutX(458);
    } else if(image.getLayoutY() >= 420 && image.getLayoutY() < 450){
      image.setLayoutX(image.getLayoutX() + villager.getVelocidade());
      image.setLayoutY(image.getLayoutY() + villager.getVelocidade());
      image.setRotate(120);
    } else if(image.getLayoutY() >= 450 && image.getLayoutY() < 580){
      image.setLayoutY(image.getLayoutY() + villager.getVelocidade());
      image.setRotate(180);
      image.setLayoutX(485);
    } else if(image.getLayoutY() >= 580 && image.getLayoutY() < 610){
      image.setLayoutX(image.getLayoutX() - villager.getVelocidade());
      image.setLayoutY(image.getLayoutY() + villager.getVelocidade());
      image.setRotate(240);
    } else if(image.getLayoutY() >= 610){
      image.setLayoutY(image.getLayoutY() + villager.getVelocidade());
      image.setRotate(180);
      image.setLayoutX(458);
    }// Fim das verificacoes de movimentacao

    if(image.getLayoutY() > 760){
      villager.setPosicaoIncial(villager.getPosicao());
    }
  }// Fim do metodo movimentarVillagerEsquerdaCima
}// Fim da classe MovimentoController