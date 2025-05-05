/* ***************************************************************
* Autor............: Fernando Nardes Ferreira Neto
* Matricula........: 202410403
* Inicio...........: 14/03/2025
* Ultima alteracao.: 05/04/2025
* Nome.............: Villager.java
* Funcao...........: Classe modelo do objeto Villager, no qual sera a representacao do trem na aplicacao
*                    e a execucao da thread, realizacao do movimento e verificacao de colisao.
*************************************************************** */
package model;

import javafx.application.Platform;
import javafx.scene.control.Slider;
import javafx.scene.image.ImageView;

public class Villager extends Thread{
  private ImageView villagerImagem; // Representa a imagem do villager na interface grafica
  private volatile double velocidade; // Velocidade padrao do villager
  private int posicao; // Posicao inicial do villager
  private Slider slider; // Slider de velocidade do villager
  private volatile boolean running = true; // Condicao para o while do run
  private static int tecnicaColisao; // Tecnica de colisao selecionada
  private int id; // ID do objeto de acordo com o lado

  // Variaveis para o controle de colisao
  private static volatile int trava1 = 0;
  private static volatile int trava2 = 0;
  private static volatile int turn1 = 0;
  private static volatile int turn2 = 0;
  private static volatile boolean[] flag1 = {false, false};
  private static volatile boolean[] flag2 = {false, false};
  private boolean zonaCritica1 = false;
  private boolean zonaCritica2 = false;

  /* ***************************************************************
  * Metodo: Villager (Construtor)
  * Funcao: Inicializa um objeto Villager com a imagem e a posicao especificada
  * Parametros: imageVillager -> Objeto ImageView representando a imagem do villager
  *             posicao -> Inteiro indicando a posicao inicial do villager
  * Retorno: Instancia de um objeto Villager
  *************************************************************** */
  public Villager(ImageView imageVillager, int posicao, Slider slider, int tecnica){
    this.villagerImagem = imageVillager;
    this.posicao = posicao;
    this.slider = slider;
    tecnicaColisao = tecnica;
    setPosicaoInicial(posicao); // Chama metodo para definir a posicao inicial
    if(posicao == 1 || posicao == 2){ // Verifica a orientacao do villager e seta um id correspondente
      id = 0;
    } else {
      id = 1;
    }
  }// Fim do construtor Villager

