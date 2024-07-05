package main.java.org.ortega.ortega.client;

import main.java.org.ortega.ortega.bankapp.OperationsBancaires;
import main.java.org.ortega.ortega.bankapp.OperationsBancairesHelper;
import org.omg.CORBA.ORB;
import org.omg.CORBA.Object;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;

public class CORBAClient {

    public static void main(String[] args) {
        try {

            ORB orb = ORB.init(args, null);
            Object objRef = orb.resolve_initial_references("NameService");
            NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);
            OperationsBancaires op = OperationsBancairesHelper.narrow(ncRef.resolve_str("OperationsBancaire"));

            System.out.println("Initial balance: " + op.balance());
            int amount = 150;
            op.depot(amount);
            System.out.println("deposited: "+ amount);
            System.out.println("current balance: " + op.balance());

            amount = 300;
            op.retrait(amount);
            System.out.println("withdraw " + amount);
            System.out.println("final balance: " + op.balance());

        } catch (Exception e) {
            System.out.println("Erreur lors de l'ouverture du serveur " + e);
        }
    }

}
