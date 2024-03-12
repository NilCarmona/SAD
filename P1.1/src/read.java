Map<Integer, Integer> keyMap = new HashMap<>();
keyMap.put(Keys.INSERT, Keys.xINSERT);
keyMap.put(Keys.RIGHT, Keys.xRIGHT);
keyMap.put(Keys.LEFT, Keys.xLEFT);
keyMap.put(Keys.INICIO, Keys.xINICIO);
keyMap.put(Keys.FIN, Keys.xFIN);
keyMap.put(Keys.SUPR, Keys.xSUPR);

caracter = super.read();
if (keyMap.containsKey(caracter)) {
    caracter = keyMap.get(caracter);
}

if (caracter <= 64) { // Si leemos un número
    super.read(); // Obviamos el "~"
}

return caracter;
  
