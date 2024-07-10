/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package guiclient;

import java.awt.Toolkit;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
//import java.net.SocketException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author Admin
 */
public class GuiClient extends javax.swing.JFrame {

    private Socket socket;              
    private BufferedReader br;      //citamo poruke od servera
    private PrintWriter pw;         //kad saljemo poruke serveru
    private ReceiveMessageFromServer rmfs;      //privatno polje koje zapravo objekat nove klase ReceiveMessageFromServer
    //to je u suštini thread gdje ćemo primati poruke od servera(to je neophodno jer moramo imati thread gdje ćemo čekati na server)
    private String message;
    
    public GuiClient() {
        
        message = "";
        initComponents();
        
    }
    
    public PrintWriter getPw() {
        return pw;
    }

    public ReceiveMessageFromServer getRmfs() {
        return rmfs;
    }

    public String getMessage() {
        return message;
    }
    
    public BufferedReader getBr() {
        return br;
    }
    
    public Socket getSoc() {
        return socket;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }
    
    public JButton getBtnConnect() {
        return bnConnect;
    }

    public void setBtnConnect(JButton bnConnect) {
        this.bnConnect = bnConnect;
    }

    public JButton getBtnLogin() {
        return bnLogin;
    }

    public void setBtnLogin(JButton bnLogin) {
        this.bnLogin = bnLogin;
    }
    
    public JButton getBtnLogout() {
        return bnLogout;
    }

    public void setBtnLogout(JButton bnLogout) {
        this.bnLogout = bnLogout;
    }

    public JButton getBtnPitanja() {
        return bnPitanja;
    }

    public void bnPitanja(JButton bnPitanja) {
        this.bnPitanja = bnPitanja;
    }
    
    public JButton getBtnPoruka() {
        return bnPoruka;
    }

    public void setBtnPoruka(JButton bnPoruka) {
        this.bnPoruka = bnPoruka;
    }
    
    public JButton getBtnSetPitanja() {
        return bnSetPitanja;
    }

    public void setBtnSetPitanja(JButton bnSetPitanja) {
        this.bnSetPitanja = bnSetPitanja;
    }
    
    public JButton getBtnTabela() {
        return bnTabela;
    }

    public void setBtnTabela(JButton bnTabela) {
        this.bnTabela = bnTabela;
    }
    
    public JButton getBtnUkloni() {
        return bnUkloni;
    }

    public void setBtnUkloni(JButton bnUkloni) {
        this.bnUkloni = bnUkloni;
    }
    
    public JButton getBtnDodaj() {
        return tfDodaj;
    }

    public void setBtnDodaj(JButton tfDodaj) {
        this.tfDodaj = tfDodaj;
    }
    
    public JButton getBtnUcitaj() {
        return bnUcitaj;
    }

    public void setBtnUcitaj(JButton bnZatrazi) {
        this.bnUcitaj = bnZatrazi;
    }
    
    public JLabel getjLabel1() {
        return jLabel1;
    }

    public void setjLabel1(JLabel jLabel1) {
        this.jLabel1 = jLabel1;
    }
    
    public JLabel getjLabel2() {
        return jLabel2;
    }

    public void setjLabel2(JLabel jLabel2) {
        this.jLabel2 = jLabel2;
    }
    
    public JLabel getjLabel3() {
        return jLabel3;
    }

    public void setjLabel3(JLabel jLabel3) {
        this.jLabel3 = jLabel3;
    }
    
    public JLabel getjLabel4() {
        return jLabel4;
    }

    public void setjLabel4(JLabel jLabel4) {
        this.jLabel4 = jLabel4;
    }
    
    public JLabel getjLabel5() {
        return jLabel5;
    }

    public void setjLabel5(JLabel jLabel5) {
        this.jLabel5 = jLabel5;
    }
    
    public JLabel getjLabel6() {
        return jLabel6;
    }

    public void setjLabel6(JLabel jLabel6) {
        this.jLabel6 = jLabel6;
    }
    