  /* ***************************************************************
  * Metodo: setPosicaoInicial
  * Funcao: Define a posicao inicial do villager na tela baseado no numero informado
  * Parametros: posicao -> Inteiro que define a posicao do villager na tela
  * Retorno: void
  *************************************************************** */
  public void setPosicaoInicial(int posicao){
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
    * Metodo: run
    * Funcao: Metodo chamado a cada execucao da thread, executando tudo que esta contido, movimentando o Villager
    * Parametros: nenhum
    * Retorno: void
    *************************************************************** */
  @Override
  public void run(){
    while(running){
      Platform.runLater(() -> {
        this.mover();
      });// Fim do Platform.runLater
      try {
        Thread.sleep(10); // Pausa de 10ms para evitar dominacao de uma thread e diminuir a velocidade;
      } catch (InterruptedException e) {
          e.printStackTrace();
      }
    }
  }

  /* ***************************************************************
    * Metodo: parar
    * Funcao: Metodo que muda a condicao do while da thread, terminando sua execucao, e reseta o sistema de colisao.
    * Parametros: nenhum
    * Retorno: void
    *************************************************************** */
  public void parar(){
    resetarColisao();
    running = false;
  }

  /* ***************************************************************
  * Metodo: mover
  * Funcao: Metodo que realiza as comparacoes da posicao do objeto e faz com que ele se mova
  * Parametros: nenhum
  * Retorno: void
  *************************************************************** */
  public void mover(){
    Villager villager = this;
    ImageView image = villager.getVillagerImage();
    villager.setVelocidadeSlider();

    switch (villager.getPosicao()) { //Muda a movimentacao de acordo com a posicao inicial setada no atributo posicao do villager
      case 1: // movimentarVillagerDireitaBaixo
      if(image.getLayoutY() > 560){ // Verifica a posicao do villager ate o limite da curva
        image.setLayoutY(image.getLayoutY() - villager.getVelocidade()); // Movimenta o villager no eixo Y
      } else if(image.getLayoutY() <= 560 && image.getLayoutY() > 540){// Verifica a posicao do villager do inicio ate o fim da curva
        // INICIO ZONA CRITICA 1
        if(controleEntradaZona1() && !(zonaCritica1)){
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
        // FIM ZONA CRITICA 1
      } else if(image.getLayoutY() <= 390 && image.getLayoutY() > 260){
        controleSaidaZona1();
        image.setLayoutY(image.getLayoutY() - villager.getVelocidade());
        image.setRotate(0);
        image.setLayoutX(508);
        // INICIO ZONA CRITICA 2
      } else if(image.getLayoutY() <= 260 && image.getLayoutY() > 240){
        if(controleEntradaZona2() && !(zonaCritica2)){
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
        // FIM ZONA CRITICA 2
      } else if(image.getLayoutY() <= 90){
        controleSaidaZona2();
        image.setLayoutY(image.getLayoutY() - villager.getVelocidade());
        image.setRotate(0);
        image.setLayoutX(508);
      }// Fim das verificacoes de movimentacao
      
      // If que verifica o limiar das bordas da aplicacao, resetando a posicao incial do villager quando ele ultrapassa-la
      if(image.getLayoutY() < -60){
        villager.setPosicaoInicial(villager.getPosicao());
      }// Fim do if
        break;

      case 2: // movimentarVillagerDireitaCima
      if(image.getLayoutY() < 90){
        image.setLayoutY(image.getLayoutY() + villager.getVelocidade());
      } else if(image.getLayoutY() >= 90 && image.getLayoutY() < 110){
        if(controleEntradaZona2() && !(zonaCritica2)){
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
        controleSaidaZona2();
        image.setLayoutY(image.getLayoutY() + villager.getVelocidade());
        image.setRotate(180);
        image.setLayoutX(508);
      } else if(image.getLayoutY() >= 390 && image.getLayoutY() < 410){
        if(controleEntradaZona1() && !(zonaCritica1)){
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
        controleSaidaZona1();
        image.setLayoutY(image.getLayoutY() + villager.getVelocidade());
        image.setRotate(180);
        image.setLayoutX(508);
      }// Fim das verificacoes de movimentacao
  
      if(image.getLayoutY() > 700){
        villager.setPosicaoInicial(villager.getPosicao());
      }// Fim do if
        break;

      case 3: // movimentarVillagerEsquerdaBaixo
      if(image.getLayoutY() > 560){
        image.setLayoutY(image.getLayoutY() - villager.getVelocidade());
      } else if(image.getLayoutY() <= 560 && image.getLayoutY() > 540){
        if(controleEntradaZona1() && !(zonaCritica1)){
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
        controleSaidaZona1();
        image.setLayoutY(image.getLayoutY() - villager.getVelocidade());
        image.setRotate(0);
        image.setLayoutX(460);
      } else if(image.getLayoutY() <= 260 && image.getLayoutY() > 240){
        if(controleEntradaZona2() && !(zonaCritica2)){
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
        controleSaidaZona2();
        image.setLayoutY(image.getLayoutY() - villager.getVelocidade());
        image.setRotate(0);
        image.setLayoutX(460);
      }// Fim das verificacoes de movimentacao
  
      if(image.getLayoutY() < -60){
        villager.setPosicaoInicial(villager.getPosicao());
      }// Fim do if
        break;
      case 4: // movimentarVillagerEsquerdaCima
      image = villager.getVillagerImage();

      
      if(image.getLayoutY() < 90){
        image.setLayoutY(image.getLayoutY() + villager.getVelocidade());
      } else if(image.getLayoutY() >= 90 && image.getLayoutY() < 110){
        if(controleEntradaZona2() && !(zonaCritica2)){
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
        controleSaidaZona2();
        image.setLayoutY(image.getLayoutY() + villager.getVelocidade());
        image.setRotate(180);
        image.setLayoutX(460);
      } else if(image.getLayoutY() >= 390 && image.getLayoutY() < 410){
        if(controleEntradaZona1() && !(zonaCritica1)){
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
        controleSaidaZona1();
        image.setLayoutY(image.getLayoutY() + villager.getVelocidade());
        image.setRotate(180);
        image.setLayoutX(460);
      }// Fim das verificacoes de movimentacao
  
      if(image.getLayoutY() > 700){
        villager.setPosicaoInicial(villager.getPosicao());
      }// Fim do if
        break;
      default:
        break;
    }// Fim do switch
  }// Fim do metodo mover

  /* ***************************************************************
  * Metodo: controleEntradaZona1
  * Funcao: Metodo que controla a zona1, verificando a colisao de acordo com a tecnica selecionada
  * Parametros: nenhum
  * Retorno: boolean
  *************************************************************** */
  public boolean controleEntradaZona1(){
    switch(tecnicaColisao){
      case 1: // Variavel de travamento
        if(trava1 == 0){
          trava1 = 1;
          zonaCritica1 = true;
          return false;
        }
        return true;

      case 2:
        if(turn1 != this.getTurnId()){
          zonaCritica1 = true;
          return false;
        }
        return true;
      case 3:
        flag1[this.getTurnId()] = true;
        int outro1 = 1 - this.getTurnId();
        turn1 = outro1;
        if(flag1[outro1] && turn1 == outro1){
          return true;
        }
        zonaCritica1 = true;
        return false;
      default:
        return false;
    }
  }

  /* ***************************************************************
  * Metodo: controleSaidaZona1
  * Funcao: Metodo que libera a zona1, permitindo que outro objeto a acesse
  * Parametros: nenhum
  * Retorno: void
  *************************************************************** */
  public void controleSaidaZona1(){
    switch(tecnicaColisao){
      case 1: // Variavel de travamento
        trava1 = 0;
        zonaCritica1 = false;
        break;
      case 2:
        turn1 = this.getTurnId();
        zonaCritica1 = false;
      case 3:
        flag1[this.getTurnId()] = false;
        zonaCritica1 = false;
      default:
        break;
    }
  }

  /* ***************************************************************
  * Metodo: controleEntradaZona2
  * Funcao: Metodo que controla a zona2, verificando a colisao de acordo com a tecnica selecionada
  * Parametros: nenhum
  * Retorno: boolean
  *************************************************************** */
  public boolean controleEntradaZona2(){
    switch(tecnicaColisao){
      case 1: // Variavel de travamento
        if(trava2 == 0){
          trava2 = 1;
          zonaCritica2 = true;
          return false;
        }
        return true;
      case 2:
        if(turn2 != this.getTurnId()){
          zonaCritica2 = true;
          return false;
        }
        return true;
      case 3:
        flag2[this.getTurnId()] = true;
        int outro2 = 1 - this.getTurnId();
        turn2 = outro2;
        if(flag2[outro2] && turn2 == outro2){
          return true;
        }
        zonaCritica2 = true;
        return false;
      default:
        return false;
    }
  }

  /* ***************************************************************
  * Metodo: controleSaidaZona2
  * Funcao: Metodo que libera a zona2, permitindo que outro objeto a acesse
  * Parametros: nenhum
  * Retorno: void
  *************************************************************** */
  public void controleSaidaZona2(){
    switch(tecnicaColisao){
      case 1: // Variavel de travamento
        trava2 = 0;
        zonaCritica2 = false;
        break;
      case 2:
        turn2 = this.getTurnId();
        zonaCritica2 = false;
        break;
      case 3:
        flag2[this.getTurnId()] = false;
        zonaCritica2 = false;
        break;
      default:
        break;
    }
  }

  /* ***************************************************************
  * Metodo: resetarColisao
  * Funcao: Reseta todos os parametros utilizados nas tecnicas de colisao
  * Parametros: nenhum
  * Retorno: nenhum
  *************************************************************** */
  public void resetarColisao(){
    trava1 = 0;
    trava2 = 0;
    turn1 = 0;
    turn2 = 0;
    flag1[0] = false;
    flag1[1] = false;
    flag2[0] = false;
    flag2[1] = false;
    zonaCritica1 = false; 
    zonaCritica2 = false;
  }

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
  * Metodo: setPosicao
  * Funcao: Seta a posicao atual do villager
  * Parametros: posicao
  * Retorno: void
  *************************************************************** */
  public void setPosicao(int posicao){
    this.posicao = posicao;
  }

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
  * Metodo: setVelocidade
  * Funcao: Seta a velocidade do villager
  * Parametros: Velocidade
  * Retorno: void
  *************************************************************** */
  public void setVelocidade(double velocidade){
    this.velocidade = velocidade;
  }// Fim do metodo setVelocidade

  /* ***************************************************************
  * Metodo: setVelocidadeSlider
  * Funcao: Seta a velocidade do villager de acordo com o valor do slider
  * Parametros: Velocidade
  * Retorno: void
  *************************************************************** */
  public void setVelocidadeSlider(){
    this.velocidade = slider.getValue();
  }// Fim do metodo setVelocidadeSlider
  
  /* ***************************************************************
  * Metodo: getTecnicaColisao
  * Funcao: Retorna a tecnica de colisao atual do villager
  * Parametros: Nenhum
  * Retorno: int -> Tecnica de colisao
  *************************************************************** */
  public int getTecnicaColisao(){
    return tecnicaColisao;
  }// Fim do metodo getTecnicaColisao
  /* ***************************************************************
  * Metodo: getTurnId
  * Funcao: Retorna o id do villager
  * Parametros: Nenhum
  * Retorno: int -> Id
  *************************************************************** */
  public int getTurnId(){
    return id;
  }// Fim do metodo getTurnId
}// Fim da classe Villager