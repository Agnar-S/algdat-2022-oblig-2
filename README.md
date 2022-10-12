# Obligatorisk oppgave 2 i Algoritmer og Datastrukturer

Denne oppgaven er en innlevering i Algoritmer og Datastrukturer. 
Oppgaven er levert av følgende studenter:
* S362071 Agnar Kåre Hereid Spilde 
* S362046 Pedram Ziaei
* S354528 Alan Blanco Alquézar
# Arbeidsfordeling 
 
I oppgaven har vi hatt følgende arbeidsfordeling:

* s362046 Pedram Ziaei: Oppgaver 1,4,7.
* s362071 Agnar Kåre Hereid Spilde: Oppgave 2, 5, 8, 10.
* s354528 Alan Blanco ALquézar: Oppgaver 3, 6, 9.

# Oppgavebeskrivelse

I oppgave 1 så gikk vi frem ved å bare returnere bare antallet siden vi har antall og for boolean tøm trengte vi bare en
if test som sjekker om at antall er større enn null hvis ja så tydes at det ikke er tøm og da returneres false else er
tabellen tøm og returneres true. for metoden dobbeltlinket liste(T[] a) så kastes først en nullpointerexc om tabellen er
null. videre skulle alle null-er blitt fjernet og da laget jeg en hjelpemetode nullfjerner som looper med if og for løkker 
gjennom verdier og utsetter null-er. videre velger vi hode og hale. gjennom en for løkke settes pekerne.

I oppgave 2 brukes det StringBuilder for å løse metoden toString(). 
Dette setter hver node i listen i en streng, og det sjekkes listen ikke er tom.
Første node peker på hode og går så til neste peker. 
Deretter brukes det en while-løkke for å ta alle nodene som ikke er null  også legge dem etter hverandre i strengen.
OmvendtString er strukturert på samme måte utenom at den starter fra halen og går deretter til forrige peker. 

I oppgave 3, for finnNode() metoden bruker vi en if statement for å sjekke hvis noden er nærmere til hoden eller halen. 
Etterpå vi bruker en for løkke som enten starter fra hoden og drar til høyre,
eller som starter fra slutten og drar til venstre til vi finner noden i indeksen, og så returnerer vi noden der.
For hent() metoden bruker vi da finnNode() metoden og returnerer vi verdien til noden. 
Men kun hvis den passer en indeks kontroll først. For oppdater() metoden har vi også indeks kontroll,
og vi erstatter den gamle verdien på noden med ny verdi ved hjelpen av en hjelpevariabel.
Sublisten har også en kontroll som sjekker at intervallet er lovlig.
Vi sjekker hvis intervallet er tomt, hvis det er tomt vi returnerer en tom liste.
Så instansierer vi en ny dobbelt lenket liste som skal bli sublisten vår.
Vi bruker hjelpen av en node som vi setter på begynnelsen av vår liste.
Da går vi gjennom liste og når vi finner oss mellom intervallet vi ser etter, bruker vi leggInn() metoden for å fylle opp sublisten vår.
Til slutten returnerer vi sublisten.


I oppgave 4 i den metoden var vi etter å ha en metode der hvor det blir gitt en verdi og da sjekkes det med en if test
om listen er null eller antallet er lik null og da returneres -1. hvis listen ikke var null så looper vi gjennom
listen med utvalgte hode videre mot endenn/hale sp skrives -1 om verdien er .equal lik den verdien innom loopen.

I oppgave 5 så sjekker metoden leggInn() om indeks er null, om listen er tom og om indeksen er lik som antall.
Deretter legges den nye verdien på valgt indeks og flytter noden til neste indeks.

I oppgave 6 har vi lagt to fjern() metoder. En fjerner elementer som finnes på en indeks. 
Først bruker vi indeksKontroll() metoden for å sjekke et den er lovlig. 
Da sjekker vi om vi har en tom liste eller hoden. Vi brujer finnNode() for å finne elementen som skal fjernes. 
Vi lagrer verdien for å returnere den, og vi fjerner den ved å oppdatere pekerne.

I oppgave 7 har blitt laget en metode som tømmer listen. laget en for løkke som setter antallet som liste lengde og i
løkken så fjernes den indeksen/verdien som er i loopet.

I oppgave 8 flytter next, noden et hakk frem. Iteratoren lager itaretor objekt og returnerer det.
Det samme gjelder iterator (indeks), utenom at den starter ved en gitt indeks.

I oppgave 9 sjekker vi med en if statement at vi kan kalle metoden med boolean fjernOK. 
Vi passer også på at antall endringer mellom listen og iterator er det samme. 
Så setter vi fjernOK til false, så at remove() kan ikke kalles på det samme stedet vi har allerede fjernet noe i.
Vi sjekker de spesielle tilfellene med if statements, f.e hvis den som skal fjernes er den eneste verdien, eller hvis den er hoden eller halen.
Da finner vi noden to posisjoner bak fra «denne», og oppdaterer pekerne for å fjerne elementen som finnes bak fra «denne». 
Da reduseres det antall og både endringer og iteratorendringer økes.
