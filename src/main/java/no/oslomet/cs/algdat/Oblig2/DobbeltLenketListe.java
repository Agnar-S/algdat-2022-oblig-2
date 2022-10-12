package no.oslomet.cs.algdat.Oblig2;


////////////////// class DobbeltLenketListe //////////////////////////////


import java.util.*;


public class DobbeltLenketListe<T> implements Liste<T> {

    /**
     * Node class
     *
     * @param <T>
     */
    private static final class Node<T> {
        private T verdi;                   // nodens verdi
        private Node<T> forrige, neste;    // pekere

        private Node(T verdi, Node<T> forrige, Node<T> neste) {
            this.verdi = verdi;
            this.forrige = forrige;
            this.neste = neste;
        }

        private Node(T verdi) {
            this(verdi, null, null);
        }
    }

    // instansvariabler
    private Node<T> hode;          // peker til den første i listen
    private Node<T> hale;          // peker til den siste i listen
    private int antall;            // antall noder i listen
    private int endringer;         // antall endringer i listen

    public DobbeltLenketListe() {
        hode = hale = null;
        antall = 0;
        endringer = 0;
    }

    public DobbeltLenketListe(T[] a) {
        if (a == null) {
            throw new NullPointerException("Tabell a er null!"); //feilmedling sendes om tabellen er null
        }

        a = nullfjerner(a);       //setter listen a gjennom nullfjerner for at det skal ikke være nuller blant tallene

        if(a.length == 0){
            return;
        }

        Node<T> forste = new Node<>(a[0]);       //første noden opprettes og settes i a[0]
        antall++;                                //antall økes
        hode = forste;                           //første node blir satt som hode

        for (int i = 1; i < a.length; i++) {     //startes på 1 siden allerede hode er tatt på første
            Node<T> neste = new Node<>(a[i]);    //oppretter nye noder i sine verdier
            antall++;
            forste.neste = neste;               //nye noder blir satt etter forste >> peker frem til neste node
            neste.forrige = forste;             //setter neste sin forrige peker  >> siden dobbelt lenket så pekes til den forrige o
            forste = neste;                     //og siden det skal lages noder for alle verdier så settes neste som forste
            // altså a[0] = forste så skjer forste = neste blir slik at vi går opp a[i]
        }
        //til slutt blir vi tom av verdier og går ut av for_løkka og da settes siste verdi lik forste
        hale = forste;
    }

    private T[] nullfjerner(T[] a) {
        int antall_nuller = 0;
        for (T verdi : a) {
            if (verdi == null) {                  // Teller hvor mange nuller det finnes
                antall_nuller++;
            }
        }
        if (antall_nuller == 0) {
            return a;
        }     //Om det ikke finnes nuller i lista så returneres selve lista

        T[] b = (T[]) new Object[a.length - antall_nuller];     // antall nuller subtraheres fra liste sin lengde

        int j = 0;

        for (int i = 0; i < a.length; i++) { //en for løkke som lopper for å fjerne nuller eller å bare sette tall ved
            if (a[i] != null) {               // siden av hverandre
                b[j] = a[i];
                j++;
            }
        }
        return b;
    }

    private Node<T> finnNode(int indeks) {

        Node<T> p = (indeks < antall / 2) ? hode : hale; // Index to the left of the middle ? Yes, start at head : No, start at tail.

        if (p == hode) {    // If we start at head, move to the right until we reach our index
            for (int i = 0; i < indeks; i++) p = p.neste;
            return p;  //Return the node in the index
        } else {    // Otherwise, move to the left until we reach our index.
            for (int i = antall - 1; i > indeks; i--) p = p.forrige;
            return p; //Return the node in the index
        }
    }

    private void fratilKontroll(int antall, int fra, int til) { //Help method to check that the interval is correct
        if (fra < 0) {
            throw new IndexOutOfBoundsException("Fra (" + fra + ") er negativ!"); //Fra can't be negative
        }
        if (til > antall) {
            throw new IndexOutOfBoundsException("Til (" + til + ")er for stor!"); //Til can't exceed the length
        }
        if (fra > til) {
            throw new IllegalArgumentException("Fra (" + fra + ") er større enn til (" + til + ")!"); //And fra must be smaller than til
        }
    }


    public Liste<T> subliste(int fra, int til) {

        fratilKontroll(antall, fra, til); //Checking that the interval is legal

        if (fra == til) {  //If the interval is empty, return an empty list.
            return new DobbeltLenketListe<>();
        }

        DobbeltLenketListe<T> sublist = new DobbeltLenketListe<>();

        Node<T> current = this.hode;    //Starting node
        int count = 0;

        while (current != null) {   //We run through the orig. list until we run out of values
            if (count >= fra && count < til) {  //And while count is within the bounds we want, we add the value to the sublist
                sublist.leggInn(current.verdi);
            }
            current = current.neste;
            count++;
        }

        sublist.endringer = 0;  //Its a fresh list so no changes

        return sublist;

    }

