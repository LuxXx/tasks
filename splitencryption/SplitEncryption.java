import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

public class SplitEncryption extends JFrame {
	
	private static final long serialVersionUID = 7934407244052825628L;

	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel( UIManager.getSystemLookAndFeelClassName() );
		} catch( Exception e ) {
			// Problem changing UI occured. Ignore and show in default mode instead.
		}
		new SplitEncryption();
	}
	public SplitEncryption() {
		setPreferredSize(new Dimension(600,200));
		pack();
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setTitle("Split Encryption");
		setLayout(new GridLayout(1,2));
		JButton encrypt = new JButton("Encrypt file");
		encrypt.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser ch = new JFileChooser();
				int result = ch.showOpenDialog(SplitEncryption.this);
				if (result == JFileChooser.APPROVE_OPTION) {		
					File file = ch.getSelectedFile();
					encryptFile(file);
				}
			}
		});
		add(encrypt);
		
		JButton decrypt = new JButton("Decrypt file");
		
		decrypt.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser ch = new JFileChooser();
				int result = ch.showOpenDialog(SplitEncryption.this);
				if (result == JFileChooser.APPROVE_OPTION) {		
					File file = ch.getSelectedFile();
					JFileChooser ch2 = new JFileChooser();
					int result2 = ch2.showOpenDialog(SplitEncryption.this);
					if (result2 == JFileChooser.APPROVE_OPTION) {		
						File file2 = ch2.getSelectedFile();
						decrypt(file, file2);
					}
				}
			}
		});
		
		add(decrypt);
		
		setVisible(true);
	}
	
	private void encryptFile(File f) {
		try {
			byte[] baFile = Files.readAllBytes(f.toPath());
			
			// generate a random key
			byte[] key = new byte[baFile.length];
			for (int i = 0; i < key.length; i++) {
				key[i] = randomByte();
			}
			
			// encrypt with key
			for (int i = 0; i < baFile.length; i++) {
				baFile[i] = (byte) (baFile[i] ^ key[i]);
			}
			// store crypt file and key
			Files.write(new File(f.getAbsolutePath() + ".crypt1").toPath(), key, StandardOpenOption.CREATE);
			Files.write(new File(f.getAbsolutePath() + ".crypt2").toPath(), baFile, StandardOpenOption.CREATE);
			
			JOptionPane.showMessageDialog(this, f.getName() + " decrypted to " + f.getName() + ".key and " + f.getName() + ".crypt");
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void decrypt(File f, File f2) {

		try {
			byte[] af1 = Files.readAllBytes(f.toPath());
			byte[] af2 = Files.readAllBytes(f2.toPath());
			if (af1.length != af2.length) {
				JOptionPane.showMessageDialog(this, "Files do not match.");
				return;
			}
			for (int i = 0; i < af2.length; i++) {
				af1[i] = (byte) (af1[i] ^ af2[i]);
			}
			Files.write(new File(f.getAbsolutePath().substring(0,f.getAbsolutePath().length() - 6)+ ".decrypt").toPath(), af1, StandardOpenOption.CREATE);
			JOptionPane.showMessageDialog(this, "File decrypted");

			
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}
	
	private static byte randomByte() {
		return (byte) (Math.random()*256);
	}
}
