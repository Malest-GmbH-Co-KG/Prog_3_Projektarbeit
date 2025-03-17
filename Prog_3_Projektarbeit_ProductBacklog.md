# BACKLOG ITEMS

# PRODUCT BACKLOG

## USER-STORY 1: Datenbank anlegen
### BESCHREIBUNG:
- ALS Programmierer
- WILL ICH eine relationale Datenbank anlegen
- DAMIT ich Benutzer, Konten und Transaktionen verwalten kann.

### AKZEPTANZKRITERIEN:
1. Nutzer sollen eine Nutzer-ID, einen Nutzernamen und ein Passwort besitzen.
2. Konten sollen eine Konto-ID (Primärschlüssel) und einen Kontostand haben.
3. Transaktionen sollen als N:M-Beziehung zwischen Konto und Nutzer abgebildet werden.

### ANMERKUNGEN UND DETAILS:
- Die Datenbank kann in SQLite umgesetzt werden.
- Beziehungen müssen durch Fremdschlüssel gesichert sein.

---

## USER-STORY 2: Benutzerregistrierung
### BESCHREIBUNG:
- ALS neuer Benutzer
- WILL ICH mich registrieren können
- DAMIT ich mein Konto verwalten und Transaktionen durchführen kann.

### AKZEPTANZKRITERIEN:
1. Der Benutzer kann sich mit einem eindeutigen Benutzernamen und Passwort registrieren.
2. Die Daten müssen sicher in der Datenbank gespeichert werden (Passwort-Hashing erforderlich).
3. Fehlermeldungen sollen ausgegeben werden, falls der Benutzername bereits existiert.

### ANMERKUNGEN UND DETAILS:
- Passwort-Hashing mit bcrypt oder einer ähnlichen Methode.

---

## USER-STORY 3: Anmeldung
### BESCHREIBUNG:
- ALS registrierter Benutzer
- WILL ICH mich mit meinem Benutzernamen und Passwort anmelden
- DAMIT ich auf mein Konto zugreifen kann.

### AKZEPTANZKRITERIEN:
1. Der Benutzer kann sich mit gültigen Anmeldedaten anmelden.
2. Falsche Anmeldedaten führen zu einer Fehlermeldung.
3. Nach erfolgreicher Anmeldung wird der Benutzer auf das Dashboard weitergeleitet.

### ANMERKUNGEN UND DETAILS:
- Verwendung von Sessions oder Tokens für Authentifizierung.

---

## USER-STORY 4: Transaktionen durchführen
### BESCHREIBUNG:
- ALS Benutzer
- WILL ICH Geld zwischen meinen eigenen Konten überweisen können
- DAMIT ich meine Finanzen organisieren kann.

### AKZEPTANZKRITERIEN:
1. Der Benutzer kann ein Quell- und Zielkonto auswählen.
2. Der Betrag darf nicht höher als der aktuelle Kontostand sein.
3. Nach erfolgreicher Transaktion wird der neue Kontostand aktualisiert und gespeichert.

### ANMERKUNGEN UND DETAILS:
- Eventuell können auch Überweisungen an andere Benutzer hinzugefügt werden.

---

## USER-STORY 5: Dashboard-Anzeige
### BESCHREIBUNG:
- ALS Benutzer
- WILL ICH eine Übersicht meiner Konten und Transaktionen sehen
- DAMIT ich meine Finanzen im Blick habe.

### AKZEPTANZKRITERIEN:
1. Das Dashboard zeigt alle meine Konten mit aktuellem Kontostand.
2. Eine Liste der letzten Transaktionen wird angezeigt.
3. Die Benutzeroberfläche ist benutzerfreundlich und responsiv.

### ANMERKUNGEN UND DETAILS:
- Eine grafische Darstellung (z. B. Diagramme) könnte später hinzugefügt werden.

---

## USER-STORY 6: Logout-Funktion (Neu hinzugefügt)
### BESCHREIBUNG:
- ALS Benutzer
- WILL ICH mich sicher ausloggen können
- DAMIT meine Daten geschützt sind.

### AKZEPTANZKRITERIEN:
1. Der Benutzer kann sich mit einem Klick ausloggen.
2. Nach dem Logout wird die Sitzung beendet und der Benutzer zur Anmeldeseite umgeleitet.
3. Tokens oder Sessions werden invalidiert.

### ANMERKUNGEN UND DETAILS:
- Ein automatischer Logout nach einer gewissen Inaktivität könnte später hinzugefügt werden.

---


Grundlegende Funktionen sind hiermit aber abgedeckt