    @Override
    public int antall() {
        return antall;
    }

    @Override
    public boolean tom() {
        if (antall > 0) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public boolean leggInn(T verdi) {
        Objects.requireNonNull(verdi,"Null verdier er ikke tillat!");
        if(antall == 0){
            hode = hale = new Node<>(verdi,null,null);
        }else{
            hale = hale.neste = new Node<>(verdi, hale, null);
        }
        antall ++;
        endringer++;
        return true;
    }

    @Override
    public void leggInn(int indeks, T verdi) {
        Objects.requireNonNull(verdi,"Ikke tillatt med null-verdier!");
//
        indeksKontroll(indeks,true);

        if (indeks == 0) {
            if(antall == 0) {
                hode = hale = new Node<>(verdi, null, null);
            }else{
                Node<T> p = hode;
                hode = new Node<>(verdi,null,hode);
                p.forrige = hode;
            }
        }else if(indeks == antall){
            Node<T> p = hale;
            hale = new Node<>(verdi,hale,null);
            p.neste = hale;
        }else{
            Node<T> p = hode;
            Node<T> q = hode;
            for(int i = 1; i < indeks; i++){
                p = p.neste;
            }
            for(int i = 1; i < indeks+1; i++){
                q = q.neste;
            }
            Node<T> r = new Node<>(verdi,p,q);
            p.neste = q.forrige = r;
        }
        endringer ++;
        antall ++;
    }

    @Override
    public boolean inneholder(T verdi) { //sjekker om verdien finnes
        int indeks = indeksTil(verdi);   //verdien hentes fra indeksTil og settes som indeks
        if (indeks == -1) {               //om det er -1 så får false
            return false;
        }
        return true;                     //else får vi true
    }

    @Override
    public T hent(int indeks) {
        indeksKontroll(indeks, false); // Throws an exception if the index is out of bounds.
        return finnNode(indeks).verdi; // Returns value of the node at given index using our help method.
    }

    @Override
    public int indeksTil(T verdi) {          //sjekker mot en verdi finnes
        if (antall == 0 || verdi == null) {   // om det ikke finnes eller det null så returneres -1
            return -1;
        }
        Node<T> forste = hode;
        for (int i = 0; i < antall; i++) {
            if (forste.verdi.equals(verdi)) { //om verdien finnes når vi looper så returneres det
                return i;
            }
            forste = forste.neste;           // else forsettes vi gjennom hele lista
        }
        return -1;
    }

    @Override
    public T oppdater(int indeks, T nyverdi) {

        Objects.requireNonNull(nyverdi);

        indeksKontroll(indeks, false); // Throws an exception if index is out of bounds

        Node<T> p = finnNode(indeks); // We find the node we are going to replace the value of
        T oldValue = p.verdi; // We store the value of the node

        p.verdi = nyverdi; // We replace the value of the node with the new value
        endringer++; //We increment the number of changes.
        return oldValue; // Finally we return the old value.
    }

    @Override
    public boolean fjern(T verdi) {

        if (verdi == null) {
            return false;
        }                  //returnerer false om verdi er null
        Node<T> forste = hode;

        int i = 1;                                          //siden hode er satt på forste plass altså 0. posisjon
        while (!forste.verdi.equals(verdi) && i < antall) { //til forste er ikke lik verdi og i er mindre enn antall så..
            forste = forste.neste;                         //fortsetter vi på neste
            i++;
        }

        if (!forste.verdi.equals(verdi) && i == antall) {  //hvis forste er ikke lik verdi og i er lik antall
            return false;                                 //returneres false
        }
        if (antall == 1) {                                  //om antall er lik null både hode og hale nullstilles
            hode = null;
            hale = null;
        } else if (forste.forrige == null) {                 //ellers hvis forrige ledd av forste er null
            hode.neste.forrige = null;                    //så settes hode neste og forrige lik null
            hode = hode.neste;                            //og hode får/blir satt til neste verdi
        } else if (forste.neste == null) {                   //ellers hvis forste.neste er null så..
            hale.forrige.neste = null;                    //så settes hale forrige og neste lik null
            hale = hale.forrige;                          //og hale får/blir satt til forrige verdi
        } else {
            forste.forrige.neste = forste.neste;
            forste.neste.forrige = forste.forrige;
        }

        antall--;
        endringer++;
        return true;
    }

    @Override
    public T fjern(int indeks) {
        indeksKontroll(indeks, false);

        T fjernverdi;

        if(indeks == 0) {
            if (antall == 1){
                fjernverdi = hode.verdi;
                hale = hode = null;
            } else {
                fjernverdi = hode.verdi;
                hode = hode.neste;
                hode.forrige = null;
            }

        } else if(indeks == antall-1){
            fjernverdi = hale.verdi;
            hale = hale.forrige;
            hale.neste = null;
        } else{
            Node<T> p = finnNode(indeks -1);
            Node<T> q = p.neste;
            Node<T> r = q.neste;
            fjernverdi = q.verdi;
            p.neste = r;
            r.forrige = p;
        }
        antall--;
        endringer++;
        return fjernverdi;
    }



    @Override
    public void nullstill() {

        Node<T> p = hode;
        Node<T> q;

        while (p != null)
        {
            q = p.neste;
            p.neste = null;
            p.verdi = null;
            p = q;
        }

        hode = hale = null;
        antall = 0;
        endringer++;
    }


    @Override
        public String toString() {
            StringBuilder s = new StringBuilder();
            s.append('[');

            if(!tom()){
                Node<T> p = hode;
                s.append(p.verdi);

                p = p.neste;

                while( p  != null){
                    s.append(',').append(' ').append(p.verdi);
                    p = p.neste;
                }

            }
            s.append(']');
            return s.toString();
        }


    public String omvendtString() {
        StringBuilder s = new StringBuilder();
        s.append('[');

        if(!tom()){
            Node<T> p = hale;
            s.append(p.verdi);

            p = p.forrige;

            while( p != null){
                s.append(',').append(' ').append(p.verdi);
                p = p.forrige;
            }
        }
        s.append(']');
        return s.toString();
    }



    @Override
    public Iterator<T> iterator() {

        return new DobbeltLenketListeIterator();
    }

    public Iterator<T> iterator(int indeks) {
        indeksKontroll(indeks, false);
        return new DobbeltLenketListeIterator(indeks);
    }

    private class DobbeltLenketListeIterator implements Iterator<T> {
        private Node<T> denne;
        private boolean fjernOK;
        private int iteratorendringer;

        private DobbeltLenketListeIterator(int indeks) {
            denne = finnNode(indeks);

            fjernOK = false;
            iteratorendringer = endringer;
        }

        private DobbeltLenketListeIterator() {
            denne = hode;     // p starter på den første i listen
            fjernOK = false;  // blir sann når next() kalles
            iteratorendringer = endringer;  // teller endringer
        }

        @Override
        public boolean hasNext() {
            return denne != null;
        }

        @Override
        public T next() {
            if (iteratorendringer!=endringer)
            {
                throw new ConcurrentModificationException();
            }
            else if (!hasNext()) {
                throw new NoSuchElementException();
            }
            fjernOK = true;
            T returnVerdi = denne.verdi;
            denne = denne.neste;
            return returnVerdi;
        }

        @Override
        public void remove() {

            if (!fjernOK) {  //Hvis det ikke er tillatt å kalle denne metoden, skal det kastes en IllegalStateException.
                throw new IllegalStateException("Kan ikke fjernes!");
            }
            if (iteratorendringer != endringer) {
                throw new ConcurrentModificationException();
            }

            fjernOK = false; //Kan ikke kalle remove() på den elementen igjen

            if (antall == 1) {   //Hvis elementen vi skal fjernes er den eneste, så er både håde og hale null
                hode = null;
                hale = null;
                antall--;
                endringer++;
                iteratorendringer++;
                return;
            }

            Node<T> f = hode; //Hjelp

            if (denne.forrige == hode) { //Hvis hoden som skal fjernes er den første
                hode = hode.neste;      //Så blir den andre håden

                if (denne == null) {
                    hale = null;        //Hvis den var den eneste noden, så er hale null også.
                }
            } else {
                Node<T> q = hode;
                while (q.neste.neste != denne) {     //Vi flytter q to posisjoner bak fra denne.
                    q = q.neste;
                }
                f = q.neste;    //Den neste elementen er den som skal fjernes.
                q.neste = denne;    //Vi hopper over elementen som skal fjernes.

                if (denne == null) {
                    hale = q;   //Hvis elementen vi fjernet var den siste, so er noden q halen nå.
                }
            }
            f.neste = null;
            f.verdi = null;     //Noden fjernes

            antall--;
            endringer++;
            iteratorendringer++;
        }

    } // class DobbeltLenketListeIterator

    public static <T> void sorter(Liste<T> liste, Comparator<? super T> c) {
        throw new UnsupportedOperationException();
    }

} // class DobbeltLenketListe



