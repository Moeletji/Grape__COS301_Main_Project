/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package simulatorInterface;

import financialmarketsimulator.exception.NotEnoughDataException;
import financialmarketsimulator.market.MarketEntryAttempt;
import financialmarketsimulator.market.MarketExchange;
import financialmarketsimulator.market.MarketParticipant;
import financialmarketsimulator.market.StockManager;
import financialmarketsimulator.marketData.MatchedMarketEntryAttempt;
import financialmarketsimulator.strategies.Phantom;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;

/**
 *
 * @author Daniel Makgonta
 */
public class MatchEngine extends javax.swing.JFrame {

    private StockManager manager;

    public MatchEngine() {
        initComponents();
        manager = new StockManager("INV");
        try {
            manager.attach(new MarketParticipant("grape", "grape", null, "INV", new Phantom()));
        } catch (NotEnoughDataException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        btnTrade = new javax.swing.JButton();
        txtPricePerShare = new javax.swing.JTextField();
        txtNumberOfShares = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        lbxTrades = new javax.swing.JList();
        jScrollPane2 = new javax.swing.JScrollPane();
        lbxBids = new javax.swing.JList();
        jScrollPane3 = new javax.swing.JScrollPane();
        lbxOffers = new javax.swing.JList();
        jLabel7 = new javax.swing.JLabel();
        cbxSide = new javax.swing.JComboBox();
        jLabel8 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(0, 0, 0));
        jPanel1.setLayout(null);

        btnTrade.setBackground(new java.awt.Color(51, 255, 0));
        btnTrade.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnTrade.setText("Trade");
        btnTrade.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(0, 255, 0), new java.awt.Color(0, 255, 51), java.awt.Color.black, new java.awt.Color(51, 255, 0)));
        btnTrade.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTradeActionPerformed(evt);
            }
        });
        jPanel1.add(btnTrade);
        btnTrade.setBounds(40, 290, 100, 50);

        txtPricePerShare.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.pink, java.awt.Color.pink, java.awt.Color.pink, java.awt.Color.pink));
        jPanel1.add(txtPricePerShare);
        txtPricePerShare.setBounds(20, 150, 140, 30);

        txtNumberOfShares.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.pink, java.awt.Color.pink, java.awt.Color.pink, java.awt.Color.pink));
        txtNumberOfShares.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNumberOfSharesActionPerformed(evt);
            }
        });
        jPanel1.add(txtNumberOfShares);
        txtNumberOfShares.setBounds(20, 80, 140, 30);

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Trades");
        jPanel1.add(jLabel2);
        jLabel2.setBounds(670, 50, 60, 20);

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Side:");
        jPanel1.add(jLabel3);
        jLabel3.setBounds(20, 200, 110, 14);

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Number of Shares:");
        jPanel1.add(jLabel4);
        jLabel4.setBounds(20, 60, 110, 14);

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Matching Engine");
        jPanel1.add(jLabel5);
        jLabel5.setBounds(290, 10, 200, 30);

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Offers");
        jPanel1.add(jLabel6);
        jLabel6.setBounds(450, 50, 60, 20);

        lbxTrades.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(51, 255, 0), new java.awt.Color(51, 255, 0), java.awt.Color.green, java.awt.Color.green));
        jScrollPane1.setViewportView(lbxTrades);

        jPanel1.add(jScrollPane1);
        jScrollPane1.setBounds(580, 80, 240, 260);

        lbxBids.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(51, 255, 0), new java.awt.Color(51, 255, 0), java.awt.Color.green, java.awt.Color.green));
        jScrollPane2.setViewportView(lbxBids);

        jPanel1.add(jScrollPane2);
        jScrollPane2.setBounds(210, 80, 160, 260);

        lbxOffers.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(51, 255, 0), new java.awt.Color(51, 255, 0), java.awt.Color.green, java.awt.Color.green));
        jScrollPane3.setViewportView(lbxOffers);

        jPanel1.add(jScrollPane3);
        jScrollPane3.setBounds(400, 80, 160, 260);

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Bids");
        jPanel1.add(jLabel7);
        jLabel7.setBounds(260, 50, 50, 20);

        cbxSide.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "BID", "OFFER" }));
        jPanel1.add(cbxSide);
        cbxSide.setBounds(20, 220, 140, 20);

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Price per Share:");
        jPanel1.add(jLabel8);
        jLabel8.setBounds(20, 130, 110, 14);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 833, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 383, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnTradeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTradeActionPerformed
        String priceString = txtPricePerShare.getText();
        String shareString = txtNumberOfShares.getText();
        String side = cbxSide.getSelectedItem().toString();

        if ("".equals(priceString) || "".equals(shareString) || "".equals(side)) {
            MessageBox.infoBox("Please fill in both fields", "Empty Fields");
            return;
        }

        if (!Number.isInteger(shareString) || !Number.isDouble(priceString)) {
            MessageBox.infoBox("Numbers are not formatted correctly", "Number Format Inconsistency");
            return;
        }

        int shares = Integer.parseInt(shareString);
        double price = Double.parseDouble(priceString);
        MarketEntryAttempt.SIDE _side;

        switch (side) {
            case "BID":
                _side = MarketEntryAttempt.SIDE.BID;
                break;

            case "OFFER":
                _side = MarketEntryAttempt.SIDE.OFFER;
                break;

            default:
                _side = MarketEntryAttempt.SIDE.BID;
        }

        MarketEntryAttempt attempt = new MarketEntryAttempt(price, shares, "grape", _side);
        try {
            manager.acceptOrder(attempt);
        } catch (InterruptedException ex) {
            Logger.getLogger(MatchEngine.class.getName()).log(Level.SEVERE, null, ex);
        }

        updateDisplay();
    }//GEN-LAST:event_btnTradeActionPerformed

    private void updateDisplay() {
        //Code used to update GUI
        DefaultListModel modelBids = new DefaultListModel();
        DefaultListModel modelOffers = new DefaultListModel();
        DefaultListModel modelMatched = new DefaultListModel();

        Vector offers = manager.getOrderList().getOffers();
        Vector bids = manager.getOrderList().getBids();
        Vector matched = manager.getOrderList().getMatchedOrders();

        for (int i = 0; i < bids.size(); i++) {
            MarketEntryAttempt attempt = (MarketEntryAttempt) bids.get(i);
            modelBids.addElement(attempt.toString());
        }

        for (int i = 0; i < offers.size(); i++) {
            MarketEntryAttempt attempt = (MarketEntryAttempt) offers.get(i);
            modelOffers.addElement(attempt.toString());
        }

        for (int i = 0; i < matched.size(); i++) {
            MatchedMarketEntryAttempt attempt = (MatchedMarketEntryAttempt) matched.get(i);
            modelMatched.addElement(attempt.toString());
        }

        if ((lbxBids != null) && (lbxOffers != null) && (lbxTrades != null)) {
            lbxBids.setModel(modelBids);
            lbxOffers.setModel(modelOffers);
            lbxTrades.setModel(modelMatched);
        }
    }

    private void txtNumberOfSharesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNumberOfSharesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNumberOfSharesActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {

        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MatchEngine.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MatchEngine.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MatchEngine.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MatchEngine.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MatchEngine().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnTrade;
    private javax.swing.JComboBox cbxSide;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JList lbxBids;
    private javax.swing.JList lbxOffers;
    private javax.swing.JList lbxTrades;
    private javax.swing.JTextField txtNumberOfShares;
    private javax.swing.JTextField txtPricePerShare;
    // End of variables declaration//GEN-END:variables
}