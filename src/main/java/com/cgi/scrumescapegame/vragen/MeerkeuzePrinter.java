    package com.cgi.scrumescapegame.vragen;

    import java.util.List;

    public class MeerkeuzePrinter {
        private MeerkeuzeVraag vraag;

        public MeerkeuzePrinter(MeerkeuzeVraag vraag) {
            this.vraag = vraag;
        }

        public void toonVraag() {
            System.out.println("Meerkeuzevraag: " + vraag.getTekst());
            List<String> opties = vraag.getOpties();
            for (int i = 0; i < opties.size(); i++) {
                System.out.println((char)('A' + i) + ". " + opties.get(i));
            }
            System.out.print("Uw antwoord (A, B, C, ...): ");
        }
    }
