import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.DefaultListModel;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class CekilisEkrani extends javax.swing.JFrame {
    private String dosyaYolu = null; // Seçilen dosyanın yolunu tutmak için değişken
    DefaultListModel model = new DefaultListModel(); // JList için kullanılacak model
    private Set<String> kazananlar =  new TreeSet<String>(); // Kazananların tutulacağı küme
    private ArrayList<String> katilanlar = new ArrayList<String>(); // Çekilişe katılanların tutulacağı liste

    /** Creates new form CekilisEkrani */
    public void alkisSesi() { // Alkış sesini çalmak için metot
        try {
            File file = new File("alkış.wav"); // Alkış sesi dosyasının yolunu belirtme
            Clip clip = AudioSystem.getClip(); // Ses klibi oluşturma
            clip.open(AudioSystem.getAudioInputStream(file)); // Ses dosyasını klibe yükleme
            clip.start(); // Ses dosyasını çalma
        } catch (Exception e) {
            System.err.println(e.getMessage()); // Hata durumunda hata mesajını yazdırma
        }
    }

    public CekilisEkrani()  { // Kurucu metot
        initComponents();// Swing bileşenlerini başlatma
        kazanan_listesi.setModel(model); // Kazananlar listesine modeli ayarlama
    }

    private void initComponents() {
    }

    // Diğer otomatik oluşturulmuş kodlar ve bileşenler burada yer alır...

    private void gozatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_gozatActionPerformed
        JFileChooser fc = new JFileChooser(); // Dosya seçici oluşturma

        int i = fc.showOpenDialog(this); // Dosya seçici penceresini açma

        if (i == JFileChooser.APPROVE_OPTION) { // Kullanıcı dosyayı seçerse
            this.dosyaYolu = fc.getSelectedFile().getPath(); // Seçilen dosyanın yolunu al
            cekilisDosyasi.setText(this.dosyaYolu); // Dosya yolunu ekrana yazdır
        }
    }//GEN-LAST:event_gozatActionPerformed

    public void kazananlariBelirle() { // Kazananları belirleme metodu
        Random random = new Random(); // Rastgele sayı üretmek için nesne oluşturma

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(dosyaYolu), "ISO8859_9"))) {
            String kelime;
            while ((kelime = reader.readLine()) != null) { // Dosya satır satır okunduğu sürece
                katilanlar.add(kelime); // Katılanlar listesine katılımcıları ekle
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(CekilisEkrani.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(CekilisEkrani.class.getName()).log(Level.SEVERE, null, ex);
        }

        while (kazananlar.size() != 10) { // Kazananlar listesi 10 kişi olana kadar
            kazananlar.add(katilanlar.get(random.nextInt(katilanlar.size()))); // Rastgele bir kazanan seç
        }
    }

    private void cekilisBaslatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cekilisBaslatActionPerformed
        if (this.dosyaYolu == null) { // Dosya seçilmediyse
            JOptionPane.showMessageDialog(this, "Lütfen bir çekiliş dosyası seçin."); // Uyarı mesajı göster
        } else {
            kazananlariBelirle(); // Kazananları belirle
            for (String kazanan : kazananlar) { // Her kazanan için
                model.addElement(kazanan); // Kazananları ekrana ekle
            }
            alkisSesi(); // Alkış sesini çal
        }
    }//GEN-LAST:event_cekilisBaslatActionPerformed

    // Diğer otomatik oluşturulmuş kodlar burada yer alır...

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(CekilisEkrani.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CekilisEkrani.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CekilisEkrani.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CekilisEkrani.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CekilisEkrani().setVisible(true); // Çekiliş ekranını görünür yap
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cekilisBaslat;
    private javax.swing.JTextField cekilisDosyasi;
    private javax.swing.JButton gozat;

    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JList<String> kazanan_listesi;
    // End of variables declaration//GEN-END:variables

}
