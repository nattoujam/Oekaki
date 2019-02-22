/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Oekaki;

import Oekaki.Packets.SEPacket;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.media.AudioClip;
import javax.sound.midi.ControllerEventListener;
import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MetaEventListener;
import javax.sound.midi.MidiEvent;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Receiver;
import javax.sound.midi.Sequence;
import javax.sound.midi.Sequencer;
import javax.sound.midi.ShortMessage;
import javax.sound.midi.Synthesizer;
import javax.sound.midi.Track;
import javax.sound.midi.Transmitter;

/**
 *
 * @author local-nattou
 */
public class SoundEffect {
    
    public static final int GAME_START = 0;
    public static final int CORRECT = 1;
    public static final int UNCORRECT = 2;
    
    //private final Sequencer himawariBGM;
    //private final Sequencer airsulgBGM;
    private final Sequencer acceptSE;
    private final Sequencer receiveSE;
    //private final Sequencer fanfareSE;
    private final AudioClip gameStartSE;
    private final AudioClip correctSE;
    private final AudioClip uncorrectSE;
    
    public SoundEffect() {
        //himawariBGM = makeFromPath(getClass().getResource("/Mediafile/himawari.mid"));
        //himawariBGM.setLoopCount(Sequencer.LOOP_CONTINUOUSLY);
        //airsulgBGM = makeFromPath(getClass().getResource("/Mediafile/airsulg.mid"));
        //airsulgBGM.setLoopCount(Sequencer.LOOP_CONTINUOUSLY);
        //fanfareSE = makeFromPath(getClass().getResource("/Mediafile/futta-fanfare.mid"));
        gameStartSE = new AudioClip(getClass().getResource("/Mediafile/by_chance.mp3").toString());
        correctSE = new AudioClip(getClass().getResource("/Mediafile/crrect_answer2.mp3").toString());
        uncorrectSE = new AudioClip(getClass().getResource("/Mediafile/blip01.mp3").toString());
        
        List<Message> temp = new ArrayList<>();
        
        temp.add(new Message(0, 0, 100, 127, 0));
        acceptSE = makeFromMessage(temp);
        
        temp.clear();
        temp.add(new Message(4, 0, 80, 127, 0));
        temp.add(new Message(4, 0, 85, 127, 60));
        receiveSE = makeFromMessage(temp);
    }
    
    public void playSE(SEPacket p) {
        switch(p.getSEName()) {
            case GAME_START:
                gameStartSE();
                break;
            case CORRECT:
                correctSE();
                break;
            case UNCORRECT:
                uncorrctSE();
                break;
        }
    }
    
    public void acceptSE() {
        acceptSE.setTickPosition(0);
        acceptSE.start();
    }
    
    public void receivetSE() {
        receiveSE.setTickPosition(0);
        receiveSE.start();
    }
    
    public void fanfareSE() {
        //fanfareSE.setTickPosition(0);
        //fanfareSE.start();
        System.err.println("fanfare");
    }
    
    public void gameStartSE() {
        gameStartSE.play();
    }
    
    public void correctSE() {
        correctSE.play();
    }
    
    public void uncorrctSE() {
        uncorrectSE.play();
    }
    
    public void frontPageBGM() {
        //himawariBGM.setTickPosition(0);
        //himawariBGM.start();
        System.err.println("himawariBGM");
    }
    
    public void mainFrameBGM() {
        //airsulgBGM.setTickPosition(0);
        //airsulgBGM.start();
        System.err.println("sirsulgBGM");
    }
    
    public void stopBGM() {
        //if(himawariBGM.isRunning()) himawariBGM.stop();
        //if(airsulgBGM.isRunning()) airsulgBGM.stop();
        System.err.println("stopBGM");
    }
     
    private Sequencer makeFromPath(URL path) {
        Sequencer seq = null;
        try {
            seq = MidiSystem.getSequencer();
            seq.open();
            Sequence sequence = MidiSystem.getSequence(path);
            seq.setSequence(sequence);
        }
        catch (MidiUnavailableException ex) {
            Logger.getLogger(SoundEffect.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (InvalidMidiDataException ex) {
            Logger.getLogger(SoundEffect.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (IOException ex) {
            Logger.getLogger(SoundEffect.class.getName()).log(Level.SEVERE, null, ex);
        }
        return seq;
    }
    
    private Sequencer makeFromMessage(List<Message> input) {
        Sequencer seq = null;
        try {
            seq = MidiSystem.getSequencer(false);
            seq.open();
            Sequence sequence = new Sequence(Sequence.PPQ, 480);
            Track track = sequence.createTrack();
            for(Message l : input) {
                track.add(new MidiEvent(new ShortMessage(ShortMessage.PROGRAM_CHANGE, l.channel, l.instrument, 0), 0));
                track.add(new MidiEvent(new ShortMessage(ShortMessage.NOTE_ON, l.channel, l.noteNumber, l.velocity), l.tick));
                track.add(new MidiEvent(new ShortMessage(ShortMessage.NOTE_OFF, l.channel, l.noteNumber, l.velocity), l.tick + 60));
            }
            
            //Synthesizer synthesizer = MidiSystem.getSynthesizer();
            //synthesizer.open();
    
            seq.setSequence(sequence);
            //seq.getTransmitter().setReceiver(synthesizer.getReceiver());
        }
        catch (InvalidMidiDataException ex) {
            Logger.getLogger(SoundEffect.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (MidiUnavailableException ex) {
            Logger.getLogger(SoundEffect.class.getName()).log(Level.SEVERE, null, ex);
        }
        return seq;
    }
    
    private static class Message {
        private final int instrument;
        private final int channel;
        private final int noteNumber;
        private final int velocity;
        private final int tick;
        public Message(int instrument, int channel, int number, int velocity, int tick) {
            this.instrument = instrument;
            this.channel = channel;
            this.noteNumber = number;
            this.velocity = velocity;
            this.tick = tick;
        } 
    }
}
