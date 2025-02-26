### Skriv en Main klass
Skriv en Main klass med en main metod som hanterar CarController och CarView och en lista av bilar, så att dessa kan hänvisa till varandras variabler utan för hög coupling.

### Konstruktorer
Vi kommer att behöva modifiera konstruktorerna i CarController och CarView på grund av att vi har skapat en ny Main klass

### Skapa en "CarStorage" interface
En CarStorage interface kan användas för att ha en gemensam definition av hur förvaring av bilar ska ske. CarWorkshop och CarTransport kan då implementera varsin variant av dessa interface som t.ex. är "First-in-First-out" respektive "First-in-Last-out".

### Truck
Truck kan bytas från att vara ett barn av TrimCar eftersom att en lastbil skulle kunna ha en helt egen metod för att hantera hastighet

### Utvecklingspotenial
En metod som vi inte lärt oss är Factory Pattern, detta har vi fått veta det hade kunnat användas, men att det täcks senare i kursen
