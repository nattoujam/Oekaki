/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chapter12;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Sequence;
import javax.sound.midi.Sequencer;

/**
 *
 * @author local-nattou
 */
public class SoundEffect {
    
    private Sequencer sequencer;
    
    public SoundEffect() {
        sequencer = null;
        try {
            sequencer = MidiSystem.getSequencer();
            sequencer.open();
        }
        catch (MidiUnavailableException ex) {
            Logger.getLogger(SoundEffect.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void StartTestSound() {
        try {
            File file = new File("C:\\Users\\natto\\Desktop\\test.mid");
            Sequence sequence = MidiSystem.getSequence(file);
            
            sequencer.setSequence(sequence);
        }
        catch (InvalidMidiDataException ex) {
            Logger.getLogger(SoundEffect.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (IOException ex) {
            Logger.getLogger(SoundEffect.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        sequencer.start();
    }
}
