#!/bin/bash
clear


#!/bin/bash

afficher_titre() {
   
    echo "          * * * * * * * * * * * * * * * * * * * * * * * * * * * * *"
    echo "          *                                                       *"
    echo "          *                Application L-Systèmes                 *"
    echo "          *                                                       *"
    echo "          * * * * * * * * * * * * * * * * * * * * * * * * * * * * *"
    echo ""
}


afficher_titre1() {
    clear
   echo "          * * * * * * * * * * * * * * * * * * * * * * * * * * * * *";
   echo "          *                Application L-Systèmes                 *";
   echo "          *                                                       *";
   echo "          *                  -- Mode console --                   *";
   echo "          * * * * * * * * * * * * * * * * * * * * * * * * * * * * *";
   echo "";
}


afficher_titre2() {
    clear
       echo "          * * * * * * * * * * * * * * * * * * * * * * * * * * * * *";
       echo "          *                Application L-Systèmes                 *";
       echo "          *                  -- Mode console --                   *";
       echo "          *                                                       *";
       echo "          * * * * * * * * * * * * * * * * * * * * * * * * * * * * *";
       echo "          *                                                       *";
       echo "          *               --Voir des modèles prêts--              *";
       echo "          *                                                       *";
       echo "          * * * * * * * * * * * * * * * * * * * * * * * * * * * * *";
       echo ""; 
}




afficher_titre3() {
       clear
       echo "          * * * * * * * * * * * * * * * * * * * * * * * * * * * * *";
       echo "          *                Application L-Systèmes                 *";
       echo "          *                  -- Mode console --                   *";
       echo "          *                                                       *";
       echo "          * * * * * * * * * * * * * * * * * * * * * * * * * * * * *";
       echo "          *                                                       *";
       echo "          *              --Personnaliser mon modèles--            *";
       echo "          *                                                       *";
       echo "          * * * * * * * * * * * * * * * * * * * * * * * * * * * * *";
       echo "";
}


afficher_titre4() {
    clear
   echo "          * * * * * * * * * * * * * * * * * * * * * * * * * * * * *";
   echo "          *                Application L-Systèmes                 *";
   echo "          *                                                       *";
   echo "          *                    -- Mode GUI --                     *";
   echo "          * * * * * * * * * * * * * * * * * * * * * * * * * * * * *";
   echo "";
}

case "$(uname -s)" in
    CYGWIN*|MINGW32*|MSYS*|MINGW*)
        classPathSeparator=";"
        OS="Windows"
        ;;
    Darwin*)
        classPathSeparator=":"
        OS="Mac"
        ;;
    Linux*)
        classPathSeparator=":"
        OS="Linux"
        ;;
    *)
        echo "Système d'exploitation non pris en charge."
        exit 1
        ;;
esac

joglPath="../lib/jogl_librairies/jogl"
jomlPath="../lib/joml"
junitPath="../lib/JUnit"
mockitoPath="../lib/mockito"
buildPath="../build"

CLASSPATH="${buildPath}${classPathSeparator}${jomlPath}/*${classPathSeparator}${junitPath}/*${classPathSeparator}${mockitoPath}/*"

CLASSPATH="${CLASSPATH}${classPathSeparator}${joglPath}/gluegen-rt.jar${classPathSeparator}${joglPath}/jogl-all.jar"

case $OS in
    Windows)
        CLASSPATH="${CLASSPATH}${classPathSeparator}${joglPath}_windows/*"
        ;;
    Mac)
        CLASSPATH="${CLASSPATH}${classPathSeparator}${joglPath}_mac/*"
        ;;
    Linux)
        CLASSPATH="${CLASSPATH}${classPathSeparator}${joglPath}_linux/*"
        ;;
esac

