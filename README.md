

## RASSURANCE

Ce programme, écrit intégralement en Java, permet de traiter les réclamations d'un client pour un mois donné.
La réclamation est fournie en format Json et contient:

+ Le dossier du client (contient la lettre du contrat suivi du numéro du client).
+ Le mois pour lequel il fait sa réclamation.
+ Les réclamations pour les différents soins reçus durant le mois:
    - Le numéro du soin.
    - La date à laquelle le soin a été prodigué.
    - Le montant réclamé.

Le programme vérifie que les informations sont valides, fait le calcul et génère un fichier Json avec les informations sur le client, le soin et bien évidemment le montant du remboursement.
Le programme accumule les statistiques lors de chaque exécution. Il comptabilise le nombre de réclamations traitées, le nombre de demandes rejetées, ainsi que le nombre de soins déclarés pour chaque type de soin.

## Utilisation du logiciel
Il y a deux façons d'utiliser ce logiciel:
1.  Dans un environnement de développement intégré:
    + Vous devriez télécharger ce dépôt et l'ouvrir dans votre IDE en tant que projet.
2.  En ligne de commande:
    + Un jar exécutable est fourni à la racine du projet.
    + Vous devez vous mettre dans le répertoire qui contient le jar et taper une des commandes suivantes:
#
1. Pour faire la demande de remboursement:
```bash 
java -jar Rassurance.jar inputfile.json refunds.json
```
2. Pour afficher les statistiques:
```bash 
java -jar Rassurance.jar -S
```
3. Pour réinitialiser les statistiques:
```bash
java -jar Rassurance.jar -SR
```
### Exemples de fichier d'entrée et de sortie

**inputFile.json**

```json
{
    "dossier": "A100323",
    "mois": "2021-01",
    "reclamations": [
        {
            "soin": 175,
            "date": "2021-01-11",
            "montant": "130.00$"
        },
        {
            "soin": 175,
            "date": "2021-01-14",
            "montant": "130.00$"
        },
        {
            "soin": 175,
            "date": "2021-01-15",
            "montant": "130.00$"
        },
        {
            "soin": 175,
            "date": "2021-01-17",
            "montant": "130.00$"
        }
    ]
}
```

**outputFile.json**

```json
{
  "dossier": "A100323",
  "mois": "2020-02",
  "remboursements": [
    {
      "soin": 175,
      "date": "2021-01-11",
      "montant": "65.00$"
    },
    {
      "soin": 175,
      "date": "2021-01-14",
      "montant": "65.00$"
    },
    {
      "soin": 175,
      "date": "2021-01-15",
      "montant": "65.00$"
    },
    {
      "soin": 175,
      "date": "2021-01-17",
      "montant": "5.00$"
    }
  ],
  "total": "200.00$"
}
```

**statistiques.json**

```json
{
   "nombre de réclamations valides traitées": 78,
   "nombre de réclamations rejetées": 3,
   "nombre de soins déclarés pour chaque type de soin":    {
      "massothérapie": 1,
      "ostéopathie": 18,
      "kinésithérapie": 1,
      "médecin généraliste privé": 1,
      "psychologie individuelle": 18,
      "soins dentaires": 24,
      "naturopathie / acupuncture": 1,
      "chiropractie": 6,
      "physiothérapie": 1,
      "orthophonie / ergothérapie": 7
   }
}
```

### Contrats et soins remboursés

Le programme calcule les remboursements selon ce tableau:

| Numéro de soin | Maximum mensuel |     Catégorie de soin     |  A  |       B      |  C  |       D       |       E      |
| -------------- | --------------- | ------------------------- | --- | ------------ | --- | ------------- | ------------ |
|       0        |        -        |       Massothérapie       | 25% |  50% max 40$ | 90% | 100% max  85$ |     15%      |
|      100       |       250$      |        Ostéopathie        | 35% |  50% max 50$ | 95% | 100% max  75$ |     25%      |
|      150       |        -        |       Kinésithérapie      |  0% |       0%     | 85% | 100% max 150$ |     15%      |
|      175       |       200$      | Médecin généraliste privé | 50% |      75%     | 90% |      95%      |  25% max 20$ |
|      200       |       250$      |  Psychologie individuelle | 25% |     100%     | 90% | 100% max 100$ |     12%      |
|  \[300..399\]  |        -        |      Soins dentaires      |  0% |      50%     | 90% |     100%      |     60%      |
|      400       |        -        | Naturopathie, acuponcture |  0% |       0%     | 90% | 100% max  65$ |  25% max 15$ |
|      500       |       150$      |        Chiropraxie        | 25% |  50% max 50$ | 90% |     100%      |  30% max 20$ |
|      600       |       300$      |       Physiothérapie      | 40% |     100%     | 75% | 100% max 100$ |     15%      |
|      700       |        -        | Orthophonie, ergothérapie |  0% |      70%     | 90% | 100% max  90$ |     22%      |

Il existe un montant maximum mensuel pour le remboursement de certains soins, tel qu'indiqué dans le tableau ci-dessus.

### Dépendances

Ce projet est construit avec l'outil de gestion et d'automatisation de production des projets logiciels Java: Maven.
Toutes les dépendances et les plugins utilisés dans le cadre de ce projet sans dans le fichier [pom.xml](https://github.com/Saff-Buraq-Dev/Rassurance/blob/main/pom.xml).




