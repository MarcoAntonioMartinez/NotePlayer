import javax.sound.midi.MidiChannel;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Synthesizer;

public class Notes {
			//default instrument   
			private static final int DEFAULT_INSTRUMENT = 1;
			
			//midi channel
			private MidiChannel channel;
			
			//midi player for default instrument
			public Notes() throws MidiUnavailableException{
				
				this(DEFAULT_INSTRUMENT);
			} 
		
			//midi player for intruments obtained from channel
			public Notes(int instrument) throws MidiUnavailableException{
				channel = getChannel(instrument);
			}
			
			//set the intrument
			public void setInstrument(int instrument){
				channel.programChange(instrument);
			}
			
			//get the instrument
			public int getInstrument(){
				return channel.getProgram();
			}
			
			//turn the note on
			public void play(final int note){
				channel.noteOn(note, 50);
			}

			//turn the note off
			public void release(final int note)
			{
				channel.noteOff(note, 50);
			}
			
			//play the note 
			public void play(final int note, final long length) throws InterruptedException{
				play(note);
				Thread.sleep(length);
				release(note);
			}
			
			//stop the note playing
			public void stop(){
				channel.allNotesOff();
			}
			
			//gets the channel
			private static MidiChannel getChannel(int instrument) throws MidiUnavailableException{
			Synthesizer synth = MidiSystem.getSynthesizer();
				synth.open();
				return synth.getChannels()[instrument];
			}
			
		
}