    public JLabel getjLabel7() {
        return jLabel7;
    }

    public void setjLabel7(JLabel jLabel7) {
        this.jLabel7 = jLabel7;
    }
    
    public JLabel getjLabel8() {
        return jLabel8;
    }

    public void setjLabel8(JLabel jLabel8) {
        this.jLabel8 = jLabel8;
    }
    
    public JLabel getjLabel9() {
        return jLabel9;
    }

    public void setjLabel9(JLabel jLabel9) {
        this.jLabel9 = jLabel9;
    }
    
    public JLabel getjLabel10() {
        return jLabel10;
    }

    public void setjLabel10(JLabel jLabel10) {
        this.jLabel10 = jLabel10;
    }
    
    public JLabel getjLabel11() {
        return jLabel11;
    }

    public void setjLabel11(JLabel jLabel11) {
        this.jLabel11 = jLabel11;
    }
    
    public JScrollPane getjScrollPane1() {
        return jScrollPane1;
    }

    public void setjScrollPane1(JScrollPane jScrollPane1) {
        this.jScrollPane1 = jScrollPane1;
    }
    
    public JTextArea getTa() {
        return taPoruka;
    }

    public void setTa(JTextArea taPoruka) {
        this.taPoruka = taPoruka;
    }
    
    public JTextField getTfIme() {
        return tfIme;
    }

    public void setTfIme(JTextField tfIme) {
        this.tfIme = tfIme;
    }
    
    public JTextField getTfImeKreiranje() {
        return tfImeKreiranje;
    }

    public void setTfImeKreiranje(JTextField tfImeKreiranje) {
        this.tfImeKreiranje = tfImeKreiranje;
    }
    
    public JTextField getTfLogovanje() {
        return tfImeLogovanje;
    }

    public void setTfLogovanje(JTextField tfImeLogovanje) {
        this.tfImeLogovanje = tfImeLogovanje;
    }
    
    public JTextField getTfPoruka() {
        return tfPoruka;
    }

    public void setTfPoruka(JTextField tfPoruka) {
        this.tfPoruka = tfPoruka;
    }
    
    public JTextField getTfSetPitanja() {
        return tfSetPitanja;
    }

    public void setTfSetPitanja(JTextField tfSetPitanja) {
        this.tfSetPitanja = tfSetPitanja;
    }
    
    public JTextField getTfSifraKreiranje() {
        return tfSifraKreiranje;
    }

    public void setTfSifraKreiranje(JTextField tfSifraKreiranje) {
        this.tfSifraKreiranje = tfSifraKreiranje;
    }
    
    public JTextField getTfSifraLogovanje() {
        return tfSifraLogovanje;
    }

    public void setTfSifraLogovanje(JTextField tfSifraLogovanje) {
        this.tfSifraLogovanje = tfSifraLogovanje;
    }
    
    public JTextField getTfUkloni() {
        return tfUkloni;
    }

    public void setTfUkloni(JTextField tfUkloni) {
        this.tfUkloni = tfUkloni;
    }
    
    public JTextField getTfUlogaKreiranje() {
        return tfUlogaKreiranje;
    }

    public void setTfUlogaKreiranje(JTextField tfUlogaKreiranje) {
        this.tfUlogaKreiranje = tfUlogaKreiranje;
    }
    
    public JTextField getTfUlogaLogovanje() {
        return tfUlogaLogovanje;
    }

    public void setTfUlogaLogovanje(JTextField tfUlogaLogovanje) {
        this.tfUlogaLogovanje = tfUlogaLogovanje;
    }
    
    
    public JTextField getTfPitanja() {
        return tfPitanja;
    }

