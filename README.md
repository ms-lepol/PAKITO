Rendu de projet - readme de la version console
de SAINT GILLES Tristan & MAILLARD-SALIN Paul

------FONCTIONNALITES : 

Architecture MVC - Fait et testé (compilation et execution OK)

Classes abstraites et Interfaces : Occupant (Piece), Mobile, Fixed, Questionnable et Moveable - Tout OK

Personnages : - Hunter (implémenté et testé) - Tout OK
			  - Wise (pas implémenté)
			  -	Cheater (pas implémenté)

Objets Fixes : - Trésor - Tout OK (condition de victoire)
			   - Cartes - Implémenté
			   - Bord - Tout OK (rebond)
			   - Glue - Tout OK (ralentissement de 1 tour)
			   - Tool - Tout OK (Echelle qui permet de passer par dessus un mur)
			   - Stone - OK ('Glissement' le long du mur) -- Bug avec certaines conditions qui fait boucler le Hunter

Grille : - Génération aléatoire de la grille : - Objets divers (Trésor, Glue, etc...) - Tout OK
											   - Génération des Roadmap - implémenté mais bug à fix
											   - Génération aléatoire des murs selon les contraintes du sujet - Tout OK
											   
Position : - Classe d'objet pour la key de la grille, plusieurs fonctions utiles toutes testées et commentées
									
Superpositions - Tout ok									

------CONTRAINTES DE CODAGES : 

Interface Questionnable et méthode process sur toutes les pièces - Tout OK
Interface Moveable et méthodes - Tout OK


Les méthodes sont toutes commentées en JAVADOC
