import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.sound.midi.MidiUnavailableException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

//time to remove radio button code after I've done the code to play an instrument using a selection from the list
//use getSelectedIndex to play the instrument that is selected in the list

public class NotePlayer extends JFrame implements ActionListener {

	//user-defined serial version UID
	private static final long serialVersionUID = 1L;
	
	//panel to hold main components
	private static JPanel p1;
	
	private static JPanel p2;
	
	//list to hold the instruments
	private static JList<String> instrList;	
	
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
	static int instrument = 0;
	
	//constant is total number of midi instruments
	public static final int INSTR_MAX = 128;
	
	//array to hold the instruments 
	public static String[] instrArray = new String[INSTR_MAX];
	
	
	/*
	 * 
	 * 
	 */
	public void makeGUI(){
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setTitle("Note Player");
		
		//create and add play button
		play = new JButton("Play");
		play.addActionListener(this);
		
		p1 = new JPanel();
		//p1.setLayout(new BorderLayout(11,11));
		add(p1,BorderLayout.LINE_END);
		
		p1.add(play, BorderLayout.LINE_END);
		
		
		p2 = new JPanel();
		p2.setLayout(new BorderLayout(11,11));
		//p2.setLayout(new GridLayout(2, 8, 10, 8));
		add(p2,BorderLayout.CENTER);
		
		//list of all the instruments/sounds
		instrList = new JList<String>(instrArray);
		instrList.setVisibleRowCount(9);
		instrList.setFixedCellWidth(23);
	
		//get the the instrument the user selected
		instrList.addListSelectionListener(new ListSelectionListener(){
			@Override
			public void valueChanged(ListSelectionEvent e) {
				//change list index to be created with data type that getSelectedIndex returns
				int listIndex = instrList.getSelectedIndex();
				instrument  = listIndex;		
		
			}
		});
			
		//end
	
		//scrollpane to hold instrList
		JScrollPane instrScroll = new JScrollPane(instrList);

	    p2.add(instrScroll, BorderLayout.CENTER);    
		//p2.add(instrScroll);
		
		//set up the gui to be visible
		pack();
		setSize(350,350);
		
		setLocationRelativeTo(null);
		setVisible(true);
	}

	
	
	public static void main(String[] args) throws MidiUnavailableException, InterruptedException {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				NotePlayer frame = new NotePlayer();
				
				File file;	
			
				//get the file and read it
				file = new File("instrument_list.txt");
	   
				//try to read the file
				try {
					Scanner sc = new Scanner(file);
					 
	    			readInstruments(sc);                                            
	
					}catch(FileNotFoundException e) {
				        System.out.println("File not found"); 
						e.printStackTrace();
			        }
				
					frame.makeGUI();
				
		
		}});
		
		
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		//make notes object to play notes
		try {
			notePlay.setInstrument(instrument);
			notePlay.play(note, 100);
			
			System.out.println(instrument);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
				
	}
	
	//read the file with the list of instruments and add them to an array
			public static void readInstruments(Scanner fileScanner) {
				
				String text;
				int index = 0;
				
				//loop as long as there are lines to read
				while(fileScanner.hasNextLine())
				{
					//take in the next line and store it in string
					text = fileScanner.nextLine();
					
					//set the contents of the array to the file line by line 
					instrArray[index] = text;
										
					index++;
				}
					
			}	
			
}


