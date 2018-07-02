/*
 * JFrBuildTestTournament.java
 */

package info.vannier.gotha;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author  Luc Vannier
 */
public class JFrBuildTestTournament extends javax.swing.JFrame{
    private TournamentInterface tournament;
    
    private volatile boolean running = true;
    /** Creates new form JFrBuildTestTournament */
    public JFrBuildTestTournament(TournamentInterface tournament) throws RemoteException{
        this.tournament = tournament;
                
        initComponents();
        customInitComponents();
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        cbxSystem = new javax.swing.JComboBox<>();
        cbxPlayers = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        btnBuild = new javax.swing.JButton();
        btnClose = new javax.swing.JButton();
        btnGenerateGames = new javax.swing.JButton();
        btnMakeTeams = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Build test tournament");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });
        getContentPane().setLayout(null);

        jLabel1.setFont(new java.awt.Font("Tahoma", 3, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 0, 0));
        jLabel1.setText("To be used for test only !");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(20, 20, 340, 20);

        cbxSystem.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "McMahon", "Swiss", "SwissCat" }));
        getContentPane().add(cbxSystem);
        cbxSystem.setBounds(20, 70, 120, 20);

        cbxPlayers.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "4 players, 4D to 1D", "4 players, 2D to 1D", "8 players, 10K", "16 players, 1K to 4K", "16 players, 1K to 4K, club issues", "64 players, 6D to 2K, MMG issues", "64 players, 1K to 16K", "64 players, 1K to 17K, parity issue", "64 players, 1K to 8K, diff ratings", "64 players, 1K to 8K, imparity, diff ratings", "500 players", "1200 players", "2000 players" }));
        getContentPane().add(cbxPlayers);
        cbxPlayers.setBounds(150, 70, 260, 20);

        jLabel2.setText("System");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(20, 50, 130, 14);

        jLabel3.setText("Players");
        getContentPane().add(jLabel3);
        jLabel3.setBounds(170, 50, 130, 14);

        btnBuild.setText("Build");
        btnBuild.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuildActionPerformed(evt);
            }
        });
        getContentPane().add(btnBuild);
        btnBuild.setBounds(420, 70, 140, 30);

        btnClose.setText("Close");
        btnClose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCloseActionPerformed(evt);
            }
        });
        getContentPane().add(btnClose);
        btnClose.setBounds(10, 220, 540, 30);

        btnGenerateGames.setText("Generate random games");
        btnGenerateGames.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGenerateGamesActionPerformed(evt);
            }
        });
        getContentPane().add(btnGenerateGames);
        btnGenerateGames.setBounds(150, 110, 410, 30);

        btnMakeTeams.setText("Make Teams");
        btnMakeTeams.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMakeTeamsActionPerformed(evt);
            }
        });
        getContentPane().add(btnMakeTeams);
        btnMakeTeams.setBounds(150, 150, 410, 30);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnGenerateGamesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGenerateGamesActionPerformed
        try {
            tournament.removeAllGames();
        } catch (RemoteException ex) {
            Logger.getLogger(JFrBuildTestTournament.class.getName()).log(Level.SEVERE, null, ex);
        }
 
        TournamentParameterSet tps = null;
        try {
            tps = tournament.getTournamentParameterSet();
        } catch (RemoteException ex) {
                        Logger.getLogger(JFrBuildTestTournament.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (tps == null) return;
        GeneralParameterSet gps = tps.getGeneralParameterSet();
       
       ArrayList<Player> alPlayers = null;
       int numberOfRounds = 0;
       int numberOfPlayers = 0;
       try {
            alPlayers = tournament.playersList();
            numberOfRounds = gps.getNumberOfRounds();
            numberOfPlayers = tournament.numberOfPlayers();
        } catch (RemoteException ex) {
                        Logger.getLogger(JFrBuildTestTournament.class.getName()).log(Level.SEVERE, null, ex); 
        }
       for(int r = 0; r < numberOfRounds; r++){
           for (int i = 0; i < numberOfPlayers / 2; i++){
               Player p1 = alPlayers.get(i);
               Player p2 = alPlayers.get ( (numberOfPlayers/2) + (i + r) % (numberOfPlayers/2));
               Game g = new Game(r, i, p1, p2, true, 0, Game.RESULT_WHITEWINS);
               g.setKnownColor(true);
                try {              
                    tournament.addGame(g);
                } catch (RemoteException ex) {
                                Logger.getLogger(JFrBuildTestTournament.class.getName()).log(Level.SEVERE, null, ex);
                } catch (TournamentException ex) {
                                Logger.getLogger(JFrBuildTestTournament.class.getName()).log(Level.SEVERE, null, ex);
                }
           }          
       } 
       
       this.tournamentChanged();

    }//GEN-LAST:event_btnGenerateGamesActionPerformed

    private void btnCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCloseActionPerformed
        cleanClose();
    }//GEN-LAST:event_btnCloseActionPerformed

    private void cleanClose(){
        running = false;
        dispose();
    }

    private void btnBuildActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuildActionPerformed
        int system = TournamentParameterSet.TYPE_SWISS;
        String strSystem = (String)this.cbxSystem.getSelectedItem();
        if (strSystem.compareTo("McMahon") == 0) system = TournamentParameterSet.TYPE_MCMAHON;
        if (strSystem.compareTo("Swiss") == 0) system = TournamentParameterSet.TYPE_SWISS;
        if (strSystem.compareTo("SwissCat") == 0) system = TournamentParameterSet.TYPE_SWISSCAT;
        
        TournamentParameterSet tps = new TournamentParameterSet();
        tps.initBase();

        switch (system) {
            case TournamentParameterSet.TYPE_MCMAHON:
                tps.initForMM();
                break;
            case TournamentParameterSet.TYPE_SWISS:
                tps.initForSwiss();
                break;
            case TournamentParameterSet.TYPE_SWISSCAT:
                tps.initForSwissCat();
                break;
            default:
                tps.initForMM();

        }
        try {
            tournament.removeAllGames();
            tournament.removeAllPlayers();           
            tournament.setTournamentParameterSet(tps);
        } catch (RemoteException ex) {
                        Logger.getLogger(JFrBuildTestTournament.class.getName()).log(Level.SEVERE, null, ex);
        } 
        
        String strInfoPlayers = (String)this.cbxPlayers.getSelectedItem();

        if (strInfoPlayers.compareTo("4 players, 4D to 1D") == 0){
            for (int i = 0; i < 4; i++){
                int rank = 3 - i;
                Player p = null;
                try {
                    p = new Player(
                            generateName(i),
                            "p_" + (rank+30),
                            "FR", "76Ro",
                            "123456789", "87654321","", "99999", "", 
                            rank,  rank * 100 -50, "INI", "", 0, "FIN");
                } catch (PlayerException ex) {
                                Logger.getLogger(JFrBuildTestTournament.class.getName()).log(Level.SEVERE, null, ex);
                }
                try {
                    tournament.fastAddPlayer(p);
                } catch (RemoteException ex) {
                                Logger.getLogger(JFrBuildTestTournament.class.getName()).log(Level.SEVERE, null, ex);
                } catch (TournamentException ex) {
                                Logger.getLogger(JFrBuildTestTournament.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        
        if (strInfoPlayers.compareTo("4 players, 2D to 1D") == 0){
            for (int i = 0; i < 4; i++){
                int rank = 1 - i/2;
                Player p = null;
                try {
                    p = new Player(
                            generateName(i),
                            "p_" + (rank+30),
                            "FR", "76Ro",
                            "123456789", "87654321","", "99999", "", 
                            rank,  rank * 100 -50, "INI", "", 0, "FIN");
                } catch (PlayerException ex) {
                                Logger.getLogger(JFrBuildTestTournament.class.getName()).log(Level.SEVERE, null, ex);
                }
                try {
                    tournament.fastAddPlayer(p);
                } catch (RemoteException ex) {
                                Logger.getLogger(JFrBuildTestTournament.class.getName()).log(Level.SEVERE, null, ex);
                } catch (TournamentException ex) {
                                Logger.getLogger(JFrBuildTestTournament.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }        
        if (strInfoPlayers.compareTo("8 players, 10K") == 0){
            for (int i = 0; i < 8; i++){
                int rank = -10;
                Player p = null;
                try {
                    p = new Player(
                            generateName(i),
                            "p_" + (rank+30),
                            "DE", "Ham",
                            "123456789", "87654321","", "99999", "", 
                            rank,  rank * 100 -50, "INI", "", 0, "FIN");
                } catch (PlayerException ex) {
                                Logger.getLogger(JFrBuildTestTournament.class.getName()).log(Level.SEVERE, null, ex);
                }
                try {
                    tournament.fastAddPlayer(p);
                } catch (RemoteException ex) {
                                Logger.getLogger(JFrBuildTestTournament.class.getName()).log(Level.SEVERE, null, ex);
                } catch (TournamentException ex) {
                                Logger.getLogger(JFrBuildTestTournament.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        
        if (strInfoPlayers.compareTo("16 players, 1K to 4K") == 0){
            for (int i = 0; i < 16; i++){
                int rank = -1 - (i/4);
                Player p = null;
                try {
                    p = new Player(
                            generateName(i),
                            "p_" + (rank+30),
                            "NL", "Ams",
                            "xxxyyyzzz", "07235000", "", "99999", "", 
                            rank,  rank * 100 -50, "INI", "", 0, "FIN");
                } catch (PlayerException ex) {
                                Logger.getLogger(JFrBuildTestTournament.class.getName()).log(Level.SEVERE, null, ex);
                }
                try {
                    tournament.fastAddPlayer(p);
                } catch (RemoteException ex) {
                                Logger.getLogger(JFrBuildTestTournament.class.getName()).log(Level.SEVERE, null, ex);
                } catch (TournamentException ex) {
                                Logger.getLogger(JFrBuildTestTournament.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        if (strInfoPlayers.compareTo("16 players, 1K to 4K, club issues") == 0){
            for (int i = 0; i < 16; i++){
                int rank = -1 - (i/4);
                Player p = null;
                try {
                    p = new Player(
                            generateName(i),
                            "p_" + (rank+30),
                            "NL", generateName(i/8),
                            "xxxyyyzzz", "07235000", "", "99999", "",
                            rank,  rank * 100 -50, "INI", "", 0, "FIN");
                } catch (PlayerException ex) {
                                Logger.getLogger(JFrBuildTestTournament.class.getName()).log(Level.SEVERE, null, ex);
                }
                try {
                    tournament.fastAddPlayer(p);
                } catch (RemoteException ex) {
                                Logger.getLogger(JFrBuildTestTournament.class.getName()).log(Level.SEVERE, null, ex);
                } catch (TournamentException ex) {
                                Logger.getLogger(JFrBuildTestTournament.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        if (strInfoPlayers.compareTo("64 players, 6D to 2K, MMG issues") == 0){
            for (int i = 0; i < 64; i++){
                int rank = 5 - (i/8);
                Player p = null;
                try {
                    p = new Player(
                            generateName(i),
                            "p_" + (rank+30),
                            "FR", "76Ro",
                            "xxxyyyzzz", "07235000", "", "99999", "",
                            rank,  (rank + 20) * 100 - i, "EGF", "", 0, "FIN");
                } catch (PlayerException ex) {
                                Logger.getLogger(JFrBuildTestTournament.class.getName()).log(Level.SEVERE, null, ex);
                }
                try {
                    tournament.fastAddPlayer(p);
                } catch (RemoteException ex) {
                                Logger.getLogger(JFrBuildTestTournament.class.getName()).log(Level.SEVERE, null, ex);
                } catch (TournamentException ex) {
                                Logger.getLogger(JFrBuildTestTournament.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        if (strInfoPlayers.compareTo("64 players, 1K to 16K") == 0){
            for (int i = 0; i < 64; i++){
                int rank = -1 - (i/4);
                Player p = null;
                try {
                    p = new Player(
                            generateName(i),
                            "p_" + (rank+30),
                            "NL", "Ams",
                            "xxxyyyzzz", "07235000", "", "99999", "",
                            rank,  rank * 100 -50, "INI", "", 0, "FIN");
                } catch (PlayerException ex) {
                                Logger.getLogger(JFrBuildTestTournament.class.getName()).log(Level.SEVERE, null, ex);
                }
                try {
                    tournament.fastAddPlayer(p);
                } catch (RemoteException ex) {
                                Logger.getLogger(JFrBuildTestTournament.class.getName()).log(Level.SEVERE, null, ex);
                } catch (TournamentException ex) {
                                Logger.getLogger(JFrBuildTestTournament.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        
        if (strInfoPlayers.compareTo("64 players, 1K to 16K, parity issue") == 0){
            for (int i = 0; i < 64; i++){
                int rank = -1 - ((i+1)/4);
                Player p = null;
                try {
                    p = new Player(
                            generateName(i),
                            "p_" + (rank+30),
                            "FR", "76Ro",
                            "xxxyyyzzz", "07235000", "", "99999", "",
                            rank,  rank * 100 - i, "FFG", "", 0, "FIN");
                } catch (PlayerException ex) {
                                Logger.getLogger(JFrBuildTestTournament.class.getName()).log(Level.SEVERE, null, ex);
                }
                try {
                    tournament.fastAddPlayer(p);
                } catch (RemoteException ex) {
                                Logger.getLogger(JFrBuildTestTournament.class.getName()).log(Level.SEVERE, null, ex);
                } catch (TournamentException ex) {
                                Logger.getLogger(JFrBuildTestTournament.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        if (strInfoPlayers.compareTo("64 players, 1K to 8K, diff ratings") == 0){
            for (int i = 0; i < 64; i++){
                int rank = -1 - (i/8);
                Player p = null;
                try {
                    p = new Player(
                            generateName(i),
                            "p_" + (rank+30),
                            "NL", "Ams",
                            "xxxyyyzzz", "07235000", "", "99999", "",
                            rank,  rank * 100 -i, "FFG", "", 0, "FIN");
                } catch (PlayerException ex) {
                                Logger.getLogger(JFrBuildTestTournament.class.getName()).log(Level.SEVERE, null, ex);
                }
                try {
                    tournament.fastAddPlayer(p);
                } catch (RemoteException ex) {
                                Logger.getLogger(JFrBuildTestTournament.class.getName()).log(Level.SEVERE, null, ex);
                } catch (TournamentException ex) {
                                Logger.getLogger(JFrBuildTestTournament.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        
        if (strInfoPlayers.compareTo("64 players, 1K to 8K, imparity, diff ratings") == 0){
            for (int i = 0; i < 64; i++){
                int rank = -1 - ((i+1)/8);
                Player p = null;
                try {
                    p = new Player(
                            generateName(i),
                            "p_" + (rank+30),
                            "NL", "Ams",
                            "xxxyyyzzz", "07235000", "", "99999", "",
                            rank,  rank * 100 -i, "FFG", "", 0, "FIN");
                } catch (PlayerException ex) {
                                Logger.getLogger(JFrBuildTestTournament.class.getName()).log(Level.SEVERE, null, ex);
                }
                try {
                    tournament.fastAddPlayer(p);
                } catch (RemoteException ex) {
                                Logger.getLogger(JFrBuildTestTournament.class.getName()).log(Level.SEVERE, null, ex);
                } catch (TournamentException ex) {
                                Logger.getLogger(JFrBuildTestTournament.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        
        if (strInfoPlayers.compareTo("500 players") == 0){
            for (int i = 0; i < 500; i++){
                int rank = 8 - i/16;
                Player p = null;
                try {
                    p = new Player(
                            generateName(i),
                            "p_" + (rank+30),
                            "FR", "76Ro",
                            "123456789", "87654321", "", "99999", "",
                            rank,  rank * 100 -50, "INI", "", 0, "FIN");
                } catch (PlayerException ex) {
                                Logger.getLogger(JFrBuildTestTournament.class.getName()).log(Level.SEVERE, null, ex);
                }
                try {
                    tournament.fastAddPlayer(p);
                } catch (RemoteException ex) {
                                Logger.getLogger(JFrBuildTestTournament.class.getName()).log(Level.SEVERE, null, ex);
                } catch (TournamentException ex) {
                                Logger.getLogger(JFrBuildTestTournament.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        if (strInfoPlayers.compareTo("1200 players") == 0){
            for (int i = 0; i < 1200; i++){
                int rank = 8 - i/64;
                Player p = null;
                try {
                    p = new Player(
                            generateName(i),
                            "p_" + (rank+30),
                            "FR", "76Ro",
                            "123456789", "87654321", "", "99999", "",
                            rank,  rank * 100 -50, "INI", "", 0, "FIN");
                } catch (PlayerException ex) {
                                Logger.getLogger(JFrBuildTestTournament.class.getName()).log(Level.SEVERE, null, ex);
                }
                try {
                    tournament.fastAddPlayer(p);
                } catch (RemoteException ex) {
                                Logger.getLogger(JFrBuildTestTournament.class.getName()).log(Level.SEVERE, null, ex);
                } catch (TournamentException ex) {
                                Logger.getLogger(JFrBuildTestTournament.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        
        if (strInfoPlayers.compareTo("2000 players") == 0){
            for (int i = 0; i < 2000; i++){
                int rank = 8 - i/100;
                Player p = null;
                try {
                    p = new Player(
                            generateName(i),
                            "p_" + (rank+30),
                            "FR", "76Ro",
                            "123456789", "87654321", "", "99999", "",
                            rank,  rank * 100 -50, "INI", "", 0, "FIN");
                } catch (PlayerException ex) {
                                Logger.getLogger(JFrBuildTestTournament.class.getName()).log(Level.SEVERE, null, ex);
                }
                try {
                    System.out.println("fastAddPlayer i = " + i);
                    tournament.fastAddPlayer(p);
                } catch (RemoteException ex) {
                                Logger.getLogger(JFrBuildTestTournament.class.getName()).log(Level.SEVERE, null, ex);
                } catch (TournamentException ex) {
                                Logger.getLogger(JFrBuildTestTournament.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        this.tournamentChanged();    
    }//GEN-LAST:event_btnBuildActionPerformed

    private void btnMakeTeamsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMakeTeamsActionPerformed
        TeamTournamentParameterSet tps = null;
        try {
            tps = tournament.getTeamTournamentParameterSet();
        } catch (RemoteException ex) {
            Logger.getLogger(JFrBuildTestTournament.class.getName()).log(Level.SEVERE, null, ex);
        }

        TeamGeneralParameterSet tgps = tps.getTeamGeneralParameterSet();
        int teamSize = tgps.getTeamSize();
        
        ArrayList<Player> alPlayers = null;
        try {
            alPlayers = tournament.playersList();
        } catch (RemoteException ex) {
            Logger.getLogger(JFrBuildTestTournament.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        for (int iTeam = 0; iTeam < alPlayers.size() / teamSize; iTeam++){
            Team team = new Team(iTeam, "Equipe" + (iTeam + 1));
            for (int iBoard = 0; iBoard < teamSize; iBoard++){
                Player p = alPlayers.get(iTeam * teamSize + iBoard);
                for (int ir = 0; ir < Gotha.MAX_NUMBER_OF_ROUNDS; ir++){
                    team.setTeamMember(p, ir, iBoard);
                }
            }
            try {
                tournament.addTeam(team);
            } catch (RemoteException ex) {
                Logger.getLogger(JFrBuildTestTournament.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        this.tournamentChanged();

    }//GEN-LAST:event_btnMakeTeamsActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        cleanClose();
    }//GEN-LAST:event_formWindowClosing

    private void customInitComponents()throws RemoteException{       
    }
    
    private String generateName(int i){
        int i2 = 'A' + (i % 26);
        int i1 = 'A' + (i/26)%26;
        int i0 = 'A' + (i/26/26);
        char[] c = new char[3];
        c[2] = (char)i2;
        c[1] = (char)i1;
        c[0] = (char)i0;
        String s = new String(c);
        
        return s;
    }
    
    
    

    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuild;
    private javax.swing.JButton btnClose;
    private javax.swing.JButton btnGenerateGames;
    private javax.swing.JButton btnMakeTeams;
    private javax.swing.JComboBox<String> cbxPlayers;
    private javax.swing.JComboBox<String> cbxSystem;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    // End of variables declaration//GEN-END:variables

    
    private void tournamentChanged() {
        try {
            tournament.setLastTournamentModificationTime(tournament.getCurrentTournamentTime());
        } catch (RemoteException ex) {
            Logger.getLogger(JFrBuildTestTournament.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
