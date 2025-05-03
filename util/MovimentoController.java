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
  private static int turnId1 = 0;
  private static int turnId2 = 0;
  private static volatile int outro1, outro2; // Para ZonaCritica1
  private static volatile boolean[] interessado1 = {false, false}; // Para ZonaCritica1
  private static volatile boolean[] interessado2 = {false, false}; // Para ZonaCritica2
  private static boolean zonaCritica1 = false;
  private static boolean zonaCritica2 = false;
  private static Villager villagerZonaCritica1 = null;
  private static Villager villagerZonaCritica2 = null;
  
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
  * Funcao: Movimenta o Villager executando condições adequada de acordo com sua posicao atual
  * Parametros: villager -> Objeto Villager a ser movimentado
  * Retorno: void
  *************************************************************** */
  public void movimentarVillagers(Villager villager){
    switch (villager.getPosicao()) { //Muda a movimentacao de acordo com a posicao inicial setada no atributo posicao do villager
      case 1: // movimentarVillagerDireitaBaixo
      ImageView image = villager.getVillagerImage();
    
      if(image.getLayoutY() > 560){ // Verifica a posicao do villager ate o limite da curva
        image.setLayoutY(image.getLayoutY() - villager.getVelocidade()); // Movimenta o villager no eixo Y
      } else if(image.getLayoutY() <= 560 && image.getLayoutY() > 540){// Verifica a posicao do villager do inicio ate o fim da curva
        if(controleEntradaZona1(villager)){
          break;
        }
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
        controleSaidaZona1(villager);
        image.setLayoutY(image.getLayoutY() - villager.getVelocidade());
        image.setRotate(0);
        image.setLayoutX(508);
      } else if(image.getLayoutY() <= 260 && image.getLayoutY() > 240){
        if(controleEntradaZona2(villager)){
          break;
        }
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
        controleSaidaZona2(villager);
        image.setLayoutY(image.getLayoutY() - villager.getVelocidade());
        image.setRotate(0);
        image.setLayoutX(508);
      }// Fim das verificacoes de movimentacao
      
      // If que verifica o limiar das bordas da aplicacao, resetando a posicao incial do villager quando ele ultrapassa-la
      if(image.getLayoutY() < -60){
        villager.setPosicaoIncial(villager.getPosicao());
      }// Fim do if
        break;
      case 2: // movimentarVillagerDireitaCima
      image = villager.getVillagerImage();
    
      if(image.getLayoutY() < 90){
        image.setLayoutY(image.getLayoutY() + villager.getVelocidade());
      } else if(image.getLayoutY() >= 90 && image.getLayoutY() < 110){
        if(controleEntradaZona2(villager)){
          break;
        }
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
        controleSaidaZona2(villager);
        image.setLayoutY(image.getLayoutY() + villager.getVelocidade());
        image.setRotate(180);
        image.setLayoutX(508);
      } else if(image.getLayoutY() >= 390 && image.getLayoutY() < 410){
        if(controleEntradaZona1(villager)){
          break;
        }
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
        controleSaidaZona1(villager);
        image.setLayoutY(image.getLayoutY() + villager.getVelocidade());
        image.setRotate(180);
        image.setLayoutX(508);
      }// Fim das verificacoes de movimentacao
  
      if(image.getLayoutY() > 700){
        villager.setPosicaoIncial(villager.getPosicao());
      }// Fim do if
        break;
      case 3: // movimentarVillagerEsquerdaBaixo
      image = villager.getVillagerImage();
    
      if(image.getLayoutY() > 560){
        image.setLayoutY(image.getLayoutY() - villager.getVelocidade());
      } else if(image.getLayoutY() <= 560 && image.getLayoutY() > 540){
        if(controleEntradaZona1(villager)){
          break;
        }
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
        controleSaidaZona1(villager);
        image.setLayoutY(image.getLayoutY() - villager.getVelocidade());
        image.setRotate(0);
        image.setLayoutX(460);
      } else if(image.getLayoutY() <= 260 && image.getLayoutY() > 240){
        if(controleEntradaZona2(villager)){
          break;
        }
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
        controleSaidaZona2(villager);
        image.setLayoutY(image.getLayoutY() - villager.getVelocidade());
        image.setRotate(0);
        image.setLayoutX(460);
      }// Fim das verificacoes de movimentacao
  
      if(image.getLayoutY() < -60){
        villager.setPosicaoIncial(villager.getPosicao());
      }// Fim do if
        break;
      case 4: // movimentarVillagerEsquerdaCima
      image = villager.getVillagerImage();
    
      if(image.getLayoutY() < 90){
        image.setLayoutY(image.getLayoutY() + villager.getVelocidade());
      } else if(image.getLayoutY() >= 90 && image.getLayoutY() < 110){
        if(controleEntradaZona2(villager)){
          break;
        }
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
        controleSaidaZona2(villager);
        image.setLayoutY(image.getLayoutY() + villager.getVelocidade());
        image.setRotate(180);
        image.setLayoutX(460);
      } else if(image.getLayoutY() >= 390 && image.getLayoutY() < 410){
        if(controleEntradaZona1(villager)){
          break;
        }
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
        controleSaidaZona1(villager);
        image.setLayoutY(image.getLayoutY() + villager.getVelocidade());
        image.setRotate(180);
        image.setLayoutX(460);
      }// Fim das verificacoes de movimentacao
  
      if(image.getLayoutY() > 700){
        villager.setPosicaoIncial(villager.getPosicao());
      }// Fim do if
        break;
      default:
        break;
    }// Fim do switch
  }// Fim do metodo movimentarVillagers

  public boolean controleEntradaZona1(Villager villager){
    switch (villager.getTecnicaColisao()) {
      case 1: // Variavel de travamento
        if(zonaCritica1 && villagerZonaCritica1 != null && !(villagerZonaCritica1.equals(villager))){
          return true;
        }
        zonaCritica1 = true;
        villagerZonaCritica1 = villager;
        return false;

      case 2: // Estrita Alternancia
        if(turnId1 != villager.getTurnId() && villagerZonaCritica1 != null){
          return true;
        }
        turnId1 = villager.getTurnId();
        villagerZonaCritica1 = villager;
        return false;
      
      case 3: // Solucao de Peterson
        int id = villager.getTurnId();
        interessado1[id] = true;
        outro1 = 1 - id;
        if (interessado1[outro1] && villagerZonaCritica1 != null && !(villagerZonaCritica1.equals(villager))) {
          return true;
        }
        villagerZonaCritica1 = villager;
        return false;
      default:
        return false;
    }
  }

  public void controleSaidaZona1(Villager villager){
    switch (villager.getTecnicaColisao()) {
      case 1: // Variavel de travamento
        zonaCritica1 = false;
        villagerZonaCritica1 = null;
      
      case 2: // Estrita Alternancia
        turnId1 = 1 - villager.getTurnId();
        villagerZonaCritica1 = null;

      case 3: // Solucao de Peterson
        int id = villager.getTurnId();
        interessado1[id] = false;
        villagerZonaCritica1 = null;

      default:
        break;
    }
  }

  public boolean controleEntradaZona2(Villager villager){
    switch (villager.getTecnicaColisao()) {
      case 1: // Variavel de travamento
        if(zonaCritica2 && villagerZonaCritica2 != null && !(villagerZonaCritica2.equals(villager))){
          return true;
        }
        zonaCritica2 = true;
        villagerZonaCritica2 = villager;
        return false;

      case 2: // Estrita Alternancia
        if(turnId2 != villager.getTurnId() && villagerZonaCritica2 != null){
          return true;
        }
        turnId2 = villager.getTurnId();
        villagerZonaCritica2 = villager;
        return false;

      case 3:
        int id = villager.getTurnId();
        interessado2[id] = true;
        outro2 = 1 - id;
        if (interessado2[outro2] && villagerZonaCritica2 != null && !(villagerZonaCritica2.equals(villager))) {
          return true;
        }
        villagerZonaCritica2 = villager;
        return false;
      default:
        return false;
    }
  }

  public void controleSaidaZona2(Villager villager){
    switch (villager.getTecnicaColisao()) {
      case 1: // Variavel de travamento
        zonaCritica2 = false;
        villagerZonaCritica2 = null;

      case 2: // Estrita Alternancia
        turnId2 = 1 - villager.getTurnId();
        villagerZonaCritica2 = null;
      
      case 3:
        int id = villager.getTurnId();
        interessado2[id] = false;
        villagerZonaCritica2 = null;

      default:
        break;
    }
  }

  public void resetarColisao(){
    zonaCritica1 = false;
    zonaCritica2 = false;
    villagerZonaCritica1 = null;
    villagerZonaCritica2 = null;
    turnId1 = 0;
    turnId2 = 0;
  }
}// Fim da classe MovimentoController