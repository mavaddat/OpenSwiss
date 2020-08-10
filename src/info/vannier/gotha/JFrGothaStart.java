/*
 * JFrGothaStart.java
 */
package info.vannier.gotha;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.File;
import java.net.InetAddress;
import java.rmi.RemoteException;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author  Luc Vannier
 */
public class JFrGothaStart extends javax.swing.JFrame {

    /** Creates new form JFrGothaStart */
    public JFrGothaStart() {        
        File rootDir;
        rootDir = new File(System.getProperty("user.dir"));
        File dir;
        dir = findADirectoryContaining(rootDir, "tournamentfiles");

        if (dir == null) {
            String str = JOptionPane.showInputDialog(this, "Please enter the OpenGotha directory path" +
                    "\nThis is where opengotha.jar, tournamentfiles, etc. have been installed",
                    rootDir.toString());
            if (!new File(str, "tournamentfiles").exists())
                JOptionPane.showMessageDialog(this, "This path does not look correct. Some path issues may occur!", "Message",
                        JOptionPane.WARNING_MESSAGE);
            dir = new File(str);
        }

        Gotha.runningDirectory = dir;
        Gotha.tournamentDirectory = new File(Gotha.runningDirectory, "tournamentfiles");
        Gotha.exportDirectory = new File(Gotha.runningDirectory, "exportfiles");
        Gotha.exportHTMLDirectory = new File(Gotha.runningDirectory, "exportfiles/html");
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

        grpLanguage = new javax.swing.ButtonGroup();
        grpRunningMode = new javax.swing.ButtonGroup();
        btnStart = new javax.swing.JButton();
        pnlRunningMode = new javax.swing.JPanel();
        rdbSAL = new javax.swing.JRadioButton();
        rdbServer = new javax.swing.JRadioButton();
        rdbClient = new javax.swing.JRadioButton();
        btnHelp = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Gotha start");
        setResizable(false);
        getContentPane().setLayout(null);

        btnStart.setText("Start");
        btnStart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStartActionPerformed(evt);
            }
        });
        getContentPane().add(btnStart);
        btnStart.setBounds(185, 260, 170, 30);

        pnlRunningMode.setBorder(javax.swing.BorderFactory.createTitledBorder("Running mode"));
        pnlRunningMode.setLayout(null);

        grpRunningMode.add(rdbSAL);
        rdbSAL.setSelected(true);
        rdbSAL.setText("Stand-Alone");
        rdbSAL.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        rdbSAL.setMargin(new java.awt.Insets(0, 0, 0, 0));
        pnlRunningMode.add(rdbSAL);
        rdbSAL.setBounds(20, 40, 120, 15);

        grpRunningMode.add(rdbServer);
        rdbServer.setText("Server");
        rdbServer.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        rdbServer.setMargin(new java.awt.Insets(0, 0, 0, 0));
        pnlRunningMode.add(rdbServer);
        rdbServer.setBounds(20, 80, 120, 15);

        grpRunningMode.add(rdbClient);
        rdbClient.setText("Client");
        rdbClient.setToolTipText("For LAN clients");
        rdbClient.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        rdbClient.setMargin(new java.awt.Insets(0, 0, 0, 0));
        pnlRunningMode.add(rdbClient);
        rdbClient.setBounds(20, 120, 120, 15);

        getContentPane().add(pnlRunningMode);
        pnlRunningMode.setBounds(185, 30, 170, 160);

        btnHelp.setIcon(new javax.swing.ImageIcon(getClass().getResource("/info/vannier/gotha/gothalogo16.jpg"))); // NOI18N
        btnHelp.setText("help");
        btnHelp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHelpActionPerformed(evt);
            }
        });
        getContentPane().add(btnHelp);
        btnHelp.setBounds(185, 220, 170, 25);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void customInitComponents() {
        int w = JFrGotha.SMALL_FRAME_WIDTH;
        int h = JFrGotha.SMALL_FRAME_HEIGHT;
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((dim.width - w) / 2, (dim.height - h) / 2, w, h);

        setIconImage(Gotha.getIconImage());
        setTitle("OpenGotha");
    }

    private void btnStartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStartActionPerformed
        Gotha.locale = new Locale("en");

        Gotha.runningMode = Gotha.RUNNING_MODE_UNDEFINED;

        TournamentInterface tournament = null;

        if (grpRunningMode.getSelection() == this.rdbSAL.getModel()) {
            Gotha.runningMode = Gotha.RUNNING_MODE_SAL;
        }

        if (grpRunningMode.getSelection() == this.rdbServer.getModel()) {
            //Request information
//            String strInfo = "You use OpenGotha in Sever/Client mode";
//            strInfo += "\nPlease send an eMail to luc.vannier@gmail.com";
//            strInfo += "\nTell me details about the way you manage your tournament : Number of players, Number of Workstations , ...";
//            strInfo += "\nThis will help me giving good directions to OpenGootha's developments";
//            strInfo += "\nThank you";
//            strInfo += "\nLuc";
//            JOptionPane.showMessageDialog(this, strInfo);
//            
            String strIPAd = Gotha.getBestIPAd().toString();
            strIPAd = strIPAd.replace("/", "");
            strIPAd = JOptionPane.showInputDialog("Please, enter IP address of this server in Dot-decimal notation (xxx.xxx.xxx.xxx)", strIPAd);
            if (strIPAd == null) return;

            System.setProperty("java.rmi.server.hostname", strIPAd);

            Gotha.runningMode = Gotha.RUNNING_MODE_SRV;
        }

        if (grpRunningMode.getSelection() == this.rdbClient.getModel()) {
            String strSN = "";
            try {
                strSN = InetAddress.getLocalHost().getHostAddress();
            } catch (java.net.UnknownHostException ex) {
                            Logger.getLogger(JFrGothaStart.class.getName()).log(Level.SEVERE, null, ex);
            }
            Gotha.serverName = JOptionPane.showInputDialog("Enter Server address", strSN);

            String[] lstTou = GothaRMIClient.tournamentNamesList(Gotha.serverName);
            String strTN = "";
            if (lstTou == null || lstTou.length == 0){
                String strMessage = "No tournament found on " + Gotha.serverName;
                strMessage += "\nBe sure that at least one tournament is open on " + Gotha.serverName;
                strMessage += "\nThen try again";
                JOptionPane.showMessageDialog(this, strMessage);
                return;
            }
            if (lstTou.length > 0) {
                strTN = (String) JOptionPane.showInputDialog(this, "Please, choose a tournament", "OpenGotha",
                        JOptionPane.INFORMATION_MESSAGE, null, lstTou, lstTou[0]);
            }
            
            tournament = GothaRMIClient.getTournament(Gotha.serverName, strTN);
            try {
                Gotha.clientName = tournament.addGothaRMIClient(Gotha.getHostName());
            } catch (RemoteException ex) {
                Logger.getLogger(JFrGothaStart.class.getName()).log(Level.SEVERE, null, ex);
            }
            Gotha.runningMode = Gotha.RUNNING_MODE_CLI;
        }
        
        // Log elements
        String strRM = "";
        switch(Gotha.runningMode){
            case Gotha.RUNNING_MODE_SAL : strRM = "SAL"; break;
            case Gotha.RUNNING_MODE_SRV : strRM = "SRV"; break;
            case Gotha.RUNNING_MODE_CLI : strRM = "CLI"; break;
            default : strRM = "UNDEFINED"; 
        }       
