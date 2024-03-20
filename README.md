# SAD
P1:
La primera pràctica és un miniprojecte en el que es treballa el patró MVC, parsing de seqüències d'escape d'un emulador de terminal i execució de programes externs des de JAVA.

--------------------------------------------------------------------MVC------------------------------------------------------------------------------------

-He introduit el mode EDIT: buidar el terminal per a una millor visualitzacio).

-He implementat el mode Insert o Overwrite: mode Insert per afegir, mode Overwrite per sobreescriure.

-En el mode Overwrite es sobreescriu unicament si estas entre caracters, si et trobes al final s'afegeix. (com els editors que he vist)

-Left i right nomes serveixen per mouret entre la frase, si vols avançar en la linia buida fes espai. (com els editors que he vist)

-Les tecles UP I DOWN estan inhabilitades en el MVC (les activare en els extres)

-Les tecles INICI/FIN funcionen tal que al clicar fin dos cops el cursorPosition no es mogui

-Tindre en conte que en el OS Windows no funciona el mode RAW, es per aixo que les instruccions estan posades per a OS Linux

-Es te en compte el numero maxim de columnes del terminal

-La clase Line unicament conte l'estat del programa (linea)

-La clase TeclasEsc es una interficie ja que nomes te constants

-la clase EditableBufferedReader implementa la interficie TeclasEsc, ja que utilitza els seus atributs.

-----------------------------------------------------------------OPCIONALS-------------------------------------------------------------------------------

La primera pràctica te alguns apartats opcionals un dels quals és una calculadora bàsica amb formularis de text.

-S'estan fent encara, no disponibles
