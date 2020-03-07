![Java CI with Maven](https://github.com/team-chip-tuning/guest-house/workflows/Java%20CI%20with%20Maven/badge.svg)
[![Build Status](https://travis-ci.org/team-chip-tuning/guest-house.svg?branch=master)](https://travis-ci.org/team-chip-tuning/guest-house)

# Projekt: Hotel Verwaltung

### Projekt Team
Team Chip-Tuning

### Entwicklung IDE und Plugin
Das Projekt wurde mit IntelliJ IDEA ULTIMATE 2019.3 von JetBrains erstellt und entwickelt.
Es wurden die Standard Einstellungen verwendet für die Java Entwicklung.

Lombok wird verwendet im Projekt und darum muss das Plugin ```Lombok``` installiert sein.
Das Plugin findet man über die Suche im Plugin Dialog im IntelliJ.
Oder man kann es herunterladen und installieren unter: ``https://plugins.jetbrains.com/plugin/6317-lombok/``
Es wurde die Verion ``0.28`` verwendet in der Entwicklung.

### Java
Das Projket verwendet **OpenJdk 13**, diese Version ist im Maven File ``pom.xml`` gesetzt.
Zu finden ist OpenJdk 13 unter: ``https://jdk.java.net/13/``.

Um das Projekt erfolgreich mit Maven oder mit IntelliJ zu erstellen.
Muss für Maven OpenJdk 13 zugänglich sein.

### Projekt Import
Man kann am einfachsen das Projekt über Intellij über das Menu ``File - New - Project from existing Source`` öffnen.
Bitte dann das ``pom.xml`` File wählen zum erstellen des Intellij Projekts, die default Einstellungen sollten reichen.
Im Dialog für die Wahl der SDK, bitte die korrekte OpenJdk 13 verwenden.

Danach im Maven Tab ``clean`` und ``install`` durchführen. Dabei sollten die Unit Tests ebenfalls erfolgreich durchgeführt werden.
Im IntelliJ Build Dropdown Menu müssten zwei Einträge erscheinen ``DEV GuestHouse`` und ``PROD GuestHouse``, da diese IntelliJ Build Profile im Projekt abgelegt wurden.
> Wichtig!
Bitte prüfen Sie gesetzte JDK Version in diesen Build Profilen, dort müssen Sie Ihre OpenJDK 13 verwenden. Je nach Ihrem Intellij Setup ist die default JDK anders.

Im Build Profile ``DEV GuestHouse`` ist unter dem Eintrag ``Active profiles`` ``dev`` eingestellt.
Dieses Build Profile wird mit einer h2 Datenbank betrieben, diese sollte Lokal lauffähig sein.

### Docker
Um das **Prod** Profile zu verwenden, muss Docker und Docker-Compose installiert sein.
Die Console/Terminal im Projektverzeichnis öffnen.
Dann das Kommando ``docker-compose up`` ausführen, um die Infrastruktur Postgres DB zu starten. 

Je nach Version muss man entsprechend Informationen anpassen im ``docker-compose`` File.
Die Informationen müssen übereinstimmen im ``application-prod.yaml``, dort weden diverse ODBC Informationen zur Datenbank gesetzt.

### MIT
MIT License

Copyright (c) 2019

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.

