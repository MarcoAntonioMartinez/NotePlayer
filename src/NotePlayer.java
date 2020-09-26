import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.sound.midi.MidiUnavailableException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class NotePlayer extends JFrame implements ActionListener {

	//put gui stuff here
	private static final long serialVersionUID = 1L;
	
	//panel to hold main components
	private static JPanel p1;
	
	//list to hold the instruments
	private static JList<String> instrList;	
	
	public static boolean buttonSelected = false;
	
	private static JRadioButton drumButton;
	
	private static JButton play;
	
	
	//notes object to play notes
	private static Notes notePlay;
	
	//static init for the Notes object
	static {
		try {
			notePlay = new Notes();
		} catch (RuntimeException run) {
			run.printStackTrace();
		}catch (MidiUnavailableException midi) {
			midi.printStackTrace();
		}
	}
	//the note
	int note = 100;
	
	//the instrument to be played
	int instrument = 0;
	
	//array to hold the instruments 
	public static String[] instrArray;
	
	public static String[] piano = new String[PIANO.getInstr()];
	public static String[] chromaPerc;
	public static String[] organ;
	public static String[] guitar;
	public static String[] bass;
	public static String[] strings;
	public static String[] ensemble;
	public static String[] brass;
	public static String[] reed;
	public static String[] pipe;
	public static String[] synthLead;
	public static String[] synthPad;
	public static String[] synthEffects;
	public static String[] ethnic;
	public static String[] percusv;
	public static String[] sndFx;
	
	public void makeGUI(){
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setSize(950,350);
		
		
		
		//action listener for the radio buttons
		RadioButtonActionListener rbActionLstnr = new RadioButtonActionListener();
		
		String drumStr = "Drums";
		
		drumButton = new JRadioButton(drumStr);
		drumButton.addActionListener(rbActionLstnr);
		
		play = new JButton("Play");
		play.addActionListener(this);
		
		p1 = new JPanel();
		p1.setLayout(new BorderLayout(11,11));
		add(p1,BorderLayout.LINE_START);
		
		
		p1.add(drumButton, BorderLayout.LINE_START);
		p1.add(play, BorderLayout.LINE_END);
		
		//set up the gui to be visible
		pack();
		setLocation(0,0);
		setVisible(true);
	}

	
	public static void main(String[] args) throws MidiUnavailableException, InterruptedException {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				NotePlayer frame = new NotePlayer();
			
				frame.makeGUI();
			
		}});
		
		
	}
	
	class RadioButtonActionListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			//button used to check which button is selected
			JRadioButton button = (JRadioButton) e.getSource();
			
			if(button == drumButton) {
				instrument = 115;
				notePlay.setInstrument(instrument);
						
			}
			
			else {
				instrument = 0;
				notePlay.setInstrument(instrument);
			}

			
		}
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		//make notes object to play notes
		try {
			notePlay.play(note, 100);
			System.out.println(instrument);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
				
	}
			
}


