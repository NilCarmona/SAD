public int readCharacter() {
    int caracter = super.read();
  
    // Using a do-while loop for letters
    if (caracter > 64) {
      do {
        switch (caracter) {
          case Keys.INSERT:
            caracter = Keys.xINSERT;
            break;
          case Keys.RIGHT:
            caracter = Keys.xRIGHT;
            break;
          case Keys.LEFT:
            caracter = Keys.xLEFT;
            break;
          case Keys.INICIO:
            caracter = Keys.xINICIO;
            break;
          case Keys.FIN:
            caracter = Keys.xFIN;
            break;
        }
        caracter = super.read();
      } while (caracter > 64);
    } else { // Using an if-else for numbers
      if (caracter == Keys.INSERT) {
        caracter = Keys.xINSERT;
      } else if (caracter == Keys.SUPR) {
        caracter = Keys.xSUPR;
        // Ignore the "~"
        super.read();
      }
    }
  
    return caracter;
  }
  