//        LogElements.incrementElement("gotha.runningmode", strRM);

        try {
            new JFrGotha(tournament).setVisible(true);            
        } catch (RemoteException ex) {
            Logger.getLogger(JFrGothaStart.class.getName()).log(Level.SEVERE, null, ex);
        }

        dispose();
    }//GEN-LAST:event_btnStartActionPerformed

    private void btnHelpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHelpActionPerformed
        Gotha.displayGothaHelp("Starting OpenGotha");
    }//GEN-LAST:event_btnHelpActionPerformed

    /**
     * Recursively searches for a directory containing a directory whose name is strFile
     * @param rootDir
     * @param strFile
     * @return
     */
    public File findADirectoryContaining(File rootDir, String strFile){
        if (new File(rootDir, strFile).exists()){
            return rootDir;
        }
        else{
            File[] lst = rootDir.listFiles();
            if (lst == null) return null;
            for (int i = 0; i < lst.length; i++){
                if (!lst[i].isDirectory()) continue;
                if (lst[i].isHidden()) continue;
                File f = findADirectoryContaining(lst[i], strFile);
                if (f!= null){
                    return f;
                }
            }
            return null;
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                new JFrGothaStart().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnHelp;
    private javax.swing.JButton btnStart;
    private javax.swing.ButtonGroup grpLanguage;
    private javax.swing.ButtonGroup grpRunningMode;
    private javax.swing.JPanel pnlRunningMode;
    private javax.swing.JRadioButton rdbClient;
    private javax.swing.JRadioButton rdbSAL;
    private javax.swing.JRadioButton rdbServer;
    // End of variables declaration//GEN-END:variables
}
