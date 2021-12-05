import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.sound.sampled.*;

public class WaltzPlayer {
    public static Clip clip;

    public static void play(String fileName) {

        try {

            File file = new File(fileName);
            AudioInputStream ais = AudioSystem.getAudioInputStream(file);
            clip = AudioSystem.getClip();
            clip.open(ais);
            if (clip != null) {
                clip.loop(0);
            }
            assert clip != null;
            long seconds = clip.getMicrosecondLength() / 1000;
            Thread.sleep(seconds);
        } catch (IOException e) {
            System.err.println("error opening file: " + fileName);
        } catch (InterruptedException e) {
            System.err.println(" Playing Interrupted ");
        } catch (UnsupportedAudioFileException e) {
            System.err.println(".wav format not supported by JVM/SPI");
        } catch (LineUnavailableException e) {
            System.err.println(".wav format not supported");
        }
    }

    public static void save(String[] sourceFilesList, String destinationFileName) throws Exception {

        AudioInputStream audioInputStream = null;
        List<AudioInputStream> audioInputStreamList = null;
        AudioFormat audioFormat = null;
        Long frameLength = null;

        try {
            // loop through our files first and load them up
            for (String sourceFile : sourceFilesList) {
                audioInputStream = AudioSystem.getAudioInputStream(new File(sourceFile));

                // get the format of first file
                if (audioFormat == null) {
                    audioFormat = audioInputStream.getFormat();
                }

                // add it to our stream list
                if (audioInputStreamList == null) {
                    audioInputStreamList = new ArrayList<>();
                }
                audioInputStreamList.add(audioInputStream);

                // keep calculating frame length
                if (frameLength == null) {
                    frameLength = audioInputStream.getFrameLength();
                } else {
                    frameLength += audioInputStream.getFrameLength();
                }
            }

            // now write our concatenated file
            assert audioInputStreamList != null;
            AudioSystem.write(new AudioInputStream(new SequenceInputStream
                    (Collections.enumeration(audioInputStreamList)),
                    audioFormat, frameLength), AudioFileFormat.Type.WAVE, new File(destinationFileName));

        } finally {
            if (audioInputStream != null) {
                audioInputStream.close();
            }
        }
    }

    public static void close() {
        clip.close();
    }

    /**
     * Demonstrate how to use the WaltzPlayer class.
     */
    public static void main(String[] args) {
        WaltzPlayer.play("MozartWaltzGenerator/Waves/M73.wav");
        WaltzPlayer.play("MozartWaltzGenerator/Waves/M74.wav");
        WaltzPlayer.play("MozartWaltzGenerator/Waves/M75.wav");
        WaltzPlayer.close();
    }
}