    public void setTfPitanja(JTextField tfPitanja) {
        this.tfPitanja = tfPitanja;
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        tfIme = new javax.swing.JTextField();
        bnTabela = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        taPoruka = new javax.swing.JTextArea();
        bnPitanja = new javax.swing.JButton();
        tfPoruka = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        tfImeLogovanje = new javax.swing.JTextField();
        tfSifraLogovanje = new javax.swing.JTextField();
        tfUlogaLogovanje = new javax.swing.JTextField();
        bnLogin = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        tfUlogaKreiranje = new javax.swing.JTextField();
        tfSifraKreiranje = new javax.swing.JTextField();
        tfImeKreiranje = new javax.swing.JTextField();
        tfDodaj = new javax.swing.JButton();
        bnUkloni = new javax.swing.JButton();
        tfUkloni = new javax.swing.JTextField();
        bnSetPitanja = new javax.swing.JButton();
        tfSetPitanja = new javax.swing.JTextField();
        bnPoruka = new javax.swing.JButton();
        bnLogout = new javax.swing.JButton();
        bnConnect = new javax.swing.JButton();
        tfPitanja = new javax.swing.JTextField();
        bnUcitaj = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("PODACI O TAKMICARU");

        jLabel2.setText("Ime takmicara:");

        tfIme.setEnabled(false);
        tfIme.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfImeActionPerformed(evt);
            }
        });

        bnTabela.setText("Provjeri tabelu");
        bnTabela.setEnabled(false);
        bnTabela.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bnTabelaActionPerformed(evt);
            }
        });

        taPoruka.setColumns(20);
        taPoruka.setRows(5);
        taPoruka.setEnabled(false);
        jScrollPane1.setViewportView(taPoruka);

        bnPitanja.setText("Zatrazi pitanja");
        bnPitanja.setEnabled(false);
        bnPitanja.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bnPitanjaActionPerformed(evt);
            }
        });

        tfPoruka.setEnabled(false);
        tfPoruka.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfPorukaActionPerformed(evt);
            }
        });

        jLabel3.setText("Logovanje:");

        jLabel4.setText("Korisnicko ime:");

        jLabel5.setText("Sifra:");

        jLabel6.setText("Uloga:");

        tfImeLogovanje.setEnabled(false);
        tfImeLogovanje.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfImeLogovanjeActionPerformed(evt);
            }
        });

        tfSifraLogovanje.setEnabled(false);

        tfUlogaLogovanje.setEnabled(false);
        tfUlogaLogovanje.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfUlogaLogovanjeActionPerformed(evt);
            }
        });

        bnLogin.setText("Login");
        bnLogin.setEnabled(false);
        bnLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bnLoginActionPerformed(evt);
            }
        });

        jLabel7.setText("FORMA ZA ADMINE");

        jLabel8.setText("Dodavanje novih korisnika");

        jLabel9.setText("Ime korisnika");

        jLabel10.setText("Sifra");

        jLabel11.setText("Uloga");

        tfUlogaKreiranje.setEnabled(false);
        tfUlogaKreiranje.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfUlogaKreiranjeActionPerformed(evt);
            }
        });

        tfSifraKreiranje.setToolTipText("");
        tfSifraKreiranje.setEnabled(false);
        tfSifraKreiranje.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfSifraKreiranjeActionPerformed(evt);
            }
        });

        tfImeKreiranje.setEnabled(false);
        tfImeKreiranje.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfImeKreiranjeActionPerformed(evt);
            }
        });

        tfDodaj.setText("Dodaj korisnika");
        tfDodaj.setEnabled(false);
        tfDodaj.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bnDodajActionPerformed(evt);
            }
        });

        bnUkloni.setText("Ukloni korisnika");
        bnUkloni.setEnabled(false);
        bnUkloni.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bnUkloniActionPerformed(evt);
            }
        });

        tfUkloni.setEnabled(false);
        tfUkloni.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfUkloniActionPerformed(evt);
            }
        });

        bnSetPitanja.setText("Set pitanja");
        bnSetPitanja.setEnabled(false);
        bnSetPitanja.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bnSetPitanjaActionPerformed(evt);
            }
        });

        tfSetPitanja.setEnabled(false);

        bnPoruka.setText("Posalji poruku");
        bnPoruka.setEnabled(false);
        bnPoruka.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bnPorukaActionPerformed(evt);
            }
        });

        bnLogout.setText("Logout");
        bnLogout.setEnabled(false);
        bnLogout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bnLogoutActionPerformed(evt);
            }
        });

        bnConnect.setText("Connect");
        bnConnect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bnConnectActionPerformed(evt);
            }
        });

        tfPitanja.setEnabled(false);
        tfPitanja.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfPitanjaActionPerformed(evt);
            }
        });

        bnUcitaj.setText("Ucitaj pitanja");
        bnUcitaj.setEnabled(false);
        bnUcitaj.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bnUcitajActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(18, 18, 18)
                                .addComponent(tfIme, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(bnPitanja, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(bnTabela, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(bnPoruka, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(tfPoruka)
                                    .addComponent(tfPitanja)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(bnUcitaj, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, Short.MAX_VALUE))))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 296, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(50, 50, 50)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel5)
                                    .addComponent(bnLogin))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(tfSifraLogovanje)
                                        .addComponent(tfUlogaLogovanje)
                                        .addComponent(tfImeLogovanje, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(bnLogout)))
                            .addComponent(bnConnect, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 66, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addComponent(jLabel7))
                        .addGap(105, 105, 105))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(tfDodaj)
                        .addGap(100, 100, 100))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(bnUkloni)
                            .addComponent(bnSetPitanja, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tfSetPitanja, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tfUkloni, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(61, 61, 61))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9)
                            .addComponent(jLabel10)
                            .addComponent(jLabel11))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(tfUlogaKreiranje, javax.swing.GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE)
                            .addComponent(tfSifraKreiranje)
                            .addComponent(tfImeKreiranje))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel1)
                        .addGap(27, 27, 27)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(tfIme, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(bnPitanja)
                            .addComponent(tfPitanja, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(bnTabela)
                            .addComponent(bnUcitaj))
                        .addGap(24, 24, 24)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tfPoruka, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(bnPoruka)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(24, 24, Short.MAX_VALUE)
                        .addComponent(jLabel7)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(27, 27, 27)
                                .addComponent(jLabel8)
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel9)
                                    .addComponent(tfImeKreiranje, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel10)
                                    .addComponent(tfSifraKreiranje, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel11)
                                    .addComponent(tfUlogaKreiranje, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(tfDodaj)
                                .addGap(45, 45, 45)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(bnUkloni)
                                    .addComponent(tfUkloni, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(bnSetPitanja)
                                    .addComponent(tfSetPitanja, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(92, 92, 92)
                                .addComponent(bnConnect, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 21, Short.MAX_VALUE)
                                .addComponent(jLabel3)
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel4)
                                    .addComponent(tfImeLogovanje, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(9, 9, 9)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel5)
                                    .addComponent(tfSifraLogovanje, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel6)
                                    .addComponent(tfUlogaLogovanje, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(bnLogin)
                            .addComponent(bnLogout))))
                .addGap(46, 46, 46))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tfImeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfImeActionPerformed
        // TODO add your handling code here:
