package no.oslomet.cs.algdat.Oblig2;


////////////////// class DobbeltLenketListe //////////////////////////////


import java.util.Comparator;
import java.util.Iterator;


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

        private T[] nullfjerner (T[] a){
            int antall_nuller = 0;
            for (T verdi : a) {
                if (verdi == null) {                  // Teller hvor maneg nuller det finnes
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

    public Liste<T> subliste(int fra, int til) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int antall() {
        return antall;
    }

    @Override
    public boolean tom() {
        if(antall > 0){
            return false;
        }
        else {
            return true;
        }
    }

    @Override
    public boolean leggInn(T verdi) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void leggInn(int indeks, T verdi) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean inneholder(T verdi) {
        throw new UnsupportedOperationException();
    }

    @Override
    public T hent(int indeks) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int indeksTil(T verdi) {
        throw new UnsupportedOperationException();
    }

    @Override
    public T oppdater(int indeks, T nyverdi) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean fjern(T verdi) {
        throw new UnsupportedOperationException();
    }

    @Override
    public T fjern(int indeks) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void nullstill() {
        throw new UnsupportedOperationException();
    }

    @Override
    public String toString() {
        throw new UnsupportedOperationException();
    }

    public String omvendtString() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterator<T> iterator() {
        throw new UnsupportedOperationException();
    }

    public Iterator<T> iterator(int indeks) {
        throw new UnsupportedOperationException();
    }

    private class DobbeltLenketListeIterator implements Iterator<T> {
        private Node<T> denne;
        private boolean fjernOK;
        private int iteratorendringer;

        private DobbeltLenketListeIterator() {
            denne = hode;     // p starter på den første i listen
            fjernOK = false;  // blir sann når next() kalles
            iteratorendringer = endringer;  // teller endringer
        }

        private DobbeltLenketListeIterator(int indeks) {
            throw new UnsupportedOperationException();
        }

        @Override
        public boolean hasNext() {
            return denne != null;
        }

        @Override
        public T next() {
            throw new UnsupportedOperationException();
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }

    } // class DobbeltLenketListeIterator

    public static <T> void sorter(Liste<T> liste, Comparator<? super T> c) {
        throw new UnsupportedOperationException();
    }

} // class DobbeltLenketListe