echo "Compilation des sources..."
javac -Xlint:unchecked -d $buildPath -cp "$CLASSPATH" ../src/mainPackage/*.java ../src/model/*.java ../src/controle/*.java ../src/view/*.java ../src/util/*.java ../../tests/modelTests/*.java ../../tests/mainPackageTest/*.java

if [ $? -eq 0 ]; then
    echo "Compilation réussie."
else
    echo "Erreur de compilation."
    exit 1
fi


executer_tests() {
    echo -e "\nExécution des tests unitaires...\n"

    CLASSPATH="${buildPath}${classPathSeparator}${jomlPath}/*${classPathSeparator}${junitPath}/*${classPathSeparator}${mockitoPath}/*"
    CLASSPATH="${CLASSPATH}${classPathSeparator}${joglPath}/gluegen-rt.jar${classPathSeparator}${joglPath}/jogl-all.jar"

    case $OS in
        Windows)
            CLASSPATH="${CLASSPATH}${classPathSeparator}${joglPath}_windows/*"
            ;;
        Mac)
            CLASSPATH="${CLASSPATH}${classPathSeparator}${joglPath}_mac/*"
            ;;
        Linux)
            CLASSPATH="${CLASSPATH}${classPathSeparator}${joglPath}_linux/*"
            ;;
    esac

    declare -a directories=("mainPackageTest" "modelTests")


    for dir in "${directories[@]}"; do
        echo -e "\n--- Exécution des tests dans $dir ---\n"
        for testClass in "../build/$dir"/*.class; do
            # Convertit le chemin de fichier en un nom de classe complet
            fullClassName=$(echo "$testClass" | sed -e 's|../build/||' -e 's|/|.|g' -e 's|.class$||')

            echo -e "\nExécution de $fullClassName\n"

            java -cp "$CLASSPATH" org.junit.runner.JUnitCore "$fullClassName"
        done
    done

    echo -e "\nTests terminés.\n"
    read -p "===> Appuyez sur n'importe quelle touche pour revenir." -n 1
}



voir_documentation() {
    echo "Ouverture de la documentation..."
    
    case $OS in
        "Linux"|"Mac")

            if command -v xdg-open &> /dev/null; then
                xdg-open "../javadoc/index.html"
            elif command -v open &> /dev/null; then
                open "../javadoc/index.html"
            else
                echo "Aucun outil pour ouvrir la documentation n'a été trouvé."
                return 1
            fi
            ;;
        "Windows")

            cmd /c start "../javadoc/index.html"
            ;;
        *)
            echo "Système d'exploitation non pris en charge pour la visualisation de la documentation."
            return 1
            ;;
    esac

    echo "Appuyez sur n'importe quelle touche pour revenir."
    read
}



traiter_mode_gui() {
 
    afficher_titre4
    echo "Lancement du mode GUI..."
    
    java -cp "$CLASSPATH" mainPackage.MainGUI
    
    echo "===> Appuyez sur n'importe quelle touche pour revenir."
    read
}





personnaliser_modele() {
  afficher_titre3

  valider_axiome
  valider_nombre "Combien de règles souhaitez-vous définir ?"
  n=$num
  valider_regles $n
  valider_nombre "Entrez le nombre d'itérations :"
  nbIt=$num
  valider_angle
  valider_type_personnalisation


 afficher_recapitulatif # Appel de la fonction de récapitulatif ici


  echo -e "\nAvez-vous besoin de changer quelque chose dans ces données ? (O/N)"
  read choix
  choix=$(echo "$choix" | tr '[:lower:]' '[:upper:]')

  if [[ "$choix" == "O" ]]; then
    modifier_donnees
fi

afficher_recapitulatif

rulesStr=$(IFS=,; echo "${regles[*]}")

java -cp "$CLASSPATH" mainPackage.MainConsole "$type_personnalisation" "$axiom" "$rulesStr" "$nbIt" "$angle"

echo "===> Appuyez sur n'importe quelle touche pour revenir."
read
}





menu_principal() {
    afficher_titre
    echo "1. Mode Console"
    echo "2. Mode GUI"
    echo "3. Exécuter les tests unitaires"
    echo "4. Voir la documentation"
    echo "5. Quitter"
    echo -n "Choisissez une option: "
}
menu_console() {
    afficher_titre1
    echo "1. Personnaliser mon modèle"
    echo "2. Retour"
    echo -n "Choisissez une option: "
}

traiter_mode_console() {
    while true; do
        menu_console
        read choix
        case "$choix" in
            1) personnaliser_modele ;;
            2) break ;;
            *) echo "Option invalide, veuillez réessayer." ;;
        esac
    done
}





valider_axiome() {
  echo -e "\nEntrez l'axiome (une chaîne de caractères) :"
  read axiom
  axiom=$(echo "$axiom" | tr '[:lower:]' '[:upper:]') 

  while [[ -z "$axiom" ]] || [[ "$axiom" =~ ^[+-]?[0-9]+([.][0-9]+)?$ ]]; do
    if [[ -z "$axiom" ]]; then
      echo "L'axiome doit être une chaîne de caractères non vide. Veuillez réessayer :"
    else
      echo "L'axiome ne doit pas être un numéro. Veuillez entrer une chaîne de caractères :"
    fi
    read axiom
    axiom=$(echo "$axiom" | tr '[:lower:]' '[:upper:]') 
  done
}



valider_nombre() {
  echo -e "\n$1"
  read num

  while ! [[ "$num" =~ ^[0-9]+$ ]] || [ "$num" -le 0 ]; do
    echo "Veuillez entrer un nombre entier positif :"
    read num
  done
}




valider_angle() {
  echo -e "\nEntrez l'angle de rotation :"
  read angle

  while ! [[ "$angle" =~ ^-?[0-9]+([.][0-9]+)?$ ]]; do
    echo "L'angle de rotation doit être un nombre. Veuillez réessayer :"
    read angle
  done
}




valider_regles() {
  n=$1
  regles=()

  echo -e "\nEntrez les règles sous la forme 'symbole:traduction' pour chacune :"
  for ((i=1; i<=n; i++)); do
    while :; do
      echo -e "\nRègle $i (format attendu 'symbole:traduction') :"
      read regle
      regle=$(echo "$regle" | tr '[:lower:]' '[:upper:]')

      # Modifier l'expression régulière pour accepter n'importe quel caractère après le symbole
      if [[ "$regle" =~ ^[A-Z]:.+$ ]]; then
        regles+=("$regle")
        break 
      else
        echo "La règle ne respecte pas le format attendu. Veuillez réessayer."
      fi
    done
  done
}



valider_type_personnalisation() {
  echo "Choisissez le type de personnalisation :"
  echo "1. 2D"
  echo "2. 3D"
  echo -n "Votre choix : "
  read type_personnalisation
  type_personnalisation=$(echo "$type_personnalisation" | tr '[:lower:]' '[:upper:]') 

  case $type_personnalisation in
    "1"|"2D") type_personnalisation="DEUX_D" ;;
    "2"|"3D") type_personnalisation="TROIS_D" ;;
    *) echo "Choix invalide"
       valider_type_personnalisation ;;
  esac
}



modifier_donnees() {
  echo -e "\nQuelle donnée souhaitez-vous modifier ?"
  echo "1. Axiome"
  echo "2. Règles"
  echo "3. Nombre d'itérations"
  echo "4. Angle de rotation"
  echo "5. Type de personnalisation"
  echo "6. Terminer les modifications"
  read choix_modif

  case $choix_modif in
    1)
      valider_axiome ;;
    2)
      valider_nombre "Combien de règles souhaitez-vous définir ?"
      n=$num
      valider_regles $n ;;
    3)
      valider_nombre "Entrez le nombre d'itérations :"
      nbIt=$num ;;
    4)
      valider_angle ;;
    5)
      valider_type_personnalisation ;;
    6)
      return ;;
    *)
      echo "Choix invalide. Veuillez réessayer."
      modifier_donnees ;;
  esac

  echo -e "\nVoulez-vous modifier autre chose ? (O/N)"
  read choix_continue
  choix_continue=$(echo "$choix_continue" | tr '[:lower:]' '[:upper:]')

  if [[ "$choix_continue" == "O" ]]; then
    modifier_donnees
  fi
}




afficher_recapitulatif() {
  clear
  echo "          * * * * * * * * * * * * * * * * * * * * * * * * * * * * *"
  echo "          *                Application L-Systèmes                 *"
  echo "          *                  -- Mode console --                   *"
  echo "          *                                                       *"
  echo "          *              --Personnaliser mon modèles--            *";
  echo "          *                                                       *"
  echo "          *                                                       *"
  echo "          * * * * * * * * * * * * * * * * * * * * * * * * * * * * *"
  echo "          *                                                       *"
  echo "          *              Récapitulatif de vos choix               *"
  echo "          *                                                       *"
  echo "          * * * * * * * * * * * * * * * * * * * * * * * * * * * * *";
  echo "          *     					          *";
  echo "          *     Axiome: $axiom                                     ";
  echo "          *     Nombre de règles: $n                               ";
  echo "          *     Règles: ${regles[*]}                               "; 
  echo "          *     Nombre d'itérations: $nbIt                         ";  
  echo "          *     Angle de rotation: $angle                          ";    
  echo "          *     Type de personnalisation: $type_personnalisation   ";   	          
  echo "          *                                                       *";
  echo "          * * * * * * * * * * * * * * * * * * * * * * * * * * * * *";
  echo ""
}







while true; do
    clear
    menu_principal
    read choix
    case "$choix" in
        1) traiter_mode_console ;;
        2) traiter_mode_gui ;;
        3) executer_tests ;;
        4) voir_documentation ;;
        5) echo "Au revoir !" ; exit 0 ;;
        *) echo "Option invalide, veuillez réessayer." ;;
    esac
done