//        if (!this.tfImeLogovanje.getText().equals("")) {
//            String poruka = this.tfImeLogovanje.getText();
//            this.tfIme.setText(poruka);
//        }
    }//GEN-LAST:event_tfImeActionPerformed

    private void bnTabelaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bnTabelaActionPerformed
        // TODO add your handling code here:
        System.out.println("Show table");
        String prvaPoruka = "Tabela";
        this.pw.println(prvaPoruka);
    }//GEN-LAST:event_bnTabelaActionPerformed

    private void bnPitanjaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bnPitanjaActionPerformed
        // TODO add your handling code here:
        if (!this.tfPitanja.getText().equals("")) {
            System.out.println("Asking for question");

            String porukaZaSlanje = this.tfPitanja.getText();
            System.out.println(porukaZaSlanje);
            this.pw.println(porukaZaSlanje);
        }
        
    }//GEN-LAST:event_bnPitanjaActionPerformed

    private void tfPorukaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfPorukaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfPorukaActionPerformed

    private void tfImeLogovanjeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfImeLogovanjeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfImeLogovanjeActionPerformed

    private void tfImeKreiranjeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfImeKreiranjeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfImeKreiranjeActionPerformed

    private void bnDodajActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bnDodajActionPerformed
        // TODO add your handling code here:
        System.out.println("Adding now user");
        String regexUsername = "^[a-zA-Z][a-zA-Z0-9]*$";
        String regexPassword = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{6,}$";
        
        if ( this.tfImeKreiranje.getText().matches(regexUsername) && this.tfSifraKreiranje.getText().matches(regexPassword) && (this.tfUlogaKreiranje.getText().matches("admin") || this.tfUlogaKreiranje.getText().matches("takmicar")) ) {
            
            String prvaPoruka;
            prvaPoruka = this.tfImeKreiranje.getText() + ":" + this.tfSifraKreiranje.getText() + ":" + this.tfUlogaKreiranje.getText();
            String porukaZaSlanje = "Dodavanje:" + prvaPoruka;
            System.out.println(porukaZaSlanje);
            this.pw.println(porukaZaSlanje);
            //this.taPoruka.setText("poslat korisnik");
            //bnLogout.setEnabled(true);
        }
        else{
            
            this.getTa().setEnabled(true);
            this.taPoruka.setText("los unos");
            
        }
    }//GEN-LAST:event_bnDodajActionPerformed
    
    
    private void bnConnectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bnConnectActionPerformed
        
        System.out.println("Now i'm going to connect to the server.");
        try {
            //Kreiraj novi socket (ako nije localhost, treba promeniti IP adresu)
            this.socket = new Socket("127.0.0.1", 6001);
            //napravi BufferedReader i PrintWriter kako bi slao i primao poruke
            this.br = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
            this.pw = new PrintWriter(new OutputStreamWriter(this.socket.getOutputStream()), true);
            //za prijem poruka od servera (stizace asinhrono) koristi poseban thread
            //da bismo u novom thread-u mogli da menjamo sadrzaj komponenti (npr Combo Box-a)
            //konstruktoru novog thread-a se prosledjuje this, u novom threadu gdje primamo poruku, treba osvjeziti komponentu u GUI thread-u, saljemo
            //objekat ove klase, znaci cim primimo nesto potrebno je osvjeziti
            this.rmfs = new ReceiveMessageFromServer(this);
            Thread thr = new Thread(rmfs);
            thr.start();

            
            tfImeLogovanje.setEnabled(true);
            tfSifraLogovanje.setEnabled(true);
            tfUlogaLogovanje.setEnabled(true);
            bnLogin.setEnabled(true);
            //bnLogout.setEnabled(true);
            
            bnConnect.setEnabled(false);
            
        } catch (IOException ex) {
            Logger.getLogger(GuiClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_bnConnectActionPerformed
    
    private void tfUlogaKreiranjeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfUlogaKreiranjeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfUlogaKreiranjeActionPerformed

    private void tfPitanjaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfPitanjaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfPitanjaActionPerformed

    private void tfUlogaLogovanjeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfUlogaLogovanjeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfUlogaLogovanjeActionPerformed

    private void bnLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bnLoginActionPerformed
        // TODO add your handling code here:
        System.out.println("User Login");

        if (!this.tfImeLogovanje.getText().equals("")) {
                this.pw.println( tfImeLogovanje.getText() + ":" + tfSifraLogovanje.getText() + ":" + tfUlogaLogovanje.getText());

        } 
    }//GEN-LAST:event_bnLoginActionPerformed

    private void bnPorukaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bnPorukaActionPerformed
        // TODO add your handling code here:
        if(!tfPoruka.getText().equals("")){
            System.out.println("Sending message");
            String prvaPoruka;
            prvaPoruka = this.tfPoruka.getText();
            String porukaZaSlanje = "Poslata poruka takmicara:" + prvaPoruka;
            this.pw.println(porukaZaSlanje);
        }
    }//GEN-LAST:event_bnPorukaActionPerformed

    private void bnUkloniActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bnUkloniActionPerformed
        // TODO add your handling code here:     
        System.out.println("Removing user");
        String prvaPoruka;
        prvaPoruka = this.tfUkloni.getText();
        String porukaZaSlanje = "Uklanjanje:" + prvaPoruka;
        System.out.println(porukaZaSlanje);
        this.pw.println(porukaZaSlanje);
    }//GEN-LAST:event_bnUkloniActionPerformed

    private void bnSetPitanjaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bnSetPitanjaActionPerformed
        // TODO add your handling code here:
        System.out.println("Activating set of questions");
        String prvaPoruka;
        if(this.tfSetPitanja.getText().equals("set1.txt") || this.tfSetPitanja.getText().equals("set2.txt") || this.tfSetPitanja.getText().equals("set3.txt") || this.tfSetPitanja.getText().equals("set4.txt")){
            prvaPoruka = this.tfSetPitanja.getText();
            String porukaZaSlanje ="Pitanja:" + prvaPoruka;
            System.out.println(porukaZaSlanje);
            this.pw.println(porukaZaSlanje);
        }
        else{
            this.getTa().setEnabled(true);
            this.taPoruka.setText("Los unos pitanja");
        }
    }//GEN-LAST:event_bnSetPitanjaActionPerformed

    private void tfUkloniActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfUkloniActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfUkloniActionPerformed

    private void tfSifraKreiranjeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfSifraKreiranjeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfSifraKreiranjeActionPerformed

    private void bnLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bnLogoutActionPerformed
        // TODO add your handling code here: 
        String prvaPoruka = "User:Logout";
        this.pw.println(prvaPoruka);
        System.out.println("Loging out");
        WindowEvent windowClosing = new WindowEvent(this, WindowEvent.WINDOW_CLOSING);
        Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(windowClosing);

    }//GEN-LAST:event_bnLogoutActionPerformed

    private void bnUcitajActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bnUcitajActionPerformed
        // TODO add your handling code here:
        if (!this.taPoruka.getText().equals("")) {
            System.out.println("Loading Questions");
            String poruka = "Pitanja:";
            String porukaZaSlanje = poruka + this.taPoruka.getText();
            this.pw.println(porukaZaSlanje);
        }
    }//GEN-LAST:event_bnUcitajActionPerformed

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
            java.util.logging.Logger.getLogger(GuiClient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GuiClient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GuiClient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GuiClient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GuiClient().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bnConnect;
    private javax.swing.JButton bnLogin;
    private javax.swing.JButton bnLogout;
    private javax.swing.JButton bnPitanja;
    private javax.swing.JButton bnPoruka;
    private javax.swing.JButton bnSetPitanja;
    private javax.swing.JButton bnTabela;
    private javax.swing.JButton bnUcitaj;
    private javax.swing.JButton bnUkloni;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea taPoruka;
    private javax.swing.JButton tfDodaj;
    private javax.swing.JTextField tfIme;
    private javax.swing.JTextField tfImeKreiranje;
    private javax.swing.JTextField tfImeLogovanje;
    private javax.swing.JTextField tfPitanja;
    private javax.swing.JTextField tfPoruka;
    private javax.swing.JTextField tfSetPitanja;
    private javax.swing.JTextField tfSifraKreiranje;
    private javax.swing.JTextField tfSifraLogovanje;
    private javax.swing.JTextField tfUkloni;
    private javax.swing.JTextField tfUlogaKreiranje;
    private javax.swing.JTextField tfUlogaLogovanje;
    // End of variables declaration//GEN-END:variables

}
