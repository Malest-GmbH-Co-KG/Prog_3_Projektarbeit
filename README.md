# Prog_3_Projektarbeit

# **IDE Requirements:**
+ java 21
+ Maven Version 3.19.15

---

# Repository klonen

+ Navigieren Sie auf GitHub zur Hauptseite des Repositorys.
+ Klicke oberhalb der Liste der Dateien auf <> **Code**.
<br> <img width="356" alt="Image" src="https://github.com/user-attachments/assets/572d9893-81e9-4c61-b413-59a7a54320a7" />
+ Kopiere die URL für das Repository.
<br> <img width="305" alt="Image" src="https://github.com/user-attachments/assets/cede2f5e-27ca-403f-a09b-c29ee3dae182" />
+ Öffne Git Bash.
+ Ändere das aktuelle Arbeitsverzeichnis zum Speicherort, in dem Du das geklonte Verzeichnis haben willst.
+ Gib git clone ein, und füge dann die zuvor kopierte URL ein.
<br> ![Image](https://github.com/user-attachments/assets/1a1f13ee-1367-4f7b-b517-ebd36d456131)
+ Drücke die EINGABETASTE, um den lokalen Klon zu erstellen.

Für weitere informationen besuche https://docs.github.com/de/repositories/creating-and-managing-repositories/cloning-a-repository

---

# Quick Start Anleitung: 

+ IDE Terminal öffnen
<br>![Image](https://github.com/user-attachments/assets/32ba5036-5775-4de7-bcb4-bab8614c4ae9)
+ Command  | mvn javafx:run | ausführen
<br>![Image](https://github.com/user-attachments/assets/469880bf-a46e-4233-a2b6-3cbd2bcdb9b5)

---

# Fenster Erläuterungen
## **Loginfenster**
+ [Anmelden](#3-mit-bestehendem-nutzer-anmelden)
+ [Registrieren](#1-nutzer-anlegen)
+ [Daten updaten](#2-nutzer-updaten)

<br>![Image](https://github.com/user-attachments/assets/2a05c892-e554-405c-884f-6bb5b7b4cd25)

## **Budgetfenster**
+ Liste aller Budgets:
  <br> -> Hier werden alle angelegten Budgets sowie diejenigen angezeigt, an denen man beteiligt ist.
  <br> -> Jedes Budget besteht aus dem Budgetnamen, der Budget-ID und dem verfügbaren Betrag.
+ [Budgetoptionen](#budgetoptionen)

<br>![Image](https://github.com/user-attachments/assets/413a558f-37b8-485e-82bd-ef11b9f86ee6)

## **Transaktionsfenster**
+ **Liste aller Transaktionen:**
  <br> ->  Hier werden alle getätigten Transaktionen angezeigt.
  <br> -> Eine Transaktion enthält folgende Informationen:
  <br> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; + **Nutzer**, der die Transaktion durchgeführt hat
  <br> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; + **Name**, **ID** und **Betrag** der Transaktion
  <br> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; + **Datum**, an dem die Transaktion eingetragen wurde
+ **Nutzer im Budget:**
  <br> -> Liste aller Nutzer, die im Budget mitwirken.
+ [Transaktionsoptionen](#transaktionsoptionen)
+ **Beschreibungsfeld:**
  <br> ->Hier werden die Beschreibungen der Transaktionen angezeigt
+ **Übersicht der Transaktionen:**
   <br> -> **Budgetsumme:** Die Höhe des anfänglichen Budgets
   <br> -> **Summe aller Transaktionen:** Der Gesamtbetrag der ausgegeben wurde
   <br> -> **Restsumme:** Das noch zur verfügung stehende Budget
   <br> ->  Das **Kuchendiagramm** stellt die **Budgetsumme** und die **Restsumme** visuell sowie prozentual dar.
  
<br>![Image](https://github.com/user-attachments/assets/2b6330b7-9e8f-4012-90d1-5e4b69651652)

---

# Program Walktrough

## **Anmeldeoptionen:**

### 1. Nutzer anlegen:

+ Nutzername eingeben
+ Vorname eingeben
+ Nachname eingeben
+ Passwort eingeben
+ Auf Nutzer erstellen Button drücken
<br> ![Image](https://github.com/user-attachments/assets/d19a7c60-dd34-41e9-8edf-eee8a38b5cd2)

### 2. Nutzer updaten:

+ Alter Nutzername eingeben
+ Neuer Nutzername eingeben
+ Auf Jetzt Nutzer updaten Button drücken
<br> ![Image](https://github.com/user-attachments/assets/c48ece43-06c9-4e99-8890-a774fa24fea1)

### 3. Mit bestehendem Nutzer anmelden:

+ Nutzername eingeben
+ Passwort eingeben
+ Auf Einloggen Button drücken
<br> ![Image](https://github.com/user-attachments/assets/9306d793-6795-4197-9d21-d98e60bee844)

## **Budgetoptionen:**

### 1. Budget erstellen:

+ Budget Name eingeben
+ Budgetsumme eingeben
+ Auf Budget hinzufügen Button drücken
<br> ![Image](https://github.com/user-attachments/assets/8d3be914-0e34-47ee-9e36-053170c4f7cd)

### 2. Budget Löschen:

+ Budget im Menü auswählen
+ Auf Budget löschen Button drücken
<br> ![Image](https://github.com/user-attachments/assets/d5d936eb-396c-4052-a4bd-590335fc0c00)

### 3. Nutzer zu Budget hinzufügen:

+ Ein Budget im Menü auswählen
+ Auf Nutzer zu Budget hinzufügen Button drücken
+ Nutzername eingeben
+ Auf Hinzufügen Button drücken
<br> ![Image](https://github.com/user-attachments/assets/59a9ae53-84a2-4190-8ed2-4dd9bf86946c)

### 4. Transaktionen ansehen:

+ Ein Budget im Menü auswählen
<br> ![Image](https://github.com/user-attachments/assets/87dee55e-a8f0-4f2b-8727-fdbf9f4d5c71)
+ Auf Transaktion ansehen Button drücken
<br> ![Image](https://github.com/user-attachments/assets/b325c14b-14a1-409e-b158-5be65071419a)

## **Transaktionsoptionen:**

### 1. Transaktion hinzufügen:

+ Transaktionsname eingeben
<br> ![Image](https://github.com/user-attachments/assets/0fe1a7aa-6785-443f-8c5f-99f554f289fe)
+ Transaktionsbetrag eingeben
<br> ![Image](https://github.com/user-attachments/assets/e1e5f763-acb5-4c9f-bd97-b4e907401304)
+ Transaktionsbeschreibung angeben
<br> ![Image](https://github.com/user-attachments/assets/7a17981c-b798-4045-81a5-6035e080117d)
+ Auf Transaktion hinzufügen Button drücken
<br> ![Image](https://github.com/user-attachments/assets/ea3aa16a-6c97-457e-b137-3644ffe25cb3)

### 2. Transaktion löschen:

+ Transaktion auswählen
<br> ![Image](https://github.com/user-attachments/assets/a6c547b4-48ee-4d7e-8826-4f8c2e709008)
+ Auf Transaktion löschen Button drücken
<br> ![Image](https://github.com/user-attachments/assets/415a76f9-2b7f-445b-b82c-6ff3f2e1653e)

### 3. Beschreibung ansehen:

+ Transaktion auswählen
<br> ![Image](https://github.com/user-attachments/assets/a6c547b4-48ee-4d7e-8826-4f8c2e709008)
+ Auf Beschreibung anzeigen Button drücken
<br> ![Image](https://github.com/user-attachments/assets/52f5ebb2-9deb-4605-b23e-0407f1b32e4a)
<br> ![Image](https://github.com/user-attachments/assets/c5c70e72-0e1e-44a9-8454-05e151fbed3f)

### 4. Beschreibung ändern:

+ Transaktion auswählen
<br> ![Image](https://github.com/user-attachments/assets/a6c547b4-48ee-4d7e-8826-4f8c2e709008)
+ Auf Bescreibung ändern Button drücken
+ Neue Beschreibung eingeben und auf ändern Button drücken
<br>![Image](https://github.com/user-attachments/assets/916533dd-679a-4a3c-81a0-2d2d416fb47a)

